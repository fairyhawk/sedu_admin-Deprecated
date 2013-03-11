package com.shangde.edu.iphone.service;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.iphone.condition.QueryCourseIPhoneCondition;

public interface ICourseIPhone {
	
	
	/**
	 * 为张栋提供iphone端数据 得到最新推荐课程
	 */
	public PageResult getNewCourse(QueryCourseIPhoneCondition queryCourseIPhoneCondition);
	
	
	/**
	 * 为张栋提供iphone端数据 根据售卖方式ID 得到课程
	 */
	public PageResult getCourseBySellWayId(QueryCourseIPhoneCondition queryCourseIPhoneCondition);

}
