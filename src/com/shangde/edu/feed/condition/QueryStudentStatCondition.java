package com.shangde.edu.feed.condition;

import com.shangde.common.vo.PageQuery;

public class QueryStudentStatCondition  extends PageQuery implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 時間条件查询
	 */
	private String searchDate;

	/**
	 * 来源
	 * @return
	 */
	private String fromId;
	
	
	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
}
