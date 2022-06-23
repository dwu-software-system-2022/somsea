package com.project.somsea.repository;

import com.project.somsea.domain.Collection;
import com.project.somsea.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
	List<Collection> findAllByUser(User user);
	List<Collection> findByNameContaining(String keyword);
}