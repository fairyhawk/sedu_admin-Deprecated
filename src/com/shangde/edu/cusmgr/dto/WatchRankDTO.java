package com.shangde.edu.cusmgr.dto;

import java.io.Serializable;


public class WatchRankDTO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4895641135826715003L;

	private String cusId;
	
	private String email;
	
	private String photo;
	
	private String userName;
	
	private String sex;

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
