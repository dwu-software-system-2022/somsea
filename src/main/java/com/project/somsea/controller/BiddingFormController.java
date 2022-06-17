package com.project.somsea.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.project.somsea.dto.AuctionDto;
import com.project.somsea.dto.BiddingDto;
import com.project.somsea.dto.UserDto;
import com.project.somsea.service.AuctionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("biddingDto")
public class BiddingFormController {
	private final AuctionService auctionService;
	
	@ModelAttribute("biddingDto")
	public BiddingDto.Request formBackingObject(@SessionAttribute("userDto") UserDto.Request user, @SessionAttribute("auctionDto") AuctionDto.Request auction, HttpServletRequest request) {
		BiddingDto.Request biddingDto = BiddingDto.Request.newInstance();
		biddingDto.setUserId(user.getUserId());
		biddingDto.setAuctionId(auction.getAuctionId());
		biddingDto.setPrice(auctionService.findTopBid(Long.parseLong(request.getParameter("auctionId"))) + 1); // 최소한의 입찰가
		
		return biddingDto;
	}
	
	@GetMapping("/bidding/add/form")
	public String addBiddingForm(Model model) {
//		BiddingDto.Request biddingDto = BiddingDto.Request.newInstance();
//		biddingDto.setPrice(auctionService.findTopBid(auctionId) + 1); // 최소한의 입찰가
//		User user = (User) auctionService.findUser(userId);
		
//		model.addAttribute("topBid", topBid);
//		model.addAttribute("user", user);
		return "/bidding/form";
	}
	
	@PostMapping("/bidding/add")
	public String addBidding(@ModelAttribute("biddingDto")BiddingDto.Request requestDto, SessionStatus status) {
		requestDto.setTime(LocalDateTime.now());
		auctionService.addBidding(requestDto);
		status.setComplete();
		return "redirect:/auction/view";
	}
}
