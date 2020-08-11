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
	public List<MessageDto> findReceiveMessage(String username, int startIndex, int perPageNum) {
		return messageMapper.findReceiveMessage(username, startIndex, perPageNum);
	}

	@Override
	public List<MessageDto> findSendMessage(String username, int startIndex, int perPageNum) {
		return messageMapper.findSendMessage(username, startIndex, perPageNum);
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

	@Override
	public boolean updateSendMessage(int no) {
		if(messageMapper.updateSendMessage(no, true) > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean updateReceiveMessage(int no) {
		if(messageMapper.updateRecieveMessage(no, true) > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int countTotalReceiveMessage(String username) {
		return messageMapper.countTotalReceiveMessage(username);
	}

	@Override
	public int countTotalSendMessage(String username) {
		return messageMapper.countTotalSendMessage(username);
	}

	@Override
	public MessageDto findOneSendMessage(int no) {
		return messageMapper.findOneSendMessage(no);
	}

	@Override
	public MessageDto findOneReceiveMessage(int no) {
		return messageMapper.findOneReceiveMessage(no);
	}
	
	
	
}
