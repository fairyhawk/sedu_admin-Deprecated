package com.shangde.edu.exam.dto;

import java.io.Serializable;

public class QstAddQueryDTO implements Serializable{

	private int courseId;
	private int sortId;
	private String isAsr;
	private int qstType;
	private int level;
	private String coursesortName;
	private String courseTitle;
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	public String getIsAsr() {
		return isAsr;
	}
	public void setIsAsr(String isAsr) {
		this.isAsr = isAsr;
	}
	public int getQstType() {
		return qstType;
	}
	public void setQstType(int qstType) {
		this.qstType = qstType;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getCoursesortName() {
		return coursesortName;
	}
	public void setCoursesortName(String coursesortName) {
		this.coursesortName = coursesortName;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	
}
