package com.project.somsea.dto;

import java.time.LocalDateTime;

import com.project.somsea.domain.Auction;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuctionDto {
	private Long auctionId;
    private Long nfTId;
    private double startPrice;
    private LocalDateTime registerDate;
    private LocalDateTime dueDate;
    private String status;
    
    public static AuctionDto of(Auction auction) {
    	return AuctionDto.builder()
    			.auctionId(auction.getId())
    			.nfTId(auction.getId())
    			.startPrice(auction.getStartPrice())
    			.registerDate(auction.getRegisterDate())
    			.dueDate(auction.getDueDate())
    			.status(auction.getStatus().name())
    			.build();
    }
}
