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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gospec.domain.BoardTeamDto;
import com.gospec.domain.PageDto;
import com.gospec.service.BoardTeamService;
import com.gospec.service.TeamServiceImpl;

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
	
	@ApiOperation(value = "팀 구하기 글 등록")
	@PostMapping(value = "")
	public ResponseEntity<String> setBoardBeam(@RequestBody BoardTeamDto dto){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setUsername(username);
		dto.setTeamId(username + "_" + dto.getContestNo());
		int res = boardTeamService.save(dto);
		if(res == 0) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "팀구하기 글 상세 보기")
	@GetMapping(value = "/detail/{postNo}")
	public ResponseEntity<BoardTeamDto> getBoardTeamDetail(@PathVariable("postNo") int postNo){
		BoardTeamDto data = boardTeamService.viewDetails(postNo);
		return new ResponseEntity<BoardTeamDto>(data, HttpStatus.OK);
	}
	
	@ApiOperation(value = "팀 구하기 글 수정")
	@PatchMapping(value = "")
	public ResponseEntity<String> updateBoardTeam(@RequestBody BoardTeamDto dto){
		int res = boardTeamService.update(dto);
		if(res == 0) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "팀 구하기 글 삭제")
	@DeleteMapping(value = "")
	public ResponseEntity<String> deleteBoardTeam(@RequestBody Map<String, Object> param){
		String originUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		String username = (String)param.get("username");
		int postNo = (Integer)param.get("postNo");
		if(!originUsername.equals(username)) {
			return new ResponseEntity<String>("fail forbidden", HttpStatus.FORBIDDEN);
		}
		int res = boardTeamService.delete(postNo);
		if(res == 0) {
			return new ResponseEntity<String>("fail delete", HttpStatus.OK);
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
}
