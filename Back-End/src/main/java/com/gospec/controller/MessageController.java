package com.gospec.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.gospec.domain.MessageDto;
import com.gospec.domain.PageDto;
import com.gospec.service.MessageService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@ApiOperation(value = "메세지 저장후 receiver의 Url에 새로생긴 메세지가 있는지 true, false로 보내준다", response = Boolean.class)
	@MessageMapping("/{receiver}")
	@SendTo("/topic/{receiver}")
	public Integer sendMessge(@DestinationVariable String receiver, String message) {
		Gson gson = new Gson();
		MessageDto dto = gson.fromJson(message, MessageDto.class);
		messageService.saveSendMessage(dto);
		messageService.saveReceiveMessage(dto);
		return messageService.countNewReceiveMessage(receiver);
	}
	
	@ApiOperation(value = "받은 쪽지  조회, 사용자 아이디로 받은 쪽지을 전체 조회한다.", response = Map.class)
	@GetMapping(value="/api/message/receiver/{type}/{username}")
	public ResponseEntity<Map<String, Object>> getReceiveList(@PathVariable("type") int type, @PathVariable("username") String username){
		List<MessageDto> messageList = null;
		switch(type) {
			case 1 : messageList = messageService.findReceiveMessage(username);
					 break;
			case 2 : messageList = messageService.findAllMessage(username);
					 break;
			case 3 : messageList = messageService.findImportantMessage(username);
					 break;
			case 4 : messageList = messageService.findDeleteMessage(username);
					 break;
		}		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", messageList);
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}
	
	@ApiOperation(value = "보낸 쪽지  조회, 사용자 아이디로 보낸 쪽지을 전체 조회한다.", response = Map.class)
	@GetMapping(value="/api/message/sender/all/{username}")
	public ResponseEntity<Map<String, Object>> getSendList(@PathVariable("username") String username){
		List<MessageDto> messageList = messageService.findSendMessage(username);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", messageList);
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}
	
	@ApiOperation(value = "휴지통으로 이동, 쪽지 번호로 해당 쪽지를 이동한다.", response = Boolean.class)
	@PatchMapping(value="/api/message/trash-can")
	public ResponseEntity<Boolean> moveToTrashCan(@RequestBody Map<String, Object> param){
		List<Integer> noList = (List<Integer>) param.get("no");
		boolean state = (boolean) param.get("state");
		for(Integer no : noList) {
			if(!messageService.updateDelete(no, state)) {
				new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@ApiOperation(value = "보관함 이동, 쪽지 번호로 해당 쪽지를 이동한다.", response = Boolean.class)
	@PatchMapping(value="/api/message/important-box")
	public ResponseEntity<Boolean> moveToImportantBox(@RequestBody Map<String, Object> param){
		int no = (int) param.get("no");
		boolean state = (boolean) param.get("state");
		return new ResponseEntity<Boolean>(messageService.updateImportant(no, state), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "휴지통 쪽지 정보 삭제, 쪽지 번호로 해당 쪽지를 삭제한다.", response = Boolean.class)
	@DeleteMapping(value="/api/message/receiver")
	public ResponseEntity<Boolean> deleteReceiveMessage(@RequestBody Map<String, Object> param){
		List<Integer> noList = (List<Integer>) param.get("no");
		for(Integer no : noList) {
			if(!messageService.deleteReceiveMessage(no)) {
				new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@ApiOperation(value = "보낸 쪽지 정보 삭제, 쪽지 번호로 해당 쪽지를 삭제한다.", response = Boolean.class)
	@DeleteMapping(value="/api/message/sender")
	public ResponseEntity<Boolean> deleteSendMessage(@RequestBody Map<String, Object> param){
		List<Integer> noList = (List<Integer>) param.get("no");
		for(Integer no : noList) {
			if(!messageService.deleteSendMessage(no)) {
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 쪽지 번호 읽음상태로 변환", response = Integer.class)
	@PatchMapping(value="/api/message/reading")
	public ResponseEntity<Integer> updateReading(@RequestBody Map<String, Object> param){
		int no = (int) param.get("no");
		boolean flag = messageService.updateReading(no);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		int cnt = messageService.countNewReceiveMessage(username);
		return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
	}
	
	@ApiOperation(value = "읽지않은 쪽지 개수확인", response = Integer.class)
	@GetMapping(value="/api/message/unreadMessage/{username}")
	public ResponseEntity<Integer> unreadMessage(@PathVariable("username") String username){
		int cnt = messageService.countNewReceiveMessage(username);
		return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 받은쪽지 조회", response = MessageDto.class)
	@GetMapping(value="/api/message/receiver/{no}")
	public ResponseEntity<MessageDto> findOneReceiveMessage(@PathVariable int no){
		return new ResponseEntity<MessageDto>(messageService.findOneReceiveMessage(no), HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 보낸쪽지 조회", response = MessageDto.class)
	@GetMapping(value="/api/message/sender/{no}")
	public ResponseEntity<MessageDto> findOneSendMessage(@PathVariable int no){
		return new ResponseEntity<MessageDto>(messageService.findOneSendMessage(no), HttpStatus.OK);
	}
	
	
	
	

}
