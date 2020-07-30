package com.gospec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gospec.domain.MessageDto;
import com.gospec.mapper.MessageMapper;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Override
	public List<MessageDto> findRecieveMessage(String username) {
		return messageMapper.findRecieveMessage(username);
	}

	@Override
	public List<MessageDto> findSendMessage(String username) {
		return messageMapper.findSendMessage(username);
	}

	@Override
	public boolean saveRecieveMessage(MessageDto message) {
		if(messageMapper.saveRecieveMessage(message) > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean saveSendMessage(MessageDto message) {
		if(messageMapper.saveSendMessage(message) > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int countNewRecieveMessage(String username) {
		return messageMapper.countNewRecieveMessage(username, false);
	}

	@Override
	public boolean deleteRecieveMessage(int no) {
		if(messageMapper.deleteRecieveMessage(no) > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteSendMessage(int no) {
		if(messageMapper.deleteSendMessage(no) > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
