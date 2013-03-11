package com.shangde.edu.feed.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 微学习，ad广告统计记录模型
 * 
 * @author Libg
 * 
 */
public class AdStat implements Serializable {

	/**
	 * id
	 */
	private Integer id;
	/**
	 * ad表id
	 */
	private Integer adId;
	/**
	 * 用户id
	 */
	private Integer cusId;
	/**
	 * ip地址
	 */
	private String ip;
	/**
	 * ua，浏览器头部信息
	 */
	private String ua;
	/**
	 * 发布时间
	 */
	private Date pubDate;

	/**
	 * 广告对象
	 */
	private Ad ad;

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
	 * @return the ad
	 */
	public Ad getAd() {
		return ad;
	}

	/**
	 * @param ad the ad to set
	 */
	public void setAd(Ad ad) {
		this.ad = ad;
	}
}
