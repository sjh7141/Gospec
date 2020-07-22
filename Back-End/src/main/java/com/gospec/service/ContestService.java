package com.gospec.service;

import java.util.List;

import com.gospec.domain.ContestDto;

public interface ContestService {

	public List<ContestDto> findAll();
	public List<ContestDto> findDurationAll(String startDate, String endDate);
}
