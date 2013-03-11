package com.shangde.edu.cou.domain;

import java.io.Serializable;
import java.util.Date;

public class RebatePrice implements Serializable {
	private Integer rebateId;//促销价格主键
	private Integer sellId;//商品ID
	private Float rebatePrice;//促销价格
	private Date rebateBeginTime;//促销开始时间
	private Date rebateEndTime;//促销结束时间
	
	public Integer getRebateId() {
		return rebateId;
	}
	public void setRebateId(Integer rebateId) {
		this.rebateId = rebateId;
	}
	public Integer getSellId() {
		return sellId;
	}
	public void setSellId(Integer sellId) {
		this.sellId = sellId;
	}
	public Float getRebatePrice() {
		return rebatePrice;
	}
	public void setRebatePrice(Float rebatePrice) {
		this.rebatePrice = rebatePrice;
	}
	public Date getRebateBeginTime() {
		return rebateBeginTime;
	}
	public void setRebateBeginTime(Date rebateBeginTime) {
		this.rebateBeginTime = rebateBeginTime;
	}
	public Date getRebateEndTime() {
		return rebateEndTime;
	}
	public void setRebateEndTime(Date rebateEndTime) {
		this.rebateEndTime = rebateEndTime;
	}
}
