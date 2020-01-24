package com.gym.tracker.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gym.tracker.dto.ExerciseDTO;
import com.gym.tracker.exception.EntityNotFoundException;
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

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handleException(EntityNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	@ResponseBody
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ExerciseDTO> createExercise(@RequestBody ExerciseDTO exercise) {
		Exercise map = modelMapper.map(exercise, Exercise.class);
		return ResponseEntity.ok(exerciseService.addExerciseEntry(map));

	}

	@ResponseBody
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ExerciseDTO> updateExercise(@PathVariable String id, @RequestBody ExerciseDTO exercise) {
		Exercise map = modelMapper.map(exercise, Exercise.class);
		if (exerciseService.isPresent(id)) {
			return ResponseEntity.ok(exerciseService.updateExercise(map));
		} else {
			throw new EntityNotFoundException(id);
		}
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity removeExercise(@PathVariable(required = true, value = "id") String id) {
		if (exerciseService.isPresent(id)) {
			exerciseService.removeExercise(id);
			return ResponseEntity.ok().build();
		} else {
			throw new EntityNotFoundException(id);
		}
	}

	@ResponseBody
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ExerciseDTO>> getAll() {
		return ResponseEntity.ok(exerciseService.getExercises());
	}

	@ResponseBody
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ExerciseDTO> getById(@PathVariable(required = true, value = "id") String id) {
		return ResponseEntity.ok(exerciseService.getById(id));
	}

}
