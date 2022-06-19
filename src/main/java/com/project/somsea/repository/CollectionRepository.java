package com.project.somsea.repository;

import com.project.somsea.domain.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
	Optional<Collection> findById(String id);	
	Optional<Collection> findByName(String name);
}