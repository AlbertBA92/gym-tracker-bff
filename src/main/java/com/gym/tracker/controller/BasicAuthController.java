package com.gym.tracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.tracker.dto.AuthenticationBeanDTO;

@RestController
@RequestMapping("/basicauth")
public class BasicAuthController {

	@GetMapping
	public AuthenticationBeanDTO basicauth() {
		return new AuthenticationBeanDTO("You are authenticated");
	}

}
