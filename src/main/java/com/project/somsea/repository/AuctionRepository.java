package com.project.somsea.repository;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.somsea.domain.Auction;
import com.project.somsea.domain.Auction.Status;
import com.project.somsea.domain.Nft;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
	public Auction findByStatusAndNftId(Status status, Long nftId);
	public Auction findByNft(Nft nft);
	@Transactional
	@Modifying
	@Query("update Auction set status = 'COMPLETED' where due_date <= ?1")
	public void closeEvent(LocalDateTime date);
	@Transactional
	@Modifying
	@Query("update Auction set topBid = ?1 where id = ?2")
	public void updateAuction(Long topBid, Long id);
	@Transactional
	@Modifying
	@Query("update Auction set topBid = NULL where id = ?1")
	public void updateNullAuction(Long id);
}