package com.project.somsea.dto;

import com.project.somsea.domain.User;

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
		
		public User toEntity() {
			return User.builder()
					.email(email)
					.password(password)
					.name(name)
					.build();
		}
		
		public static Request newInstance() {
            return Request.builder().build();
        }
	}
}
