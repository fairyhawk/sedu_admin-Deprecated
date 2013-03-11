package com.shangde.edu.crm.dto;

import java.io.Serializable;
import java.util.Date;

public class SimpleStatDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sellName;
	private Date reasonTime;
	private int sellCount;
	private int payCount;
	private String sysUserName;
	private int sendCount;
	private int callCount;
	private int successCount;
	private int sysUserId;
	private String groupName;
	private float intBankTotelPrice;
	private float sendTotelPrice;
	private int sendSuccessCount;
	private int cancelCount;
	private int backCancelCount;
	private int effSendTotelPrice;
	private int scene;
	
	private int hours;
	
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
	public String getSellName() {
		return sellName;
	}
	public void setSellName(String sellName) {
		this.sellName = sellName;
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
	public String getSysUserName() {
		return sysUserName;
	}
	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}
	public Date getReasonTime() {
		return reasonTime;
	}
	public void setReasonTime(Date reasonTime) {
		this.reasonTime = reasonTime;
	}
	public int getSendCount() {
		return sendCount;
	}
	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}
	public int getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(int sysUserId) {
		this.sysUserId = sysUserId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getScene() {
		return scene;
	}
	public void setScene(int scene) {
		this.scene = scene;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public float getIntBankTotelPrice() {
		return intBankTotelPrice;
	}
	public void setIntBankTotelPrice(float intBankTotelPrice) {
		this.intBankTotelPrice = intBankTotelPrice;
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
}
