package com.project.somsea.dao;

import java.util.List;

import com.project.somsea.domain.Auction;

public interface AuctionDAO {
	int addAuction(Auction auction);
	int updateAuction(int auctionId);
	int deleteAuction(int auctionId);
	int selectByCategory(int categoryId);
	int selectByType(int type);
	int getAuction(int auctionId);
	List<Auction> getAuctionList(int ownerId);
}
