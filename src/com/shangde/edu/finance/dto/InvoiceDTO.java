package com.shangde.edu.finance.dto;

import java.io.Serializable;

import com.shangde.edu.finance.domain.Invoice;

public class InvoiceDTO extends Invoice implements Serializable {

	private static final long serialVersionUID = 1L;
	private String payType;//支付方式
	private String mobile;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}

}
