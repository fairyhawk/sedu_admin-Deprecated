package com.shangde.edu.cus.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class QueryCustomerFeedbackCondition extends PageQuery{
	
	private Integer id;
	
	private String email;
	
	private Integer qq;
	
	private String mobile;
	
	private Integer province;
	
	private Integer city;
	
	/**反馈信息状态 0未解决 1已解决**/
	private Integer status;
	/**--项目专业Id--**/
	private Integer subject_id;
	
	/**--用户反馈描述信息--**/
	private String description;
	
	/**运营商id**/
	private Integer sp;
	/**网络带宽**/
	private Integer bandwidth;
	
	private Date createTime;
	
	/**后台查询 开始时间条件**/
	private Date startTime;
	
	/**后台查询结束时间条件**/
	private Date endTime;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getQq() {
		return qq;
	}

	public void setQq(Integer qq) {
		this.qq = qq;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(Integer subject_id) {
		this.subject_id = subject_id;
	}


	public Integer getSp() {
		return sp;
	}

	public void setSp(Integer sp) {
		this.sp = sp;
	}

	public Integer getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(Integer bandwidth) {
		this.bandwidth = bandwidth;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
