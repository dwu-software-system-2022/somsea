package com.project.somsea.controller;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.project.somsea.domain.Auction.Status;
import com.project.somsea.dto.AuctionDto;
import com.project.somsea.service.AuctionService;
import com.project.somsea.users.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("auctionDto")
public class AuctionController {
	private final AuctionService auctionService;
	
	@GetMapping("/auction/{nftId}/add/form") 
 	public String form(Model model, @PathVariable("nftId") Long nftId) {
 		AuctionDto.Request auctionDto = AuctionDto.Request.newInstance();
 		auctionDto.setNftId(nftId);
 		model.addAttribute("auctionDto", auctionDto);
 		return "auction/form";
 	}
	
	@PostMapping("/auction/add")
	public String addAuctionForm(@Valid @ModelAttribute("auctionDto") AuctionDto.Request auctionDto, Model model,
			@AuthenticationPrincipal CustomUserDetails userDetails, SessionStatus status,
			BindingResult result) {
		if (result.hasErrors()) {
			return "auction/form";
		}
		try {
			auctionDto.setStatus(Status.IN_PROGRESS);
			Long auctionId = auctionService.addAuction(auctionDto); // scheduler 같이 작동
			auctionDto.setAuctionId(auctionId);
			status.setComplete();
			return "redirect:/user/" + userDetails.getUserId();
		} catch (Exception e) {
			return "auction/form";
		}
	}
}
