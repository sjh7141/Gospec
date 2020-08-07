package com.gospec.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionControllerAdvice {
	
	@ExceptionHandler(BadCredentialsException.class)
	public String ex(Exception e) {
		System.out.println("ㅇㅇㅇ");
		return e.getMessage();
	}

}
