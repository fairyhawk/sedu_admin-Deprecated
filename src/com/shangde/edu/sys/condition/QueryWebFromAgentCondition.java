package com.shangde.edu.sys.condition;

import com.shangde.common.vo.PageQuery;

public class QueryWebFromAgentCondition extends PageQuery {
	
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
	 * 创建者
	 */
	private int userId;
	/**
	 * 开始时间
	 * @return
	 */
	private java.util.Date beginTime;
	/**
	 * 结束时间
	 * @return
	 */
	private java.util.Date endTime;
	/**
	 * groupId
	 * @return
	 */
	
	
	public java.util.Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(java.util.Date beginTime) {
		this.beginTime = beginTime;
	}
	public java.util.Date getEndTime() {
		return endTime;
	}
	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}
	
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setWebAgentInfo(String webAgentInfo) {
		this.webAgentInfo = webAgentInfo;
	}
	public String getWebAgentInfo() {
		return webAgentInfo;
	}
	public void setWebFromInfo(String webFromInfo) {
		this.webFromInfo = webFromInfo;
	}
	public String getWebFromInfo() {
		return webFromInfo;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	
}
