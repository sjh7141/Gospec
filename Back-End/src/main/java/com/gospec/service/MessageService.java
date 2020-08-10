package com.gospec.service;

import java.util.List;

import com.gospec.domain.MessageDto;

public interface MessageService {

		public List<MessageDto> findReceiveMessage(String username);
		
		public List<MessageDto> findSendMessage(String username);

		public int countNewReceiveMessage(String username);
		
		public boolean saveReceiveMessage(MessageDto message);
		
		public boolean saveSendMessage(MessageDto message);
		
		public boolean deleteReceiveMessage(int no);
		
		public boolean deleteSendMessage(int no);
		
		public boolean updateSendMessage(int no);
		
		public boolean updateReceiveMessage(int no);
}
