package com.shangde.edu.cms.domain;

import java.io.Serializable;
import java.util.Date;

public class TemplateHistory implements Serializable{
	
	/**
	 * 模板记录ID
	 */
	private int id;
	
	/**
	 * 模板ID
	 */
	private int tmpId;
	
	/**
	 * 模板记录名称
	 */
	private String tmpName;
	
	/**模板记录内容
	 * 
	 */
	private String tmpContent;
	
	/**
	 * 模板记录类型
	 */
	private String tmpType;
	
	/**
	 * 用户ID
	 */
	private int userId;
	
	/**
	 * 模板记录添加时间
	 */
	private Date tmpTime;
	
	/**
	 * 登陆名
	 */
	private String loginName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTmpId() {
		return tmpId;
	}
	public void setTmpId(int tmpId) {
		this.tmpId = tmpId;
	}
	public String getTmpName() {
		return tmpName;
	}
	public void setTmpName(String tmpName) {
		this.tmpName = tmpName;
	}
	public String getTmpContent() {
		return tmpContent;
	}
	public void setTmpContent(String tmpContent) {
		this.tmpContent = tmpContent;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getTmpTime() {
		return tmpTime;
	}
	public void setTmpTime(Date tmpTime) {
		this.tmpTime = tmpTime;
	}
	public String getTmpType() {
		return tmpType;
	}
	public void setTmpType(String tmpType) {
		this.tmpType = tmpType;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
}
