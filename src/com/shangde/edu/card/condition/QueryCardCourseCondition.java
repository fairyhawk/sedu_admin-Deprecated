package com.shangde.edu.card.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class QueryCardCourseCondition extends PageQuery {
	private Date createBeginTime;
	private Date createEndTime;
	private String cardMoney;
	private String cardCodeBegin;
	private String cardCodeEnd;
	private Integer cardCourseStatus;
	private String cardName;
	private String updateUser;
	private Integer cardMainId;
	private String sellIds;
	private Integer cardUserStatus;
	private Integer cardCourseUseStatus;

	public Integer getCardCourseUseStatus() {
		return cardCourseUseStatus;
	}
	public void setCardCourseUseStatus(Integer cardCourseUseStatus) {
		this.cardCourseUseStatus = cardCourseUseStatus;
	}
	public Integer getCardUserStatus() {
		return cardUserStatus;
	}
	public void setCardUserStatus(Integer cardUserStatus) {
		this.cardUserStatus = cardUserStatus;
	}
	public String getSellIds() {
		return sellIds;
	}
	public void setSellIds(String sellIds) {
		this.sellIds = sellIds;
	}
	public Integer getCardMainId() {
		return cardMainId;
	}
	public void setCardMainId(Integer cardMainId) {
		this.cardMainId = cardMainId;
	}
	public String getCardMoney() {
		return cardMoney;
	}
	public void setCardMoney(String cardMoney) {
		this.cardMoney = cardMoney;
	}
	public Date getCreateBeginTime() {
		return createBeginTime;
	}
	public void setCreateBeginTime(Date createBeginTime) {
		this.createBeginTime = createBeginTime;
	}
	public Date getCreateEndTime() {
		return createEndTime;
	}
	public void setCreateEndTime(Date createEndTime) {
		this.createEndTime = createEndTime;
	}
	public String getCardCodeBegin() {
		return cardCodeBegin;
	}
	public void setCardCodeBegin(String cardCodeBegin) {
		this.cardCodeBegin = cardCodeBegin;
	}
	public String getCardCodeEnd() {
		return cardCodeEnd;
	}
	public void setCardCodeEnd(String cardCodeEnd) {
		this.cardCodeEnd = cardCodeEnd;
	}
	public Integer getCardCourseStatus() {
		return cardCourseStatus;
	}
	public void setCardCourseStatus(Integer cardCourseStatus) {
		this.cardCourseStatus = cardCourseStatus;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}
