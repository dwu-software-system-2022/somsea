package com.project.somsea.controller;

import java.util.List;

import com.project.somsea.dto.NftDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.somsea.domain.User;
import com.project.somsea.dto.UserDto;

import com.project.somsea.service.NftService;
import com.project.somsea.service.UserService;
import com.project.somsea.users.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final NftService nftService;
	
	@ModelAttribute("userDto")
	public UserDto.Request formBackingObject() {
		UserDto.Request userDto = UserDto.Request.newInstance();
		return userDto;
	}
	
	@GetMapping("/user/add")
	public String addUserForm(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
		if (userDetails != null)
			return "redirect:/";
		model.addAttribute("user", UserDto.Request.newInstance());
		return "users/form";
	}
	
	@PostMapping("/user/add")
	public String addUser(@ModelAttribute("userDto") UserDto.Request userDto) {
		userService.add(userDto);
		return "redirect:/";
	}
	
	@GetMapping("/user/login")
	public String login() {
		return "users/loginForm";
	}
	
	@GetMapping("/user/{userId}")
	public String profile(Model model, @PathVariable Long userId) {
		List<NftDto.Response> nfts = nftService.readNftsByUserId(userId);
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		model.addAttribute("nfts", nfts);
		return "users/profile";
	}

}
