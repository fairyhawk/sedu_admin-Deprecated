package com.shangde.edu.feed.domain;

import java.io.Serializable;

/**
 * 微学习，定制用户模型
 * 
 * @author Libg
 * 
 */
public class SubscribeUser implements Serializable {

	/**
	 * 记录id
	 */
	private Integer id;
	/**
	 * 邮件
	 */
	private String email;
	/**
	 * 专业id
	 */
	private Integer subjectId;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 发布时间
	 */
	private java.util.Date pubdate;
	/**
	 * 修改时间
	 */
	private java.util.Date modified;

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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the subjectId
	 */
	public Integer getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId
	 *            the subjectId to set
	 */
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
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
	 * @return the pubdate
	 */
	public java.util.Date getPubdate() {
		return pubdate;
	}

	/**
	 * @param pubdate
	 *            the pubdate to set
	 */
	public void setPubdate(java.util.Date pubdate) {
		this.pubdate = pubdate;
	}

	/**
	 * @return the modified
	 */
	public java.util.Date getModified() {
		return modified;
	}

	/**
	 * @param modified
	 *            the modified to set
	 */
	public void setModified(java.util.Date modified) {
		this.modified = modified;
	}

}
