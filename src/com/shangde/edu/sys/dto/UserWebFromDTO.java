package com.shangde.edu.sys.dto;

import java.io.Serializable;
import java.util.Date;

public class UserWebFromDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String mobile;
	private String email;
	private Integer subjectId;
	private String subjectName;
	private String webFrom;
	private String webAgent;
	private Date regDate;
	private Integer recordCount;
	private Integer payedRecordCount;
	private Integer cusId;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getWebFrom() {
		return webFrom;
	}

	public void setWebFrom(String webFrom) {
		this.webFrom = webFrom;
	}

	public String getWebAgent() {
		return webAgent;
	}

	public void setWebAgent(String webAgent) {
		this.webAgent = webAgent;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public Integer getPayedRecordCount() {
		return payedRecordCount;
	}

	public void setPayedRecordCount(Integer payedRecordCount) {
		this.payedRecordCount = payedRecordCount;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

}
