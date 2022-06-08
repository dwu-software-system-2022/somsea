package main.java.com.project.somsea.repository;

import com.project.somsea.domain.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	public List<Category> findAllById(Long id); //id로 검색
	public List<Category> findAllByName(String name); //name로 검색
	public List<Category> findAll(); //전체 검색
}