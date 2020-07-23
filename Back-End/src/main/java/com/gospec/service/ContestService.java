package com.gospec.service;

import java.util.List;

import com.gospec.domain.ContestDto;

public interface ContestService {

	public List<ContestDto> findAll();
	public List<ContestDto> findDurationAll(String startDate, String endDate);
	public List<ContestDto> findByBookMarkWithDurationAndEmail(String email, String startDate, String endDate);
	public List<ContestDto> findByCategory(String type, int startPage, int perPageNum);
	public int getCountByCategory(String type);
}
