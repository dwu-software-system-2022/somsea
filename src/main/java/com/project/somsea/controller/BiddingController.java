package com.project.somsea.controller;



import com.project.somsea.domain.Auction;
import com.project.somsea.domain.Bidding;
import com.project.somsea.domain.User;
import com.project.somsea.users.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.project.somsea.dto.BiddingDto;
import com.project.somsea.service.AuctionService;
import com.project.somsea.service.NftService;
import com.project.somsea.service.WalletService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@SessionAttributes("biddingDto")
public class BiddingController {
	private final AuctionService auctionService;
	private final NftService nftService;
	private final WalletService walletService;

	@GetMapping("/auctions/{nftId}/bidding/form")
	public String addBiddingForm(Model model, @PathVariable Long nftId) {
		Auction auction = auctionService.findByNft(nftId);
		BiddingDto.Request biddingDto = BiddingDto.Request.newInstance();
		Long topBidPrice = auctionService.findTopBid(auction.getId());
		if (topBidPrice != null) {
			biddingDto.setPrice(topBidPrice + 1); // 최소한의 입찰가
		} else {
			biddingDto.setPrice(auction.getStartPrice() + 1); // 최소한의 입찰가
		}
		model.addAttribute("biddingDto", biddingDto);
		return "/nfts/biddingForm";
	}

	@PostMapping("/auctions/{nftId}/bidding/form")
	public String addBidding(@Valid @ModelAttribute("requestDto") BiddingDto.Request requestDto,
							BindingResult result
							 ,@PathVariable Long nftId
							 ,@AuthenticationPrincipal CustomUserDetails userDetails) {
		Auction auction = auctionService.findByNft(nftId);
		
		if (result.hasErrors()) return "nfts/biddingForm";
		
		if (requestDto.getPrice() <= auction.getTopBid()) {
			result.rejectValue("price", "smallPrice", "최소 입찰가를 입력하세요.");
			return "nfts/biddingForm";
		}
		
		requestDto.setUserId(userDetails.getUserId());
		requestDto.setTime(LocalDateTime.now());

		requestDto.setAuctionId(auction.getId());

		auctionService.addBidding(requestDto);
//		Auction auction = auctionService.findAuction(auctionId);
		return "redirect:/nfts/" + nftId;
	}

	@GetMapping("/nfts/{auctionId}/bidding/win")
	public String winBiddingForm(@PathVariable Long auctionId, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
		User user = auctionService.findUser(userDetails.getUserId()); // userDetails 작동하면 필요없고 userDetails 쓰면됨.
		Long biddingid = auctionService.findBiddingIdByTopBid(auctionId);
		Bidding bidding = auctionService.findBidding(biddingid);
		model.addAttribute("bidding", bidding);
		model.addAttribute("user", user); // userDetails 사용할 예정.
		return "auction/result"; 
	}
	
	@PostMapping("/nfts/{auctionId}/bidding/win")
	public String winBidding(@PathVariable Long auctionId, @AuthenticationPrincipal CustomUserDetails userDetails) {
		Auction auction = auctionService.findAuction(auctionId);
		Long biddingid = auctionService.findBiddingIdByTopBid(auction.getId());
		Bidding bidding = auctionService.findBidding(biddingid);
		//update문 써야 됨. user_id 바꿔야 됨. nft의
		nftService.updateUserIdOfNft(userDetails.getUserId(), auction.getNft().getId());
		// wallet balance도 바꿔야 됨. balance가 입찰가보다 낮으면 충전하세요!.
		walletService.updateBalance(bidding.getPrice(), userDetails.getUserId());
		return "redirect:/user/" + userDetails.getUserId(); // or /users/mypage
	}

	@RequestMapping("/nfts/{nftId}/bidding/{biddingId}/delete") // biddingDto 받을 수 있는지 해보기.
	public String deleteBidding(@PathVariable("nftId") Long nftId, 
			@PathVariable Long biddingId, @AuthenticationPrincipal CustomUserDetails userDetails) {
		Auction auction = auctionService.findByNft(nftId);
		auctionService.deleteBidding(biddingId, auction.getId());
		return "redirect:/nfts/" + nftId;
	}
}
