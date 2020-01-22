package com.gym.tracker.dto;

import lombok.Data;

@Data
public class ExerciseDTO {

	private String id;
	private String name;
	private String weight;
	private String reps;
	private String series;
	private String lastIncrease;

}
