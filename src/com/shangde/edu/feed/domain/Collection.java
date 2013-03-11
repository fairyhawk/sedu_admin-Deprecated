package com.shangde.edu.feed.domain;

import java.io.Serializable;
import java.util.Date;

public class Collection implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer videoId;
	private Integer userId;
	private Integer courseId;
	private Date statisticsTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVideoId() {
		return videoId;
	}
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Date getStatisticsTime() {
		return statisticsTime;
	}
	public void setStatisticsTime(Date statisticsTime) {
		this.statisticsTime = statisticsTime;
	}
	
}
