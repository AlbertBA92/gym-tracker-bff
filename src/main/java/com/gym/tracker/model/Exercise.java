package com.gym.tracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "exercises")
public class Exercise {

	@Id
	private String id;
	private String name;
	private String weight;
	private String reps;
	private String series;
	private String lastIncrease;

}
