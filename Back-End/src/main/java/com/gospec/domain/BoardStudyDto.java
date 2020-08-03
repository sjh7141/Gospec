package com.gospec.domain;

public class BoardStudyDto {

	private String username;
	private int postNo;
	private String title;
	private String content;
	private int memberMax;
	private int memberCur;
	private String registTime;
	private String region;
	private String field;
	private String teamId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getMemberMax() {
		return memberMax;
	}

	public void setMemberMax(int memberMax) {
		this.memberMax = memberMax;
	}

	public int getMemberCur() {
		return memberCur;
	}

	public void setMemberCur(int memberCur) {
		this.memberCur = memberCur;
	}

	public String getRegistTime() {
		return registTime;
	}

	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

}
