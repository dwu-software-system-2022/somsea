package com.project.somsea.dao;

import java.util.List;

import com.project.somsea.domain.Auction;

public interface AuctionDAO {
	long addAuction(Auction auction);
	long updateAuction(long auctionId);
	long deleteAuction(long auctionId);
	long selectByCategory(long categoryId);
	long selectByType(long type);
	long getAuction(long auctionId);
	List<Auction> getAuctionList(long ownerId);
}