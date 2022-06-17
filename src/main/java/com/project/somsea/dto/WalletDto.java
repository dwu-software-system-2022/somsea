package com.project.somsea.dto;

import com.project.somsea.domain.Wallet;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class WalletDto {
	@Setter
	@Getter
	@Builder
	public static class Request {
		private Long walletId;
		private Long balance;
		
		public Wallet toEntity(Long balance) {
			return Wallet.builder()
					.balance(balance)
					.build();
		}
	}
}
