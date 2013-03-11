package com.shangde.edu.crm.dto;

import java.io.Serializable;
import java.util.Date;

public class ChanceRecordDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 销售机会id
	 */
	private int id;
	/**
     * 机会来源
     */
    private int origin;
    /**
     * 项目id
     */
    private int subjectId;
    /**
     * 用户跟踪状态
     * 1放弃2跟踪3热点4成交
     */
    private int followStatus;
    /**
     * 创建时间
     */
    private java.util.Date chanceStime;
    /**
     * 最后沟通时间
     */
    private Date endCommTime;
    /**
     * 最后指派时间
     */
    private Date chanceUtime;
    /**
     * 销售坐席id
     */
    private int userId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrigin() {
		return origin;
	}
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getFollowStatus() {
		return followStatus;
	}
	public void setFollowStatus(int followStatus) {
		this.followStatus = followStatus;
	}
	public java.util.Date getChanceStime() {
		return chanceStime;
	}
	public void setChanceStime(java.util.Date chanceStime) {
		this.chanceStime = chanceStime;
	}
	public Date getEndCommTime() {
		return endCommTime;
	}
	public void setEndCommTime(Date endCommTime) {
		this.endCommTime = endCommTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getChanceUtime() {
		return chanceUtime;
	}
	public void setChanceUtime(Date chanceUtime) {
		this.chanceUtime = chanceUtime;
	}

}
