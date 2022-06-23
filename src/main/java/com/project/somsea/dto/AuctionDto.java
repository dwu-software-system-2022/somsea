package com.project.somsea.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.project.somsea.domain.Auction;
import com.project.somsea.domain.Auction.Status;
import com.project.somsea.domain.Nft;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class AuctionDto {
	@Setter
	@Getter
	@Builder
	public static class Request {
		private Long auctionId;
	    private Long nftId;
	    
	    @NotNull(message="price를 입력해야합니다.")
	    private Long startPrice;
//	    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
//	    private LocalDateTime registerDate;
	    @NotNull(message="날짜를 입력해야합니다.")
	    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	    private LocalDateTime dueDate;
	    private Status status;
	    private Long topBid;
	    
	    
	    public Auction toEntity(Nft nft) {
	    	return Auction.builder()
	    			.nft(nft)
	    			.startPrice(startPrice)
//	    			.registerDate(registerDate)
	    			.dueDate(dueDate)
	    			.status(status)
	    			.topBid(topBid)
	    			.build();
	    }
		
	    public static Request newInstance() {
	    	return Request.builder().build();
	    }
	}
}
