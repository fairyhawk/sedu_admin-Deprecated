package com.shangde.edu.feed.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户使用行为记录模型
 * 
 * @author Libg
 * 
 */
public class UserUse implements Serializable {

	/**
	 * 记录id
	 */
	private Integer id;
	/**
	 * ip地址
	 */
	private String ip;
	/**
	 * ua，浏览器头部信息
	 */
	private String ua;
	/**
	 * 类型[1=注册点击,2=注册成功,3=登录点击,4=登录成功]
	 */
	private Integer type;

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
	 * 市场部门-提供参数{每当用户登录/注册成功}该字段才有值
	 */
	private String utma;
	/**
	 * 添加时间
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
	 * @return the utma
	 */
	public String getUtma() {
		return utma;
	}

	/**
	 * @param utma
	 *            the utma to set
	 */
	public void setUtma(String utma) {
		this.utma = utma;
	}

}
