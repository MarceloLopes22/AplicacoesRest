package com.claro.mobile.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.claro.mobile.api.entity.Mobile;
import com.claro.mobile.api.repositories.MobileRepository;
import com.claro.mobile.api.services.MobileService;

@Service
public class MobileServiceImpl implements MobileService {

	@Autowired
	private MobileRepository repository;
	
	@Override
	public Mobile findByCode(String code) {
		return repository.findByCode(code);
	}

	@Override
	public Mobile createOrUpdate(Mobile mobile) {
		return repository.save(mobile);
	}

	@Override
	public Mobile findById(String id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(String id) {
		repository.delete(id);;
	}

	@Override
	public Page<Mobile> findAll(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return repository.findAll(pages);
	}

}
