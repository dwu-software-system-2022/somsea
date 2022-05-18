package com.project.somsea.dao;

import com.project.somsea.domain.Bidding;

public interface BiddingDAO {
	long insert(Bidding bidding);
	long update(long biddingId);
	long delete(long biddingId);
	long select(long biddingId);
	long selectByAuctionId(long auctionId);
	long selectIdByAuctionId(long auctionId);
	long getBidding(long biddingId);
	long getBiddingList(long biddingId);
}