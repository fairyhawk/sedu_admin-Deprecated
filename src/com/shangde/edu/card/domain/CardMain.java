package com.shangde.edu.card.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CardMain implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer cardMainId;//主键ID
	private String cardName;//卡名称
	private BigDecimal cardMoney;//卡金额
	private Integer cardType;//卡类型
	private Integer cardCount;//卡数量
	private Integer  cardUserStatus;//卡状态（后台操作）
	private Date validBeginTime;//有效期开始时间
	private Date validEndTime;//有效期结束时间
	private String remark;//新建备注
	private String activateRemark;//激活备注
	private String activateUser;//激活人
	private String imageUrl;//图片url
	private String creator;//创建者
	private Date createTime;//创建时间
	private String updateUser;//更新者
	private Date updateTime;//更新时间
	private BigDecimal agentMoney;//代理商金额

	public BigDecimal getAgentMoney() {
		return agentMoney;
	}
	public void setAgentMoney(BigDecimal agentMoney) {
		this.agentMoney = agentMoney;
	}
	public String getActivateUser() {
		return activateUser;
	}
	public void setActivateUser(String activateUser) {
		this.activateUser = activateUser;
	}
	public String getActivateRemark() {
		return activateRemark;
	}
	public void setActivateRemark(String activateRemark) {
		this.activateRemark = activateRemark;
	}
	public Integer getCardMainId() {
		return cardMainId;
	}
	public Integer getCardCount() {
		return cardCount;
	}
	public void setCardCount(Integer cardCount) {
		this.cardCount = cardCount;
	}
	public void setCardMainId(Integer cardMainId) {
		this.cardMainId = cardMainId;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public BigDecimal getCardMoney() {
		return cardMoney;
	}
	public void setCardMoney(BigDecimal cardMoney) {
		this.cardMoney = cardMoney;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public Integer getCardUserStatus() {
		return cardUserStatus;
	}
	public void setCardUserStatus(Integer cardUserStatus) {
		this.cardUserStatus = cardUserStatus;
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	
}
