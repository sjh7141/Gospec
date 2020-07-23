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
		return contestMapper.findByDurationAll(startDate, endDate);
	}

	@Override
	public List<ContestDto> findByBookMarkWithDurationAndEmail(String email, String startDate, String endDate) {
		return contestMapper.findByBookMarkWithDurationAndEmail(email, startDate, endDate);
	}

	@Override
	public List<ContestDto> findByCategory(String type, int startPage, int perPageNum) {
		return contestMapper.findByCategory(type, startPage, perPageNum);
	}

	@Override
	public int getCountByCategory(String type) {
		return contestMapper.getCountByCategory(type);
	}
}
