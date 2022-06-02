package com.project.somsea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.somsea.domain.Bidding;

public interface BiddingRepository extends JpaRepository<Bidding, Long> {
	List<Bidding> findByAuctionId(Long auctionId);
}
