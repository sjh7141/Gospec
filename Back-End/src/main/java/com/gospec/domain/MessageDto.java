package com.gospec.domain;

public class MessageDto {
	private int no;
	private String contents;
	private String sender;
	private String receiver;
	private boolean reading;
	private String registTime;
	private boolean isDelete;
	private boolean isImportant;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public boolean isReading() {
		return reading;
	}
	public void setReading(boolean reading) {
		this.reading = reading;
	}
	public String getRegistTime() {
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}	
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	public boolean isImportant() {
		return isImportant;
	}
	public void setImportant(boolean isImportant) {
		this.isImportant = isImportant;
	}
	public MessageDto() {
	
	}
	
	
	
}
