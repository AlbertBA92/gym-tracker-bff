package com.gym.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationBeanDTO {

	private String message;

	@Override
	public String toString() {
		return String.format("HelloWorldBean [message=%s]", message);
	}
}
