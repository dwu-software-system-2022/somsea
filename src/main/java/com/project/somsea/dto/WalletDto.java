package com.project.somsea.dto;

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
		private User user;
		
	}
}
