package com.project.somsea.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.project.somsea.domain.User;
import com.project.somsea.domain.Wallet;
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
	
	private User findUser(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User Id 값이 없습니다. UserId: " + userId));
	}
	
	private Wallet findWallet(Long walletId) {
		return walletRepository.findById(walletId)
				.orElseThrow(() -> new IllegalArgumentException("Wallet Id 값이 없습니다. WalletId: " + walletId));
	}
	
	public Long add(Long userId, WalletDto.Request walletDto) {
		User user = findUser(userId);
		Wallet wallet = walletDto.toEntity(user, Integer.toUnsignedLong(10));
		walletRepository.save(wallet);
		return wallet.getId();
	}
	
	public void delete(Long walletId) {
		Wallet wallet = findWallet(walletId);
		walletRepository.delete(wallet);
	}
}
