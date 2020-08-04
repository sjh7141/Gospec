package com.gospec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gospec.domain.ActiveRegionDto;
import com.gospec.domain.BookMarkDto;
import com.gospec.domain.ClusterDto;
import com.gospec.domain.InterestFieldDto;
import com.gospec.domain.UserDto;

@Mapper
public interface UserMapper {

	public UserDto findByUsername(String username);
	public List<UserDto> findAll();
	public int save(UserDto dto);
	public int checkId(String username);
	public int newPwd(String username, String password);
	public int deleteByUsername(String username);
	public int checkPwd(String username, String password);
	public int updateByUsername(UserDto user);
	public List<ActiveRegionDto> findAllActiveRegion(String username);
	public List<BookMarkDto> findAllBookMark(String username);
	public List<String> findAllInterestField(String username);
	public int checkNickName(String nickname);
	public int deleteInterestField(String username);
	public int saveInterestField(List<InterestFieldDto> fields);
	public List<InterestFieldDto> findInterestFieldDumamy();
	public void makeCluster(List<ClusterDto> list);

}

