package com.gym.tracker.controller;

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

import com.gym.tracker.dto.SetDTO;
import com.gym.tracker.exception.EntityNotFoundException;
import com.gym.tracker.model.Set;
import com.gym.tracker.service.ISetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/set")
@RequiredArgsConstructor
public class SetController {

	@Autowired
	private ISetService setService;

	@Autowired
	private ModelMapper modelMapper;

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handleException(EntityNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@ResponseBody
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SetDTO> getById(@PathVariable(required = true, value = "id") String id) {
		return ResponseEntity.ok(setService.getById(id));
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity removeExercise(@PathVariable(required = true, value = "id") String id) {
		if (setService.isPresent(id)) {
			setService.removeSet(id);
			return ResponseEntity.ok().build();
		} else {
			throw new EntityNotFoundException(id);
		}
	}

	@ResponseBody
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SetDTO> addExercise(@RequestBody SetDTO exercise) {
		return ResponseEntity.ok(setService.updateSet(modelMapper.map(exercise, Set.class)));
	}

	@ResponseBody
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SetDTO> updateExercise(@PathVariable String id, @RequestBody SetDTO exercise) {
		Set map = modelMapper.map(exercise, Set.class);
		if (setService.isPresent(id)) {
			return ResponseEntity.ok(setService.updateSet(map));
		} else {
			throw new EntityNotFoundException(id);
		}
	}
}
