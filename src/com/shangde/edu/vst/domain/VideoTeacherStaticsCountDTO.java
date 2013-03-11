package com.shangde.edu.vst.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 教师（购买，试听）人数及时长统计
 * @author HQL
 */
public class VideoTeacherStaticsCountDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;//主键ID

	private String teacherName;//老师名称
	
	private String projectName;//项目名称
	
	private Integer projectId;//项目ID
	
	private Long buyCusCount;//购买观看人数
	
	private Long buyCusAllWatchTime;//购买观看总时长
	
	private Long tryCusCount;//试听观看人数
	
	private Long tryCusAllWatchTime;//试听观看总时长
	
	private Date startTime;//开始时间
	
	private Date endTime;//结束时间
	
	private Long buyCusAllWatchTimeAvg;//购买平均时长
	
	private Long tryCusAllWatchTimeAvg;//试听平均时长
	
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Long getBuyCusCount() {
		return buyCusCount;
	}

	public void setBuyCusCount(Long buyCusCount) {
		this.buyCusCount = buyCusCount;
	}

	public Long getBuyCusAllWatchTime() {
		return buyCusAllWatchTime;
	}

	public void setBuyCusAllWatchTime(Long buyCusAllWatchTime) {
		this.buyCusAllWatchTime = buyCusAllWatchTime;
	}

	public Long getTryCusCount() {
		return tryCusCount;
	}

	public void setTryCusCount(Long tryCusCount) {
		this.tryCusCount = tryCusCount;
	}

	public Long getTryCusAllWatchTime() {
		return tryCusAllWatchTime;
	}

	public void setTryCusAllWatchTime(Long tryCusAllWatchTime) {
		this.tryCusAllWatchTime = tryCusAllWatchTime;
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

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getBuyCusAllWatchTimeAvg() {
		return buyCusAllWatchTimeAvg;
	}

	public void setBuyCusAllWatchTimeAvg(Long buyCusAllWatchTimeAvg) {
		this.buyCusAllWatchTimeAvg = buyCusAllWatchTimeAvg;
	}

	public Long getTryCusAllWatchTimeAvg() {
		return tryCusAllWatchTimeAvg;
	}

	public void setTryCusAllWatchTimeAvg(Long tryCusAllWatchTimeAvg) {
		this.tryCusAllWatchTimeAvg = tryCusAllWatchTimeAvg;
	}
}
