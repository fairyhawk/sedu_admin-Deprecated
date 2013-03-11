package com.shangde.edu.feed.service.impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryCourseStaticsCondition;
import com.shangde.edu.feed.domain.CourseStatic;
import com.shangde.edu.feed.service.ICourseStatics;

public class CourseStaticsImpl  extends BaseService implements ICourseStatics  {

	public void addCourseStatics(CourseStatic courseStatic) {
		simpleDao.createEntity("Feed_Course_Statics_NS.createCourseStatic", courseStatic);
	}

	public PageResult getUseUserListByCourseId(QueryCourseStaticsCondition queryCourseStaticsCondition) {
		return simpleDao.getPageResult("Feed_Course_Statics_NS.getUserListByCourseId", "Feed_Course_Statics_NS.getUserListByCourseIdCount", queryCourseStaticsCondition);
	}

	public PageResult getCourseStaticsList(
			QueryCourseStaticsCondition queryCourseStaticsCondition) {
		List<CourseStatic> courseStaticList = new ArrayList<CourseStatic>();
		PageResult pr = simpleDao.getPageResult("Feed_Course_Statics_NS.getCouserStaticListByTerm", "Feed_Course_Statics_NS.getCouserStaticListCount", queryCourseStaticsCondition);
		courseStaticList = pr.getPageResult();
		for(CourseStatic courseStatic:courseStaticList){
			queryCourseStaticsCondition.setCourseId(courseStatic.getCourseId());
			courseStatic.setUsePersonNumber((Integer)simpleDao.getEntity("Feed_Course_Statics_NS.usePersonNum",queryCourseStaticsCondition));
		}
		return pr;
	}


}
