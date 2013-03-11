package com.shangde.edu.cms.condition;

import java.io.Serializable;

import com.shangde.common.vo.PageQuery;

public class QueryAcmentCondition extends PageQuery implements Serializable {
	/**
	 * 个人中信课程公告查询
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 公告标题
	 */
	private String title;
	
	/**
	 * 专业ID
	 */
	private Integer subjectId;
	
	/**
	 * 根据时间查询公告 开始时间
	 */
	private String startTime;
	
	/**
	 * 根据时间查询公告 结束时间
	 */
	private String endTime;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
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
