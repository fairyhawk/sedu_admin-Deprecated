package com.shangde.edu.feed.domain;

import java.util.Date;

import org.apache.axis2.context.externalize.SafeSerializable;

/**
 * 
 * 微学习  课程类别管理
 */
public class CourseCategory implements SafeSerializable{
	/**
	 * 类别ID
	 */
	private int id;
	/**
	 * 类别名称
	 */
	private String categoryName;
	/**
	 * 类别缩写
	 */
	private String categoryAbbreviation;
	/**
	 * 类别状态     1.删除   2.冻结
	 */
	private int categoryStatus;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 修改时间
	 */
	private Date modifTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryAbbreviation() {
		return categoryAbbreviation;
	}
	public void setCategoryAbbreviation(String categoryAbbreviation) {
		this.categoryAbbreviation = categoryAbbreviation;
	}
	public int getCategoryStatus() {
		return categoryStatus;
	}
	public void setCategoryStatus(int categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getModifTime() {
		return modifTime;
	}
	public void setModifTime(Date modifTime) {
		this.modifTime = modifTime;
	}
	
	
}
