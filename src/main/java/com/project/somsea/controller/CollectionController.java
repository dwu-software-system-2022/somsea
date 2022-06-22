package com.project.somsea.controller;

import com.project.somsea.domain.Collection;
import com.project.somsea.dto.CollectionDto;
import com.project.somsea.dto.NftDto;
import com.project.somsea.dto.PartDto;
import com.project.somsea.helper.ImageUploader;
import com.project.somsea.service.CollectionService;
import com.project.somsea.users.CustomUserDetails;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CollectionController {
	private final CollectionService collectionService;
	private final ImageUploader imageUploader;

	@GetMapping("/collections")
	public String showCollectionList(Model model){
		List<CollectionDto.Response> collections = collectionService.findAll();
		model.addAttribute("collections", collections);
		return "collections/collectionList";
	}

	@GetMapping("/collections/form")
	public String addCollectionForm(Model model) {
		CollectionDto.Request collectionDto = CollectionDto.Request.newInstance();
		model.addAttribute("collection", collectionDto);
		return "collections/upload";
	}

	@PostMapping("/collections/form")
	public String addCollection(@ModelAttribute("requestDto") CollectionDto.Request requestDto,
						 @AuthenticationPrincipal CustomUserDetails userDetails) throws IOException {
		Long userId = userDetails.getUserId();

		String imageUrl = imageUploader.upload(requestDto.getImageFile());
		requestDto.setLogoImgUrl(imageUrl);

		Long collectionId = collectionService.add(userId, requestDto);

		return "redirect:/collections/" + collectionId + "/nfts";
	}

	@GetMapping("/categories/{categoryId}/collections")
	public String showCollectionListFilterByCategory(Model model,
													 @PathVariable Long categoryId) {

		List<CollectionDto.Response> collections = collectionService.findByCategoryId(categoryId);

		model.addAttribute("collections", collections);
		return "collections/collectionList";
	}
}