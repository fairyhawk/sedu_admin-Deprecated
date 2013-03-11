package com.shangde.edu.feed.condition;

import java.io.Serializable;

import com.shangde.common.vo.PageResult;

public class QueryUserStatLogCondition extends PageResult implements
		Serializable {

	public String toString() {
		// TODO Auto-generated method stub
		return id + "" + cusId + "--->email" + email + "--->date" + date
				+ "-->pubDate" + pubDate + "-->fromId" + fromId;
	}

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
	 * 日期
	 * 
	 * 可以不同业务的查询条件，可以是任意格式 例如1：yyyy-MM-dd
	 */
	private String date;

	/**
	 * 时间
	 */
	private String pubDate;
	/**
	 * 来源id
	 */
	private Integer fromId;

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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

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

	public Integer getFromId() {
		return fromId;
	}

	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

}
