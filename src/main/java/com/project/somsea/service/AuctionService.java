package com.project.somsea.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.project.somsea.domain.Auction;
import com.project.somsea.domain.Auction.Status;
import com.project.somsea.domain.Bidding;
import com.project.somsea.domain.Collection;
import com.project.somsea.domain.Nft;
import com.project.somsea.domain.TradeHistory;
import com.project.somsea.domain.User;
import com.project.somsea.dto.AuctionDto;
import com.project.somsea.dto.BiddingDto;
import com.project.somsea.repository.AuctionRepository;
import com.project.somsea.repository.BiddingRepository;
import com.project.somsea.repository.CollectionRepository;
import com.project.somsea.repository.NftRepository;
import com.project.somsea.repository.TradeHistoryRepository;
import com.project.somsea.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuctionService {
	private final AuctionRepository auctionRepository;
	private final UserRepository userRepository;
	private final  NftRepository nftRepository;
	private final BiddingRepository biddingRepository;
	private final CollectionRepository collectionRepository;
	private final TradeHistoryRepository tradeHistoryRepository;
	
	public void addAuction(AuctionDto.Request auctionDto) {
//		if (isExistingUser(userId)) { // nft 쪽에서 user 존재하는지 확인하는 코드 있으면 없어도 됨.(nft가 존재하는지만 확인필요)
//			Nft nft = findNft(auctionDto.getNftId());
//			Auction auction = auctionDto.toEntity(nft);
//			auctionRepository.save(auction);
//		} else {
//			throw new IllegalArgumentException("User가 존재하지 않습니다.");
//		}
		Nft nft = findNft(auctionDto.getNftId());
		Auction auction = auctionDto.toEntity(nft);
		auctionRepository.save(auction);
		auction.setTopBid(findTopBid(auction.getId()));
	}
	
//	public boolean isExistingUser(Long userId) {
//		return userRepository.existsById(userId);
//	}
	
	public Nft findNft(Long nftId) {
		return nftRepository.findById(nftId)
				.orElseThrow(() -> new IllegalArgumentException("Nft id 값이 없습니다. nftId : " + nftId));		
	}
	
	public void deleteAuction(Long auctionId, Long nftId) {
		findNft(nftId); // nft 존재 여부 확인.
		Auction auction = auctionRepository.getById(auctionId);
		List<Bidding> bidding = findBiddingList(auction);
		for (int i = 0; i < bidding.size(); i++) {
			biddingRepository.deleteById(bidding.get(i).getId());
		}
		auctionRepository.delete( // auction 여부 확인하면서 삭제
				auctionRepository.findById(auctionId).orElseThrow(() -> new IllegalArgumentException("Auction id 값이 없습니다. auctionId : " + auctionId)));
	}
	
	public User findUser(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User id가 없습니다. userId : " + userId));
	}
	
	public Auction findAuction(Long auctionId) {
		return auctionRepository.findById(auctionId)
				.orElseThrow(() -> new IllegalArgumentException("Auction id 값이 없습니다. aucionId : " + auctionId));
	}
	
	public Collection findCollection(Long collectionId) {
		return collectionRepository.findById(collectionId)
				.orElseThrow(() -> new IllegalArgumentException("Collection id가 없습니다. collectionId : " + collectionId));
	}
	
	public List<Bidding> findBiddingList(Auction auction) {
		return biddingRepository.findByAuction(auction);
	}
	// collection -> click status by radio button => 해당 경매 관련 nft 나옴.
	// Q. nftid 만 모은 list로 구현할지??? 
	public List<Auction> getAuctionListByStatus(Long collectionId, Status status) {
		Collection collection = findCollection(collectionId);
		List<Nft> nft = nftRepository.findAllByCollection(collection);
		for (Nft n : nft) {
			findNft(n.getId());
		}
		return auctionRepository.findByStatus(status);
	}
	
	public Auction findByNft(Long nftId) {
		Nft nft = findNft(nftId); 
		return auctionRepository.findByNft(nft);
	}
	
	public Bidding findBidding(Long biddingId) {
		return biddingRepository.findById(biddingId)
				.orElseThrow(() -> new IllegalArgumentException("Bidding id 값이 없습니다." + biddingId));
	}
	
	/* bidding */
	public void addBidding(Long auctionId, Long userId, BiddingDto.Request biddingDto) {
		Auction auction = findAuction(auctionId);
		User user = findUser(userId);
		Bidding bidding = biddingDto.toEntity(user, auction);
		biddingRepository.save(bidding);
		bidding.setFloorBid(findFloorBid(auctionId));
		double floorDif = FloorDifference(bidding.getId());
		if (floorDif < 100) {
			bidding.setFloorDifference(floorDif + "below");
		} else if (floorDif > 100) {
			bidding.setFloorDifference(floorDif + "above");
		} else {
			bidding.setFloorDifference(floorDif + "same");
		}
	}
	
	public void deleteBidding(Long biddingId, Long auctionId) {
		findAuction(auctionId);
		biddingRepository.delete(
				biddingRepository.findById(biddingId).orElseThrow(() -> new IllegalArgumentException("Bidding id 값이 없습니다. biddingId : " + biddingId)));
	}
	
	public Long findTopBid(Long auctionId) {
		Auction auction = findAuction(auctionId);
		List<Bidding> biddingList = findBiddingList(auction);
		Collections.sort(biddingList, Collections.reverseOrder());
		return biddingList.get(0).getPrice();
	}
	
	public Long findFloorBid(Long auctionId) {
		Auction auction = findAuction(auctionId);
		List<Bidding> biddingList = findBiddingList(auction);
		Collections.sort(biddingList);
		return biddingList.get(0).getPrice();
	}
	
	public double FloorDifference(Long biddingId) { // 바닥가 대비 입찰가와의 차이
		Bidding bidding = findBidding(biddingId);
		Long floorBid = findFloorBid(bidding.getAuction().getId());
		double differencePercent = 0;
		differencePercent = Math.round(((((double)bidding.getPrice() / floorBid) * 100) * 100) / 100);
//		String.format("%.2f", ((double)bidding.getPrice() / floorBid) * 100);
		return differencePercent;
 	}
	
//	public int calExpiration(Long biddingId) {
//		Bidding bidding = findBidding(biddingId);
//		LocalDateTime time = bidding.getTime();
//		if ()
//	}
	
	/* tradeHistory */
	public void addTradeHistory() {
		
	}
	
	public void deleteTradeHistory(Long tradeId) {
		TradeHistory trade = findTradeHistory(tradeId);
		findAuction(trade.getAuction().getId());
		findUser(trade.getUser().getId());
		tradeHistoryRepository.delete(trade);
	}
	
	public TradeHistory findTradeHistory(Long tradeId) {
		return tradeHistoryRepository.findById(tradeId)
				.orElseThrow(() -> new IllegalArgumentException("TradeId 값이 없습니다. tradeId : " + tradeId));
	}
}
