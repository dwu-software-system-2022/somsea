package com.project.somsea.controller;

import com.project.somsea.dto.CategoryDto;
import com.project.somsea.dto.CollectionDto;
import com.project.somsea.service.CategoryService;
import com.project.somsea.service.CollectionService;
import com.project.somsea.repository.CollectionRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class CollectionController {
		private final CollectionService collectionService;
		
		@PostMapping ("/collections")
		@ResponseBody //데이터 전달	
		//Category 저장 메소드
		public Long saveCollection (CollectionDto collectionDto) {
	        return collectionService.saveCollection(collectionDto);
	    }
		@GetMapping ("/collections/{id}")
	    @ResponseBody
	    public CollectionDto getCollectionByName (@PathVariable String name) {
	        return (collectionService).getCollectionByName(name);
	    }
}