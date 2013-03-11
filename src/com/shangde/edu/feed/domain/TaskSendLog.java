package com.shangde.edu.feed.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 微学习->任务发送记录
 * 
 * @author Libg
 * 
 */
public class TaskSendLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private int taskListId;// 邮件任务信息表id
	private String email;// 邮箱名称
	private Date pubDate;// 添加时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaskListId() {
		return taskListId;
	}

	public void setTaskListId(int taskListId) {
		this.taskListId = taskListId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
}
