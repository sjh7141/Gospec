package com.gospec.domain;

public class ActiveRegionDto {
	private String region;

	private String username;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ActiveRegionDto() {
	
	}

	public ActiveRegionDto(String region, String username) {
		this.region = region;
		this.username = username;
	}
	
	
	
	
	
}
