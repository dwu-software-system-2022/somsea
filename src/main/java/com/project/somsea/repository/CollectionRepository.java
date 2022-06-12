package main.java.com.project.somsea.repository;

import main.java.com.project.somsea.domain.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
	public List<Collection> findById(Long id); 
	
	public List<Collection> findByName(String name); 
	
	public List<Collection> findAll(); 
}
