package com.shangde.edu.finance.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class QueryInvoiceCondition extends PageQuery {
	private String orderCode;//订单编码
	private String waybillCode;//运单号
	private String phoneNumber;//手机号码
	private String invoiceCode;//发票号码
	private String payType;//支付方式
	private String invoiceStatus;//发票状态
	private Date createBeginDate;//创建开始时间===》用于查询条件中的时间选择开始时间
	private Date createEndDate;//创建结束时间===》用于查询条件中的时间结束时间
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getWaybillCode() {
		return waybillCode;
	}
	public void setWaybillCode(String waybillCode) {
		this.waybillCode = waybillCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	public Date getCreateBeginDate() {
		return createBeginDate;
	}
	public void setCreateBeginDate(Date createBeginDate) {
		this.createBeginDate = createBeginDate;
	}
	public Date getCreateEndDate() {
		return createEndDate;
	}
	public void setCreateEndDate(Date createEndDate) {
		this.createEndDate = createEndDate;
	}
}
