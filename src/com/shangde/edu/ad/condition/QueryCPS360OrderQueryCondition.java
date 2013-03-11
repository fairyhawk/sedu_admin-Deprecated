package com.shangde.edu.ad.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

/**
 * 360CPS订单查询的查询条件。
 * 
 * @author ZHENG QIANG
 */
public class QueryCPS360OrderQueryCondition extends PageQuery {

	private String webFrom;
	private String[] orderIds;
	private Date startTime;
	private Date endTime;
	private Date updstartTime;
	private Date updendTime;
	private String lastOrderId;

	public String getWebFrom() {
		return webFrom;
	}

	public void setWebFrom(String webFrom) {
		this.webFrom = webFrom;
	}

	public String[] getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String[] orderIds) {
		this.orderIds = orderIds;
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

	public Date getUpdstartTime() {
		return updstartTime;
	}

	public void setUpdstartTime(Date updstartTime) {
		this.updstartTime = updstartTime;
	}

	public Date getUpdendTime() {
		return updendTime;
	}

	public void setUpdendTime(Date updendTime) {
		this.updendTime = updendTime;
	}

	public String getLastOrderId() {
		return lastOrderId;
	}

	public void setLastOrderId(String lastOrderId) {
		this.lastOrderId = lastOrderId;
	}

}
