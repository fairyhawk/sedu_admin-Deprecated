package com.shangde.edu.cusmgr.service;

import java.util.ArrayList;
import java.util.List;

import com.shangde.common.service.BaseService;
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

public class CusMgrImpl extends BaseService implements ICusMgr {

	public PageResult getNomalCourseListBySortId(
			QueryCusMgrCondition queryCusMgrCondition) {
		return simpleDao.getPageResult("CusMgr_NS.getNomalCourseListBySortId",
				"CusMgr_NS.getNomalCourseCountBySortId",
				queryCusMgrCondition);
	}

	public PageResult getCustomerListByUser(QueryCusMgrCondition queryCusMgrCondition) {
		return simpleDao.getPageResult("CusMgr_NS.getCustomerListByUser",
				"CusMgr_NS.getCustomerCountByUser",
				queryCusMgrCondition);
	}

	public List<Grade> getgetGradeListBySubjectId(
			QueryCusMgrCondition queryCusMgrCondition) {
		return simpleDao.getForList("CusMgr_NS.getGradeListBySubjectId", queryCusMgrCondition);
	}

	public List<CourseDTO> getCourseListByCusId(int cusId) {
		return simpleDao.getForList("CusMgr_NS.getCourseListByCusId", cusId);
	}

	@Override
	public List<WatchRankDTO> getWatchRankCus(String[] user) {
		List<String> value= new ArrayList<String>();
		for(String s:user){
			value.add(s);
		}
		return simpleDao.getForList("CusMgr_NS.getWatchRankCus", value);
	}
}
