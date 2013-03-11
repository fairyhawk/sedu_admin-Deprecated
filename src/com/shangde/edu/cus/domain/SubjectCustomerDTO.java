package com.shangde.edu.cus.domain;
import java.io.Serializable;
/**
 * 简单用户DTO
 * @author chenshuai
 *
 */
public class SubjectCustomerDTO implements Serializable {
	private int cusId;
	private String cusName;
	private int contractNumber;
	private int contractNumberPay;

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

	public int getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(int contractNumber) {
		this.contractNumber = contractNumber;
	}

	public int getContractNumberPay() {
		return contractNumberPay;
	}

	public void setContractNumberPay(int contractNumberPay) {
		this.contractNumberPay = contractNumberPay;
	}

}
