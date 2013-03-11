package com.shangde.edu.finance.domain;

import java.io.Serializable;
import java.util.Date;

public class Coupon implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String title;   //标题

	private Date createTime;  //创建时间
 
	private Coupontype couponType;  //类型
	
	private int parentId;	

	private Integer state;

	private Integer paystate;

	private String picPath;
	
    private int contractId;
    
    private String operatingName;  //创建人


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Coupontype getCouponType() {
		return couponType;
	}

	public void setCouponType(Coupontype couponType) {
		this.couponType = couponType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getPaystate() {
		return paystate;
	}

	public void setPaystate(Integer paystate) {
		this.paystate = paystate;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public String getOperatingName() {
		return operatingName;
	}

	public void setOperatingName(String operatingName) {
		this.operatingName = operatingName;
	}
	
	
}
