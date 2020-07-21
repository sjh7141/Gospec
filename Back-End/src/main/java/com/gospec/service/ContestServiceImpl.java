package com.gospec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gospec.domain.ContestDto;
import com.gospec.repository.ContestRepository;

@Service
public class ContestServiceImpl implements ContestService {

	@Autowired
	private ContestRepository contestRepo;

	@Override
	public List<ContestDto> findAll() {
		return contestRepo.findAll();
	}

	@Override
	public List<ContestDto> findDurationAll(String startDate, String endDate) {
		return contestRepo.findByStartDateBetween(startDate, endDate);
	}
}
