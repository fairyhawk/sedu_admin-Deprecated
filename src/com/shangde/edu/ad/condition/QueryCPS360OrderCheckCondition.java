package com.shangde.edu.ad.condition;

import com.shangde.common.vo.PageQuery;

/**
 * 360CPS对账查询的查询条件。
 * 
 * @author ZHENG QIANG
 */
public class QueryCPS360OrderCheckCondition extends PageQuery {

	private String webFrom;
	private Integer billMonthYear;
	private Integer billMonthMonth;
	private String lastOrderId;

	public String getWebFrom() {
		return webFrom;
	}

	public void setWebFrom(String webFrom) {
		this.webFrom = webFrom;
	}

	public Integer getBillMonthYear() {
		return billMonthYear;
	}

	public void setBillMonthYear(Integer billMonthYear) {
		this.billMonthYear = billMonthYear;
	}

	public Integer getBillMonthMonth() {
		return billMonthMonth;
	}

	public void setBillMonthMonth(Integer billMonthMonth) {
		this.billMonthMonth = billMonthMonth;
	}

	public String getLastOrderId() {
		return lastOrderId;
	}

	public void setLastOrderId(String lastOrderId) {
		this.lastOrderId = lastOrderId;
	}

}
