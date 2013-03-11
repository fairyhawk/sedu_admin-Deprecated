/**
 * 
 */
package com.shangde.edu.feed.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 微学习入口页面统计 reg*_mg.html
 * 
 * @author Libg
 * 
 */
public class Entrance implements Serializable {

	/**
	 * id
	 */
	private Integer id;
	/**
	 * ad广告id
	 */
	private Integer adId;
	/**
	 * 来源id
	 */
	private Integer fromId;
	/**
	 * ip地址
	 */
	private String ip;
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
	 * @return the adId
	 */
	public Integer getAdId() {
		return adId;
	}

	/**
	 * @param adId
	 *            the adId to set
	 */
	public void setAdId(Integer adId) {
		this.adId = adId;
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
}
