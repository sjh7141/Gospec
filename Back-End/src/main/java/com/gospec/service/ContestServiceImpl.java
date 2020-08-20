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
	public List<ContestDto> findByCategory(String type, String mode, int startIndex, int perPageNum) {
		return contestMapper.findByCategory(type, mode, startIndex, perPageNum);
	}
	
	@Override
	public List<ContestDto> findByBestView(String type) {
		return contestMapper.findByBestView(type);
	}
	
	@Override
	public List<ContestDto> findByBestBookMark(String type) {
		return contestMapper.findByBestBookMark(type);
	}
	
	@Override
	public List<String> teamSearchByContest(int contestNo) {
		return contestMapper.teamSearchByContest(contestNo);
	}

	@Override
	public int getCountByCategory(String type, String mode) {
		return contestMapper.getCountByCategory(type, mode);
	}
	
	@Override
	public int registBookMark(String username, int contestNo) {
		return contestMapper.registBookMark(username, contestNo);
	}
	
	@Override
	public List<String> findCategoryByContestNo(int contestNo) {
		return contestMapper.findCategoryByContestNo(contestNo);
	}
	
	@Override
	public int deleteBookMark(String username, int contestNo) {
		return contestMapper.deleteBookMark(username, contestNo);
	}
	
	@Override
	public int checkBookMark(String username, int contestNo) {
		return contestMapper.checkBookMark(username, contestNo);
	}
	
	@Override
	public void updateViewCount(int contestNo) {
		contestMapper.updateViewCount(contestNo);
	}
	
	@Override
	public List<ContestDto> findByCategoryWithCalendar(String type) {
		return contestMapper.findByCategoryWithCalendar(type);
	}
	
	@Override
	public List<ContestDto> findByBookMarkWithField(String username, String type) {
		return contestMapper.findByBookMarkWithField(username, type);
	}
}
