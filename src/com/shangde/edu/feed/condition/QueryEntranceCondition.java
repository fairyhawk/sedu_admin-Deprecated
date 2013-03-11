package com.shangde.edu.feed.condition;

import com.shangde.common.vo.PageQuery;

public class QueryEntranceCondition extends PageQuery implements
		java.io.Serializable {

	/**
	 * 来源id
	 */
	private Integer fromId;
	
	public Integer getFromId() {
		return fromId;
	}

	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * 根据当天时间查询  
	 * pubDate  2011-11-18
	 */
	private String  pubDate;
	
}
