package com.shangde.edu.exam.dto;

import java.io.Serializable;
import java.util.Date;

public class ExampaperRecorDTO implements Serializable{
	private int cusId;
	private Date addtime;
	private float accuracy;
	private float userScore;
    private float testTime;
	private int userEpId;
	private String email;
	private String epName;
	private int eptype;
	private int joinNum;
	private float epTotelScore;
	private String subjectName;
	
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public float getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}
	public float getUserScore() {
		return userScore;
	}
	public void setUserScore(float userScore) {
		this.userScore = userScore;
	}
	public float getTestTime() {
		return testTime;
	}
	public void setTestTime(float testTime) {
		this.testTime = testTime;
	}
	public int getUserEpId() {
		return userEpId;
	}
	public void setUserEpId(int userEpId) {
		this.userEpId = userEpId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEpName() {
		return epName;
	}
	public void setEpName(String epName) {
		this.epName = epName;
	}
	public int getEptype() {
		return eptype;
	}
	public void setEptype(int eptype) {
		this.eptype = eptype;
	}
	public int getJoinNum() {
		return joinNum;
	}
	public void setJoinNum(int joinNum) {
		this.joinNum = joinNum;
	}

	public float getEpTotelScore() {
		return epTotelScore;
	}
	public void setEpTotelScore(float epTotelScore) {
		this.epTotelScore = epTotelScore;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
}
