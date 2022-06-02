package com.project.somsea.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.somsea.domain.Auction;
import com.project.somsea.domain.Bidding;
import com.project.somsea.domain.Nft;
import com.project.somsea.dto.AuctionDto;
import com.project.somsea.repository.AuctionRepository;
import com.project.somsea.repository.BiddingRepository;
import com.project.somsea.repository.NftRepository;
import com.project.somsea.repository.UserRepository;

@Service
@Transactional
public class AuctionService {
	@Autowired
	private AuctionRepository auctionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private NftRepository nftRepository;
	@Autowired
	private BiddingRepository biddingRepository;
	
	public void insert(Long userId, AuctionDto auctionDto) {
		if (isExistingUser(userId)) { // nft 쪽에서 user 존재하는지 확인하는 코드 있으면 없어도 됨.(nft가 존재하는지만 확인필요)
			findNft(auctionDto.getNfTId());
			Auction auction = auctionRepository.getById(auctionDto.getAuctionId());
			auctionRepository.save(auction);
		} else {
			throw new IllegalArgumentException("User가 존재하지 않습니다.");
		}
	}
	
	public boolean isExistingUser(Long user_id) {
		return userRepository.existsById(user_id);
	}
	
	public Nft findNft(Long nftId) {
		return nftRepository.findById(nftId)
				.orElseThrow(() -> new IllegalArgumentException("Nft id 값이 없습니다. nftId : " + nftId));		
	}
	
	public void delete(Long auctionId, Long nftId) {
		findNft(nftId); // nft 존재 여부 확인.
		List<Bidding> bidding = findBidding(auctionId);
		for (int i = 0; i < bidding.size(); i++) {
			biddingRepository.deleteById(bidding.get(i).getId());
		}
		auctionRepository.delete( // auction 여부 확인하면서 삭제
				auctionRepository.findById(auctionId).orElseThrow(() -> new IllegalArgumentException("Auction id 값이 없습니다. auctionId : " + auctionId)));
	}
	
	public Auction findAuction(Long auctionId) {
		return auctionRepository.findById(auctionId)
				.orElseThrow(() -> new IllegalArgumentException("Auction id 값이 없습니다. aucionId : " + auctionId));
	}
	
	public List<Bidding> findBidding(Long auctionId) {
		return biddingRepository.findByAuctionId(auctionId);
	}
	// collection -> click status by radio button => 해당 경매 관련 nft 나옴.
	// Q. nftid 만 모은 list로 구현할지??? 
	public List<Auction> getAuctionListByStatus(String status) {
		return auctionRepository.findByStatus(status);
	}
	
	public Auction getAuctionByNftId(Long nftId) {
		findNft(nftId); 
		return auctionRepository.getByNftId(nftId);
	}
}
