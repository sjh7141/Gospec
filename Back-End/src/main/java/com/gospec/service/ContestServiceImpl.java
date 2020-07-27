package com.gospec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gospec.domain.ContestDto;
import com.gospec.domain.UserDto;
import com.gospec.mapper.ContestMapper;

@Service
public class ContestServiceImpl implements ContestService {

	@Autowired
	private ContestMapper contestMapper;

	@Override
	public ContestDto findDetail(int contestNo) {
		return contestMapper.findDetail(contestNo);
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
	public List<ContestDto> findByBestView(String type) {
		return contestMapper.findByBestView(type);
	}
	
	@Override
	public List<String> teamSearchByContest(int contestNo) {
		return contestMapper.teamSearchByContest(contestNo);
	}

	@Override
	public int getCountByCategory(String type) {
		return contestMapper.getCountByCategory(type);
	}
	
	@Override
	public int registBookMark(String username, int contestNo) {
		return contestMapper.registBookMark(username, contestNo);
	}
	
	@Override
	public List<String> findCategoryByContestNo(int contestNo) {
		return contestMapper.findCategoryByContestNo(contestNo);
	}
}
