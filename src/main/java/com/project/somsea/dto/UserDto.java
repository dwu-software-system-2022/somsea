package com.project.somsea.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
		
		@NotBlank(message = "이메일을 입력해주세요.")
		@Email
		private String email;
		
		@NotBlank(message = "비밀번호를 입력해주세요.")
		@Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}",
		             message = "비밀번호는 영문과 숫자 조합으로 8 ~ 16자리까지 가능합니다.")
		private String password;
		
		@NotBlank(message = "이름을 입력해주세요.")
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

		public static Request of(User user) {
			return Request.builder()
					.email(user.getEmail())
					.password(user.getPassword())
					.name(user.getName())
					.build();
		}
	}
}
