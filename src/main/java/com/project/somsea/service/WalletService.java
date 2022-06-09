package com.project.somsea.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.project.somsea.dto.WalletDto;
import com.project.somsea.repository.UserRepository;
import com.project.somsea.repository.WalletRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WalletService {
	private final UserRepository userRepository;
	private final WalletRepository walletRepository;
	
	public Long add(Long userId, WalletDto.Request wallteDto) {
		
	}
}
