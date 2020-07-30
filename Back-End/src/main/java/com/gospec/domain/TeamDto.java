package com.gospec.domain;

import java.util.List;

public class TeamDto {

	private int postNo;
	private String teamId;
	private String username;
	private String title;
	private String content;
	private int memberMax;
	private int memberCur;
	private String teamName;
	private String contestStartTime;
	private String contestEndTime;
	private String registTime;
	private int contest_no;	
	
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getContestStartTime() {
		return contestStartTime;
	}
	public void setContestStartTime(String contestStartTime) {
		this.contestStartTime = contestStartTime;
	}
	public String getContestEndTime() {
		return contestEndTime;
	}
	public void setContestEndTime(String contestEndTime) {
		this.contestEndTime = contestEndTime;
	}
	public String getRegistTime() {
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
	public int getContest_no() {
		return contest_no;
	}
	public void setContest_no(int contest_no) {
		this.contest_no = contest_no;
	}
	public TeamDto() {
	
	}
	
	
	
}
