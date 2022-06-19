package com.project.somsea.repository;

import com.project.somsea.domain.Category;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findById(String id);
	
	Optional<Category> findByName(String name);
}