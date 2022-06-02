package main.java.com.project.somsea.repository;

import com.project.somsea.domain.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	List<Category> getCategoryList();
	
	Category getCategory(Long id);
}