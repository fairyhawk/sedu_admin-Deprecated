package com.shangde.edu.finance.domain;

import java.io.Serializable;
import java.util.Date;

public class Waybill implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer waybillId;//主键ID
	private String waybillCode;//运单号
	private String expressCompany;//快递公司
	private String goodsCategory;//物品类别
	private String consigneeEmail;//收货人email
	private String consigneePhone;//收货人电话
	private String orderCode;//订单编号
	private Date createDate;//创建时间
	private String creator;//创建人
	private Date updateDate;//更新时间
	private String updateUser;//创建人
	private String waybillStatus;
	private String consigneeName;
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public String getWaybillStatus() {
		return waybillStatus;
	}
	public void setWaybillStatus(String waybillStatus) {
		this.waybillStatus = waybillStatus;
	}
	public Integer getWaybillId() {
		return waybillId;
	}
	public void setWaybillId(Integer waybillId) {
		this.waybillId = waybillId;
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
	public String getGoodsCategory() {
		return goodsCategory;
	}
	public void setGoodsCategory(String goodsCategory) {
		this.goodsCategory = goodsCategory;
	}
	public String getConsigneeEmail() {
		return consigneeEmail;
	}
	public void setConsigneeEmail(String consigneeEmail) {
		this.consigneeEmail = consigneeEmail;
	}
	public String getConsigneePhone() {
		return consigneePhone;
	}
	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
