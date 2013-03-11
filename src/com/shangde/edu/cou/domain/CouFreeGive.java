package com.shangde.edu.cou.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.shangde.edu.cou.dto.CourseIsAuditionDTO;
import com.shangde.edu.exam.domain.Exampaper;
import com.shangde.edu.res.domain.Vedio;
import com.shangde.edu.sys.domain.Grade;
import com.shangde.edu.sys.domain.Subject;

/**
 * 免费赠送表
 * @author fanxin
 */
public class CouFreeGive implements Serializable{

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
    
    private String email;
    private String crInfo;
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCrInfo() {
		return crInfo;
	}

	public void setCrInfo(String crInfo) {
		this.crInfo = crInfo;
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


    

}
