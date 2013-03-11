package com.shangde.edu.feed.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 微学习，针对某页面的浏览器记录模型
 * 
 * @author Lee
 * 
 */
public class BrowseLog implements Serializable {

	/**
	 * id
	 */
	private Integer id;
	/**
	 * 用户id
	 */
	private Integer cusId;
	/**
	 * ip地址
	 */
	private String ip;
	/**
	 * ua,浏览器头部信息
	 */
	private String ua;

	/**
	 * 类型
	 */
	private Integer type;

	/**
	 * 来源id
	 */
	private Integer fromId;
	/**
	 * 头部信息
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
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the ua
	 */
	public String getUa() {
		return ua;
	}

	/**
	 * @param ua
	 *            the ua to set
	 */
	public void setUa(String ua) {
		this.ua = ua;
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
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
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
}
