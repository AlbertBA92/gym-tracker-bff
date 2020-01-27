package com.gym.tracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gym.tracker.model.Set;

public interface SetRepo extends MongoRepository<Set, String> {

	Set findOneById(String id);

	void delete(Set set);

}
