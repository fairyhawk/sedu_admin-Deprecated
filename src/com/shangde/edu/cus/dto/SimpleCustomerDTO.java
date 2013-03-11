package com.shangde.edu.cus.dto;

import java.io.Serializable;
/**
 * 简单用户DTO
 * @author chenshuai
 *
 */
public class SimpleCustomerDTO implements Serializable {
	private int cusId;
	private String cusName;
	private int cusType;

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public int getCusType() {
		return cusType;
	}

	public void setCusType(int cusType) {
		this.cusType = cusType;
	}
}
