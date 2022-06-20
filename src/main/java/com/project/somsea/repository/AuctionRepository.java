package com.project.somsea.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import com.project.somsea.domain.Auction;
import com.project.somsea.domain.Auction.Status;
import com.project.somsea.domain.Nft;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
	public List<Auction> findByStatus(Status status);
	public Auction findByNft(Nft nft);
	@Transactional
	@Modifying
	@Query("update Auction set status = 'COMPLETED' where due_date <= ?1")
	public void closeEvent(LocalDateTime date);
	@Transactional
	@Modifying
	@Query("update Auction set topBid = ?1 where id = ?2")
	public void updateAuction(Long topBid, Long id);
}