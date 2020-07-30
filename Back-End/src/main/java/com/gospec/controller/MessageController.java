package com.gospec.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.gospec.domain.MessageDto;
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
//		System.out.println(receiver);
//		System.out.println(message);
		Gson gson = new Gson();
		MessageDto dto = gson.fromJson(message, MessageDto.class);
//		System.out.println(dto.getContents());
		messageService.saveSendMessage(dto);
		messageService.saveReceiveMessage(dto);
		return messageService.countNewReceiveMessage(receiver);
	}
	
	@ApiOperation(value = "받은 쪽지  조회, 사용자 아이디로 받은 쪽지을 전체 조회한다.", response = MessageDto.class)
	@GetMapping(value="/api/message/receiver/{username}")
	public ResponseEntity<List<MessageDto>> getReceiveList(@PathVariable("username") String username){
		return new ResponseEntity<List<MessageDto>>(messageService.findReceiveMessage(username), HttpStatus.OK);
	}
	
	@ApiOperation(value = "보낸 쪽지  조회, 사용자 아이디로 보낸 쪽지을 전체 조회한다.", response = MessageDto.class)
	@GetMapping(value="/api/message/sender/{username}")
	public ResponseEntity<List<MessageDto>> getSendList(@PathVariable("username") String username){
		return new ResponseEntity<List<MessageDto>>(messageService.findSendMessage(username), HttpStatus.OK);
	}
	
	@ApiOperation(value = "받은 쪽지 정보 삭제, 쪽지 번호로 해당 쪽지를 삭제한다.", response = Boolean.class)
	@DeleteMapping(value="/api/message/receiver")
	public ResponseEntity<Boolean> deleteReceiveMessage(@RequestBody Map<String, Object> param){
		int no = (int) param.get("no");
		return new ResponseEntity<Boolean>(messageService.deleteReceiveMessage(no), HttpStatus.OK);
	}
	
	@ApiOperation(value = "보낸 쪽지 정보 삭제, 쪽지 번호로 해당 쪽지를 삭제한다.", response = Boolean.class)
	@DeleteMapping(value="/api/message/sender")
	public ResponseEntity<Boolean> deleteSendMessage(@RequestBody Map<String, Object> param){
		int no = (int) param.get("no");
		return new ResponseEntity<Boolean>(messageService.deleteSendMessage(no), HttpStatus.OK);
	}

}
