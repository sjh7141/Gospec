package com.gospec.service;

import java.util.List;

import com.gospec.domain.TeamApprovalDto;
import com.gospec.domain.TeamDto;
import com.gospec.domain.UserDto;

public interface TeamService {

		public List<TeamDto> findAll();
		
		public boolean saveTeamApproval(TeamApprovalDto teamApproval);
		
		public boolean updateTeamApproval(TeamApprovalDto teamApproval);
		
		public boolean deleteTeamApproval(TeamApprovalDto teamApproval);
		
		public List<TeamApprovalDto> findTeamById(String teamId);
		
		public List<UserDto> recommandByAuto(String username);
}
