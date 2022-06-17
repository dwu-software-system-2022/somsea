package com.project.somsea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.somsea.domain.Auction;
import com.project.somsea.domain.TradeHistory;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory, Long> {
	List<TradeHistory> findByAuction(Auction auction);
}
