package com.shangde.edu.feed.domain;

import java.util.Date;

/**
 * 公告信息表 模型
 * 
 * @author Lee
 * 
 */
public class Notice {

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 类别id
	 */
	private Integer courseCategoryId;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 发布时间
	 */
	private Date pubDate;

	/**
	 * 修改时间
	 */
	private Date modified;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the pubDate
	 */
	public Date getPubDate() {
		return pubDate;
	}

	/**
	 * @param pubDate
	 *            the pubDate to set
	 */
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser
	 *            the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified
	 *            the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @return the courseCategoryId
	 */
	public Integer getCourseCategoryId() {
		return courseCategoryId;
	}

	/**
	 * @param courseCategoryId
	 *            the courseCategoryId to set
	 */
	public void setCourseCategoryId(Integer courseCategoryId) {
		this.courseCategoryId = courseCategoryId;
	}
}
