package com.shangde.edu.hadata.domain;

import java.io.Serializable;
import java.util.Date;

public class CusWatchRecord implements Serializable {
	private Integer id;
	private Integer cusId;
	private String email;
	private Integer sellWayId;
	private Integer courseId;
	private Integer videoPointId;
	private String videoName;
	private String teacher;
	private Date startTime;
	private Date endTime;
	private Integer watchAllTime;
	private Integer subjectId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCusId() {
		return cusId;
	}
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getSellWayId() {
		return sellWayId;
	}
	public void setSellWayId(Integer sellWayId) {
		this.sellWayId = sellWayId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getVideoPointId() {
		return videoPointId;
	}
	public void setVideoPointId(Integer videoPointId) {
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
	public Integer getWatchAllTime() {
		return watchAllTime;
	}
	public void setWatchAllTime(Integer watchAllTime) {
		this.watchAllTime = watchAllTime;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

}
