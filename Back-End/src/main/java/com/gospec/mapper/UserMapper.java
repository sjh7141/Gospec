package com.gospec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gospec.domain.ActiveRegionDto;
import com.gospec.domain.BookMarkDto;
import com.gospec.domain.CareerDto;
import com.gospec.domain.ClusterDto;
import com.gospec.domain.InterestFieldDto;
import com.gospec.domain.LicenseDto;
import com.gospec.domain.UserDto;

@Mapper
public interface UserMapper {

	public UserDto findByUsername(String username);
	public int save(UserDto dto);
	public int checkId(String username);
	public int newPwd(String username, String password);
	public int deleteByUsername(String username);
	public int checkPwd(String username, String password);
	public int updateByUsername(UserDto user);
	public int checkNickName(String nickname);
	
	public List<String> findAllActiveRegion(String username);
	public List<BookMarkDto> findAllBookMark(String username);
	public List<String> findAllInterestField(String username);
	public List<String> findAllLicense(String username);
	public List<CareerDto> findAllCareer(String username);
	
	public int saveInterestField(List<InterestFieldDto> fields);
	public int saveActiveRegion(List<ActiveRegionDto> regions);
	public int saveLicense(List<LicenseDto> licenses);
	public int saveCareer(List<CareerDto> careers);
	
	public int deleteInterestField(String username);
	public int deleteActiveRegion(String username);
	public int deleteLicense(String username);
	public int deleteCareer(String username);
	
	public List<InterestFieldDto> findByInterestFieldWithCluster();
	public void makeCluster(List<ClusterDto> list);
	public void resetCluster();
}

