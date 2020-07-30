package com.gospec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gospec.domain.ContestDto;

@Mapper
public interface ContestMapper {

	public ContestDto findDetail(int contestNo);
	public List<ContestDto> findByDurationAll(String startDate, String endDate);
	public List<ContestDto> findByBookMarkWithDurationAndEmail(String email, String startDate, String endDate);
	public List<ContestDto> findByCategory(@Param("type") String type, @Param("mode") String mode, int startIndex, int perPageNum);
	public List<ContestDto> findByBestView(@Param("type") String type);
	public int getCountByCategory(@Param("type") String type, @Param("mode") String mode);
	public List<String> teamSearchByContest(int contestNo);
	public int registBookMark(@Param("username") String username, @Param("contestNo") int contestNo);
	public int deleteBookMark(@Param("username") String username, @Param("contestNo") int contestNo);
	public int checkBookMark(@Param("username") String username, @Param("contestNo") int contestNo);
	public List<String> findCategoryByContestNo(int contestNo);
	public void updateViewCount(int contestNo);
}
