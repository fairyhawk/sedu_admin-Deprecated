package com.shangde.edu.feed.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 来源,微学习来源位置
 * 
 * @author Libg
 * 
 */
public class From implements Serializable {

	/**
	 * id
	 */
	private Integer id;
	/**
	 * 名称，标题
	 */
	private String name;
	/**
	 * 备注
	 */
	private String remark;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
