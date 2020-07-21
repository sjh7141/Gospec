package com.gospec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gospec.domain.ContestDto;
import com.gospec.mapper.ContestMapper;

@Service
public class ContestServiceImpl implements ContestService {

	@Autowired
	private ContestMapper contestMapper;

	@Override
	public List<ContestDto> findAll() {
		return contestMapper.findAll();
	}

	@Override
	public List<ContestDto> findDurationAll(String startDate, String endDate) {
		return contestMapper.findByDuration(startDate, endDate);
	}
}
