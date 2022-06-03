package com.project.somsea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.somsea.domain.Auction;
import com.project.somsea.domain.Auction.Status;
import com.project.somsea.domain.Nft;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
	public List<Auction> findByStatus(Status status);
	public Auction findByNft(Nft nft);
}