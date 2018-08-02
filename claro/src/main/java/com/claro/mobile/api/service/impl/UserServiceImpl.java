package com.claro.mobile.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.claro.mobile.api.entity.User;
import com.claro.mobile.api.repositories.UserRepository;
import com.claro.mobile.api.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public User createOrUpdate(User user) {
		return repository.save(user);
	}

	@Override
	public User findById(String id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(String id) {
		repository.delete(id);
	}

	@Override
	public Page<User> findAll(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return repository.findAll(pages);
	}

}
