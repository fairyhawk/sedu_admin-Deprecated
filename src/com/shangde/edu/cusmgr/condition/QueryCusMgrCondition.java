package com.shangde.edu.cusmgr.condition;

import com.shangde.common.vo.PageResult;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */

public class QueryCusMgrCondition extends PageResult{

	private String courseName;
	private int sortId;
	private int cusId;
	private int cusCouKpointId;
	private int courseId;
	private int userId;
	private int gradeId;
	private int subjectId;
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getCusCouKpointId() {
		return cusCouKpointId;
	}
	public void setCusCouKpointId(int cusCouKpointId) {
		this.cusCouKpointId = cusCouKpointId;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
}
