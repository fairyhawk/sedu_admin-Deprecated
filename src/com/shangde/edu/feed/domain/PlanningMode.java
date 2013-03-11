package com.shangde.edu.feed.domain;

import java.io.Serializable;

/**
 * 计划模式模型
 * 
 * @author Libg
 * 
 */
public class PlanningMode implements Serializable {

	private int id;
	private String name;
	private String cronExpression;
	private String remark;
	private int status;
	private java.util.Date pubdate;
	private java.util.Date modified;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public java.util.Date getPubdate() {
		return pubdate;
	}

	public void setPubdate(java.util.Date pubdate) {
		this.pubdate = pubdate;
	}

	public java.util.Date getModified() {
		return modified;
	}

	public void setModified(java.util.Date modified) {
		this.modified = modified;
	}

}
