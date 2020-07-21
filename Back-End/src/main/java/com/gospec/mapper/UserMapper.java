package com.gospec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gospec.domain.UserDto;

@Mapper
public interface UserMapper {

	public UserDto findByUsername(String username);
	public List<UserDto> findAll();
	public void save(UserDto dto);
}
