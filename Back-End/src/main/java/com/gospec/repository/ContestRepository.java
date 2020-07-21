package com.gospec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gospec.domain.ContestDto;

@Repository
public interface ContestRepository extends JpaRepository<ContestDto, Integer> {
	
	public List<ContestDto> findByStartDateBetween(String startDate, String endDate);
}
