package com.shangde.edu.freshnews.domain;

import java.io.Serializable;
import java.math.*;
import java.util.*;

public class ActionRecordByExam implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer userEpId;//学员考试记录ID
	private BigDecimal accuracy;// 正确率
	private String examName;//试卷名称 
	private Integer cusId;//学员ID
	private String email;//学员email
	private String cusName;//学员昵称
	private Date createTime;// 创建时间
	private Integer subjectId;//专业ID
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public Integer getUserEpId() {
		return userEpId;
	}
	public void setUserEpId(Integer userEpId) {
		this.userEpId = userEpId;
	}
	public Integer getCusId() {
		return cusId;
	}
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public BigDecimal getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(BigDecimal accuracy) {
		this.accuracy = accuracy;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
}
