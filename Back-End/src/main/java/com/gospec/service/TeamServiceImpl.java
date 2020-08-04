package com.gospec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gospec.domain.BoardTeamDto;
import com.gospec.domain.TeamApprovalDto;
import com.gospec.mapper.TeamMapper;

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamMapper teamMapper;
	
	@Override
	public List<BoardTeamDto> findAll(String username) {
		return teamMapper.findAll(username);
	}

	@Override
	public boolean saveTeamApproval(TeamApprovalDto teamApproval) {
		if(teamMapper.saveTeamApproval(teamApproval) > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean updateTeamApproval(TeamApprovalDto teamApproval) {
		if(teamMapper.updateTeamApproval(teamApproval) > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteTeamApproval(TeamApprovalDto teamApproval) {
		if(teamMapper.deleteTeamApproval(teamApproval) > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<TeamApprovalDto> findTeamById(String teamId) {
		return teamMapper.findTeamById(teamId);
	}
	
	
	

}
