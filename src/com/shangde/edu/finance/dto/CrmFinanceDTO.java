package com.shangde.edu.finance.dto;

import java.util.Date;

public class CrmFinanceDTO {

	private int userId;//首咨销售id
	private String userName;//首咨销售姓名
	private String lastUserName;//最后指派对销售姓名
	private int origin=0;//场景
	private Date recordTime;//指派时间
	private Date lastRecordTime;//指派时间
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getOrigin() {
		return origin;
	}
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public Date getLastRecordTime() {
		return lastRecordTime;
	}
	public void setLastRecordTime(Date lastRecordTime) {
		this.lastRecordTime = lastRecordTime;
	}
	public String getLastUserName() {
		return lastUserName;
	}
	public void setLastUserName(String lastUserName) {
		this.lastUserName = lastUserName;
	}

}
