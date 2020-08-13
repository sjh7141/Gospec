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
		return messageMapper.findReceiveMessage(username, false, false);
	}

	@Override
	public List<MessageDto> findAllMessage(String username) {
		return messageMapper.findAllMessage(username, false);
	}

	@Override
	public List<MessageDto> findImportantMessage(String username) {
		return messageMapper.findImportantMessage(username, true, false);
	}

	@Override
	public List<MessageDto> findDeleteMessage(String username) {
		return messageMapper.findDeleteMessage(username, true);
	}

	@Override
	public List<MessageDto> findSendMessage(String username) {
		return messageMapper.findSendMessage(username);
	}

	@Override
	public int countNewReceiveMessage(String username) {
		return messageMapper.countNewReceiveMessage(username, false, false);
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
	public boolean saveReceiveMessage(MessageDto message) {
		if(messageMapper.saveReceiveMessage(message) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean saveSendMessage(MessageDto message) {
		if(messageMapper.saveSendMessage(message) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteReceiveMessage(int no) {
		if(messageMapper.deleteReceiveMessage(no) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteSendMessage(int no) {
		if(messageMapper.deleteSendMessage(no) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateReading(int no) {
		if(messageMapper.updateReading(no, true) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateDelete(int no, boolean isDelete) {
		if(messageMapper.updateDelete(no, isDelete) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateImportant(int no, boolean isImportant) {
		if(messageMapper.updateImportant(no, isImportant) > 0) {
			return true;
		}
		return false;
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
