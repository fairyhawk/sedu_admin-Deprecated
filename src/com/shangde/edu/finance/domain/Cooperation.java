package com.shangde.edu.finance.domain;

import java.io.Serializable;

/**
 * 合作商entity
 * @author Liming;
 *
 */

public class Cooperation implements Serializable {

    private Integer id;   	//ID
    
    private String companyName;   //合作商名字
    	
    private String companyCode;		//公司代码用于优惠券的标识

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	

}