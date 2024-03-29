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
    public String showNftDetail(Model model, @PathVariable Long nftId,
    		@AuthenticationPrincipal CustomUserDetails userDetails ) {

        NftDto.ResponseDetail nftDto = nftService.readDetailNft(nftId);
        Auction auction = auctionService.findByNft(nftId);
        List<Bidding> bidding = auctionService.findBiddingList(auction);

		if(auction != null){
			Long topBiddingId = auctionService.findBiddingIdByTopBid(auction.getId());
			if (topBiddingId != null) {
				Bidding topBidding = auctionService.findBidding(topBiddingId);
				model.addAttribute("topBid", topBidding);
			}
			else model.addAttribute("topBid", null);
			List<TradeHistory> tradeHistory = auctionService.findByAuction(auction.getId());
			model.addAttribute("tradeHistory", tradeHistory);
		}

        model.addAttribute("nft", nftDto);
        model.addAttribute("userId", userDetails != null ? userDetails.getUserId() : 0);
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
    
    @PostMapping("/nfts/{nftId}/delete")
    public String deleteNft(Model model, @PathVariable Long nftId,  @AuthenticationPrincipal CustomUserDetails userDetails) {
		Long userId = userDetails.getUserId();
        nftService.delete(userId, nftId);
        return "redirect:/nfts/me";
    }

    @GetMapping("/nfts/{nftId}/update")
    public String showUpdateNft(Model model, @PathVariable Long nftId,
                            @AuthenticationPrincipal CustomUserDetails userDetails) {
        NftDto.Request nftDto = nftService.readNftForUpdate(nftId);
        List<PartDto.Response> parts = nftService.getPartsByNftId(nftId);
//        Long userId = userDetails.getUserId();
        model.addAttribute("nft", nftDto);
        model.addAttribute("parts", parts);

        return "nfts/update";
    }

    @PostMapping("/nfts/{nftId}/update")
    public String updateNft(Model model, @PathVariable Long nftId,
                            @AuthenticationPrincipal CustomUserDetails userDetails,
                            @ModelAttribute("requestDto") NftDto.Request requestDto) {
        Long userId = userDetails.getUserId();
        nftService.update(userId, nftId, requestDto);
        return "redirect:/nfts/me";
    }

    @GetMapping("/nfts/me")
    public String showMyNfts(Model model, @AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUserId();
        List<NftDto.Response> nfts = nftService.readNftsByUserId(userId);

        model.addAttribute("nfts", nfts);

        return "users/saved";
    }

    @GetMapping("/collections/{collectionId}/nfts")
    public String showNftList(Model model, @PathVariable Long collectionId){
        List<NftDto.Response> nfts = nftService.readNftByCollection(collectionId);
        List<PartDto.Response> parts = nftService.getPartsByCollectionId(collectionId);
        PartDto.Request partRequest = PartDto.Request.newInstance();

        model.addAttribute("collectionId", collectionId);
        model.addAttribute("nfts", nfts);
        model.addAttribute("parts", parts);
        model.addAttribute("partRequest", partRequest);
        return "nfts/nftList";
    }

    @PostMapping("/collections/{collectionId}/nfts")
    public String showNftListFilterByParts(Model model,
                                           @PathVariable Long collectionId,
                                           @ModelAttribute("partRequest") PartDto.Request partRequest) {
        List<NftDto.Response> nfts = findNftByParts(partRequest, collectionId);
        List<PartDto.Response> parts = nftService.getPartsByCollectionId(collectionId);

        model.addAttribute("nfts", nfts);
        model.addAttribute("parts", parts);
        model.addAttribute("partRequest", partRequest);
        return "nfts/nftList";
    }

    private List<NftDto.Response> findNftByParts(PartDto.Request partRequest, Long collectionId) {
        if (partRequest.partIdsIsEmpty()) {
            return nftService.readNftByCollection(collectionId);
        }

        return nftService.readNftByParts(partRequest.getPartIds());
    }
}
