package com.shangde.edu.cms.domain;

import java.io.Serializable;
import java.util.Date;

public class Announcement implements Serializable {

	/**
	 * 个人中心课程公告
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 公告ID
	 */
	private int id ;		//公告ID
	/**
	 * 专业ID
	 */
	private int subjectId;	//专业ID
	
	/**
	 * 公告标题
	 */
	private String title;	//公告标题
	
	/**
	 * 公告内容
	 */
	private String content;	//公告内容
	
	/**
	 * 公告发布人
	 */
	private String author;	//公告发布人
	
	/**
	 * 点击数
	 */
	private int clickNum;	//点击数
	
	/**
	 * 公告添加时间
	 */
	private Date addTime;	//公告添加时间
	
	/**
	 * 状态  0隐藏1显示
	 */
	private int status;		//状态  0隐藏1显示
	
	/**
	 * 专业名称（非数据字段）
	 */
	private String subjectName;	//专业名称（非数据字段）
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getClickNum() {
		return clickNum;
	}
	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
