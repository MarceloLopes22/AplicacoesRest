package com.claro.mobile.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.claro.mobile.api.entity.Mobile;

public interface MobileRepository extends MongoRepository<Mobile, String> {

	Mobile findByCode(String code);
}
