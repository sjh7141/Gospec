package com.gospec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gospec.domain.ContestDto;
import com.gospec.service.ContestService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api/contest")
public class ContestController {

	@Autowired
	private ContestService contestService;
	
	@ApiOperation(value = "공모전 정보 가져오기")
	@GetMapping(value = "/")
	public ResponseEntity<List<ContestDto>> getContestList(){
		List<ContestDto> list = contestService.findAll();
		return new ResponseEntity<List<ContestDto>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공모전 특정 날짜 정보 가져오기")
	@GetMapping(value = "/{startDate}/{endDate}")
	public ResponseEntity<List<ContestDto>> getContestDurationList(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate){
		List<ContestDto> list = contestService.findDurationAll(startDate, endDate);
		return new ResponseEntity<List<ContestDto>>(list, HttpStatus.OK);
	}
}
