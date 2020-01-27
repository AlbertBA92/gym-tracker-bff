package com.gym.tracker.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gym.tracker.model.Exercise;

public interface ExerciseRepo extends MongoRepository<Exercise, String> {

	List<Exercise> findAll();

	void deleteAll();

}
