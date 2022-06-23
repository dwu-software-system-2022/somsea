package com.project.somsea.repository;

import com.project.somsea.domain.User;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	Optional<User> findByName(String name);
	
	@Transactional
	@Modifying
	@Query("update User set name=?1 where id=?2")
	int updateName(String name, Long id);
	
	@Transactional
	@Modifying
	@Query("update User set email=?1, password=?2 where id=?3")
	int updateEmailPassword(String name, String password, Long id);
}
