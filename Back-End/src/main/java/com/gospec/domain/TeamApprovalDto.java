package com.gospec.domain;

public class TeamApprovalDto {
	
	private String teamId;
	private String leaderUsername;
	private String memberUsername;
	private boolean approvalFlag;
	
	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getLeaderUsername() {
		return leaderUsername;
	}

	public void setLeaderUsername(String leaderUsername) {
		this.leaderUsername = leaderUsername;
	}

	public String getMemberUsername() {
		return memberUsername;
	}

	public void setMemberUsername(String memberUsername) {
		this.memberUsername = memberUsername;
	}

	public boolean isApprovalFlag() {
		return approvalFlag;
	}

	public void setApprovalFlag(boolean approvalFlag) {
		this.approvalFlag = approvalFlag;
	}

	public TeamApprovalDto() {
	
	}
	
	
	
}
