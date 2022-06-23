package com.project.somsea.controller;

import com.project.somsea.dto.CategoryDto;
import com.project.somsea.dto.CollectionDto;

import com.project.somsea.helper.ImageUploader;
import com.project.somsea.service.CategoryService;
import com.project.somsea.service.CollectionService;
import com.project.somsea.users.CustomUserDetails;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CollectionController {
	private final CollectionService collectionService;
	private final CategoryService categoryService;
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
		List<CategoryDto.Response> categories = categoryService.findAll();

		model.addAttribute("collection", collectionDto);
		model.addAttribute("categories", categories);
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

	@GetMapping("/collections/me")
	public String showMyCollections(Model model, @AuthenticationPrincipal CustomUserDetails userDetails){
		Long userId = userDetails.getUserId();
		List<CollectionDto.Response> collections = collectionService.readCollectionsByUserId(userId);

		model.addAttribute("collections", collections);

		return "users/myCollections";
	}

	@PostMapping("/collections/{collectionId}/delete")
	public String deleteCollections(Model model, @PathVariable Long collectionId,  @AuthenticationPrincipal CustomUserDetails userDetails) {
		Long userId = userDetails.getUserId();
		collectionService.delete(userId, collectionId);
		return "redirect:/collections/me";
	}

	@GetMapping("/collections/{collectionId}/update")
	public String showUpdateCollection(Model model, @PathVariable Long collectionId,
								@AuthenticationPrincipal CustomUserDetails userDetails) {
		CollectionDto.Request collectionDto = collectionService.readCollectionForUpdate(collectionId);
//        Long userId = userDetails.getUserId();
		model.addAttribute("collection", collectionDto);

		return "collections/update";
	}

	@PostMapping("/collections/{collectionId}/update")
	public String updateCollection(Model model, @PathVariable Long collectionId,
							@AuthenticationPrincipal CustomUserDetails userDetails,
							@ModelAttribute("requestDto") CollectionDto.Request requestDto) {
		Long userId = userDetails.getUserId();
		collectionService.update(userId, collectionId, requestDto);
		return "redirect:/collections/me";
	}

	@GetMapping("/collections/search")
	public String search(@RequestParam(value="keyword") String keyword, Model model) {
		List<CollectionDto.Response> collections = collectionService.searchPosts(keyword);
		model.addAttribute("collections", collections);
		return "collections/collectionList";
	}
}