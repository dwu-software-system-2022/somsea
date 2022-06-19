package com.project.somsea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.somsea.dto.CategoryDto;
import com.project.somsea.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService categoryService;
	
	@PostMapping ("/categories")
	@ResponseBody //데이터 전달
	//Category 저장 메소드
	public Long saveCategory (CategoryDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }
	@GetMapping ("/categories/{id}")
    @ResponseBody
    public CategoryDto getCategoryById (@PathVariable Long id) {
        return (categoryService).getCategoryById(id);
    }
}