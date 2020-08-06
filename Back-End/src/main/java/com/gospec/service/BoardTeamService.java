package com.gospec.service;

import java.util.List;

import com.gospec.domain.BoardTeamDto;

public interface BoardTeamService {

	public List<BoardTeamDto> findByContestNo(int contestNo, int startIndex, int perPageNum);
	public BoardTeamDto viewDetails(int postNo);
	public int save(BoardTeamDto dto);
	public int update(BoardTeamDto dto);
	public int delete(int postNo);
	public int getCountByContestNo(int contestNo);
	public boolean checkWriteBoard(String teamId);
	
}
