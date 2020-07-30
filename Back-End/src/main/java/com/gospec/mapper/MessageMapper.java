package com.gospec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gospec.domain.MessageDto;
import com.gospec.domain.TeamApprovalDto;

@Mapper
public interface MessageMapper {

	public List<MessageDto> findRecieveMessage(String username);
	public List<MessageDto> findSendMessage(String username);
	public int countNewRecieveMessage(String username, boolean reading);
	public int saveRecieveMessage(MessageDto message);
	public int saveSendMessage(MessageDto message);
	public int deleteRecieveMessage(int no);
	public int deleteSendMessage(int no);
	
}
