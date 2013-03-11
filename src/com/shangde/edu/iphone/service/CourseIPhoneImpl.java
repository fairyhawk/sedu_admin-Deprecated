package com.shangde.edu.iphone.service;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.iphone.condition.QueryCourseIPhoneCondition;

public class CourseIPhoneImpl extends BaseService implements ICourseIPhone {

	public PageResult getCourseBySellWayId(
			QueryCourseIPhoneCondition queryCourseIPhoneCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("CourseIPhone_NS.getCourseListBySellWayId", "CourseIPhone_NS.getCourseCountBySellWayId", queryCourseIPhoneCondition);
	}

	public PageResult getNewCourse(
			QueryCourseIPhoneCondition queryCourseIPhoneCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("CourseIPhone_NS.getNewCourse", "CourseIPhone_NS.getNewCourseCount", queryCourseIPhoneCondition);
	}
	

}
