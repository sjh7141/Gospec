package com.gospec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gospec.domain.TeamApprovalDto;
import com.gospec.domain.TeamDto;
import com.gospec.domain.UserDto;
import com.gospec.mapper.TeamMapper;

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamMapper teamMapper;
	
	@Override
	public List<TeamDto> findAll() {
		return teamMapper.findAll();
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
	
	@Override
	public List<UserDto> recommandByAuto(String username) {
		return teamMapper.recommandByAuto(username);
	}
	

}
