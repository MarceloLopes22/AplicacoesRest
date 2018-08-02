package com.claro.mobile.api.services;

import org.springframework.data.domain.Page;

import com.claro.mobile.api.entity.User;

public interface UserService {
	
	User findByEmail(String email);
	User createOrUpdate(User user);
	User findById(String id);
	void delete(String id);
	Page<User> findAll(int page, int count);
}
