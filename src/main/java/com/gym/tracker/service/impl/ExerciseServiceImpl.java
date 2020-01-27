package com.gym.tracker.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gym.tracker.dto.ExerciseDTO;
import com.gym.tracker.exception.EntityNotFoundException;
import com.gym.tracker.model.Exercise;
import com.gym.tracker.model.Set;
import com.gym.tracker.repository.ExerciseRepo;
import com.gym.tracker.repository.SetRepo;
import com.gym.tracker.service.IExerciseService;

@Component
public class ExerciseServiceImpl implements IExerciseService {

	@Autowired
	private ExerciseRepo exerciseRepo;

	@Autowired
	private SetRepo setRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ExerciseDTO addExerciseEntry(Exercise exercise) {
		return modelMapper.map(exerciseRepo.save(exercise), ExerciseDTO.class);
	}

	@Override
	public List<ExerciseDTO> getExercises() {
		return exerciseRepo.findAll().stream().map(x -> modelMapper.map(x, ExerciseDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ExerciseDTO updateExercise(Exercise exercise) {
		return modelMapper.map(exerciseRepo.save(exercise), ExerciseDTO.class);
	}

	@Override
	public void removeExercise(String id) {
		Optional<Exercise> findById = exerciseRepo.findById(id);
		if (findById.isPresent()) {
			Exercise exercise = findById.get();
			for (Set set : exercise.getSets()) {
				setRepo.delete(set);
			}
			exerciseRepo.deleteById(id);
		} else {
			throw new EntityNotFoundException(id);
		}
	}

	@Override
	public boolean isPresent(String id) {
		return exerciseRepo.findById(id).isPresent();
	}

	@Override
	public ExerciseDTO getById(String id) {
		Optional<Exercise> opcional = exerciseRepo.findById(id);
		if (opcional.isPresent()) {
			return modelMapper.map(opcional.get(), ExerciseDTO.class);
		} else {
			throw new EntityNotFoundException(id);
		}
	}

}
