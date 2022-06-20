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
	private final WalletRepository walletRepository;
	private final UserRepository userRepository;
	
	private Wallet findWallet(Long walletId) {
		return walletRepository.findById(walletId)
				.orElseThrow(() -> new IllegalArgumentException("Wallet Id 값이 없습니다. WalletId: " + walletId));
	}
	
	public Long add(WalletDto.Request walletDto) {
		Wallet wallet = walletDto.toEntity(Integer.toUnsignedLong(10));
		walletRepository.save(wallet);
		return wallet.getId();
	}
	
	public void delete(Long walletId) { 
		Wallet wallet = findWallet(walletId);
		walletRepository.delete(wallet);
	}
	
	public User findUser(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User id가 없습니다. userId : " + userId));
	}
	
	public void updateBalance(Long price, Long userId) {
		User user = findUser(userId);
		Wallet wallet = walletRepository.findByUser(user);
		Long balance = wallet.getBalance();
		Long dif = balance - price;
		
		if (dif < 0) {
			throw new IllegalArgumentException("잔고가 부족합니다.");
		} else {
			walletRepository.updateBalanceByuserId(dif, userId);
		}
	}
}
