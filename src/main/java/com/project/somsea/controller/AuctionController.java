package com.project.somsea.controller;

import java.util.List;

import javax.persistence.Transient;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.somsea.domain.Auction;
import com.project.somsea.domain.Bidding;
import com.project.somsea.domain.Collection;
import com.project.somsea.domain.Nft;
import com.project.somsea.domain.TradeHistory;
import com.project.somsea.service.AuctionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuctionController {
	private final AuctionService auctionService;
//	private final CollectionService collectionService;
	
	@GetMapping("/auction/{nftId}")
	public String showAuction(Model model, @PathVariable Long nftId) {
		Nft nft = auctionService.findNft(nftId);
//		Collection collection = 
		Auction auction = auctionService.findByNft(nftId);
		PagedListHolder<Bidding> bidding = new PagedListHolder<Bidding>(this.auctionService.findBiddingList(auction));
		bidding.setPageSize(7); 
		List<TradeHistory> tradeHistory = auctionService.findByAuction(auction.getId());
		model.addAttribute("nft", nft);
		model.addAttribute("auction", auction);
		model.addAttribute("biddingList", bidding);
		model.addAttribute("tradeList", tradeHistory);
		return "auction/view";
	}
	
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
	
	@RequestMapping("/auction/deleteBidding.do")
	public String deleteBidding(
			@RequestParam("biddingId") Long biddingId,
			@RequestParam("auctionId") Long auctionId) {
		auctionService.deleteBidding(biddingId, auctionId);
		return "auction/view";
	}
}
