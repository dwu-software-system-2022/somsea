package com.project.somsea.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.TaskScheduler;
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
import com.project.somsea.dto.TradeHistoryDto;
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
	@Autowired		// SchedulerConfig에 설정된 TaskScheduler 빈을 주입 받음
	@Qualifier("task1")
	private TaskScheduler scheduler1;
	@Autowired
	@Qualifier("task2")
	private TaskScheduler scheduler2;
	
	public Long addAuction(AuctionDto.Request auctionDto) {
		Nft nft = findNft(auctionDto.getNftId());
		Auction auction = auctionDto.toEntity(nft);
		
		Runnable updateTableRunner = new Runnable() { // anonymous class 정의
			@Override
			public void run() {   // 스케쥴러에 의해 미래의 특정 시점에 실행될 작업을 정의
				String str_curTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//				LocalDateTime d = LocalDateTime.parse(str_curTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
				DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime curTime = LocalDateTime.parse(str_curTime, df);
				System.out.println("curTime: " + curTime);
				// 실행 시점의 시각을 전달하여 그 시각 이전의 closing time 값을 갖는 event의 상태를 변경 
				auctionRepository.closeEvent(curTime);	// Auction 테이블의 레코드 갱신	
				System.out.println("updateTableRunner is executed at " + curTime);
			}
		};
		System.out.println(auction.getRegisterDate());
		auctionRepository.save(auction);
		
		Date closingTime = java.sql.Timestamp.valueOf(auctionDto.getDueDate());
		System.out.println(closingTime);
		scheduler1.schedule(updateTableRunner, closingTime);		
		System.out.println("updateTableRunner has been scheduled to execute at " + closingTime);
		
		return auction.getId();
	}

	public Nft findNft(Long nftId) {
		return nftRepository.findById(nftId)
				.orElseThrow(() -> new IllegalArgumentException("Nft id 값이 없습니다. nftId : " + nftId));		
	}
	
	public void deleteAuction(Long auctionId) {
//		findNft(nftId); // nft 존재 여부 확인.
		Auction auction = auctionRepository.getById(auctionId);
//		List<Bidding> bidding = findBiddingList(auction);
//		for (int i = 0; i < bidding.size(); i++) {
////			deleteBidding()
//		}
//		List<TradeHistory> trade = findByAuction(auctionId);
//		for (int i = 0; i < trade.size(); i++) {
//			biddingRepository.deleteById(trade.get(i).getId());
//		}
		tradeHistoryRepository.deleteAllByAuctionId(auctionId);
		biddingRepository.deleteAllByAuctionId(auctionId);
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
		List<Bidding> bidding = biddingRepository.findByAuction(auction);
//		if (bidding == null || bidding.isEmpty())
//			return null;
		return bidding;
	}
	// collection -> click status by radio button => 해당 경매 관련 nft 나옴.
	// Q. nftid 만 모은 list로 구현할지??? 
	public List<Auction> getAuctionListByStatus(Long collectionId, Status status) {
		Collection collection = findCollection(collectionId);
		List<Nft> nft = nftRepository.findAllByCollection(collection);
		List<Auction> auctionList = null;
		Auction auction = null;
		for (Nft n : nft) {
			findNft(n.getId());
			auction = auctionRepository.findByStatusAndNftId(status, n.getId());
			findAuction(auction.getId());
			auctionList.add(auction);
		}
		return auctionList;
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
	public Long addBidding(BiddingDto.Request biddingDto) {
		Auction auction = findAuction(biddingDto.getAuctionId());
		User user = findUser(biddingDto.getUserId());
		Bidding bidding = biddingDto.toEntity(user, auction);
		Long floorBid = findFloorBid(biddingDto.getAuctionId());
		if (floorBid == null) {
			floorBid = bidding.getPrice();
			bidding.setFloorBid(floorBid);
			bidding.setFloorDifference(null);
		} else {
			if (floorBid > bidding.getPrice()) {
				bidding.setFloorBid(bidding.getPrice());
				floorBid = bidding.getPrice();
			} else {
				bidding.setFloorBid(floorBid);			
			}
			double floorDif = floorDifference(bidding.getPrice(), bidding.getFloorBid());
			if (bidding.getPrice() - bidding.getFloorBid() > 0) {
//				if (floorDif < 100) {
//					bidding.setFloorDifference(floorDif + "% below");
//				} else if (floorDif > 100) {
					bidding.setFloorDifference(Math.abs(floorDif) + "% above");
//				} else {
//					bidding.setFloorDifference("same");
//				}
			} else if (bidding.getPrice() - bidding.getFloorBid() < 0){
//				if (floorDif < 100) {
//					bidding.setFloorDifference(floorDif + "% above");
//				} else if (floorDif > 100) {
					bidding.setFloorDifference(Math.abs(floorDif)+ "% below");
//				} else {
//					bidding.setFloorDifference("same");
//				}
			} else {
				bidding.setFloorDifference("similar or same");
			}
		}
		bidding.setExpiration(calExpiration(bidding.getTime(), auction.getDueDate()));
		
		Runnable updateTableRunner = new Runnable() { // anonymous class 정의
			@Override
			public void run() {   // 스케쥴러에 의해 미래의 특정 시점에 실행될 작업을 정의
				String str_curTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
				DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime curTime = LocalDateTime.parse(str_curTime, df);
				System.out.println("curTime: " + curTime);
				// 실행 시점의 시각을 전달하여 그 시각 이전의 closing time 값을 갖는 event의 상태를 변경 
				List<Bidding> bid_list = biddingRepository.findByAuction(auction);
				for (int i = 0; i < bid_list.size(); i++) {
					String expiration = calExpiration(curTime, auction.getDueDate());
//					int expiration = calExpiration(bidding.getTime(), curTime);
					biddingRepository.updateBiddingByExpiration(expiration, bid_list.get(i).getId());	// Bidding 테이블의 레코드 갱신
				}
				System.out.println("BiddingupdateTableRunner is executed at " + curTime);
			}
		};
		biddingRepository.save(bidding);
		
		biddingRepository.updateBiddingByFloorBid(floorBid, bidding.getAuction().getId());
		
		List<Bidding> list = biddingRepository.findByAuction(auction);
		for (int i = 0; i < list.size(); i++) {
			double dif = floorDifference(list.get(i).getPrice(), list.get(i).getFloorBid());
			if (list.get(i).getPrice() - list.get(i).getFloorBid() > 0) {	
			list.get(i).setFloorDifference(Math.abs(dif) + "% above");	
			} else if (list.get(i).getPrice() - list.get(i).getFloorBid() < 0){
				list.get(i).setFloorDifference(Math.abs(dif)+ "% below");
			} else {
				list.get(i).setFloorDifference("similar or same");
			}
			
			biddingRepository.updateBiddingByFloorDif(list.get(i).getFloorDifference(), list.get(i).getId());
		}
		
		Long topBid = findTopBid(auction.getId());
		auctionRepository.updateAuction(topBid, auction.getId());
		
		Date closingTime = java.sql.Timestamp.valueOf(auction.getDueDate());
		System.out.println(closingTime);
		scheduler2.schedule(updateTableRunner, closingTime);		
		System.out.println("Bidding update Table Runner has been scheduled to execute at " + closingTime);

		TradeHistory tradeHistory = TradeHistory.builder()
				.user(user)
				.auction(auction)
				.amount(biddingDto.getPrice())
				.build();

		tradeHistoryRepository.save(tradeHistory);

		return bidding.getId();
	}
	
	public void deleteBidding(Long biddingId, Long auctionId) {
		Auction auction = findAuction(auctionId);
		biddingRepository.delete(
				biddingRepository.findById(biddingId).orElseThrow(() -> new IllegalArgumentException("Bidding id 값이 없습니다. biddingId : " + biddingId)));
		Long topBidPrice = findTopBid(auctionId);
		if (topBidPrice == null) {
			auctionRepository.updateNullAuction(auctionId);
		} else {
			auctionRepository.updateAuction(topBidPrice, auctionId);
			Long floorBid = findFloorBid(auctionId);
			biddingRepository.updateBiddingByFloorBid(floorBid, auctionId);
			biddingRepository.updateBiddingByFloorBid(floorBid, auctionId);
			System.out.println("floorBid" + floorBid);
			List<Bidding> list = biddingRepository.findByAuction(auction);
			for (int i = 0; i < list.size(); i++) {
				double dif = floorDifference(list.get(i).getPrice(), floorBid);
				if (list.get(i).getPrice() - floorBid > 0) {	
					list.get(i).setFloorDifference(Math.abs(dif) + "% above");	
				} else if (list.get(i).getPrice() - floorBid < 0){
					list.get(i).setFloorDifference(Math.abs(dif)+ "% below");
				} else {
					list.get(i).setFloorDifference("similar or same");
				}

//				if (dif < 100) {
//					list.get(i).setFloorDifference(dif + "% below");
//				} else if (dif > 100) {
//					list.get(i).setFloorDifference(dif + "% above");
//				} else {
//					list.get(i).setFloorDifference("similar or same");
//				}
				biddingRepository.updateBiddingByFloorDif(list.get(i).getFloorDifference(), list.get(i).getId());
			}
		}
	}
	
	
	
	public Long findTopBid(Long auctionId) {
		Auction auction = findAuction(auctionId);
		Bidding bidding = biddingRepository.findFirstByAuctionOrderByPriceDesc(auction);
		if (bidding != null) return bidding.getPrice();
		else return null;
	}
	
	public Long findBiddingIdByTopBid(Long auctionId) {
		Auction auction = findAuction(auctionId);
		Bidding bidding = biddingRepository.findFirstByAuctionOrderByPriceDesc(auction);
		if (bidding != null) return bidding.getId();
		else return null;
	}
	
	public Long findFloorBid(Long auctionId) {
		Auction auction = findAuction(auctionId);

		Bidding bidding = biddingRepository.findFirstByAuctionOrderByPriceAsc(auction);
		if (bidding != null) return bidding.getPrice();
		return null;
	}
	
	public double floorDifference(Long price, Long floorBid) { // 바닥가 대비 입찰가와의 차이
		double differencePercent = 0;
		differencePercent = Math.round(((((double)price / floorBid - 1) * 100)));
		return differencePercent;
 	}
	
	public String calExpiration(LocalDateTime time, LocalDateTime due) {
		long hours = ChronoUnit.HOURS.between(time, due);
		if (hours <= 24) {
			return "about " + hours + " hours"; // return type String
		} else {
			long days = ChronoUnit.DAYS.between(time, due);
			return "about " + days + " days";
		}
	}
	
	/* tradeHistory */
	public Long addTradeHistory(TradeHistoryDto.Request tradeDto) {
		User user = findUser(tradeDto.getUserId());
		Auction auction = findAuction(tradeDto.getAuctionId());
		TradeHistory tradeHistory = tradeDto.toEntity(user, auction);
		tradeHistoryRepository.save(tradeHistory);
		
		return tradeHistory.getId();
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
	
	public List<TradeHistory> findByAuction(Long auctionId) {
		Auction auction = findAuction(auctionId);
		return tradeHistoryRepository.findByAuction(auction);
	}
}