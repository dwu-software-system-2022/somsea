package com.project.somsea.controller;

import java.util.List;

import javax.validation.Valid;

import com.project.somsea.dto.NftDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.somsea.domain.User;
import com.project.somsea.dto.UserDto;

import com.project.somsea.service.NftService;
import com.project.somsea.service.UserService;
import com.project.somsea.users.CustomUserDetails;
import com.project.somsea.util.CommonResponse;

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
		return "users/signUp";
	}
	
	@PostMapping("/user/add")
	public String addUser(@Valid @ModelAttribute("userDto") UserDto.Request userDto) {
		userService.add(userDto);
		return "redirect:/";
	}
	
	@GetMapping("/user/login")
	public String login() {
		return "users/signIn";
	}
	
	@GetMapping("/user/me")
	public String myProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
		return "redirect:/user/" + userDetails.getUserId();
	}
	
	@GetMapping("/user/{userId}")
	public String profile(Model model, @PathVariable Long userId,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		List<NftDto.Response> nfts = nftService.readNftsByUserId(userId);
		User user = userService.findUserById(userId);
		boolean auth = false;
		
		if (userId.equals(userDetails.getUserId())) {
			auth = true;
		}
		model.addAttribute("user", user);
		model.addAttribute("nfts", nfts);
		model.addAttribute("auth", auth);		
		return "users/profile";
	}
	
	@GetMapping("/user/update")
	public String updateUserForm(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
		UserDto.Request userDto = userService.readUserForUpdate(userDetails.getUserId());
		model.addAttribute("user", userDto);
		return "users/settings-profile";
	}
	
	@PostMapping("/user/update/{infoId}")
	public String updateUser(Model model, @AuthenticationPrincipal CustomUserDetails userDetails, 
			@ModelAttribute("requestDto") UserDto.Request requestDto, @PathVariable Long infoId) {
//		userService.
		if (infoId == 1)
			userService.updateName(userDetails.getUserId(), requestDto);
		else if (infoId == 2)
			userService.updateEmailAndPassword(userDetails.getUserId(), requestDto);
		return "redirect:/user/update";
	}
	
	@GetMapping("/user/delete")
	public String deleteUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
		userService.delete(userDetails.getUserId());
		SecurityContextHolder.clearContext();
		return "redirect:/";
	}
}
