package com.gym.tracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Set")
public class Set {

	@Id
	private String id;
	private String reps;
	private String weight;
}
