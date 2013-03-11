package com.shangde.edu.feed.domain;

import java.io.Serializable;

/**
 * 任务模型
 * 
 * @author Libg
 * 
 */
public class TaskList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int subjectId;
	private int templateId;
	private String name;
	private String content;
	private String userData;
	private int sendMode;

	private int planningModeId;
	private PlanningMode planningMode;

	private int sendTime;
	private String regularlySentTime;
	private int sendObject;
	private String videoId;
	private int status;
	private java.util.Date pubdate;
	private java.util.Date modified;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	public int getSendMode() {
		return sendMode;
	}

	public void setSendMode(int sendMode) {
		this.sendMode = sendMode;
	}

	public int getPlanningModeId() {
		return planningModeId;
	}

	public void setPlanningModeId(int planningModeId) {
		this.planningModeId = planningModeId;
	}

	public int getSendTime() {
		return sendTime;
	}

	public void setSendTime(int sendTime) {
		this.sendTime = sendTime;
	}

	public int getSendObject() {
		return sendObject;
	}

	public void setSendObject(int sendObject) {
		this.sendObject = sendObject;
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

	public PlanningMode getPlanningMode() {
		return planningMode;
	}

	public void setPlanningMode(PlanningMode planningMode) {
		this.planningMode = planningMode;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoId() {
		return videoId;
	}

	public String getRegularlySentTime() {
		return regularlySentTime;
	}

	public void setRegularlySentTime(String regularlySentTime) {
		this.regularlySentTime = regularlySentTime;
	}
}
