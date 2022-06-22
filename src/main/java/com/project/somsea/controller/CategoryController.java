package com.project.somsea.controller;

import com.project.somsea.dto.CollectionDto;
import com.project.somsea.dto.NftDto;
import com.project.somsea.dto.PartDto;
import com.project.somsea.service.CollectionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.somsea.dto.CategoryDto;
import com.project.somsea.service.CategoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CollectionService collectionService;

    @PostMapping("/categories")
    @ResponseBody
    public Long saveCategory(CategoryDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }

    @GetMapping("/categories/{id}")
    @ResponseBody
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return (categoryService).getCategoryById(id);
    }

}

