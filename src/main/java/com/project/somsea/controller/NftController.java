package com.project.somsea.controller;

import com.project.somsea.domain.Auction;
import com.project.somsea.domain.Bidding;
import com.project.somsea.domain.TradeHistory;
import com.project.somsea.domain.User;
import com.project.somsea.dto.BiddingDto;
import com.project.somsea.dto.NftDto;
import com.project.somsea.dto.PartDto;
import com.project.somsea.dto.TradeHistoryDto;
import com.project.somsea.service.AuctionService;
import com.project.somsea.helper.ImageUploader;
import com.project.somsea.service.NftService;
import com.project.somsea.users.CustomUserDetails;
import com.project.somsea.service.WalletService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NftController {

    private final NftService nftService;
    private final ImageUploader imageUploader;

    private final AuctionService auctionService;
    private final WalletService walletService;
    
    Long nftId = 1L;
    
    @GetMapping("/nfts/{nftId}")
    public String showNftDetail(Model model, @PathVariable Long nftId,
    		@AuthenticationPrincipal CustomUserDetails userDetails) {
        NftDto.ResponseDetail nftDto = nftService.readDetailNft(nftId);
        Auction auction = auctionService.findByNft(nftId);
        List<Bidding> bidding = auctionService.findBiddingList(auction);
        Long topBidddingId = auctionService.findBiddingIdByTopBid(auction.getId());
        Bidding topBidding = auctionService.findBidding(topBidddingId);
        List<TradeHistory> tradeHistory = auctionService.findByAuction(auction.getId());
        model.addAttribute("nft", nftDto);
        model.addAttribute("userId", 1L);
        model.addAttribute("auction", auction);
        model.addAttribute("bidding", bidding);
        model.addAttribute("topBid", topBidding);
        model.addAttribute("tradeHistory", tradeHistory);

        this.nftId = nftId;
        return "nfts/item";
    }

    @GetMapping("/collections/{collectionId}/nfts/form")
    public String addNftForm(Model model, @PathVariable Long collectionId) {
        NftDto.Request nftDto = NftDto.Request.newInstance();
        List<PartDto.Response> parts = nftService.getPartsByCollectionId(collectionId);

        model.addAttribute("nft", nftDto);
        model.addAttribute("parts", parts);

        return "nfts/form";
    }

//    @PostMapping("/collections/{collectionId}/nfts/form")
//    public String addNft(@ModelAttribute("requestDto") NftDto.Request requestDto) {
//        Long userId = 1L;
//        Long nftId = nftService.add(userId, requestDto);
//       
//        // TODO: NFT 추가 완료 후에 이동할 페이지 변경 필요
//        return "redirect:/nfts/" + nftId;
//    }

    @PostMapping("/collections/{collectionId}/nfts/form")
    public String addNft(@ModelAttribute("requestDto") NftDto.Request requestDto
    		, @AuthenticationPrincipal CustomUserDetails userDetails) throws IOException {
        Long userId = userDetails.getUserId();
        Long nftId = nftService.add(userId, requestDto);

		String imageUrl = imageUploader.upload(requestDto.getImageFile());
		requestDto.setImageUrl(imageUrl);

        // TODO: NFT 추가 완료 후에 이동할 페이지 변경 필요
        return "redirect:/nfts/" + nftId;
    }
    
    @PostMapping("/nfts/{nftId}")
    public String deleteNft(Model model, @PathVariable Long nftId) {
        Long userId = 1L;
        nftService.delete(userId, nftId);
        // TODO: NFT 추가 완료 후에 이동할 페이지 변경 필요
        return "redirect:/nfts/" + nftId;
    }
    
    @RequestMapping("/nfts/bidding/add")
	public String addBidding(@ModelAttribute("biddingDto")BiddingDto.Request requestDto, 
			@SessionAttribute("userId") Long userId) {
    	String str_curTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		requestDto.setTime(LocalDateTime.parse(str_curTime, df));
    	requestDto.setUserId(userId);
		requestDto.setTime(LocalDateTime.now());
		Long biddingId = auctionService.addBidding(requestDto);
		requestDto.setBiddingId(biddingId);
		
		TradeHistoryDto.Request trade = TradeHistoryDto.Request.newInstance();
		
		Auction auction = auctionService.findByNft(nftId);
		requestDto.setAuctionId(auction.getId());
		
		trade.setAuctionId(auction.getId());
		trade.setUserId(userId);
//		trade.setStatus(TradeHistory.Status.BID_FAIL);
		
//		Long topBid = auctionService.findTopBid(auction.getId());
		trade.setAmount(requestDto.getPrice());
		
		auctionService.addTradeHistory(trade);
		return "nfts/dtatil";
	}
    
    @RequestMapping("/nfts/bidding/win")
	public String winBidding(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
		User user = auctionService.findUser(userDetails.getUserId());
		Auction auction = auctionService.findByNft(nftId);
		Long biddingid = auctionService.findBiddingIdByTopBid(auction.getId());
		Bidding bidding = auctionService.findBidding(biddingid);
		//update문 써야 됨. user_id 바꿔야 됨. nft의 
		nftService.updateUserIdOfNft(userDetails.getUserId(), nftId);
		// wallet balance도 바꿔야 됨. balance가 입찰가보다 낮으면 충전하세요!.
		walletService.updateBalance(bidding.getPrice(), userDetails.getUserId());
		model.addAttribute("bidding", bidding);
		model.addAttribute("user", user);
		return "auction/result"; // or /users/mypage
	}
	
	@RequestMapping("/nfts/bidding/delete") // biddingDto 받을 수 있는지 해보기.
	public String deleteBidding(@AuthenticationPrincipal CustomUserDetails userDetails) {
		Auction auction = auctionService.findByNft(nftId);
		List<Bidding> bidding = auctionService.findBiddingList(auction);
		Long biddingId = null;
		for (int i = 0; i < bidding.size(); i++) {
			if (userDetails.getUserId() == bidding.get(i).getUser().getId()) {
				biddingId = bidding.get(i).getId();
				break;
			}
		}
		auctionService.deleteBidding(biddingId, auction.getId());
		return "redirect:/nfts/detail";
	}
}
