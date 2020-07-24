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

import io.swagger.annotations.ApiOperation;


//http://localhost:8181/swagger-ui.html
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private GoUserDetailsService userService;
	
	@Autowired
	private FileUploadDownloadService fileService;

	@ApiOperation(value = "이메일 중복을 확인하다. true : 중복, false: 존재하지않음", response = Boolean.class)
	@PostMapping(value = "/email-duplication")
	public ResponseEntity<Boolean> checkEmail(@RequestBody Map<String, Object> param){
		String username = (String) param.get("username");
		boolean check = userService.checkId(username);
		return new ResponseEntity<Boolean>(check,HttpStatus.OK);
	}
	
	@ApiOperation(value = "닉네임 중복을 확인하다. true : 중복, false: 존재하지않음", response = Boolean.class)
	@PostMapping(value = "/nickname-duplication")
	public ResponseEntity<Boolean> checkNickName(@RequestBody Map<String, Object> param){
		String nickname = (String) param.get("nickname");
		boolean check = userService.checkNickName(nickname);
		return new ResponseEntity<Boolean>(check,HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 사용자 정보를 입력한다.", response = UserDto.class)
	@PostMapping(value = "/")
	public ResponseEntity<Boolean> save(@RequestBody UserDto user){
		boolean check = userService.save(user);
		return new ResponseEntity<Boolean>(check, HttpStatus.OK);
	}
	
	@ApiOperation(value = "비밀번호찾기, 새로운 비밀번호로 수정한다.", response = Boolean.class)
	@PostMapping(value = "/password")
	public ResponseEntity<Boolean> findpwd(@RequestBody Map<String, Object> param){
		String username = (String) param.get("username");
		String password = (String) param.get("password");
		boolean check = userService.newPwd(username, password);
		return new ResponseEntity<Boolean>(check,HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자 정보조회, 해당 아이디 정보조회", response = UserDto.class)
	@GetMapping(value = "/{username}")
	public ResponseEntity<UserDto> findInfo(@PathVariable String username){
		UserDto user = userService.findByUsername(username);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
	
	@ApiOperation(value = "내정보수정, 해당 아이디 정보 수정", response = Boolean.class)
	@PatchMapping(value = "/")
	public ResponseEntity<Boolean> updateInfo(@RequestBody UserDto user){
		boolean check = userService.updateByUsername(user);
		return new ResponseEntity<Boolean>(check,HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원탈퇴, 해당 아이디 계정 탈퇴", response = Boolean.class)
	@DeleteMapping(value = "/")
	public ResponseEntity<Boolean> deleteInfo(@RequestBody Map<String, Object> param){
		String username = (String) param.get("username");
		boolean check = userService.deleteByUsername(username);
		return new ResponseEntity<Boolean>(check,HttpStatus.OK);
	}
	
	@ApiOperation(value = "활동지역 조회, 사용자 아이디로 활동지역을 전체 조회한다.", response = ActiveRegionDto.class)
	@GetMapping(value ="/regions/{username}")
	public ResponseEntity<List<ActiveRegionDto>> getActiveRegionList(@PathVariable("username") String username){
		return new ResponseEntity<List<ActiveRegionDto>>(userService.findAllActiveRegion(username), HttpStatus.OK);
	}
	
	@ApiOperation(value = "북마크 조회, 사용자 아이디로 북마크를 전체 조회한다.", response = BookMarkDto.class)
	@GetMapping(value ="/bookmarks/{username}")
	public ResponseEntity<List<BookMarkDto>> getBookMarkList(@PathVariable("username") String username){
		return new ResponseEntity<List<BookMarkDto>>(userService.findAllBookMark(username), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "관심지역 조회, 사용자 아이디로 관심영역을 전체 조회한다.", response = InterestFieldDto.class)
	@GetMapping(value ="/fields/{username}")
	public ResponseEntity<List<InterestFieldDto>> getInterestFieldList(@PathVariable("username") String username){
		return new ResponseEntity<List<InterestFieldDto>>(userService.findAllInterestField(username), HttpStatus.OK);
	}
	
	@ApiOperation(value = "인증 이메일 전송, 입련된 아이디로 이메일 전송", response = String.class)
	@GetMapping(value ="/email-authentication/{username}")
	public ResponseEntity<String> sendEmail(@PathVariable("username") String username){
		System.out.println(username);
		return new ResponseEntity<String>(userService.sendMail(username), HttpStatus.OK);
	}
	
	@ApiOperation(value = "파일업로드, fileDownloadUri정보로 다운로드 가능하다.", response = FileUploadResponse.class)
	@PostMapping(value ="/file")
	public ResponseEntity<FileUploadResponse> uploadfile(@RequestParam("file") MultipartFile file) {
		String fileName = fileService.storeFile(file);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
								.path("/api/users")
								.path("/file/")
								.path(fileName)
								.toUriString();
		return new ResponseEntity<FileUploadResponse>(new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()), HttpStatus.OK);
	}
	
	@ApiOperation(value = "파일다운로드, db의 profile_img로 요청", response = FileUploadResponse.class)
	@GetMapping("/file/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        Resource resource = fileService.loadFileasResource(fileName);

        String contentType = null;
        try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}      
 
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
 
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
