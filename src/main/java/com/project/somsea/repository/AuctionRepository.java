package com.project.somsea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.somsea.domain.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
	public List<Auction> findByStatus(String status);
	public Auction getByNftId(Long id);
}