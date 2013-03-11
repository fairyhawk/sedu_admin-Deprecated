package com.shangde.edu.cus.dto;

import java.util.Date;

public class LoginRecordDetailDTO {

	private Date loginTime;
	private String address;

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
