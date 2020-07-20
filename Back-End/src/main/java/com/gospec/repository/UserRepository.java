package com.gospec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gospec.domain.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, String> {
}
