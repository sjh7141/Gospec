package com.gospec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gospec.domain.BoardTeamDto;

@Mapper
public interface BoardTeamMapper {

	public List<BoardTeamDto> findByContestNo(int contestNo, int startIndex, int perPageNum);
	public BoardTeamDto viewDetails(int contestNo);
	public int save(BoardTeamDto dto);
	public int update(BoardTeamDto dto);
	public int delte(BoardTeamDto dto);
	public int getCountByContestNo(@Param("contestNo") int contestNo);
}
