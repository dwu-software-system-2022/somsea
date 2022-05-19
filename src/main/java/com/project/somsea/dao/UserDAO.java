package com.project.somsea.dao;

import com.project.somsea.domain.User;

public interface UserDAO {
	boolean	existingUser(Long id);
	long add(User user);
	long update(User user);
	long delete(User user);
	User findUserByid(Long id);
	User findUserByName(String name);
}
