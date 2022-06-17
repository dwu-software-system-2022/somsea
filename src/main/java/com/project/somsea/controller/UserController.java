package com.project.somsea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.somsea.dto.UserDto;
import com.project.somsea.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
//	private final UserService userService;
	
	@GetMapping("/user/add")
	public String addUserForm(Model model) {
		model.addAttribute("user", UserDto.Request.newInstance());
		return "users/form";
	}
	
	@PostMapping("/user/add")
	@ResponseBody
	public String addUser(@RequestBody String body) {
//		Long userId = userService.add(userId, requestDto);
		System.out.println(body);
		return body;
	}
}
