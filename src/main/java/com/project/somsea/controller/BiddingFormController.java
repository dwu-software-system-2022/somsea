package com.project.somsea.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.project.somsea.domain.Bidding;
import com.project.somsea.dto.BiddingDto;
import com.project.somsea.service.AuctionService;
import com.project.somsea.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("biddingDto")
public class BiddingFormController {
	private final AuctionService auctionService;
	private final UserService userService;
	
	@ModelAttribute("biddingDto")
	public BiddingDto.Request formBackingObject(HttpServletRequest request) {
		BiddingDto.Request biddingDto = BiddingDto.Request.newInstance();
		biddingDto.setPrice(auctionService.findTopBid(Long.parseLong(request.getParameter("auctionId"))) + 1); // 최소한의 입찰가
		
		return biddingDto;
	}
	
	@GetMapping("/bidding/{userId}/{auctionId}/form")
	public String addBiddingForm(Model model, @PathVariable Long userId, @PathVariable Long auctionId) {
//		BiddingDto.Request biddingDto = BiddingDto.Request.newInstance();
//		biddingDto.setPrice(auctionService.findTopBid(auctionId) + 1); // 최소한의 입찰가
//		User user = (User) auctionService.findUser(userId);
		
//		model.addAttribute("topBid", topBid);
//		model.addAttribute("user", user);
		
		return "bidding/form";
	}
	
	@PostMapping("/bidding/{userId}/{auctionId}/form")
	public String addBidding(@ModelAttribute("biddingDto")BiddingDto.Request requestDto) {
		auctionService.addBidding(requestDto);
		return "/auction/view";
	}
}
