package com.shangde.edu.cms.condition;

import com.shangde.common.vo.PageResult;

public class QueryTmpHistory extends PageResult {
	
	/**
	 * 模板记录ID
	 */
	private int tmpId;
	
	/**
	 * 模板记录名称
	 */
	private String tmpName;
	
	/**
	 * 模板记录类型
	 */
	private String tmpType;
	
	/**
	 * 按时间搜索模板记录时：开始时间
	 */
	private String startTime;
	
	/**
	 * 按时间搜索模板记录时：结束时间
	 */
	private String endTime;
	
	public int getTmpId() {
		return tmpId;
	}

	public void setTmpId(int tmpId) {
		this.tmpId = tmpId;
	}

	public String getTmpName() {
		return tmpName;
	}

	public void setTmpName(String tmpName) {
		this.tmpName = tmpName;
	}

	public String getTmpType() {
		return tmpType;
	}

	public void setTmpType(String tmpType) {
		this.tmpType = tmpType;
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
