package com.myant.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myant.model.Group;

public interface GroupRepository extends MongoRepository<Group, String>{
	Optional<Group> findByName(String name);
}
