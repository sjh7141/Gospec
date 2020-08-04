package com.gospec.service;

import java.util.List;

import com.gospec.domain.BoardTeamDto;
import com.gospec.domain.TeamApprovalDto;

public interface TeamService {

		public List<BoardTeamDto> findAll(String username);
		
		public boolean saveTeamApproval(TeamApprovalDto teamApproval);
		
		public boolean updateTeamApproval(TeamApprovalDto teamApproval);
		
		public boolean deleteTeamApproval(TeamApprovalDto teamApproval);
		
		public List<TeamApprovalDto> findTeamById(String teamId);
}
