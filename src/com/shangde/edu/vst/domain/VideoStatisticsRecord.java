package com.shangde.edu.vst.domain;

import java.util.Date;

/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:
 *
 * @author		Yangning
 * @date		2012-2-9
 * @version 	修改人:
 * 				修改日期:
 * 				
 *              
 */
public class VideoStatisticsRecord {
	private int id;
	private int cusId;
	private String email;
	private int sellWayId;
	private int courseId;
	private int videoPointId;
	private String videoName;
	private String teacher;
	private Date startTime;
	private Date endTime;
	private int watchAlltime;
	private int subjectId;
	
	//范昕 增加属性
	private String sellWayTitle;
	private String courseName;
	private String subjectName;
	
	/**
	 * @return the sellWayTitle
	 */
	public String getSellWayTitle() {
		return sellWayTitle;
	}
	/**
	 * @param sellWayTitle the sellWayTitle to set
	 */
	public void setSellWayTitle(String sellWayTitle) {
		this.sellWayTitle = sellWayTitle;
	}
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}
	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSellWayId() {
		return sellWayId;
	}
	public void setSellWayId(int sellWayId) {
		this.sellWayId = sellWayId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getVideoPointId() {
		return videoPointId;
	}
	public void setVideoPointId(int videoPointId) {
		this.videoPointId = videoPointId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getWatchAlltime() {
		return watchAlltime;
	}
	public void setWatchAlltime(int watchAlltime) {
		this.watchAlltime = watchAlltime;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
}
