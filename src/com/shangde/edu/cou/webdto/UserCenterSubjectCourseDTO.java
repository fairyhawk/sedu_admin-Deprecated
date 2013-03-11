package com.shangde.edu.cou.webdto;

import java.util.ArrayList;
import java.util.List;

import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.sys.domain.Subject;

/**
 * 用户个人中心使用到，以专业区分课程列表；一个专业一个课程集合
 * @author chenshuai
 */
public class UserCenterSubjectCourseDTO {
	private Subject subject;//专业
	private List<UserCenterCourseDTO> courseList; //课程集合
	private SellWay sellWay;//授卖方式 
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public List<UserCenterCourseDTO> getCourseList() {
		if(courseList == null){
			courseList = new ArrayList<UserCenterCourseDTO>();
		}
		return courseList;
	}
	public void setCourseList(List<UserCenterCourseDTO> courseList) {
		this.courseList = courseList;
	}
	public SellWay getSellWay() {
		return sellWay;
	}
	public void setSellWay(SellWay sellWay) {
		this.sellWay = sellWay;
	}
 
	
}
