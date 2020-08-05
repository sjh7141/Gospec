package com.gospec.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gospec.domain.ActiveRegionDto;
import com.gospec.domain.BookMarkDto;
import com.gospec.domain.CareerDto;
import com.gospec.domain.ClusterDto;
import com.gospec.domain.InterestFieldDto;
import com.gospec.domain.LicenseDto;
import com.gospec.domain.UserDto;
import com.gospec.mapper.UserMapper;
import com.gospec.recommend.KMeansClustering;

@Service
public class GoUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BCryptPasswordEncoder pwEncoding;
	
	@Autowired
	private KMeansClustering kmean;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto user = userMapper.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("유저 아이디 없음");
		}
		GoUserDetails principal = new GoUserDetails(user);
		return principal;
	}
	
	public boolean save(UserDto user) {
		user.setPassword(pwEncoding.encode(user.getPassword()));
		if(userMapper.save(user) > 0) {
			userMapper.resetCluster();
			kmean.makeFile(userMapper.findByInterestFieldWithCluster());
			userMapper.makeCluster(kmean.readData());
			return true;
		}
		return false;
	}
	
	public UserDto findByUsername(String username) {
		return userMapper.findByUsername(username);
	}
	
	public boolean checkId(String username) {
		if(userMapper.checkId(username) > 0) {
			return true;
		}
		return false;
	}
	
	public boolean checkNickName(String nickname) {
		if(userMapper.checkNickName(nickname) > 0) {
			return true;
		}
		return false;
	}
	public boolean newPwd(String username, String password) {
		if(userMapper.newPwd(username, pwEncoding.encode(password)) > 0) {
			return true;
		}
		return false;
	}
	public boolean deleteByUsername(String username) {
		if(userMapper.deleteByUsername(username) > 0) {
			return true;
		}
		return false;
	}
	
	public boolean checkPwd(String username, String password) {
		if(userMapper.checkPwd(username, pwEncoding.encode(password)) > 0) {
			return true;
		}
		return false;
	}
	public boolean updateByUsername(UserDto user) {
		if(userMapper.updateByUsername(user) > 0) {
			return true;
		}
		return false;
	}
	
	public List<BookMarkDto> findAllBookMark(String username){
		return userMapper.findAllBookMark(username);
	}
	
	public List<String> findAllInterestField(String username){
		return userMapper.findAllInterestField(username);
	}
	
	public List<String> findAllActiveRegion(String username){
		return userMapper.findAllActiveRegion(username);
	}
	
	public List<String> findAllLicense(String username){
		return userMapper.findAllLicense(username);
	}
	
	public List<CareerDto> findAllCareer(String username){
		return userMapper.findAllCareer(username);
	}
	
	public void deleteInterestField(String username) {
		userMapper.deleteInterestField(username);
	}
	
	public void deleteActiveRegion(String username) {
		userMapper.deleteActiveRegion(username);
	}
	
	public void deleteLicense(String username) {
		userMapper.deleteLicense(username);
	}
	public void deleteCareer(String username) {
		userMapper.deleteCareer(username);
	}
	
	public void saveInterestField(List<InterestFieldDto> fields) {
		userMapper.saveInterestField(fields);
	}
	
	public void saveActiveRegion(List<ActiveRegionDto> regions) {
		userMapper.saveActiveRegion(regions);
	}
	public void saveLicense(List<LicenseDto> licenses) {
		userMapper.saveLicense(licenses);
	}
	public void saveCareer(List<CareerDto> careers) {
		userMapper.saveCareer(careers);
	}
	
	public List<InterestFieldDto> findByInterestFieldWithCluster() {
		return userMapper.findByInterestFieldWithCluster();
	}
	
	public void makeCluster(List<ClusterDto> list) {
		userMapper.makeCluster(list);
	}
	
	public void resetCluster() {
		userMapper.resetCluster();
	}
}

