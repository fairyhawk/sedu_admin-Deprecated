package com.shangde.edu.cusmgr.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cusmgr.condition.QueryCusMgrCondition;
import com.shangde.edu.cusmgr.dto.CourseDTO;
import com.shangde.edu.cusmgr.dto.WatchRankDTO;
import com.shangde.edu.sys.domain.Grade;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */

public interface ICusMgr {

	/**
	 * 查询待选课程列表
	 * @param queryCusMgrCondition 查询条件
	 * @return 分页结果
	 */
	public PageResult getNomalCourseListBySortId(
			QueryCusMgrCondition queryCusMgrCondition);

	/**
	 * 根据用户id及所选年份、专业来查询customer列表，并分页
	 * @param queryCusMgrCondition 查询条件
	 * @return  分页结果
	 */
	public PageResult getCustomerListByUser(QueryCusMgrCondition queryCusMgrCondition);

	/**
	 * 根据用户id和专业id查询年份
	 * @param queryCusMgrCondition 查询条件
	 * @return 年份列表
	 */
	public List<Grade> getgetGradeListBySubjectId(
			QueryCusMgrCondition queryCusMgrCondition);

	/**
	 * 根据用户id查询所选课程
	 * @param cusId 用户id
	 * @return 
	 */
	public List<CourseDTO> getCourseListByCusId(int cusId);
	
	public List<WatchRankDTO> getWatchRankCus(String [] user);

}
