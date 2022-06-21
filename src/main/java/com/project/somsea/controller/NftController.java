package com.project.somsea.controller;

import com.project.somsea.domain.Auction;
import com.project.somsea.domain.Bidding;
import com.project.somsea.domain.TradeHistory;
import com.project.somsea.dto.NftDto;
import com.project.somsea.dto.PartDto;
import com.project.somsea.service.AuctionService;
import com.project.somsea.helper.ImageUploader;
import com.project.somsea.service.NftService;
import com.project.somsea.users.CustomUserDetails;
import com.project.somsea.service.WalletService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NftController {

    private final NftService nftService;
    private final ImageUploader imageUploader;

    private final AuctionService auctionService;

    @GetMapping("/nfts/{nftId}")
    public String showNftDetail(Model model, @PathVariable Long nftId) {

        NftDto.ResponseDetail nftDto = nftService.readDetailNft(nftId);
        Auction auction = auctionService.findByNft(nftId);
        List<Bidding> bidding = auctionService.findBiddingList(auction);

		if(auction != null){
			Long topBidddingId = auctionService.findBiddingIdByTopBid(auction.getId());
			Bidding topBidding = auctionService.findBidding(topBidddingId);
			List<TradeHistory> tradeHistory = auctionService.findByAuction(auction.getId());

			model.addAttribute("topBid", topBidding);
			model.addAttribute("tradeHistory", tradeHistory);
		}

        model.addAttribute("nft", nftDto);
        model.addAttribute("userId", 1L);
        model.addAttribute("auction", auction);
        model.addAttribute("bidding", bidding);

        return "nfts/item";
    }

    @GetMapping("/collections/{collectionId}/nfts/form")
    public String addNftForm(Model model, @PathVariable Long collectionId) {
        NftDto.Request nftDto = NftDto.Request.newInstance();
        List<PartDto.Response> parts = nftService.getPartsByCollectionId(collectionId);

        model.addAttribute("nft", nftDto);
        model.addAttribute("parts", parts);

        return "nfts/upload";
    }



    @PostMapping("/collections/{collectionId}/nfts/form")
    public String addNft(@ModelAttribute("requestDto") NftDto.Request requestDto
    		, @AuthenticationPrincipal CustomUserDetails userDetails) throws IOException {
        Long userId = userDetails.getUserId();

		String imageUrl = imageUploader.upload(requestDto.getImageFile());
		requestDto.setImageUrl(imageUrl);

        Long nftId = nftService.add(userId, requestDto);
        // TODO: NFT 추가 완료 후에 이동할 페이지 변경 필요
        return "redirect:/nfts/" + nftId;
    }
    
    @PostMapping("/nfts/{nftId}")
    public String deleteNft(Model model, @PathVariable Long nftId,  @AuthenticationPrincipal CustomUserDetails userDetails) {
		Long userId = userDetails.getUserId();
        nftService.delete(userId, nftId);
        // TODO: NFT 추가 완료 후에 이동할 페이지 변경 필요
        return "redirect:/";
    }

    @GetMapping("/nfts/me")
    public String showMyNfts(Model model, @AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUserId();
        List<NftDto.Response> nfts = nftService.readNftsByUserId(userId);

        model.addAttribute("nfts", nfts);

        return "users/saved";
    }


}
