package com.gospec.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gospec.domain.ContestDto;

public interface ContestService {

	public ContestDto findDetail(int contestNo);
	public List<ContestDto> findDurationAll(String startDate, String endDate);
	public List<ContestDto> findByBookMarkWithDurationAndEmail(String email, String startDate, String endDate);
	public List<ContestDto> findByCategory(String type, String mode, int startIndex, int perPageNum);
	public List<ContestDto> findByBestView(String type);
	public int getCountByCategory(String type, String mode);
	public List<String> teamSearchByContest(int contestNo);
	public int registBookMark(String username,int contestNo);
	public List<String> findCategoryByContestNo(int contestNo);
	public int deleteBookMark(String username, int contestNo);
	public int checkBookMark(String username, @Param("contestNo") int contestNo);
	public void updateViewCount(int contestNo);
}
