package com.project.somsea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.somsea.domain.TradeHistory;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory, Long> {

}
