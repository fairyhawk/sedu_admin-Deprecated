package com.shangde.edu.cou.condition;

import com.shangde.common.vo.PageResult;

/**
 * @author fanxin
 *
 */
public class QueryCouFreeGiveCondition extends PageResult {
	
	/**
	 * ID
	 */
    private int Id;
    
    /**
     * 后台管理员 （谁开通的）
     */
    private String userName;
    
    /**
     * 用户ID （给谁开通）
     */
    private int cusId;
    
    /**
     * 订单号 （所属订单号）
     */
    private String ctId; 
  
    /**
     * 创建时间  （开通时间）
     */
    private java.util.Date createTime;

    private java.util.Date startCreateTime;
    
    private java.util.Date endCreateTime;
    private String email;
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getCtId() {
		return ctId;
	}

	public void setCtId(String ctId) {
		this.ctId = ctId;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the startCreateTime
	 */
	public java.util.Date getStartCreateTime() {
		return startCreateTime;
	}

	/**
	 * @param startCreateTime the startCreateTime to set
	 */
	public void setStartCreateTime(java.util.Date startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	/**
	 * @return the endCreateTime
	 */
	public java.util.Date getEndCreateTime() {
		return endCreateTime;
	}

	/**
	 * @param endCreateTime the endCreateTime to set
	 */
	public void setEndCreateTime(java.util.Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	
}