package com.project.somsea.controller;

import com.project.somsea.domain.Collection;
import com.project.somsea.dto.CollectionDto;
import com.project.somsea.service.CollectionService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CollectionController {
	@Autowired
	private final CollectionService collectionService;

	@GetMapping("/collections")
	public String showCollectionList(Model model){
		List<CollectionDto.Response> collections = collectionService.findAll();
		model.addAttribute("collections", collections);
		return "collections/collectionList";
	}
}