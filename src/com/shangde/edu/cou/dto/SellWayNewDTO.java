package com.shangde.edu.cou.dto;

import java.io.Serializable;
import java.util.Date;

public class SellWayNewDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sellId; //商品ID
	private String sellName; //商品名称
	private String subjectName; //商品所属项目
	private Double sellPrice; //商品售卖价格
	private Double cashRecordPrice;//商品售出实收价格
	private String email; //购买用户邮箱
	private Date addTime; //商品创建时间（上架时间）
	private Date beganSellTime; //商品开售时间
	private Date createTime; //用户购买时间
	private Date shopPayTime; //商品本单确认收入时间(不全)
	private Date validityTime; //商品服务有效期
	private Integer validityNum; //商品服务有效期
	private Date stopSellTime; //商品停销时间
	private Date dropTime; //商品下架时间
	private Date stopServiceTime; //停止商品服务时间
	private Date payTime; //商品本单确认收入时间（支付时间 ，全）
	private Date upTime;//上架时间
	private Date confirmTime;//确认时间
	public Integer getSellId() {
		return sellId;
	}
	public void setSellId(Integer sellId) {
		this.sellId = sellId;
	}
	public String getSellName() {
		return sellName;
	}
	public void setSellName(String sellName) {
		this.sellName = sellName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public Double getCashRecordPrice() {
		return cashRecordPrice;
	}
	public void setCashRecordPrice(Double cashRecordPrice) {
		this.cashRecordPrice = cashRecordPrice;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getBeganSellTime() {
		return beganSellTime;
	}
	public void setBeganSellTime(Date beganSellTime) {
		this.beganSellTime = beganSellTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getShopPayTime() {
		return shopPayTime;
	}
	public void setShopPayTime(Date shopPayTime) {
		this.shopPayTime = shopPayTime;
	}
	public Date getValidityTime() {
		return validityTime;
	}
	public void setValidityTime(Date validityTime) {
		this.validityTime = validityTime;
	}
	public Integer getValidityNum() {
		return validityNum;
	}
	public void setValidityNum(Integer validityNum) {
		this.validityNum = validityNum;
	}
	public Date getStopSellTime() {
		return stopSellTime;
	}
	public void setStopSellTime(Date stopSellTime) {
		this.stopSellTime = stopSellTime;
	}
	public Date getDropTime() {
		return dropTime;
	}
	public void setDropTime(Date dropTime) {
		this.dropTime = dropTime;
	}
	public Date getStopServiceTime() {
		return stopServiceTime;
	}
	public void setStopServiceTime(Date stopServiceTime) {
		this.stopServiceTime = stopServiceTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getUpTime() {
		return upTime;
	}
	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}
	public Date getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	
}

	
   
	
    
    
