package com.project.somsea.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import com.project.somsea.domain.Auction;
import com.project.somsea.domain.Bidding;

public interface BiddingRepository extends JpaRepository<Bidding, Long> {
	List<Bidding> findByAuction(Auction auction);
	@Nullable
	public Bidding findFirstByAuctionOrderByPriceDesc(Auction auction);
	@Nullable
	public Bidding findFirstByAuctionOrderByPriceAsc(Auction auction);
	@Transactional
	@Modifying
	@Query("update Bidding set floor_bid = ?1 where auction_id = ?2")
	public void updateBiddingByFloorBid(Long floorBid, Long auctionId);
	@Transactional
	@Modifying
	@Query("update Bidding set floor_difference = ?1 where id = ?2")
	public void updateBiddingByFloorDif(String floorDif, Long id);
	@Transactional
	@Modifying
	@Query("update Bidding set expiration = ?1 where id = ?2")
	public void updateBiddingByExpiration(String expiration, Long id);
	@Transactional
	@Modifying
	@Query("delete from Bidding where auction_id = ?1")
	public void deleteAllByAuctionId(Long auctionId);
}
