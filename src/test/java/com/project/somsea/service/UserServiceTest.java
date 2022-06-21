//package com.project.somsea.service;
//
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.project.somsea.domain.User;
//import com.project.somsea.domain.Wallet;
//import com.project.somsea.dto.UserDto;
//import com.project.somsea.repository.UserRepository;
//import com.project.somsea.repository.WalletRepository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//@Transactional
//@SpringBootTest
//public class UserServiceTest {
//	
//	@Autowired
//	private UserService userService;
//		
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	PasswordEncoder passwordEncoder;
//	
//	
//	@Test
//	@DisplayName("유저 추가")
//	void createUser() {
//		UserDto.Request userDto = UserDto.Request.builder()
//									.email("test@gamil.com")
//									.name("test")
//									.password(passwordEncoder.encode("password"))
//									.build();
//		
//		Long userId = userService.add(userDto.toEntity(null));
//		Optional<User> optionalUser = userRepository.findById(userId);
//		assertThat(optionalUser).isNotEmpty();
//	}
//	
//	
//}
