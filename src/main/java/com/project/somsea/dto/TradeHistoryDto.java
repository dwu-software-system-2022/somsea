package com.project.somsea.dto;

import java.time.LocalDateTime;

import com.project.somsea.domain.Auction;
import com.project.somsea.domain.TradeHistory;
import com.project.somsea.domain.TradeHistory.Status;
import com.project.somsea.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class TradeHistoryDto {
	@Setter
	@Getter
	@Builder
	public static class Request {
		private Long auctionId;
	    private Long userId;
	    private Long amount;
	    private Status status;
	    
	    
	    public TradeHistory toEntity(User user, Auction auction) {
	    	return TradeHistory.builder()
	    			.user(user)
	    			.auction(auction)
	    			.amount(amount)
	    			.status(status)
	    			.build();
	    }
		
	    public static Request newInstance() {
	    	return Request.builder().build();
	    }
	}
}
