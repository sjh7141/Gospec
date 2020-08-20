package com.gospec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gospec.domain.MessageDto;
import com.gospec.domain.TeamApprovalDto;

@Mapper
public interface MessageMapper {
	public List<MessageDto> findReceiveMessage(String username, boolean isImportant, boolean isDelete);
	public List<MessageDto> findAllMessage(String username, boolean isDelete);
	public List<MessageDto> findImportantMessage(String username, boolean isImportant, boolean isDelete);
	public List<MessageDto> findDeleteMessage(String username, boolean isDelete);
	public List<MessageDto> findSendMessage(String username);
	public int countNewReceiveMessage(String username, boolean reading, boolean isDelete);
	public int countTotalReceiveMessage(String username);
	public int countTotalSendMessage(String username);
	public int saveReceiveMessage(MessageDto message);
	public int saveSendMessage(MessageDto message);
	public int deleteReceiveMessage(int no);
	public int deleteSendMessage(int no);
	public int updateReading(int no, boolean reading);
	public int updateDelete(int no, boolean isDelete);
	public int updateImportant(int no, boolean isImportant);
	public MessageDto findOneSendMessage(int no);
	public MessageDto findOneReceiveMessage(int no);
	
}
