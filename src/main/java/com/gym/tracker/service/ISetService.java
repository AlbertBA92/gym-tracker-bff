package com.gym.tracker.service;

import com.gym.tracker.dto.SetDTO;
import com.gym.tracker.model.Set;

public interface ISetService {

	SetDTO updateSet(Set set);

	void removeSet(String id);

	boolean isPresent(String id);

}
