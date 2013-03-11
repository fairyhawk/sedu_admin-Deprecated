package com.shangde.edu.cus.domain;
/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:用户批量增加时实体类
 *
 * @author		Yangning
 * @date		2011-12-5
 * @version 	修改人:
 * 				修改日期:
 * 				
 *              
 */
public class CustomerDTOBath extends Customer{

	private static final long serialVersionUID = 1L;
	
	//原始密码:明文
	private String oriPwd;
	
	private String sellways;

	public String getSellways() {
		return sellways;
	}

	public void setSellways(String sellways) {
		this.sellways = sellways;
	}

	public String getOriPwd() {
		return oriPwd;
	}

	public void setOriPwd(String oriPwd) {
		this.oriPwd = oriPwd;
	}
}
