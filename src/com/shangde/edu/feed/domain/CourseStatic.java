package com.shangde.edu.feed.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 课程统计类
 * @author chensong
 *
 */
public class CourseStatic implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer courseId;
	private Integer userId;
	//课程类别
	private Integer courseCategoryId;
	//统计时间 
	private Date statisticsTime;
	
	
	private String courseName;
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	private String userName;
	private Integer usePersonNumber;
	private Integer watchNumber;
	public Integer getUsePersonNumber() {
		return usePersonNumber;
	}
	public void setUsePersonNumber(Integer usePersonNumber) {
		this.usePersonNumber = usePersonNumber;
	}
	public Integer getWatchNumber() {
		return watchNumber;
	}
	public void setWatchNumber(Integer watchNumber) {
		this.watchNumber = watchNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCourseCategoryId() {
		return courseCategoryId;
	}
	public void setCourseCategoryId(Integer courseCategoryId) {
		this.courseCategoryId = courseCategoryId;
	}
	public Date getStatisticsTime() {
		return statisticsTime;
	}
	public void setStatisticsTime(Date statisticsTime) {
		this.statisticsTime = statisticsTime;
	}

	
}
