package com.shangde.edu.purse.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Money implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer cusId;
    private BigDecimal money;
    private Integer status;
    private String creator;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
	public Integer getCusId() {
		return cusId;
	}
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}    
    
}
