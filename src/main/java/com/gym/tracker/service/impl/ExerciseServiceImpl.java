package com.gym.tracker.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gym.tracker.dto.ExerciseDTO;
import com.gym.tracker.model.Exercise;
import com.gym.tracker.repository.ExerciseRepo;
import com.gym.tracker.service.IExerciseService;

@Component
public class ExerciseServiceImpl implements IExerciseService {

	@Autowired
	private ExerciseRepo repo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ExerciseDTO addExerciseEntry(Exercise exercise) {
		return modelMapper.map(repo.save(exercise), ExerciseDTO.class);
	}

	@Override
	public List<ExerciseDTO> getExercises() {
		return repo.findAll().stream().map(x -> modelMapper.map(x, ExerciseDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ExerciseDTO updateExercise(Exercise exercise) {
		return modelMapper.map(repo.save(exercise), ExerciseDTO.class);
	}

	@Override
	public void removeExercise(String id) {
		repo.deleteById(id);
	}

	@Override
	public boolean isPresent(String id) {
		return repo.findById(id).isPresent();
	}

}
