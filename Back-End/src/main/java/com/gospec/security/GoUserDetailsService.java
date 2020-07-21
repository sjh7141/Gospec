package com.gospec.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gospec.domain.UserDto;
import com.gospec.mapper.UserMapper;
import com.gospec.repository.UserRepository;

@Service
public class GoUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BCryptPasswordEncoder pwEncoding;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto user = userMapper.findByUsername(username);
		GoUserDetails principal = new GoUserDetails(user);
		return principal;
	}
	
	public List<UserDto> findAll(){
		return userRepo.findAll();
	}
	
	public void save(UserDto user) {
		user.setPassword(pwEncoding.encode(user.getPassword()));
		userRepo.save(user);
	}
}
