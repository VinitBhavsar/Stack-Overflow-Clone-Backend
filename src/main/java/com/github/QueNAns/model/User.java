package com.github.QueNAns.model;

public class User {

	private long userid;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String password;
	private long sq_id;
	private String sq_answer;
	private String profileImageName;
	
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getSq_id() {
		return sq_id;
	}
	public void setSq_id(long sq_id) {
		this.sq_id = sq_id;
	}
	public String getSq_answer() {
		return sq_answer;
	}
	public void setSq_answer(String sq_answer) {
		this.sq_answer = sq_answer;
	}
	public String getProfileImageName() {
		return profileImageName;
	}
	public void setProfileImageName(String profileImageName) {
		this.profileImageName = profileImageName;
	}
	
}
