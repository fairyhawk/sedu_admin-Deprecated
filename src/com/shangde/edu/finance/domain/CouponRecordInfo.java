package com.shangde.edu.finance.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 发送优惠券短信记录实体
 * @author Liming
 * Date:2011-9-30
 */

public class CouponRecordInfo implements Serializable {
	private int id;          // id
	private String title;	 //短信内容
	private int mobileSum;   //手机的条数
	private int conponSum;   //优惠编码条数
	private String subjectName; //项目名字
	private int couponCatorgryId;  //优惠券ID
	private Date  addTime;         //发送时间
	private String addName; 		//发送人
	
	
	private int subjectId;
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getAddName() {
		return addName;
	}
	public void setAddName(String addName) {
		this.addName = addName;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMobileSum() {
		return mobileSum;
	}
	public void setMobileSum(int mobileSum) {
		this.mobileSum = mobileSum;
	}
	public int getConponSum() {
		return conponSum;
	}
	public void setConponSum(int conponSum) {
		this.conponSum = conponSum;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getCouponCatorgryId() {
		return couponCatorgryId;
	}
	public void setCouponCatorgryId(int couponCatorgryId) {
		this.couponCatorgryId = couponCatorgryId;
	}
	
}
