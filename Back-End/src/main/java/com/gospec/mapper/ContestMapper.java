package com.gospec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gospec.domain.ContestDto;
import com.gospec.domain.UserDto;

@Mapper
public interface ContestMapper {

	public ContestDto findDetail(int contestNo);
	public List<ContestDto> findByDurationAll(String startDate, String endDate);
	public List<ContestDto> findByBookMarkWithDurationAndEmail(String email, String startDate, String endDate);
	public List<ContestDto> findByCategory(String type, int startPage, int perPageNum);
	public List<ContestDto> findByBestView(@Param("type") String type);
	public int getCountByCategory(@Param("type") String type);
	public List<String> teamSearchByContest(int contestNo);
	public int registBookMark(@Param("username") String username, @Param("contestNo") int contestNo);
	public List<String> findCategoryByContestNo(int contestNo);
}
