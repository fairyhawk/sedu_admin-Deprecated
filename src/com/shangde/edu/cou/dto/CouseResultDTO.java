package com.shangde.edu.cou.dto;

import java.io.Serializable;

public class CouseResultDTO implements Serializable {
	
	/**
	 * 课程
	 */
	private String course;
	
	/**
	 * 推荐课程
	 */
	private String tjCourse;
	
	/**
	 * 相关课程
	 */
	private String xgCourse;
	
	public CouseResultDTO(){
	}
	
	public CouseResultDTO(String course, String tjCourse, String xgCourse) {
		super();
		this.course = course;
		this.tjCourse = tjCourse;
		this.xgCourse = xgCourse;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getTjCourse() {
		return tjCourse;
	}

	public void setTjCourse(String tjCourse) {
		this.tjCourse = tjCourse;
	}

	public String getXgCourse() {
		return xgCourse;
	}

	public void setXgCourse(String xgCourse) {
		this.xgCourse = xgCourse;
	}
}
