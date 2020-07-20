package com.gospec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gospec.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/test")
	public String test() {
		userRepo.findAll().forEach(el -> System.out.println(el.getUsername()));
		return userRepo.findAll().get(0).getUsername();
	}
}
