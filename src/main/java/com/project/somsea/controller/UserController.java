package com.project.somsea.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.project.somsea.dto.UserDto;
import com.project.somsea.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("userDto")
public class UserController {
//	private final UserService userService;
	
	@ModelAttribute("userDto")
	public UserDto.Request formBackingObject() {
		UserDto.Request userDto = UserDto.Request.newInstance();
		return userDto;
	}
	
	@GetMapping("/user/add")
	public String addUserForm(Model model) {
		model.addAttribute("user", UserDto.Request.newInstance());
		return "users/form";
	}
	
	@PostMapping("/user/add")
	@ResponseBody
	public String addUser(@RequestBody String body, @ModelAttribute("userDto") UserDto.Request userDto) {
//		Long userId = userService.add(userId, requestDto);
//		userDto.setUserId(userId); 
		System.out.println(body);
		return body;
	}
}
