package com.project.somsea.dao;

import com.project.somsea.domain.Wallet;

public interface WalletDAO {
	long update(Long id, Long amount);
	Wallet getWallet(Long id);
}
