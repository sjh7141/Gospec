package com.gospec.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@GetMapping(value = "/{contestNo}")
	public ResponseEntity<Map<String, Object>> getContestDetail(@PathVariable("contestNo") int contestNo) {
		ContestDto contest = contestService.findDetail(contestNo);
		List<String> field = contestService.findCategoryByContestNo(contestNo);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("contest", contest);
		data.put("field", field);
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공모전 조회수 증가")
	@PatchMapping(value = "/{contestNo}")
	public void setViewCount(@PathVariable("contestNo") int contestNo) {
		contestService.updateViewCount(contestNo);
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
	@GetMapping(value = "/field/{type}/{mode}/{page}")
	public ResponseEntity<Map<String, Object>> getContestCategoryList(@PathVariable("type") String type,
			@PathVariable("mode") String mode,
			@PathVariable("page") int curPage) {
		PageDto page = new PageDto(curPage);
		page.setTotalCount(contestService.getCountByCategory(type, mode));
		List<ContestDto> list = contestService.findByCategory(type, mode, page.getStartIndex(), page.getPerPageNum());

		Map<String, Object> data = new HashMap<>();
		data.put("list", list);
		data.put("page", page);
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}

	@ApiOperation(value = "인기 공모전 3개 가져오기")
	@GetMapping(value = "/field/{type}")
	public ResponseEntity<List<ContestDto>> getContestBestList(@PathVariable("type") String type) {
		List<ContestDto> list = contestService.findByBestView(type);
		return new ResponseEntity<List<ContestDto>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "공모전으로 팀원찾기") 
	@GetMapping(value = "/team-search/{contestNo}")
	public ResponseEntity<List<String>> getTeamSearch(@PathVariable("contestNo") int contestNo) {
		List<String> list = contestService.teamSearchByContest(contestNo);
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공모전 북마크 하기")
	@PostMapping(value = "/bookmark")
	public ResponseEntity<Integer> setBookMark(@RequestBody	Map<String, Object> param){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		int contestNo = (Integer)param.get("contestNo");
		int temp = contestService.registBookMark(username, contestNo);
		return new ResponseEntity<Integer>(temp, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공모전 북마크 해제")
	@DeleteMapping(value = "/bookmark")
	public ResponseEntity<Integer> clearBookMark(@RequestBody Map<String, Object> param){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		int contestNo = (Integer)param.get("contestNo");
		int temp = contestService.deleteBookMark(username, contestNo);
		return new ResponseEntity<Integer>(temp, HttpStatus.OK);
	}
	
	@ApiOperation(value = "북마크 된지 안된지 확인")
	@GetMapping(value = "/check/{contestNo}")
	public ResponseEntity<Boolean> checkBookMark(@PathVariable("contestNo") String contestNo){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		int temp = contestService.checkBookMark(username, Integer.parseInt(contestNo));
		boolean res = temp == 0 ? false : true;
		return new ResponseEntity<Boolean>(res, HttpStatus.OK);
	}
}
