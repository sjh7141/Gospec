package com.gospec.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gospec.domain.ContestDto;
import com.gospec.domain.PageDto;
import com.gospec.service.ContestService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/contest")
public class ContestController {

	@Autowired
	private ContestService contestService;

	@ApiOperation(value = "공모전 정보 가져오기")
	@GetMapping(value = "/")
	public ResponseEntity<List<ContestDto>> getContestList() {
		List<ContestDto> list = contestService.findAll();
		return new ResponseEntity<List<ContestDto>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "공모전 특정 날짜 정보 가져오기")
	@GetMapping(value = "/{startDate}/{endDate}") 
	public ResponseEntity<List<ContestDto>> getContestDurationList(@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate) {
		List<ContestDto> list = contestService.findDurationAll(startDate, endDate);
		return new ResponseEntity<List<ContestDto>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "유저별 북마크된 공모전 가져오기") 
	@GetMapping(value = "/bookmark/{email}/{startDate}/{endDate}")
	public ResponseEntity<List<ContestDto>> getBookMarkOfUser(@PathVariable("email") String email,
			@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
		List<ContestDto> list = contestService.findByBookMarkWithDurationAndEmail(email, startDate, endDate);
		return new ResponseEntity<List<ContestDto>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "카테고리 분류 전체 데에터 가져오기")
	@GetMapping(value = "/field/{type}/{page}")
	public ResponseEntity<Map<String, Object>> getContestCategoryList(@PathVariable("type") String type,
			@PathVariable("page") int curPage) {
		PageDto page = new PageDto(curPage);
		page.setTotalCount(contestService.getCountByCategory(type));
		List<ContestDto> list = contestService.findByCategory(type, page.getStartPage(), page.getPerPageNum());

		Map<String, Object> data = new HashMap<>();
		data.put("list", list);
		data.put("page", page);
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}
}
