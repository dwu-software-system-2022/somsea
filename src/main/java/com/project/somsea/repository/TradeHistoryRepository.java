package com.project.somsea.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.somsea.domain.Auction;
import com.project.somsea.domain.TradeHistory;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory, Long> {
	List<TradeHistory> findByAuction(Auction auction);
	@Transactional
	@Modifying
	@Query("delete from TradeHistory where auction_id = ?1")
	public void deleteAllByAuctionId(Long auctionId);
}
