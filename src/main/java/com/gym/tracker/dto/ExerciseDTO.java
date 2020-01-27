package com.gym.tracker.dto;

import java.util.List;

import lombok.Data;

@Data
public class ExerciseDTO {

	private String id;
	private String name;
	private List<SetDTO> sets;
	private String lastIncrease;

}
