package com.project.somsea.controller;

import javax.servlet.http.HttpServletRequest;

import com.project.somsea.domain.Auction;
import com.project.somsea.domain.Bidding;
import com.project.somsea.domain.User;
import com.project.somsea.dto.NftDto;
import com.project.somsea.dto.TradeHistoryDto;
import com.project.somsea.users.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.somsea.dto.BiddingDto;
import com.project.somsea.service.AuctionService;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes("biddingDto")
public class BiddingController {
	private final AuctionService auctionService;

	@GetMapping("/auctions/{auctionId}/bidding/form")
	public String addBiddingForm(Model model, @PathVariable Long auctionId) {

		BiddingDto.Request biddingDto = BiddingDto.Request.newInstance();
		biddingDto.setPrice(auctionService.findTopBid(auctionId) + 1); // 최소한의 입찰가

		model.addAttribute("biddingDto", biddingDto);
		return "/nfts/biddingForm";
	}

	@PostMapping("/auctions/{auctionId}/bidding/form")
	public String addBidding(@ModelAttribute("requestDto") BiddingDto.Request requestDto
							 ,@PathVariable Long auctionId
							 ,@AuthenticationPrincipal CustomUserDetails userDetails) {

		requestDto.setUserId(userDetails.getUserId());
		requestDto.setTime(LocalDateTime.now());

		requestDto.setAuctionId(auctionId);

		auctionService.addBidding(requestDto);
		return "";
	}
//
//	@RequestMapping("/nfts/bidding/win")
//	public String winBidding(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
//		User user = auctionService.findUser(userDetails.getUserId());
//		Auction auction = auctionService.findByNft(nftId);
//		Long biddingid = auctionService.findBiddingIdByTopBid(auction.getId());
//		Bidding bidding = auctionService.findBidding(biddingid);
//		//update문 써야 됨. user_id 바꿔야 됨. nft의
//		nftService.updateUserIdOfNft(userDetails.getUserId(), nftId);
//		// wallet balance도 바꿔야 됨. balance가 입찰가보다 낮으면 충전하세요!.
//		walletService.updateBalance(bidding.getPrice(), userDetails.getUserId());
//		model.addAttribute("bidding", bidding);
//		model.addAttribute("user", user);
//		return "auction/result"; // or /users/mypage
//	}
//
//	@RequestMapping("/nfts/bidding/delete") // biddingDto 받을 수 있는지 해보기.
//	public String deleteBidding(@AuthenticationPrincipal CustomUserDetails userDetails) {
//		Auction auction = auctionService.findByNft(nftId);
//		List<Bidding> bidding = auctionService.findBiddingList(auction);
//		Long biddingId = null;
//		for (int i = 0; i < bidding.size(); i++) {
//			if (userDetails.getUserId() == bidding.get(i).getUser().getId()) {
//				biddingId = bidding.get(i).getId();
//				break;
//			}
//		}
//		auctionService.deleteBidding(biddingId, auction.getId());
//		return "redirect:/nfts/detail";
//	}
}
