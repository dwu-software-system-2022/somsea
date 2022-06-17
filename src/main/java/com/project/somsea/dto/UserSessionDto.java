package com.project.somsea.dto;

import java.io.Serializable;

//import javax.management.relation.Role;

import com.project.somsea.domain.User;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class UserSessionDto implements Serializable {
	private String username;
	private String password;
	private String email;
//	private Role role;
	
	public UserSessionDto(User user) {
		this.username = user.getName();
		this.password = user.getPassword();
		this.email = user.getEmail();
//		this.role = user.getRole();
	}

}
