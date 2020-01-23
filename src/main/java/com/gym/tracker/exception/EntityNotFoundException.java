package com.gym.tracker.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5485307193906303626L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
