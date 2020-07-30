package com.gospec.service;

import java.util.List;

import com.gospec.domain.MessageDto;

public interface MessageService {

		public List<MessageDto> findRecieveMessage(String username);
		
		public List<MessageDto> findSendMessage(String username);

		public int countNewRecieveMessage(String username);
		
		public boolean saveRecieveMessage(MessageDto message);
		
		public boolean saveSendMessage(MessageDto message);
		
		public boolean deleteRecieveMessage(int no);
		
		public boolean deleteSendMessage(int no);
		
		
}
