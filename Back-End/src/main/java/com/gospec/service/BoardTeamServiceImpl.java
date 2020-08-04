package com.gospec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gospec.domain.BoardTeamDto;
import com.gospec.mapper.BoardTeamMapper;
import com.gospec.mapper.TeamMapper;

@Service
public class BoardTeamServiceImpl implements BoardTeamService {

	@Autowired
	private BoardTeamMapper boardTeamMapper;
	
	@Autowired
	private TeamMapper teamMapper;
	
	@Override
	public List<BoardTeamDto> findByContestNo(int contestNo, int startIndex, int perPageNum) {
		List<BoardTeamDto> boardTeamList = boardTeamMapper.findByContestNo(contestNo, startIndex, perPageNum);
		for(BoardTeamDto boardTeam :boardTeamList) {
			boardTeam.setApprovalList(teamMapper.findTeamById(boardTeam.getTeamId()));
		}
		return boardTeamList;
	}

	@Override
	public BoardTeamDto viewDetails(int postNo) {
		BoardTeamDto boardTeam = boardTeamMapper.viewDetails(postNo);
		boardTeam.setApprovalList(teamMapper.findTeamById(boardTeam.getTeamId()));
		return boardTeam;
	}

	@Override
	public int save(BoardTeamDto dto) {
		return boardTeamMapper.save(dto);
	}

	@Override
	public int update(BoardTeamDto dto) {
		return boardTeamMapper.update(dto);
	}

	@Override
	public int delete(int postNo) {
		return boardTeamMapper.delete(postNo);
	}

	@Override
	public int getCountByContestNo(int contestNo) {
		return boardTeamMapper.getCountByContestNo(contestNo);
	}

}
