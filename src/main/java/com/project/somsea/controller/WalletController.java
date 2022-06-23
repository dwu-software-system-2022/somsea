package com.project.somsea.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.somsea.domain.Wallet;
import com.project.somsea.dto.WalletDto;
import com.project.somsea.service.WalletService;
import com.project.somsea.users.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WalletController {
	private final WalletService walletService;
	
	@GetMapping("/wallet/me")
	public String viewWallet(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
		Wallet wallet = walletService.findWalletByUser(userDetails.getUserId());
		model.addAttribute("wallet", wallet);
		return "wallets/wallet";
	}
	
	@GetMapping("/wallet/charge")
	public String chargeForm(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
		Wallet wallet = walletService.findWalletByUser(userDetails.getUserId());
		model.addAttribute("wallet", wallet);

		return "wallets/charge";
	}
	
	@PostMapping("/wallet/charge")
	public String charge(@RequestBody String amount, @AuthenticationPrincipal CustomUserDetails userDetails) {
		walletService.addBalance(Long.parseLong(amount.split("=")[1]), userDetails.getUserId());
		return "redirect:/wallet/me";
	}
}
