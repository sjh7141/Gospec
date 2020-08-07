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
	public List<MessageDto> findReceiveMessage(String username) {
		return messageMapper.findReceiveMessage(username);
	}

	@Override
	public List<MessageDto> findSendMessage(String username) {
		return messageMapper.findSendMessage(username);
	}

	@Override
	public boolean saveReceiveMessage(MessageDto message) {
		if(messageMapper.saveReceiveMessage(message) > 0) {
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
	public int countNewReceiveMessage(String username) {
		return messageMapper.countNewReceiveMessage(username, false);
	}

	@Override
	public boolean deleteReceiveMessage(int no) {
		if(messageMapper.deleteReceiveMessage(no) > 0) {
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
