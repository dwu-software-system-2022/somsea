package com.project.somsea.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.somsea.domain.User;
import com.project.somsea.domain.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
	public Wallet findByUser(User user);
	@Transactional
	@Modifying
	@Query("update Wallet set balance = ?1 where user_id = ?2")
	public void updateBalanceByuserId(Long balance, Long userId);

}
