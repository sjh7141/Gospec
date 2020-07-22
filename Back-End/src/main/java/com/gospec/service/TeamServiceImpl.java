package com.gospec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gospec.domain.TeamDto;
import com.gospec.mapper.TeamMapper;

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamMapper teamMapper;
	
	@Override
	public List<TeamDto> findAll() {
		return teamMapper.findAll();
	}

}
