package com.project.somsea.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.project.somsea.domain.Auction;
import com.project.somsea.domain.Auction.Status;
import com.project.somsea.domain.Nft;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
public class AuctionDto {
	@Setter
	@Getter
	@Builder
	public static class Request {
		private Long auctionId;
	    private Long nftId;
	    @NotEmpty
	    private Long startPrice;
//	    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
//	    private LocalDateTime registerDate;
	    @NotEmpty
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
