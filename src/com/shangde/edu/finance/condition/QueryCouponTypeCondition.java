package com.shangde.edu.finance.condition;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class QueryCouponTypeCondition extends PageQuery {
	private int state;

	private int cType;

	private Date startTime;

	private Date endTime;
	
	private int searchType;
	
	private String keyword;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCType() {
		return cType;
	}

	public int getSearchType() {
		return searchType;
	}

	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) throws UnsupportedEncodingException {
		this.keyword =URLDecoder.decode(keyword,"UTF-8").replace(" ", "");
	}

	public void setCType(int cType) {
		this.cType = cType;
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

}