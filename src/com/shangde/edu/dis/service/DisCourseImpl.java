package com.shangde.edu.dis.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.dto.CourseDTO;
import com.shangde.edu.cou.dto.CourseIsAuditionDTO;
import com.shangde.edu.cou.dto.KeyValueDTO;
import com.shangde.edu.cou.dto.ListeningCourseDTO;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.webdto.UserCenterCourseDTO;

/**
 * 用于查询购买的课程信息，供小组模块使用
 * 
 * @author Basil
 * 
 */
public class DisCourseImpl extends BaseService implements ICourse {

	public Integer addCourse(Course course) {
		return null;
	}

	public void delCourseById(int courseId) {

	}

	public Course getAllCourseInfoById(int courseId) {
		return null;
	}

	public List<Course> getAllCourseInfoList(
			QueryCourseCondition queryCourseCondition) {
		return null;
	}

	public List<CourseIsAuditionDTO> getAuditionCourseListByCourseId(
			int courseId) {
		return null;
	}

	public Course getCourseById(int courseId) {
		return null;
	}

	public CourseDTO getCourseDTOById(int CourseId) {
		return null;
	}

	public List<KeyValueDTO> getCourseDTOListtBySortId(
			QueryCourseCondition queryCourseCondition) {
		return null;
	}

	public List<Course> getCourseList(QueryCourseCondition queryCourseCondition) {
		return null;
	}

	public List<Course> getCourseListByClazzIdForTag(
			QueryCourseCondition queryCourseCondition) {
		return null;
	}

	public List<Course> getCourseListBySortId(
			QueryCourseCondition queryCourseCondition) {
		return null;
	}

	public List getCourseListByStatus(QueryCourseCondition queryCourseCondition) {
		return null;
	}

	public List<Course> getCourseListForTag(
			QueryCourseCondition queryCourseCondition) {
		return null;
	}

	public List<Course> getCourseListtBySortIdLimit(
			QueryCourseCondition queryCourseCondition) {
		return null;
	}

	public PageResult getNomalCourseListBySortId(
			QueryCourseCondition queryCourseCondition) {
		return null;
	}

	public List<UserCenterCourseDTO> getUserCenterCourseListByCusId(
			QueryCourseCondition queryCourseCondition) {
		return null;
	}

	public List<SellWay> getUserCenterSellWayListByCusId(
			QueryCourseCondition queryCourseCondition) {
		return null;
	}

	public List<Course> getWebCourseList(
			QueryCourseCondition queryCourseCondition) {
		return null;
	}

	public List<CourseDTO> getWebCourseListLimit(int size) {
		return null;
	}

	public ListeningCourseDTO getWebListenintCourseDTOByVedioId(int vedioId) {
		return null;
	}

	public void updateCourse(Course course) {

	}

	public List<KeyValueDTO> getCourseDTOListAddOrUpdate(
			QueryCourseCondition queryCourseCondition) {
		// TODO Auto-generated method stub
		return null;
	}

	public PageResult getCourseBySellWayId(
			QueryCourseCondition queryCourseCondition) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CourseDTO> getCoursePlan(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	public PageResult getNewCourse(QueryCourseCondition queryCourseCondition) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Integer> getCourseIdAll(Integer subjectId) {
		// TODO Auto-generated method stub
		return null;
	}

}
