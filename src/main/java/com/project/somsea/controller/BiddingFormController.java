package com.project.somsea.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.project.somsea.domain.Auction;
import com.project.somsea.domain.TradeHistory;
import com.project.somsea.dto.AuctionDto;
import com.project.somsea.dto.BiddingDto;
import com.project.somsea.dto.TradeHistoryDto;
import com.project.somsea.dto.UserDto;
import com.project.somsea.service.AuctionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("biddingDto")
public class BiddingFormController {
	private final AuctionService auctionService;
	
	@ModelAttribute("biddingDto")
	public BiddingDto.Request formBacking(@SessionAttribute("userId") Long userId, 
			HttpServletRequest request) {
//		Auction auction = auctionService.findByNft(nftId);
		BiddingDto.Request biddingDto = BiddingDto.Request.newInstance();
		biddingDto.setUserId(userId);
//		biddingDto.setAuctionId(auction.getId());
//		biddingDto.setPrice(auctionService.findTopBid(auction.getId()) + 1); // 최소한의 입찰가
		return biddingDto;
	}
	
	/*@GetMapping("/nfts/bidding")
	public String addBiddingForm() {
//		BiddingDto.Request biddingDto = BiddingDto.Request.newInstance();
//		biddingDto.setPrice(auctionService.findTopBid(auctionId) + 1); // 최소한의 입찰가
//		User user = (User) auctionService.findUser(userId);
		
//		model.addAttribute("topBid", topBid);
//		model.addAttribute("user", user);
//		auctionService.addBidding(null)
		return "/auction/registerBiddingForm";
	}*/
	
	@PostMapping("/nfts/bidding/add")
	public String addBidding(@ModelAttribute("biddingDto")BiddingDto.Request requestDto, 
			@SessionAttribute("userId") Long userId, @RequestParam("nftId") Long nftId,
			SessionStatus status) {
		requestDto.setTime(LocalDateTime.now());
		Long biddingId = auctionService.addBidding(requestDto);
		requestDto.setBiddingId(biddingId);
		
		TradeHistoryDto.Request trade = TradeHistoryDto.Request.newInstance();
		
		Auction auction = auctionService.findByNft(nftId);
		requestDto.setAuctionId(auction.getId());
//		HttpSession session = request.getSession();
//		session.removeAttribute("nftId");
		
		trade.setAuctionId(auction.getId());
		trade.setUserId(userId);
//		trade.setStatus(TradeHistory.Status.BID_FAIL);
		
//		Long topBid = auctionService.findTopBid(auction.getId());
		trade.setAmount(requestDto.getPrice());
		
		auctionService.addTradeHistory(trade);
		status.setComplete();
		return "/nfts/dtatil";
	}
}
