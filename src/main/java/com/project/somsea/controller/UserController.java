package com.project.somsea.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public String addUser(@RequestBody String body, HttpServletRequest request) {
//		Long userId = userService.add(userId, requestDto);
//		userDto.setUserId(userId); 
		HttpSession session = request.getSession();
//		session.setAttribute("userId", userId); // delete하면 session 종료해야 됨.
		System.out.println(body);
		return body;
	}
}
