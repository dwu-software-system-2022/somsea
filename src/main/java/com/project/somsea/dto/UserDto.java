package com.project.somsea.dto;

import com.project.somsea.domain.User;
import com.project.somsea.domain.Wallet;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserDto {
	@Setter
	@Getter
	@Builder
	public static class Request {
		private Long userId;
		private String email;
		private String password;
		private String name;
		
		public User toEntity(Wallet wallet) {
			return User.builder()
					.email(email)
					.password(password)
					.name(name)
					.wallet(wallet)
					.build();
		}
	}
	
	
	
}
