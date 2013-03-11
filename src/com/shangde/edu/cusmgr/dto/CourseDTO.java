package com.shangde.edu.cusmgr.dto;

import java.io.Serializable;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */

public class CourseDTO implements Serializable {

	private String title;
	private String subjectName;
	private String gradeName;
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
