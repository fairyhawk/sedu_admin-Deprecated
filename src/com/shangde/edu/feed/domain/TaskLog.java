package com.shangde.edu.feed.domain;

import java.io.Serializable;

/**
 * 任务记录模型
 * 
 * @author Libg
 * 
 */
public class TaskLog implements Serializable {

	private int id;

	private int taskListId;// 任务表id
	private TaskList taskList;// 任务模型对象

	private int urlClickNum;
	private int total;
	private int sendCount;
	private int sendErrorCount;
	private int sendNum;
	private int status;
	private java.util.Date pubdate;
	private java.util.Date modified;

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

	public int getUrlClickNum() {
		return urlClickNum;
	}

	public void setUrlClickNum(int urlClickNum) {
		this.urlClickNum = urlClickNum;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	public int getSendErrorCount() {
		return sendErrorCount;
	}

	public void setSendErrorCount(int sendErrorCount) {
		this.sendErrorCount = sendErrorCount;
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

	public TaskList getTaskList() {
		return taskList;
	}

	public void setTaskList(TaskList taskList) {
		this.taskList = taskList;
	}

	public int getSendNum() {
		return sendNum;
	}

	public void setSendNum(int sendNum) {
		this.sendNum = sendNum;
	}
}
