package com.shangde.edu.res.domain;

import java.io.Serializable;
import java.util.Date;

public class VedioCount implements Serializable{
	
	private int id;
	
	private int userId;
	
	private int countnum;
	
	private Date counttime;
	
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getCountnum() {
		return countnum;
	}

	public void setCountnum(int countnum) {
		this.countnum = countnum;
	}

	public Date getCounttime() {
		return counttime;
	}

	public void setCounttime(Date counttime) {
		this.counttime = counttime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
}
