package com.gospec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gospec.domain.BoardStudyDto;
import com.gospec.mapper.BoardStudyMapper;

@Service
public class BoardStudyServiceImpl implements BoardStudyService {
	
	@Autowired
	private BoardStudyMapper boardStudyMapper; 
	
	@Override
	public List<BoardStudyDto> findByRegion(String region, int startIndex, int perPageNum) {
		return boardStudyMapper.findByRegion(region, startIndex, perPageNum);
	}

	@Override
	public BoardStudyDto viewDetails(int postNo) {
		return boardStudyMapper.viewDetails(postNo);
	}

	@Override
	public int save(BoardStudyDto dto) {
		return boardStudyMapper.save(dto);
	}

	@Override
	public int update(BoardStudyDto dto) {
		return boardStudyMapper.update(dto);
	}

	@Override
	public int delete(int postNo) {
		return boardStudyMapper.delete(postNo);
	}

}
