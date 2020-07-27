package com.gospec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gospec.domain.ActiveRegionDto;
import com.gospec.domain.BookMarkDto;
import com.gospec.domain.InterestFieldDto;
import com.gospec.domain.TeamApprovalDto;
import com.gospec.domain.TeamDto;
import com.gospec.domain.UserDto;
import com.gospec.security.GoUserDetailsService;
import com.gospec.service.TeamServiceImpl;

import io.swagger.annotations.ApiOperation;


//http://localhost:8181/swagger-ui.html
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api/teams")
public class TeamController {

	@Autowired
	private TeamServiceImpl teamservice;
	
	@ApiOperation(value = "팀 정보 조회, 사용자 아이디로 존재하는 팀게시글을 전체 조회한다.", response = Boolean.class)
	@GetMapping(value="/{username}")
	public ResponseEntity<List<TeamDto>> getTeamList(@PathVariable("username") String username){
		return new ResponseEntity<List<TeamDto>>(teamservice.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "팀 승인 정보 등록, 팀 아이디, 해당 사용자들에 대해 등록", response = Boolean.class)
	@PostMapping
	public ResponseEntity<Boolean> saveTeamApproval(TeamApprovalDto teamApproval){
		return new ResponseEntity<Boolean>(teamservice.saveTeamApproval(teamApproval), HttpStatus.OK);
	}
	
	@ApiOperation(value = "팀 승인 정보 수정, 팀 아이디, 해당 사용자들에 대해 수정", response = Boolean.class)
	@PatchMapping
	public ResponseEntity<Boolean> updateTeamApproval(TeamApprovalDto teamApproval){
		return new ResponseEntity<Boolean>(teamservice.updateTeamApproval(teamApproval), HttpStatus.OK);
	}
	
	@ApiOperation(value = "팀 승인 정보 삭제, 팀 아이디, 해당 사용자들에 대해 삭제", response = Boolean.class)
	@DeleteMapping
	public ResponseEntity<Boolean> deleteTeamApproval(TeamApprovalDto teamApproval){
		return new ResponseEntity<Boolean>(teamservice.deleteTeamApproval(teamApproval), HttpStatus.OK);
	}
	
	@ApiOperation(value = "팀 승인 정보 조회, 팀 아이디에 해당하는 승인 정보를 조회", response = TeamApprovalDto.class)
	@GetMapping(value="/approval/{teamId}")
	public ResponseEntity<List<TeamApprovalDto>> getTeamApprovalList(@PathVariable("teamId") String teamId){
		return new ResponseEntity<List<TeamApprovalDto>>(teamservice.findTeamById(teamId), HttpStatus.OK);
	}

}
