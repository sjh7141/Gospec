package com.gospec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gospec.domain.ContestDto;

@Mapper
public interface ContestMapper {

	public List<ContestDto> findByDurationAll(String startDate, String endDate);
	public List<ContestDto> findAll();
}
