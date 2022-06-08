package com.project.somsea.dto;

import java.time.LocalDateTime;

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
	    private Long startPrice;
	    private LocalDateTime registerDate;
	    private LocalDateTime dueDate;
	    private Status status;
	    
	    
	    public Auction toEntity(Nft nft) {
	    	return Auction.builder()
	    			.nft(nft)
	    			.startPrice(startPrice)
	    			.registerDate(registerDate)
	    			.dueDate(dueDate)
	    			.status(status)
	    			.build();
	    }
		
	}
//	private Long auctionId;
//    private Long nftId;
//    private Long startPrice;
//    private LocalDateTime registerDate;
//    private LocalDateTime dueDate;
//    private Status status;
//    
//    
//    public static AuctionDto of(Auction auction, Nft nft) {
//    	return AuctionDto.builder()
//    			.auctionId(auction.getId())
//    			.nftId(nft.getId())
//    			.startPrice(auction.getStartPrice())
//    			.registerDate(auction.getRegisterDate())
//    			.dueDate(auction.getDueDate())
//    			.status(auction.getStatus())
//    			.build();
//    }
//    public AuctionDto toEntity(Auction auction) {
//    	return AuctionDto.builder()
//    			.auctionId(auctionId)
//    			.nftId(nftId)
//    			.startPrice(startPrice)
//    			.registerDate(registerDate)
//    			.dueDate(dueDate)
//    			.status(status)
//    			.build();
//    }
}
