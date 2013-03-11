package com.shangde.edu.cus.domain;

import java.io.Serializable;

/**
 * 
 * @author Yangning
 *	实体类:用户保过协议信息
 */
public class CusProtocalDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int cusId;
	private String cusName;
	private String mobile;
	private String address;
	/**邮编**/
	private String postcode;
	/**身份证**/
	private String identityCard;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
}
