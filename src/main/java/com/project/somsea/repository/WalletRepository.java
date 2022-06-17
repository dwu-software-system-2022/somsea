package com.project.somsea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.somsea.domain.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
	
}
