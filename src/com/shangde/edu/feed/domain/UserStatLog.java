/**
 * 
 */
package com.shangde.edu.feed.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 使用用户,详细记录模型
 * 
 * 根据cookie登录前是否生效/点击进行记录
 * 
 * @author Libg
 * 
 */
public class UserStatLog implements Serializable {

	/**
	 * id
	 */
	private Integer id;
	/**
	 * 用户id
	 */
	private Integer cusId;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 来源id
	 */
	private Integer fromId;

	/**
	 * 状态[1=重新登录,2=左侧菜单]
	 */
	private Integer status;
	/**
	 * 发布时间
	 */
	private Date pubDate;

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
	 * @return the cusId
	 */
	public Integer getCusId() {
		return cusId;
	}

	/**
	 * @param cusId
	 *            the cusId to set
	 */
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
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
	 * @return the fromId
	 */
	public Integer getFromId() {
		return fromId;
	}

	/**
	 * @param fromId
	 *            the fromId to set
	 */
	public void setFromId(Integer fromId) {
		this.fromId = fromId;
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
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

}
