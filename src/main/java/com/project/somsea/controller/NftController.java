package com.project.somsea.controller;

import com.project.somsea.dto.NftDto;
import com.project.somsea.service.NftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class NftController {

    private final NftService nftService;

    @GetMapping("/nfts/{nftId}")
    public ModelAndView getNft(@PathVariable Long nftId) {
        NftDto.ResponseDetail nftDto = nftService.readDetailNft(nftId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/nfts/detail");
        modelAndView.addObject("nft", nftDto);

        return modelAndView;
    }

    @PostMapping("/nfts")
    public ModelAndView addNft() {
        return null;
    }

    @DeleteMapping("/nfts")
    public ModelAndView deleteNft() {
        return null;
    }
}
