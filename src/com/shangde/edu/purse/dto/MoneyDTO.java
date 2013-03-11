package com.shangde.edu.purse.dto;

import com.shangde.edu.purse.domain.Money;

public class MoneyDTO extends Money {

	private static final long serialVersionUID = 1L;
	private String userAccount;//用户账号
	private String mobile;//手机
	private String accountStatus;//账户状态
	
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	

}
