package com.shangde.edu.crm.dto;

import java.util.Date;

import com.shangde.common.action.CommonAction;

public class SalesStatDTO extends CommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String userName;
	private int origin;
	private int consultStatus;
	private int hours;
	private int sellCount;
	private int payCount;
	private float intBankTotelPrice;
	private int sendCount;
	private int callCount;
	private int successCount;
	private String groupName;
	private float sendTotelPrice;
	private int sendSuccessCount;
	private int cancelCount;
	private int backCancelCount;
	private int effSendTotelPrice;
	private int difCancelCount;
	private int difBackCancelCount;
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
	public int getConsultStatus() {
		return consultStatus;
	}
	public void setConsultStatus(int consultStatus) {
		this.consultStatus = consultStatus;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public int getSellCount() {
		return sellCount;
	}
	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}
	public int getPayCount() {
		return payCount;
	}
	public void setPayCount(int payCount) {
		this.payCount = payCount;
	}
	public float getIntBankTotelPrice() {
		return intBankTotelPrice;
	}
	public void setIntBankTotelPrice(float intBankTotelPrice) {
		this.intBankTotelPrice = intBankTotelPrice;
	}
	public int getSendCount() {
		return sendCount;
	}
	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}
	public int getCallCount() {
		return callCount;
	}
	public void setCallCount(int callCount) {
		this.callCount = callCount;
	}
	public int getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public float getSendTotelPrice() {
		return sendTotelPrice;
	}
	public void setSendTotelPrice(float sendTotelPrice) {
		this.sendTotelPrice = sendTotelPrice;
	}
	public int getSendSuccessCount() {
		return sendSuccessCount;
	}
	public void setSendSuccessCount(int sendSuccessCount) {
		this.sendSuccessCount = sendSuccessCount;
	}
	public int getCancelCount() {
		return cancelCount;
	}
	public void setCancelCount(int cancelCount) {
		this.cancelCount = cancelCount;
	}
	public int getBackCancelCount() {
		return backCancelCount;
	}
	public void setBackCancelCount(int backCancelCount) {
		this.backCancelCount = backCancelCount;
	}
	public int getEffSendTotelPrice() {
		return effSendTotelPrice;
	}
	public void setEffSendTotelPrice(int effSendTotelPrice) {
		this.effSendTotelPrice = effSendTotelPrice;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getDifCancelCount() {
		return difCancelCount;
	}
	public void setDifCancelCount(int difCancelCount) {
		this.difCancelCount = difCancelCount;
	}
	public int getDifBackCancelCount() {
		return difBackCancelCount;
	}
	public void setDifBackCancelCount(int difBackCancelCount) {
		this.difBackCancelCount = difBackCancelCount;
	}

}
