package com.project.somsea.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.project.somsea.domain.User;
import com.project.somsea.domain.Wallet;
import com.project.somsea.repository.WalletRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WalletService {
	private final WalletRepository walletRepository;
	
	
	private Wallet findWallet(Long walletId) {
		return walletRepository.findById(walletId)
				.orElseThrow(() -> new IllegalArgumentException("Wallet Id 값이 없습니다. WalletId: " + walletId));
	}
	
	public void add(User user) {
		Wallet wallet = Wallet.builder()
						.balance(Integer.toUnsignedLong(10))
						.user(user)
						.build();
		walletRepository.save(wallet);
//		return wallet.getId();
	}
	
	public void delete(Long walletId) {
		Wallet wallet = findWallet(walletId);
		walletRepository.delete(wallet);
	}

}
