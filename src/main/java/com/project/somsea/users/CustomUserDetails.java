package com.project.somsea.users;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.somsea.domain.User;

import lombok.Getter;

@Getter
@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {
	private Long userId;
	private String name;
	private String password;
	private String email;
	private String authority;
	
	public CustomUserDetails(User user) {
		this.userId = user.getId();
		this.name = user.getName();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.authority = "ROLE_USER";
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(authority));
		return auth;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	/* 
	 * 고유한 값 return
	 */
	@Override
	public String getUsername() {
		return email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
}
