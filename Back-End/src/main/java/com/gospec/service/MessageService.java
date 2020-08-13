package com.gospec.service;

import java.util.List;

import com.gospec.domain.MessageDto;

public interface MessageService {
	public List<MessageDto> findReceiveMessage(String username);
	public List<MessageDto> findAllMessage(String username);
	public List<MessageDto> findImportantMessage(String username);
	public List<MessageDto> findDeleteMessage(String username);
	public List<MessageDto> findSendMessage(String username);
	public int countNewReceiveMessage(String username);
	public int countTotalReceiveMessage(String username);
	public int countTotalSendMessage(String username);
	public boolean saveReceiveMessage(MessageDto message);
	public boolean saveSendMessage(MessageDto message);
	public boolean deleteReceiveMessage(int no);
	public boolean deleteSendMessage(int no);
	public boolean updateReading(int no);
	public boolean updateDelete(int no, boolean isDelete);
	public boolean updateImportant(int no, boolean isImportant);
	public MessageDto findOneSendMessage(int no);
	public MessageDto findOneReceiveMessage(int no);
}
