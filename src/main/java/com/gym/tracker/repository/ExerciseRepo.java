package com.gym.tracker.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gym.tracker.model.Exercise;

public interface ExerciseRepo extends MongoRepository<Exercise, String> {

	Exercise findOneById(String id);

	List<Exercise> findAll();

	void delete(Exercise exercise);

	void deleteAll();

}
