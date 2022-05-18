package com.project.somsea.dao;

import com.project.somsea.domain.Bidding;

public interface BiddingDAO {
	int insert(Bidding bidding);
	int update(int biddingId);
	int delete(int biddingId);
	int select(int biddingId);
	int selectByAuctionId(int auctionId);
	int selectIdByAuctionId(int auctionId);
	int getBidding(int biddingId);
	int getBiddingList(int biddingId);
}