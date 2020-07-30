package com.gospec.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gospec.domain.ActiveRegionDto;
import com.gospec.domain.BookMarkDto;
import com.gospec.domain.InterestFieldDto;
import com.gospec.domain.TeamDto;
import com.gospec.domain.UserDto;
import com.gospec.property.FileUploadResponse;
import com.gospec.security.GoUserDetailsService;
import com.gospec.service.FileUploadDownloadService;
import com.gospec.service.MailAuthenticationService;

import io.swagger.annotations.ApiOperation;


//http://localhost:8181/swagger-ui.html
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private GoUserDetailsService userService;
	
	@Autowired
	private MailAuthenticationService mailService;

	@ApiOperation(value = "이메일 중복을 확인하다. true : 중복, false: 존재하지않음", response = Boolean.class)
	@GetMapping(value = "/email-duplication/{username}")
	public ResponseEntity<Boolean> checkEmail(@PathVariable String username){
		boolean check = userService.checkId(username);
		return new ResponseEntity<Boolean>(check,HttpStatus.OK);
	}
	
	@ApiOperation(value = "닉네임 중복을 확인하다. true : 중복, false: 존재하지않음", response = Boolean.class)
	@GetMapping(value = "/nickname-duplication/{nickname}")
	public ResponseEntity<Boolean> checkNickName(@PathVariable String nickname){
		boolean check = userService.checkNickName(nickname);
		return new ResponseEntity<Boolean>(check,HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 사용자 정보를 입력한다.", response = UserDto.class)
	@PostMapping
	public ResponseEntity<Boolean> save(@RequestBody UserDto user){
		boolean check = userService.save(user);
		return new ResponseEntity<Boolean>(check, HttpStatus.OK);
	}
	
	@ApiOperation(value = "비밀번호찾기, 새로운 비밀번호로 수정한다.", response = Boolean.class)
	@PatchMapping(value = "/password")
	public ResponseEntity<Boolean> findpwd(@RequestBody Map<String, Object> param){
		String username = (String) param.get("username");
		String password = (String) param.get("password");
		boolean check = userService.newPwd(username, password);
		return new ResponseEntity<Boolean>(check,HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자 정보조회, 해당 아이디 정보조회", response = UserDto.class)
	@GetMapping
	public ResponseEntity<UserDto> findInfo(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserDto user = userService.findByUsername(username);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
	
	@ApiOperation(value = "내정보수정, 해당 아이디 정보 수정, 헤더내용과 불일치시 403에러", response = Boolean.class)
	@PatchMapping
	public ResponseEntity<Boolean> updateInfo(@RequestBody UserDto user){
		String headername = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!headername.equals(user.getUsername())) {
			return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
		}
		boolean check = userService.updateByUsername(user);
		return new ResponseEntity<Boolean>(check,HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원탈퇴, 해더내의 해당 아이디 계정 탈퇴", response = Boolean.class)
	@DeleteMapping
	public ResponseEntity<Boolean> deleteInfo(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean check = userService.deleteByUsername(username);
		return new ResponseEntity<Boolean>(check,HttpStatus.OK);
	}
	
	@ApiOperation(value = "활동지역 조회, 헤더의 사용자 아이디로 활동지역을 전체 조회한다.", response = ActiveRegionDto.class)
	@GetMapping(value ="/regions")
	public ResponseEntity<List<ActiveRegionDto>> getActiveRegionList(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseEntity<List<ActiveRegionDto>>(userService.findAllActiveRegion(username), HttpStatus.OK);
	}
	
	@ApiOperation(value = "북마크 조회, 헤더의 사용자 아이디로 북마크를 전체 조회한다.", response = BookMarkDto.class)
	@GetMapping(value ="/bookmarks")
	public ResponseEntity<List<BookMarkDto>> getBookMarkList(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseEntity<List<BookMarkDto>>(userService.findAllBookMark(username), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "관심지역 조회, 헤더의 사용자 아이디로 관심영역을 전체 조회한다.", response = InterestFieldDto.class)
	@GetMapping(value ="/fields")
	public ResponseEntity<List<InterestFieldDto>> getInterestFieldList(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseEntity<List<InterestFieldDto>>(userService.findAllInterestField(username), HttpStatus.OK);
	}
	
	@ApiOperation(value = "인증 이메일 전송, 입련된 아이디로 이메일 전송", response = String.class)
	@GetMapping(value ="/email-authentication/{username}")
	public ResponseEntity<String> sendEmail(@PathVariable("username") String username){
		return new ResponseEntity<String>(mailService.sendMail(username), HttpStatus.OK);
	}

}
