package com.gospec.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gospec.domain.BoardTeamDto;

public interface BoardTeamService {

	public List<BoardTeamDto> findByContestNo(int contestNo, int startIndex, int perPageNum);
	public BoardTeamDto viewDetails(int contestNo);
	public int save(BoardTeamDto dto);
	public int update(BoardTeamDto dto);
	public int delte(BoardTeamDto dto);
	public int getCountByContestNo(int contestNo);
}
