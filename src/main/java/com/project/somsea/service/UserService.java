package com.project.somsea.service;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.project.somsea.domain.User;
import com.project.somsea.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	public Long add(User user) {
		userRepository.save(user);
		return user.getId();
	}
	
	public User findUserById(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User Id 값이 없습니다. UserId : " + userId));
	}
		
	public void delete(Long userId) {
		userRepository.delete(findUserById(userId));
	}
}
