package main.java.com.project.somsea.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.project.somsea.domain.Category;
import com.project.somsea.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class CategoryService {
	private final CategoryRepository categoryRepository;

	//카테고리저장 
	public Long add(Category category) {
		Category category = findCollection(categoryDto.getId());
        CategoryRepository.save(category);
    }
}
