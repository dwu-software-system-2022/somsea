package com.project.somsea.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.project.somsea.dto.CollectionDto;
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

	public List<CategoryDto.Response> findAll(){
		return categoryRepository.findAll().stream()
				.map(CategoryDto.Response::of)
				.collect(Collectors.toList());
	}



}