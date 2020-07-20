package com.gospec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gospec.domain.UserDto;

@Repository
public interface UserRepository extends JpaRepository<UserDto, String> {
	UserDto findByUsername(String name);
}
