package com.project.somsea.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.annotation.Rollback;

import com.project.somsea.domain.*;
import com.project.somsea.domain.Auction.Status;
import com.project.somsea.dto.AuctionDto;
import com.project.somsea.dto.BiddingDto;

@Transactional
@SpringBootTest
public class AuctionServiceTest {

	@Autowired
	private AuctionService auctionService;
	
	@Test
	@Rollback(false)
	@DisplayName("Auction 추가")
	void testAddAuction() {
		LocalDateTime time = LocalDateTime.now();
		String str_curTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		String d_date = "2022-06-20 12:07";
		String[] localtime = d_date.split(" ");
		String[] ymd = localtime[0].split("-");
		String[] hm = localtime[1].split(":");
		
		LocalDateTime due = LocalDateTime.parse(d_date, df);
		
		AuctionDto.Request auctionDto = AuctionDto.Request.builder()
				.registerDate(LocalDateTime.parse(str_curTime, df))
//				.registerDate(time)
//				.dueDate(LocalDateTime.of(Integer.parseInt(ymd[0]), Integer.parseInt(ymd[1]), Integer.parseInt(ymd[2]), Integer.parseInt(hm[0]), Integer.parseInt(hm[1])))
//				.dueDate(LocalDateTime.parse(d_date, df))
				.dueDate(time)
				.nftId(9L)
				.startPrice(9000L)
				.status(Status.IN_PROGRESS)
				.build();
		
		auctionService.addAuction(auctionDto);
	
	}
	
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
//		
//		BiddingDto.Request biddingDto = BiddingDto.Request.builder()
//				.price(300000000L)
//				.time(LocalDateTime.now())
//				.userId(1L)
//				.auctionId(386L)
//				.build();
//		
//		auctionService.addBidding(biddingDto);;
//		
//	
//	}
}
