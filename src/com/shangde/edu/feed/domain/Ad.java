package com.shangde.edu.feed.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 微学习，广告模型
 * 
 * @author Libg
 * 
 */
public class Ad implements Serializable {

	/**
	 * id
	 */
	private Integer id;
	/**
	 * 广告名称
	 */
	private String name;
	/**
	 * 广告备注
	 */
	private String remark;
	/**
	 * 状态[1=正常,-1=屏蔽]
	 */
	private Integer status;
	/**
	 * 发布时间
	 */
	private Date pubDate;
	/**
	 * 修改时间
	 */
	private Date modified;

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

}
