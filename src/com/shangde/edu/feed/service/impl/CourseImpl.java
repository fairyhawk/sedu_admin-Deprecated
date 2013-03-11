package com.shangde.edu.feed.service.impl;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryCourseCondition;
import com.shangde.edu.feed.domain.Course;
import com.shangde.edu.feed.service.ICourse;

/**
 * 微学习->课程接口实现
 * 
 * @author Libg
 * 
 */
public class CourseImpl extends BaseService implements ICourse {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ICourse#addCourse(com.shangde.edu.feed.domain.Course)
	 */
	public void addCourse(Course course) {
		simpleDao.createEntity("Feed_Course_NS.createCourse", course);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ICourse#getCourseById(int)
	 */
	public Course getCourseById(int id) {
		return simpleDao.getEntity("Feed_Course_NS.getCourseById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ICourse#getCoursePageList(com.shangde.edu.feed.condition.QueryCourseCondition)
	 */
	public PageResult getCoursePageList(
			QueryCourseCondition queryCourseCondition) {
		return simpleDao.getPageResult("Feed_Course_NS.getCoursePageList",
				"Feed_Course_NS.getCourseCount", queryCourseCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ICourse#updateCourse(com.shangde.edu.feed.domain.Course)
	 */
	public Integer updateCourse(Course course) {
		return simpleDao.update("Feed_Course_NS.updateCourse", course);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ICourse#getCoursePageListByTerm(com.shangde.edu.feed.condition.QueryCourseCondition)
	 */
	public PageResult getCoursePageListByTerm(
			QueryCourseCondition queryCourseCondition) {
		return simpleDao.getPageResult("Feed_Course_NS.getCourseListByTerm",
				"Feed_Course_NS.getCourseCountByTerm", queryCourseCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ICourse#getCourseIdBySubjectId(java.lang.Integer)
	 */
	public Integer getCourseIdBySubjectId(Integer subjectId) {
		return simpleDao.getEntity("Feed_Course_NS.getCourseIdBySubjectId",
				subjectId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ICourse#getAllCourse()
	 */
	public List<Course> getAllCourse() {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Feed_Course_NS.getAllCourse", null);
	}

}
