package com.gospec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gospec.domain.BoardStudyDto;

@Mapper
public interface BoardStudyMapper {

	public List<BoardStudyDto> findByRegion(String region, int startIndex, int perPageNum);
	public BoardStudyDto viewDetails(int postNo);
	public int save(BoardStudyDto dto);
	public int update(BoardStudyDto dto);
	public int delete(int postNo);
}
