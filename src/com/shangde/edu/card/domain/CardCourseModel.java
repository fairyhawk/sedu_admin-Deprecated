package com.shangde.edu.card.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CardCourseModel extends CardCourse implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cardName;//课程卡名称
	private String cardCourseStatusContent;//课程卡状态
	private BigDecimal cardMoney;//课程卡金额
	private Date validBeginTime;//有效期开始时间
	private Date validEndTime;//有效期结束时间
	private String remark;//备注
	private String imageUrl;//验证图
	private String activateRemark;//激活人备注
	private BigDecimal agentMoney;//代理商金额
	public BigDecimal getAgentMoney() {
		return agentMoney;
	}
	public void setAgentMoney(BigDecimal agentMoney) {
		this.agentMoney = agentMoney;
	}
	public String getActivateRemark() {
		return activateRemark;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public void setActivateRemark(String activateRemark) {
		this.activateRemark = activateRemark;
	}

	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardCourseStatusContent() {
		return cardCourseStatusContent;
	}
	public void setCardCourseStatusContent(String cardCourseStatusContent) {
		this.cardCourseStatusContent = cardCourseStatusContent;
	}
	public BigDecimal getCardMoney() {
		return cardMoney;
	}
	public void setCardMoney(BigDecimal cardMoney) {
		this.cardMoney = cardMoney;
	}
	public Date getValidBeginTime() {
		return validBeginTime;
	}
	public void setValidBeginTime(Date validBeginTime) {
		this.validBeginTime = validBeginTime;
	}
	public Date getValidEndTime() {
		return validEndTime;
	}
	public void setValidEndTime(Date validEndTime) {
		this.validEndTime = validEndTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
