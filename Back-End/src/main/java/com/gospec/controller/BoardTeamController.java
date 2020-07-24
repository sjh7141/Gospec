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

import com.gospec.domain.BoardTeamDto;
import com.gospec.domain.PageDto;
import com.gospec.service.BoardTeamService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/board/teams")
public class BoardTeamController {

	@Autowired
	private BoardTeamService boardTeamService;

	@ApiOperation(value = "공모전에 대한 팀구하기 글 가져오기")
	@GetMapping(value = "/{contestNo}/{page}")
	public ResponseEntity<Map<String, Object>> getBoardTeam(@PathVariable("contestNo") int contestNo,
			@PathVariable("page") int curPage) {
		PageDto page = new PageDto(curPage);
		page.setTotalCount(boardTeamService.getCountByContestNo(contestNo));
		List<BoardTeamDto> list = boardTeamService.findByContestNo(contestNo, page.getStartIndex(), page.getPerPageNum());

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", list);
		data.put("page", page);
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}
}
