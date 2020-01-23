package com.gym.tracker.service;

import java.util.List;

import com.gym.tracker.dto.ExerciseDTO;
import com.gym.tracker.model.Exercise;

public interface IExerciseService {

	ExerciseDTO addExerciseEntry(Exercise exerciseDto);

	List<ExerciseDTO> getExercises();

	ExerciseDTO updateExercise(Exercise exercise);

	void removeExercise(String id);

	boolean isPresent(String id);
}
