package com.shangde.edu.card.domain;

import java.util.Date;

public class CardCourseQuartz {
	private Integer cardCourseId;//卡主键ID
	private String cardCourseCode;//卡编码
	private String cardCoursePassword;//卡密码
	private Integer cardMainId;//cardMain的主键
	private Integer cardCourseStatus;//卡状态 （用户操作状态 (1.激活 2.未激活,3.作废,4.作废)）
	private String userAccount;//用户账号
	private String orderCode;//订单编号
	private String creator;//创建者
	private Date createTime;//创建时间
	private String updateUser;//更新者
	private Date updateTime;//更新时间
	private Integer cardCourseUseStatus;//课程卡使用状态
	public Integer getCardCourseId() {
		return cardCourseId;
	}
	public void setCardCourseId(Integer cardCourseId) {
		this.cardCourseId = cardCourseId;
	}
	public String getCardCourseCode() {
		return cardCourseCode;
	}
	public void setCardCourseCode(String cardCourseCode) {
		this.cardCourseCode = cardCourseCode;
	}
	public String getCardCoursePassword() {
		return cardCoursePassword;
	}
	public void setCardCoursePassword(String cardCoursePassword) {
		this.cardCoursePassword = cardCoursePassword;
	}
	public Integer getCardMainId() {
		return cardMainId;
	}
	public void setCardMainId(Integer cardMainId) {
		this.cardMainId = cardMainId;
	}
	public Integer getCardCourseStatus() {
		return cardCourseStatus;
	}
	public void setCardCourseStatus(Integer cardCourseStatus) {
		this.cardCourseStatus = cardCourseStatus;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getCardCourseUseStatus() {
		return cardCourseUseStatus;
	}
	public void setCardCourseUseStatus(Integer cardCourseUseStatus) {
		this.cardCourseUseStatus = cardCourseUseStatus;
	}
}
