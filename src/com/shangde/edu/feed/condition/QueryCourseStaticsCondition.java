package com.shangde.edu.feed.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class QueryCourseStaticsCondition extends PageQuery {
	private Integer courseId;
	
	private Date startTime;
	private Date endTime;
	private Integer courseCategoryId;

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

	public Integer getCourseCategoryId() {
		return courseCategoryId;
	}

	public void setCourseCategoryId(Integer courseCategoryId) {
		this.courseCategoryId = courseCategoryId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

}
