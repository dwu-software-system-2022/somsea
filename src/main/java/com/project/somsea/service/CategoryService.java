package main.java.com.project.somsea.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.project.somsea.domain.Category;
import com.project.somsea.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import main.java.com.project.somsea.controller.Controller;

@Controller
@Transactional
@RequiredArgsConstructor
public class CategoryService {
	private final CategoryRepository categoryRepository;

	private Category findCategoryById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id value don't exist."));
	}
	private Category findCategoryByName(String name) {
		return categoryRepository.findByName(name)
				.orElseThrow(() -> new IllegalArgumentException("Name value don't exist."));
	}
	private Category findAllCategory() {
		return categoryRepository.findAll();
	}
}

