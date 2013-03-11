package com.shangde.edu.feed.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryCourseCondition;
import com.shangde.edu.feed.domain.Course;

/**
 * 
 * 微学习->课程接口
 * 
 * @author Libg
 * 
 */
public interface ICourse {

	/**
	 * 添加课程
	 * 
	 * @param course
	 */
	public void addCourse(Course course);

	/**
	 * 修改课程
	 * 
	 *
     * @param course
     * @return
	 */
	public Integer updateCourse(Course course);

	/**
	 * 根据课程id取得数据
	 * 
	 * @param id
	 * @return
	 */
	public Course getCourseById(int id);

	/**
	 * 后台列表查询
	 * 
	 * @param queryCourseCondition
	 * @return
	 */
	public PageResult getCoursePageList(
			QueryCourseCondition queryCourseCondition);

	/**
	 * 高级条件查询
	 * 
	 * @param queryCourseCondition
	 * @return
	 */
	public PageResult getCoursePageListByTerm(
			QueryCourseCondition queryCourseCondition);

	/**
	 * 根据专业id获取一个课程id
	 * 
	 *
     * @param subjectId
     * @return
	 */
	public Integer getCourseIdBySubjectId(Integer subjectId);

	/**
	 * 查询所有的课程，状态status=1的数据
	 * 
	 * @return
	 */
	public List<Course> getAllCourse();
}
