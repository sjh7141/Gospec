package com.gospec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gospec.domain.TeamApprovalDto;
import com.gospec.domain.TeamDto;
import com.gospec.domain.UserDto;

@Mapper
public interface TeamMapper {

	public List<TeamDto> findAll();
	public int saveTeamApproval(TeamApprovalDto teamApproval);
	public int updateTeamApproval(TeamApprovalDto teamApproval);
	public int deleteTeamApproval(TeamApprovalDto teamApproval);
	public List<TeamApprovalDto> findTeamById(String team_id);
	public List<UserDto> recommandByAuto(String username);
	
}
