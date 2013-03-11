package com.shangde.edu.sys.domain;

import java.io.Serializable;

public class WebFromAgent implements Serializable {
	
	/**
	 * 主键id
	 */
	private int id;
	/**
	 * 推广类型名称
	 */
	private String webFrom;
	/**
	 * webFrom备注信息
	 */
	private String webFromInfo;
	/**
	 * 推广人员
	 */
	private String webAgent;
	/**
	 * webAgent备注信息
	 */
	private String webAgentInfo;
	/**
	 * 类型
	 */
	private int typeId;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 修改时间
	 */
	private java.util.Date updateTime;
	/**
	 * 创建者
	 */
	private String loginName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWebFrom() {
		return webFrom;
	}
	public void setWebFrom(String webFrom) {
		this.webFrom = webFrom;
	}
	public String getWebAgent() {
		return webAgent;
	}
	public void setWebAgent(String webAgent) {
		this.webAgent = webAgent;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public void setWebFromInfo(String webFromInfo) {
		this.webFromInfo = webFromInfo;
	}
	public String getWebFromInfo() {
		return webFromInfo;
	}
	public void setWebAgentInfo(String webAgentInfo) {
		this.webAgentInfo = webAgentInfo;
	}
	public String getWebAgentInfo() {
		return webAgentInfo;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginName() {
		return loginName;
	}
	
}
