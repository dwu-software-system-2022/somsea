package com.project.somsea.controller;

import com.project.somsea.dto.NftDto;
import com.project.somsea.dto.PartDto;
import com.project.somsea.service.NftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NftController {

    private final NftService nftService;

    @GetMapping("/nfts/{nftId}")
    public String showNftDetail(Model model, @PathVariable Long nftId) {
        NftDto.ResponseDetail nftDto = nftService.readDetailNft(nftId);
        model.addAttribute("nft", nftDto);
        return "nfts/detail";
    }

    @GetMapping("/collections/{collectionId}/nfts/form")
    public String addNftForm(Model model, @PathVariable Long collectionId) {
        NftDto.Request nftDto = NftDto.Request.newInstance();
        List<PartDto.Response> parts = nftService.getPartsByCollectionId(collectionId);

        model.addAttribute("nft", nftDto);
        model.addAttribute("parts", parts);

        return "nfts/form";
    }

    @PostMapping("/collections/{collectionId}/nfts/form")
    public String addNft(@ModelAttribute("requestDto") NftDto.Request requestDto) {
        Long userId = 1L;
        Long nftId = nftService.add(userId, requestDto);
        // TODO: NFT 추가 완료 후에 이동할 페이지 변경 필요
        return "redirect:/nfts/" + nftId;
    }

    @DeleteMapping("/nfts")
    public ModelAndView deleteNft() {
        return null;
    }
}
