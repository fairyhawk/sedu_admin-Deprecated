package com.shangde.edu.feed.service;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryCourseStaticsCondition;
import com.shangde.edu.feed.domain.CourseStatic;

public interface ICourseStatics {
	/**
	 * 添加课程统计数据
	 * @param courseStatic
	 */
	public void addCourseStatics(CourseStatic courseStatic);
	
	/**
	 *根据课程id查询观看课程详情
	 * @param courseStatic
	 * @return
	 */
	public PageResult getUseUserListByCourseId(QueryCourseStaticsCondition queryCourseStaticsCondition);
	/**
	 * 查询所有课程统计
	 * @param queryCourseStaticsCondition
	 * @return
	 */
	public PageResult getCourseStaticsList(QueryCourseStaticsCondition queryCourseStaticsCondition);

	
}
