package com.gospec.domain;

public class LicenseDto {
	
	private String username;
	private String licensename;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLicensename() {
		return licensename;
	}
	public void setLicensename(String licensename) {
		this.licensename = licensename;
	}
	public LicenseDto(String licensename, String username) {
		this.username = username;
		this.licensename = licensename;
	}
	
	
}
