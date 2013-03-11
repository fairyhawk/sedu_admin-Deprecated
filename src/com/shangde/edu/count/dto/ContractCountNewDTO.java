package com.shangde.edu.count.dto;

import java.io.Serializable;

public class ContractCountNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer allStudent; //全部学员
	
	private Integer allOrder; //全部订单
	private Integer dtoOrder; //货到付款订单
	private Integer dtoOrderNo; //货到付款订单(未付)
	private Integer bankOrder; //支付宝订单
	private Integer bankOrderNo; //支付宝订单(未付)
	private Integer quickOrder; //快钱订单
	private Integer quickOrderNo; //快钱订单(未付)
	private Integer internetOrder; //网银在线订单
	private Integer internetOrderNo; //网银在线订单(未付)
	private Integer truefriendsOrder;//真友
	private Integer truefriendsOrderNo;//真友（未付）
	private Integer remittanceOrder;//网银汇款
	private Integer remittanceOrderNo;//网银汇款（未付）
	private Integer unionpayOrder;//银联在线
	private Integer unionpayOrderNo;//银联在线(未付)
	public Integer getTruefriendsOrder() {
		return truefriendsOrder;
	}

	public Integer getUnionpayOrder() {
		return unionpayOrder;
	}

	public void setUnionpayOrder(Integer unionpayOrder) {
		this.unionpayOrder = unionpayOrder;
	}

	public Integer getUnionpayOrderNo() {
		return unionpayOrderNo;
	}

	public void setUnionpayOrderNo(Integer unionpayOrderNo) {
		this.unionpayOrderNo = unionpayOrderNo;
	}

	public void setTruefriendsOrder(Integer truefriendsOrder) {
		this.truefriendsOrder = truefriendsOrder;
	}

	public Integer getTruefriendsOrderNo() {
		return truefriendsOrderNo;
	}

	public void setTruefriendsOrderNo(Integer truefriendsOrderNo) {
		this.truefriendsOrderNo = truefriendsOrderNo;
	}

	public Integer getRemittanceOrder() {
		return remittanceOrder;
	}

	public void setRemittanceOrder(Integer remittanceOrder) {
		this.remittanceOrder = remittanceOrder;
	}

	public Integer getRemittanceOrderNo() {
		return remittanceOrderNo;
	}

	public void setRemittanceOrderNo(Integer remittanceOrderNo) {
		this.remittanceOrderNo = remittanceOrderNo;
	}

	private Integer orderNo; //未支付订单
	private Integer giftOrder; //赠送订单
	private Integer afterAmount; //打折后金额
	private Integer Refund; //退费
	private Integer allLinshi; //当前注册条件的订单

	private String subName;//专业名称
	public Integer getAllStudent() {
		return allStudent;
	}

	public void setAllStudent(Integer allStudent) {
		this.allStudent = allStudent;
	}

	public Integer getAllOrder() {
		return allOrder;
	}

	public void setAllOrder(Integer allOrder) {
		this.allOrder = allOrder;
	}

	public Integer getDtoOrder() {
		return dtoOrder;
	}

	public void setDtoOrder(Integer dtoOrder) {
		this.dtoOrder = dtoOrder;
	}

	public Integer getDtoOrderNo() {
		return dtoOrderNo;
	}

	public void setDtoOrderNo(Integer dtoOrderNo) {
		this.dtoOrderNo = dtoOrderNo;
	}

	public Integer getBankOrder() {
		return bankOrder;
	}

	public void setBankOrder(Integer bankOrder) {
		this.bankOrder = bankOrder;
	}

	public Integer getBankOrderNo() {
		return bankOrderNo;
	}

	public void setBankOrderNo(Integer bankOrderNo) {
		this.bankOrderNo = bankOrderNo;
	}

	public Integer getQuickOrder() {
		return quickOrder;
	}

	public void setQuickOrder(Integer quickOrder) {
		this.quickOrder = quickOrder;
	}

	public Integer getQuickOrderNo() {
		return quickOrderNo;
	}

	public void setQuickOrderNo(Integer quickOrderNo) {
		this.quickOrderNo = quickOrderNo;
	}

	public Integer getInternetOrder() {
		return internetOrder;
	}

	public void setInternetOrder(Integer internetOrder) {
		this.internetOrder = internetOrder;
	}

	public Integer getInternetOrderNo() {
		return internetOrderNo;
	}

	public void setInternetOrderNo(Integer internetOrderNo) {
		this.internetOrderNo = internetOrderNo;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getGiftOrder() {
		return giftOrder;
	}

	public void setGiftOrder(Integer giftOrder) {
		this.giftOrder = giftOrder;
	}

	public Integer getAfterAmount() {
		return afterAmount;
	}

	public void setAfterAmount(Integer afterAmount) {
		this.afterAmount = afterAmount;
	}

	public Integer getRefund() {
		return Refund;
	}

	public void setRefund(Integer refund) {
		Refund = refund;
	}

	public Integer getAllLinshi() {
		return allLinshi;
	}

	public void setAllLinshi(Integer allLinshi) {
		this.allLinshi = allLinshi;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}
}
