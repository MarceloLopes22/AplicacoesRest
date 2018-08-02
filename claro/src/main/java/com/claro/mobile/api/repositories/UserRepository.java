package com.claro.mobile.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.claro.mobile.api.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByEmail(String name);

	User findById(String id);
}
