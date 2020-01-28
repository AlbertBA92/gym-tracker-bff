package com.gym.tracker.service;

import com.gym.tracker.dto.SetDTO;
import com.gym.tracker.model.Set;

public interface ISetService {

	SetDTO addSet(Set set);

	SetDTO updateSet(Set set);

	SetDTO getById(String id);

	void removeSet(String id);

	boolean isPresent(String id);

}
