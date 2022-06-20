package com.project.somsea.service;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.project.somsea.domain.CustomUserDetails;
import com.project.somsea.domain.User;
import com.project.somsea.dto.UserSessionDto;
import com.project.somsea.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component

public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;

	private final HttpSession session;
	
	@Override
	public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email).orElseThrow(() ->
			new UsernameNotFoundException("해당하는 사용자가 존재하지 않습니다. : " + email));
		session.setAttribute("user", new UserSessionDto(user));
		return new CustomUserDetails(user);
	}

}
