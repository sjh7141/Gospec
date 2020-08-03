package com.gospec.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gospec.domain.BoardStudyDto;
import com.gospec.domain.BoardTeamDto;

public interface BoardStudyService {

	public List<BoardStudyDto> findByRegion(String region, int startIndex, int perPageNum);
	public BoardStudyDto viewDetails(int postNo);
	public int save(BoardStudyDto dto);
	public int update(BoardStudyDto dto);
	public int delete(int postNo);
}
