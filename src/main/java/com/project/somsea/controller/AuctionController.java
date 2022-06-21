package com.project.somsea.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.project.somsea.domain.Auction;
import com.project.somsea.domain.Bidding;
import com.project.somsea.domain.Collection;
import com.project.somsea.domain.Nft;
import com.project.somsea.domain.TradeHistory;
import com.project.somsea.domain.User;
import com.project.somsea.domain.Auction.Status;
import com.project.somsea.dto.AuctionDto;
import com.project.somsea.dto.BiddingDto;
import com.project.somsea.dto.NftDto;
import com.project.somsea.dto.TradeHistoryDto;
import com.project.somsea.dto.UserDto;
import com.project.somsea.service.AuctionService;
import com.project.somsea.service.NftService;
import com.project.somsea.service.WalletService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("auctionDto")
public class AuctionController {
	private final AuctionService auctionService;
	private final WalletService walletService;
	private final NftService nftService;
	
	@ModelAttribute("auctionDto")
	public AuctionDto.Request formBacking(@RequestParam("nftId") Long nftId, HttpServletRequest request) {
		AuctionDto.Request auctionDto = AuctionDto.Request.newInstance();
		auctionDto.setNftId(nftId);
		return auctionDto;
	}
	
	@GetMapping("/auction/add/form") 
	public String form() {
		return "auction/form";
	}
	
	@PostMapping("/auction/add")
	public String addAuctionForm(@ModelAttribute("auctionDto") AuctionDto.Request auctionDto, Model model,
			SessionStatus status) {
		auctionDto.setStatus(Status.IN_PROGRESS);
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String str_curTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		LocalDateTime curTime = LocalDateTime.parse(str_curTime, df);
//		auctionDto.setRegisterDate(curTime);
		Long auctionId = auctionService.addAuction(auctionDto); // scheduler 같이 작동
		auctionDto.setAuctionId(auctionId);
		status.setComplete();

		return "redirect:/users/mypage";
	}
	
/*	@GetMapping("/auction/view")
	public String showAuction(@ModelAttribute("auctionDto") AuctionDto.Request auctionDto, Model model) {
		Nft nft = auctionService.findNft(auctionDto.getNftId());
//		Collection collection = 
		Auction auction = auctionService.findByNft(auctionDto.getNftId());
		PagedListHolder<Bidding> bidding = new PagedListHolder<Bidding>(this.auctionService.findBiddingList(auction));
		bidding.setPageSize(7); 
//		List<TradeHistory> tradeHistory = auctionService.findByAuction(auction.getId());
		model.addAttribute("nft", nft);
		model.addAttribute("auction", auction);
		model.addAttribute("biddingList", bidding);
//		model.addAttribute("tradeList", tradeHistory);
		return "/auction/view";
	}*/
	
	// jsp에 page 넘기는 버튼 있을 경우, jpetstore 예제 이용하여 사용(ViewProductController)
	/*@RequestMapping("/shop/viewProduct2.do") 
	public String handleRequest2(
			@ModelAttribute("product") Product product,
			@ModelAttribute("itemList") PagedListHolder<Item> itemList,
			@RequestParam("pageName") String page, 
			ModelMap model) throws Exception {
		if ("next".equals(page)) {
			itemList.nextPage();
		}
		else if ("previous".equals(page)) {
			itemList.previousPage();
		}
		model.put("itemList", itemList);
		model.put("product", product);
		return "Product";
	}*/
	
	// 낙찰받아서 살 때 (nft detail에서 버튼 낙찰자만 place bid -> buy now로 변화.) 
//	@RequestMapping("/nfts/bidding/win")
//	public String winBidding(Model model, @SessionAttribute("userId") Long userId, 
//			@RequestParam("nftId") Long nftId) {
//		User user = auctionService.findUser(userId);
//		Auction auction = auctionService.findByNft(nftId);
//		Long biddingid = auctionService.findBiddingIdByTopBid(auction.getId());
//		Bidding bidding = auctionService.findBidding(biddingid);
//		//update문 써야 됨. user_id 바꿔야 됨. nft의 
//		nftService.updateUserIdOfNft(userId, nftId);
//		// wallet balance도 바꿔야 됨. balance가 입찰가보다 낮으면 충전하세요!.
//		walletService.updateBalance(bidding.getPrice(), userId);
//		model.addAttribute("bidding", bidding);
//		model.addAttribute("user", user);
//		return "/auction/result"; // or /users/mypage
//	}
//	
//	@RequestMapping("/nfts/bidding/delete") // biddingDto 받을 수 있는지 해보기.
//	public String deleteBidding(@SessionAttribute("userId") Long userId, @RequestParam("nftId") Long nftId) {
//		Auction auction = auctionService.findByNft(nftId);
//		List<Bidding> bidding = auctionService.findBiddingList(auction);
//		Long biddingId = null;
//		for (int i = 0; i < bidding.size(); i++) {
//			if (userId == bidding.get(i).getUser().getId()) {
//				biddingId = bidding.get(i).getId();
//				break;
//			}
//		}
//		auctionService.deleteBidding(biddingId, auction.getId());
//		return "redirect:/nfts/detail";
//	}
	
//	@RequestMapping("/auction/view")
//	public String deleteBidding(
//			@RequestParam("biddingId") Long biddingId,
//			@RequestParam("auctionId") Long auctionId) {
//		auctionService.deleteBidding(biddingId, auctionId);
//		return "redirect:/auction/view";
//	}
	
	// 낙찰받아서 샀을 때.
//	@RequestMapping("/auction/trade")
//	public String updateSuccessTrade(@ModelAttribute("auctionDto") AuctionDto.Request auction, @SessionAttribute("userId") Long userId) {
//		TradeHistoryDto.Request trade = TradeHistoryDto.Request.newInstance();
//		trade.setAuctionId(auction.getAuctionId());
//		trade.setUserId(userId);
//		trade.setStatus(TradeHistory.Status.BID_SUCCESS);
//		
//		Long topBid = auctionService.findTopBid(auction.getAuctionId());
//		trade.setAmount(topBid);
//		auctionService.addTradeHistory(trade);
//		
//		return "redirect:/user/mypage";
//	}
}
