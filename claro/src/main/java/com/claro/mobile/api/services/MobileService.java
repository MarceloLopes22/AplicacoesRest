package com.claro.mobile.api.services;

import org.springframework.data.domain.Page;

import com.claro.mobile.api.entity.Mobile;

public interface MobileService {
	
	Mobile findByCode(String code);
	Mobile createOrUpdate(Mobile mobile);
	Mobile findById(String id);
	void delete(String id);
	Page<Mobile> findAll(int page, int count);
}
