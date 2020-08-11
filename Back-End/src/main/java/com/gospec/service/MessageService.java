package com.gospec.service;

import java.util.List;

import com.gospec.domain.MessageDto;

public interface MessageService {

		public List<MessageDto> findReceiveMessage(String username, int startIndex, int perPageNum);
		
		public List<MessageDto> findSendMessage(String username, int startIndex, int perPageNum);

		public int countNewReceiveMessage(String username);
		
		public int countTotalReceiveMessage(String username);
		
		public int countTotalSendMessage(String username);
		
		public boolean saveReceiveMessage(MessageDto message);
		
		public boolean saveSendMessage(MessageDto message);
		
		public boolean deleteReceiveMessage(int no);
		
		public boolean deleteSendMessage(int no);
		
		public boolean updateSendMessage(int no);
		
		public boolean updateReceiveMessage(int no);
		
		public MessageDto findOneSendMessage(int no);
		
		public MessageDto findOneReceiveMessage(int no);
}
