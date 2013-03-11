package com.shangde.edu.count.dto;

import java.io.Serializable;

public class SellWayDTO implements Serializable{
	
	private int status;   //付款状态 //赠送的包括 2赠送 4修复 0未付 1已付 3退费; 货到付款的包括 0未激活 1已激活 3已取消 4退费
	
	private int ctId;   //订单ID
	
	private int subjectId; //专业ID
	
	private int payType; //付款方式0 为赠送 1支付宝 2货到付款 3 网银在线 4 块钱
	
	private int sellPrice; //售卖方式价格
	
	private int sellId;
	
	private String sellName;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCtId() {
		return ctId;
	}

	public void setCtId(int ctId) {
		this.ctId = ctId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}

}
