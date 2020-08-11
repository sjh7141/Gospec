package com.gospec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gospec.domain.MessageDto;
import com.gospec.domain.TeamApprovalDto;

@Mapper
public interface MessageMapper {
	public List<MessageDto> findReceiveMessage(String username, int startIndex, int perPageNum);
	public List<MessageDto> findSendMessage(String username, int startIndex, int perPageNum);
	public int countNewReceiveMessage(String username, boolean reading);
	public int countTotalReceiveMessage(String username);
	public int countTotalSendMessage(String username);
	public int saveReceiveMessage(MessageDto message);
	public int saveSendMessage(MessageDto message);
	public int deleteReceiveMessage(int no);
	public int deleteSendMessage(int no);
	public int updateSendMessage(int no, boolean reading);
	public int updateRecieveMessage(int no, boolean reading);
	public MessageDto findOneSendMessage(int no);
	public MessageDto findOneReceiveMessage(int no);
	
}
