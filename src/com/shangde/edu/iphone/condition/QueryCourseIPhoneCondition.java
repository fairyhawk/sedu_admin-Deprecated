package com.shangde.edu.iphone.condition;

import com.shangde.common.vo.PageResult;

/**
 * 查询课程集合
 * @author chenshuai
 *
 */
public class QueryCourseIPhoneCondition extends PageResult {
	
	/**
	 * 课程ID
	 */
	private int courseId;
	
	/**
	 * 查询条件
	 */
	private String searchKey;
	
	/**
	 * 查询类型
	 */
	private String searchType;
	
	/**
	 * 分类ID
	 */
	private int sortId;
	
	/**
	 * 查询数量
	 */
	private int num;
	
	/**
	 * 专业ID
	 */
	private int subjectId;
	
	/**
	 * 教师ID
	 */
	private int teacherId;
	
	/**
	 * 教师推荐课程ID
	 */
	private int teacherTjCourse;
	
	/**
	 * 班级ID
	 */
	private int clazzId;
	
	private int status;
	
	private int cusId;
	
	private int sellWayId;
	
	private int newCourse;

	public int getNewCourse() {
		return newCourse;
	}

	public void setNewCourse(int newCourse) {
		this.newCourse = newCourse;
	}

	public int getTeacherTjCourse() {
		return teacherTjCourse;
	}

	public void setTeacherTjCourse(int teacherTjCourse) {
		this.teacherTjCourse = teacherTjCourse;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getClazzId() {
		return clazzId;
	}

	public void setClazzId(int clazzId) {
		this.clazzId = clazzId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getSellWayId() {
		return sellWayId;
	}

	public void setSellWayId(int sellWayId) {
		this.sellWayId = sellWayId;
	}
}