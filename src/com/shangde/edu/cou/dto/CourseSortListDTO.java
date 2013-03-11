package com.shangde.edu.cou.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.res.domain.Picture;
import com.shangde.edu.sys.domain.Subject;

/**
 * 课程专业集合，每个课程分类下的
 * 用于包含所有课程及专业
 * 用于前台课程列表
 * @author cxs
 *
 */
public class CourseSortListDTO implements Serializable{
	
	/**
	 * 所属专业
	 */
	private Subject subject;
	
	/**
	 * 专业下的课程集合
	 */
	private List<Course> courseList = new ArrayList<Course>();
	
	/**
	 * 图片
	 */
	private Picture picture;

	public Picture getPicture() {
		if(picture == null){
			return new Picture();
		}
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}
