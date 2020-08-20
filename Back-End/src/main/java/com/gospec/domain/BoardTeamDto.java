package com.gospec.domain;

import java.util.List;

public class BoardTeamDto {

	private String username;
	private int postNo;
	private String title;
	private String content;
	private int memberMax;
	private String registTime;
	private int contestNo;
	private String teamId;
	private List<TeamApprovalDto> approvalList;

	public List<TeamApprovalDto> getApprovalList() {
		return approvalList;
	}

	public void setApprovalList(List<TeamApprovalDto> approvalList) {
		this.approvalList = approvalList;
	}

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

	public String getRegistTime() {
		return registTime;
	}

	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}

	public int getContestNo() {
		return contestNo;
	}

	public void setContestNo(int contestNo) {
		this.contestNo = contestNo;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

}
