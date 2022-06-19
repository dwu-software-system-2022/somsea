package com.project.somsea.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.somsea.domain.Category;
import com.project.somsea.dto.CategoryDto;
import com.project.somsea.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
	private final CategoryRepository categoryRepository;
	
	//카테고리등록 
	public Long saveCategory (CategoryDto categoryDto) {
	      Category category = categoryDto.toEntity();
	      if (categoryDto.getName() == null) {
	    	  
	      }
	      return categoryRepository.save(category).getId();
}

	public CategoryDto getCategoryById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}