package com.shangde.edu.dis.dto;

import java.util.Date;

public class TopicDTO implements java.io.Serializable {

	private int id;
	// 小组名称
	private String disName;
	private String title;
	private String content;
	private String user;
	// 所属板块
	private String area;
	private int type;
	private int replyCounts;
	// 推荐次数
	private int recCount;
	// 推荐积分
	private int recNum;
	private Date createTime;
	private String cusName;
	private String email;
	private int cusId;
	private String sysUserName;

	// 帖子状态
	private int status;

	// 修改时间
	private Date modified;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the disName
	 */
	public String getDisName() {
		return disName;
	}

	/**
	 * @param disName
	 *            the disName to set
	 */
	public void setDisName(String disName) {
		this.disName = disName;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the replyCounts
	 */
	public int getReplyCounts() {
		return replyCounts;
	}

	/**
	 * @param replyCounts
	 *            the replyCounts to set
	 */
	public void setReplyCounts(int replyCounts) {
		this.replyCounts = replyCounts;
	}

	/**
	 * @return the recCount
	 */
	public int getRecCount() {
		return recCount;
	}

	/**
	 * @param recCount
	 *            the recCount to set
	 */
	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}

	/**
	 * @return the recNum
	 */
	public int getRecNum() {
		return recNum;
	}

	/**
	 * @param recNum
	 *            the recNum to set
	 */
	public void setRecNum(int recNum) {
		this.recNum = recNum;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the cusName
	 */
	public String getCusName() {
		return cusName;
	}

	/**
	 * @param cusName
	 *            the cusName to set
	 */
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
