package com.project.somsea.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.project.somsea.domain.*;
import com.project.somsea.domain.Auction.Status;
import com.project.somsea.dto.AuctionDto;
import com.project.somsea.dto.BiddingDto;
import com.project.somsea.dto.TradeHistoryDto;

@Transactional
@SpringBootTest
public class AuctionServiceTest {

	@Autowired
	private AuctionService auctionService;
	
//	@Test
//	@Rollback(false)
//	@DisplayName("Auction 추가")
//	void testAddAuction() {
//		LocalDateTime time = LocalDateTime.now();
//		String str_curTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//		
//		String d_date = "2022-06-20 12:07";
//		
//		AuctionDto.Request auctionDto = AuctionDto.Request.builder()
////				.registerDate(LocalDateTime.parse(str_curTime, df))
//				.dueDate(LocalDateTime.parse(d_date, df))
////				.dueDate(time)
//				.nftId(9L)
//				.startPrice(9000L)
//				.status(Status.IN_PROGRESS)
//				.build();
//		
//		auctionService.addAuction(auctionDto);
//	
//	}
	
//	@Test
//	@Rollback(false)
//	@DisplayName("Auction 삭제")
//	void testDeleteAuction() {
//		auctionService.deleteAuction(447L,9L);
//		auctionService.deleteAuction(448L,9L);
//		auctionService.deleteAuction(449L,9L);
//		auctionService.deleteAuction(454L,9L);
//	}
//	@Test
//	@Rollback(false)
//	@DisplayName("Bidding 추가")
//	void testAddBidding() { 
//		String str_curTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//		
//		BiddingDto.Request biddingDto = BiddingDto.Request.builder()
//				.price(30000000L)
//				.time(LocalDateTime.parse(str_curTime, df))
//				.userId(1L)
//				.auctionId(633L)
//				.build();
//		
//		auctionService.addBidding(biddingDto);;
//		
//	
//	}
	
//	@Test
//	@Rollback(false)
//	@DisplayName("Bidding 삭제")
//	void testDeleteBidding() { 
//		auctionService.deleteBidding(646L, 633L);
//	}
	
//	@Test
//	@Rollback(false)
//	@DisplayName("Bidding 조회")
//	void tesBiddingList() { 
//		Auction auction = auctionService.findAuction(633L);
//		auctionService.findBiddingList(auction);
//	}
	
	@Test
	@Rollback(false)
	@DisplayName("Trade 추가")
	void testAddTrade() {
		TradeHistoryDto.Request trade = TradeHistoryDto.Request.builder()
				.auctionId(633L)
				.userId(1L)
				.amount(3000000L)
				.build();
		Long tradeId = auctionService.addTradeHistory(trade);
		auctionService.findTradeHistory(tradeId);
		auctionService.findByAuction(trade.getAuctionId());
//		auctionService.getAuctionListByStatus(tradeId, null);
//		auctionService.deleteTradeHistory(652L);
	}
	
}
