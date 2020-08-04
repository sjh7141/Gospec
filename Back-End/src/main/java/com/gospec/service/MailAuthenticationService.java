package com.gospec.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailAuthenticationService {
	
	@Autowired
	public JavaMailSender javaMailSender;
	
	@Async
	public String sendMail(String username) {		
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo(username);
		simpleMessage.setSubject("Gospec 인증 메일입니다.");
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
}
