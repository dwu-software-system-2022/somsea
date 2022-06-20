package com.project.somsea.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.somsea.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	private final CustomUserDetailsService customUserDetailsService;

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/webjars/**", "/resources/**")
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "/login", "/main", "/user/add").permitAll()
			.antMatchers("/nfts/*").permitAll()
//			.antMatchers("/user/**").hasRole("USER")
			.antMatchers("/static/**").permitAll()
			.anyRequest()
			.authenticated();
//			.and()
//			.formLogin()
//			.loginProcessingUrl("/loginProc")
//			.defaultSuccessUrl("/");
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TODO Auto-generated method stub
//		auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder());
//	}

}
