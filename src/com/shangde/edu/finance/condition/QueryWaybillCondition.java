package com.shangde.edu.finance.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class QueryWaybillCondition extends PageQuery {
	private String consigneePhone;//收货人电话
	private String waybillCode;//运单号
	private String expressCompany;//快递公司
	private String consigneeEmail;//电子邮箱
	private Date createBeginDate;//创建开始时间===》用于查询条件中的时间选择开始时间
	private Date createEndDate;//创建结束时间===》用于查询条件中的时间结束时间
	public String getConsigneePhone() {
		return consigneePhone;
	}
	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}
	public String getWaybillCode() {
		return waybillCode;
	}
	public void setWaybillCode(String waybillCode) {
		this.waybillCode = waybillCode;
	}
	public String getExpressCompany() {
		return expressCompany;
	}
	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}
	public String getConsigneeEmail() {
		return consigneeEmail;
	}
	public void setConsigneeEmail(String consigneeEmail) {
		this.consigneeEmail = consigneeEmail;
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
