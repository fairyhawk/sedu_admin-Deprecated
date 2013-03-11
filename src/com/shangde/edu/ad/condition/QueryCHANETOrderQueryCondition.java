package com.shangde.edu.ad.condition;

public class QueryCHANETOrderQueryCondition {

	private String webFrom;
	private String user;   
	//开始时间（非必须）
	private String start;
	//结束时间（非必须）
	private String end;
	//订单号（非必须）
	private String orderid;
	//订单状态
	private String orderstatus;

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	/**
	 * @return the webFrom
	 */
	public String getWebFrom() {
		return webFrom;
	}

	/**
	 * @param webFrom
	 *            the webFrom to set
	 */
	public void setWebFrom(String webFrom) {
		this.webFrom = webFrom;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	/**
	 * @return the orderid
	 */
	public String getOrderid() {
		return orderid;
	}

	/**
	 * @param orderid the orderid to set
	 */
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	
}
