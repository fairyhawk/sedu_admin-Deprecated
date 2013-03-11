package com.shangde.edu.sys.domain;

import java.util.Date;

/**
 * 访问日志
 * 
 * @author ZHENG QIANG 2012.3.31
 */
public class WebFromAgentLog {

	/** 主键（自增） */
	private Integer id;
	/** 推广人员 */
	private String webAgent;
	/** 推广类型名称 */
	private String webFrom;
	/** 路径 */
	private String webPath;
	/** 项目编号 */
	private Integer subjectId;
	/** 域名 */
	private String domain;
	/** 点击时间 */
	private Date clickTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWebAgent() {
		return webAgent;
	}

	public void setWebAgent(String webAgent) {
		this.webAgent = webAgent;
	}

	public String getWebFrom() {
		return webFrom;
	}

	public void setWebFrom(String webFrom) {
		this.webFrom = webFrom;
	}

	public String getWebPath() {
		return webPath;
	}

	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}

	public Date getClickTime() {
		return clickTime;
	}

	public void setClickTime(Date clickTime) {
		this.clickTime = clickTime;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

}
