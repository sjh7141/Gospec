package com.gospec.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.gospec.domain.ActiveRegionDto;
import com.gospec.domain.BookMarkDto;
import com.gospec.domain.CareerDto;
import com.gospec.domain.InterestFieldDto;
import com.gospec.domain.LicenseDto;
import com.gospec.domain.UserDto;
import com.gospec.security.GoUserDetailsService;
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
	
	@ApiOperation(value = "새로운 사용자 정보를 입력한다. user, fields객체 입력", response = UserDto.class)
	@PostMapping
	public ResponseEntity<Boolean> save(@RequestBody Map<String, Object> param){
		Gson gson = new Gson();
		UserDto user = gson.fromJson(gson.toJson(param.get("user")), UserDto.class);
		boolean check = userService.save(user);
		
		List<String> fields = (List<String>) param.get("fields");
		if(fields.size() > 0) {
			List<InterestFieldDto> interestList = new ArrayList<InterestFieldDto>();
			for(String field : fields) {
				interestList.add(new InterestFieldDto(field,user.getUsername()));
			}
			userService.saveInterestField(interestList);
		}
		
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
	
	@ApiOperation(value = "사용자 정보조회, 해당 아이디 정보 및 프로필 조회", response = UserDto.class)
	@GetMapping
	public ResponseEntity<UserDto> findInfo(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserDto user = userService.findByUsername(username);
		user.setActiveRegionList(userService.findAllActiveRegion(username));
		user.setInterestFieldList(userService.findAllInterestField(username));
		user.setLicenseList(userService.findAllLicense(username));
		user.setCareerList(userService.findAllCareer(username));
		
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
	
	@ApiOperation(value = "내정보수정, 해당 아이디 정보 및 프로필 수정, 헤더내용과 불일치시 403에러, user,fields,regions,licenses,careers 객체 사용가능", response = Boolean.class)
	@PatchMapping
	public ResponseEntity<Boolean> updateInfo(@RequestBody Map<String, Object> param){
		String headername = SecurityContextHolder.getContext().getAuthentication().getName();
		Gson gson = new Gson();
		UserDto user = gson.fromJson(gson.toJson(param.get("user")), UserDto.class);
		String type = (String)param.get("type");
		String username = user.getUsername();
		
		if(!headername.equals(username)) {
			return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
		}
		
		List<String> fields = (List<String>) param.get("fields");
		if(fields != null && fields.size() > 0) {
			List<InterestFieldDto> interestList = new ArrayList<InterestFieldDto>();
			for(String field : fields) {
				interestList.add(new InterestFieldDto(field,username));
			}
			userService.deleteInterestField(username);
			userService.saveInterestField(interestList);
		}
		
		if(type.equals("profile")){
			List<String> regions = (List<String>) param.get("regions");
			if(regions != null && regions.size() > 0) {
				List<ActiveRegionDto> regionList = new ArrayList<ActiveRegionDto>();
				for(String region : regions) {
					regionList.add(new ActiveRegionDto(region, username));
				}
				userService.deleteActiveRegion(username);
				userService.saveActiveRegion(regionList);
			}
			
			List<String> licenses = (List<String>) param.get("licenses");
			if(licenses != null && licenses.size() > 0) {
				List<LicenseDto> licenseList = new ArrayList<LicenseDto>();
				for(String license : licenses) {
					licenseList.add(new LicenseDto(license, username));
				}
				userService.deleteLicense(username);
				userService.saveLicense(licenseList);
			}
			
			System.out.println(param.get("careers"));
			List<Object> careers = (List<Object>) param.get("careers");
			List<CareerDto> careerList = new ArrayList<CareerDto>();
			for(Object str : careers) {
				careerList.add(gson.fromJson(gson.toJson(str), CareerDto.class));
				careerList.get(careerList.size()-1).setUsername(username);
			}
			if(careerList != null && careerList.size() > 0) {
				userService.deleteCareer(username);
				userService.saveCareer(careerList);
			}
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
	
	@ApiOperation(value = "북마크 조회, 헤더의 사용자 아이디로 북마크를 전체 조회한다.", response = BookMarkDto.class)
	@GetMapping(value ="/bookmarks")
	public ResponseEntity<List<BookMarkDto>> getBookMarkList(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseEntity<List<BookMarkDto>>(userService.findAllBookMark(username), HttpStatus.OK);
	}
	
	@ApiOperation(value = "인증 이메일 전송, 입련된 아이디로 이메일 전송", response = String.class)
	@GetMapping(value ="/email-authentication/{username}")
	public ResponseEntity<String> sendEmail(@PathVariable("username") String username){
		return new ResponseEntity<String>(mailService.sendMail(username), HttpStatus.OK);
	}
	
}
