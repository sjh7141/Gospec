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
	@GetMapping(value="/api/message/receiver/{username}/{page}")
	public ResponseEntity<Map<String, Object>> getReceiveList(@PathVariable("username") String username, @PathVariable("page") int curPage){
		PageDto page = new PageDto(curPage, 10);
		page.setTotalCount(messageService.countTotalReceiveMessage(username));
		List<MessageDto> messageList = messageService.findReceiveMessage(username, page.getStartIndex(), page.getPerPageNum());
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", messageList);
		data.put("page", page);
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}
	
	@ApiOperation(value = "보낸 쪽지  조회, 사용자 아이디로 보낸 쪽지을 전체 조회한다.", response = MessageDto.class)
	@GetMapping(value="/api/message/sender/{username}/{page}")
	public ResponseEntity<Map<String, Object>> getSendList(@PathVariable("username") String username, @PathVariable("page") int curPage){
		PageDto page = new PageDto(curPage, 10);
		page.setTotalCount(messageService.countTotalSendMessage(username));
		List<MessageDto> messageList = messageService.findSendMessage(username, page.getStartIndex(), page.getPerPageNum());
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", messageList);
		data.put("page", page);
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
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
	
	@ApiOperation(value = "해당 쪽지 번호 읽음상태로 변환", response = Boolean.class)
	@PatchMapping(value="/api/message/receiver")
	public ResponseEntity<Boolean> updateReceiveMessage(@RequestBody Map<String, Object> param){
		int no = (int) param.get("no");
		return new ResponseEntity<Boolean>(messageService.updateReceiveMessage(no), HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 쪽지 번호 읽음상태로 변환", response = Boolean.class)
	@PatchMapping(value="/api/message/sender")
	public ResponseEntity<Boolean> updateSendMessage(@RequestBody Map<String, Object> param){
		int no = (int) param.get("no");
		return new ResponseEntity<Boolean>(messageService.updateSendMessage(no), HttpStatus.OK);
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
