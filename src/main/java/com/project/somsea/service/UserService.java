package com.project.somsea.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.somsea.domain.User;
import com.project.somsea.dto.UserDto;
import com.project.somsea.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	
	
	private final UserRepository userRepository;
	private final WalletService walletService;
	@Autowired
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Long add(UserDto.Request userDto) {
		// wallet 먼저 생성
		String encodePwd = bCryptPasswordEncoder.encode(userDto.getPassword());
		userDto.setPassword(encodePwd);
		User user = userDto.toEntity();
		// email 중복 발생시 예외처리 하는 부분 필요
		
		validateDuplicateUser(user.getEmail());
		userRepository.save(user);
		walletService.add(user);
		return user.getId();
	}
	
	private void validateDuplicateUser(String email) {
		if (userRepository.findByEmail(email).isPresent()) {
			throw new IllegalStateException("이미 존재하는 이메일입니다.");
		}
	}
	
	public User findUserById(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User Id 값이 없습니다. UserId : " + userId));
	}
	
	public UserDto.Request readUserForUpdate(Long userId) {
		User user = findUserById(userId);
		return UserDto.Request.of(user);
	}
	
	public void updateName(Long userId, UserDto.Request userDto) {		
		userRepository.updateName(userDto.getName(), userId);
	}
	
	public void updateEmailAndPassword(Long userId, UserDto.Request userDto) {
		String encodePwd = bCryptPasswordEncoder.encode(userDto.getPassword());
		userDto.setPassword(encodePwd);
		userRepository.updateEmailPassword(userDto.getEmail(), userDto.getPassword(), userId);
	}
		
	public void delete(Long userId) {
		userRepository.deleteById(userId);
	}
}
