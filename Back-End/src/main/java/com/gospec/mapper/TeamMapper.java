package com.gospec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gospec.domain.TeamDto;

@Mapper
public interface TeamMapper {

	public List<TeamDto> findAll();
}
