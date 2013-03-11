package com.shangde.edu.cus.dto;

import java.io.Serializable;
import java.util.Date;

public class AbnormalCustomerDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer cusId;
	private String cusName;
	private String email;
	private String mobile;
	private String freezeStatus;
	private Date freezeChangedDate;
	private Integer abnormalLoginTimes;

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFreezeStatus() {
		return freezeStatus;
	}

	public void setFreezeStatus(String freezeStatus) {
		this.freezeStatus = freezeStatus;
	}

	public Date getFreezeChangedDate() {
		return freezeChangedDate;
	}

	public void setFreezeChangedDate(Date freezeChangedDate) {
		this.freezeChangedDate = freezeChangedDate;
	}

	public Integer getAbnormalLoginTimes() {
		return abnormalLoginTimes;
	}

	public void setAbnormalLoginTimes(Integer abnormalLoginTimes) {
		this.abnormalLoginTimes = abnormalLoginTimes;
	}

}
