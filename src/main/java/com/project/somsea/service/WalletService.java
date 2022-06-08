package com.project.somsea.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.project.somsea.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WalletService {
	private final UserRepository userRepository;
	
	public Long add(Long userId, )
}
