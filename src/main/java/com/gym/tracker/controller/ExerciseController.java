package com.gym.tracker.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gym.tracker.dto.ExerciseDTO;
import com.gym.tracker.model.Exercise;
import com.gym.tracker.service.IExerciseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {

	@Autowired
	private IExerciseService exerciseService;

	@Autowired
	private ModelMapper modelMapper;

	@ResponseBody
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ExerciseDTO createExercise(@RequestBody ExerciseDTO exercise) {
		Exercise map = modelMapper.map(exercise, Exercise.class);
		return exerciseService.addExerciseEntry(map);

	}

	@ResponseBody
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<ExerciseDTO> getAll() {
		return exerciseService.getExercises();
	}

}
