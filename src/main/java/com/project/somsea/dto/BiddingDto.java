package com.project.somsea.dto;

import java.time.LocalDateTime;

import com.project.somsea.domain.Bidding;
import com.project.somsea.domain.Auction;
import com.project.somsea.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class BiddingDto {
	@Setter
	@Getter
	@Builder
	public static class Request {
//		private Long biddingId;
		private Long price;
		private LocalDateTime time;
		private Long userId;
		private Long auctionId;
		
		public Bidding toEntity(User user, Auction auction) {
			return Bidding.builder()
					.user(user)
					.auction(auction)
					.price(price)
					.time(time)
					.build();
		}
		
		public static Request newInstance() {
			return Request.builder().build();
		}
	}
}
