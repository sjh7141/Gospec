package com.gospec.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gospec.domain.UserDto;

@Mapper
public interface UserMapper {

	public UserDto findByUsername(String username);
}
