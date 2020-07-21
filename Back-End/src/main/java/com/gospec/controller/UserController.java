package com.gospec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gospec.domain.UserDto;
import com.gospec.security.GoUserDetailsService;

import io.swagger.annotations.ApiOperation;


//http://localhost:8181/swagger-ui.html
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private GoUserDetailsService userService;

	@ApiOperation(value = "테스트")
	@GetMapping("/test")
	public String test() {
		userService.findAll().forEach(el -> System.out.println(el.getUsername()));
		return userService.findAll().get(0).getUsername();
	}

	@GetMapping("/reg")
	public String reg() {
		UserDto user = new UserDto();
		user.setUsername("test");
		user.setPassword("test");
		user.setNickname("tester");
		user.setName("test");
		user.setAuthority("USER_ROLE");
		userService.save(user);

		return user.getPassword();
	}

}
