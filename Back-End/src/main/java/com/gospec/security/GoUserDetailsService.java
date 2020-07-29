package com.gospec.security;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gospec.domain.ActiveRegionDto;
import com.gospec.domain.BookMarkDto;
import com.gospec.domain.ClusterDto;
import com.gospec.domain.InterestFieldDto;
import com.gospec.domain.UserDto;
import com.gospec.mapper.UserMapper;

@Service
public class GoUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BCryptPasswordEncoder pwEncoding;
	
	@Autowired
	public JavaMailSender javaMailSender;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto user = userMapper.findByUsername(username);
		GoUserDetails principal = new GoUserDetails(user);
		return principal;
	}
	
	public List<UserDto> findAll(){
		return userMapper.findAll();
	}
	
	public boolean save(UserDto user) {
		user.setPassword(pwEncoding.encode(user.getPassword()));
		if(userMapper.save(user) > 0) {
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
		System.out.println(username+ " "+pwEncoding.encode(password));
		if(userMapper.checkPwd(username, pwEncoding.encode(password)) > 0) {
			return true;
		}
		return false;
	}
	public boolean updateByUsername(UserDto user) {
		user.setPassword(pwEncoding.encode(user.getPassword()));
		if(userMapper.updateByUsername(user) > 0) {
			return true;
		}
		return false;
	}
	
	public List<ActiveRegionDto> findAllActiveRegion(String username){
		return userMapper.findAllActiveRegion(username);
	}
	public List<BookMarkDto> findAllBookMark(String username){
		return userMapper.findAllBookMark(username);
	}
	public List<InterestFieldDto> findAllInterestField(String username){
		return userMapper.findAllInterestField(username);
	}
	
	public void saveInterestField(String username, String field) {
		userMapper.saveInterestField(username, field);
	}
	
	@Async
	public String sendMail(String username) {		
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo(username);
		simpleMessage.setSubject("Gospec 가입 인증 메일입니다.");
		String code = makeCode();
		simpleMessage.setText("안녕하세요. Gospec 입니다.\n"
							+ "회원님의 인증코드는 다음과 같습니다.\n\n"
							+ "인증코드 : " +code);
		javaMailSender.send(simpleMessage);
		return code;
	}
	
	public String makeCode() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;
		do {
			num = ran.nextInt(75) + 48;
			if((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)){
				sb.append((char) num);
			}else {
				continue;
			}
		}while(sb.length()< 12);
		
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public List<InterestFieldDto> makeDummy() {
		return userMapper.findInterestFieldDumamy();
	}
	
	public void makeCluster(List<ClusterDto> list) {
		userMapper.makeCluster(list);
	}
	
	
}
