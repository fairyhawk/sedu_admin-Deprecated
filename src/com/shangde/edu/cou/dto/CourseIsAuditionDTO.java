package com.shangde.edu.cou.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 用户知识点DTO
 * 用于正式听课及试听课程，获取课程的所有知识点相关信息
 * 
 * @author chenshuai
 */
public class CourseIsAuditionDTO implements Serializable{

	private int courseId;
	private String voName;
	private String voUrl;
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getVoName() {
		return voName;
	}
	public void setVoName(String voName) {
		this.voName = voName;
	}
	public String getVoUrl() {
		return voUrl;
	}
	public void setVoUrl(String voUrl) {
		this.voUrl = voUrl;
	}
	
}