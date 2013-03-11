package com.shangde.edu.crm.condition;

import java.io.Serializable;

import com.shangde.common.vo.PageQuery;

public class QueryStatCondition  extends PageQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private int subjectId;
	private int origin;
	private int consultStatus;
	private String startTime;
	private String endTime;
	private int groupId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getOrigin() {
		return origin;
	}
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	public int getConsultStatus() {
		return consultStatus;
	}
	public void setConsultStatus(int consultStatus) {
		this.consultStatus = consultStatus;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
