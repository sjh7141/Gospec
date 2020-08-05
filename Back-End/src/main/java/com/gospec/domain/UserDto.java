package com.gospec.domain;

import java.util.List;

public class UserDto {

	private String username;
	private String password;
	private String nickname;
	private String profileImg;
	private String selfIntroduction;
	private String name;
	private String phone;
	private String birthday;
	private String authority;
	private int age;
	private String address;
	private String major;
	private String gender;
	
	private List<String> interestFieldList;
	private List<String> activeRegionList;
	private List<CareerDto> careerList;
	private List<String> licenseList;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getSelfIntroduction() {
		return selfIntroduction;
	}

	public void setSelfIntroduction(String selfIntroduction) {
		this.selfIntroduction = selfIntroduction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<String> getInterestFieldList() {
		return interestFieldList;
	}

	public void setInterestFieldList(List<String> interestFieldList) {
		this.interestFieldList = interestFieldList;
	}

	public List<String> getActiveRegionList() {
		return activeRegionList;
	}

	public void setActiveRegionList(List<String> activeRegionList) {
		this.activeRegionList = activeRegionList;
	}

	public List<CareerDto> getCareerList() {
		return careerList;
	}

	public void setCareerList(List<CareerDto> careerList) {
		this.careerList = careerList;
	}

	public List<String> getLicenseList() {
		return licenseList;
	}

	public void setLicenseList(List<String> licenseList) {
		this.licenseList = licenseList;
	}

	public UserDto() {
	}
	
	
}


