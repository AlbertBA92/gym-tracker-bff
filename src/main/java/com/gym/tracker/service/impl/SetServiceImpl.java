package com.gym.tracker.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gym.tracker.dto.SetDTO;
import com.gym.tracker.exception.EntityNotFoundException;
import com.gym.tracker.model.Set;
import com.gym.tracker.repository.SetRepo;
import com.gym.tracker.service.ISetService;

@Component
public class SetServiceImpl implements ISetService {

	@Autowired
	private SetRepo setRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public SetDTO updateSet(Set set) {
		Optional<Set> findById = setRepo.findById(set.getId());
		if (findById.isPresent()) {
			return modelMapper.map(setRepo.save(set), SetDTO.class);
		} else {
			throw new EntityNotFoundException(set.getId());
		}
	}

	@Override
	public void removeSet(String id) {
		setRepo.deleteById(id);

	}

	@Override
	public boolean isPresent(String id) {
		return setRepo.existsById(id);
	}

	@Override
	public SetDTO getById(String id) {
		if (isPresent(id)) {
			return modelMapper.map(setRepo.findOneById(id), SetDTO.class);
		} else {
			throw new EntityNotFoundException(id);
		}
	}

	@Override
	public SetDTO addSet(Set set) {
		return modelMapper.map(setRepo.save(set), SetDTO.class);
	}

}
