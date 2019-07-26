package com.github.QueNAns.model;

public class Answer {

	private long answerid;
	private long questionid;
	private long userid;
	private String firstname;
	private String lastname;
	private String answer;
	
	public long getAnswerid() {
		return answerid;
	}
	public void setAnswerid(long answerid) {
		this.answerid = answerid;
	}
	public long getQuestionid() {
		return questionid;
	}
	public void setQuestionid(long questionid) {
		this.questionid = questionid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
