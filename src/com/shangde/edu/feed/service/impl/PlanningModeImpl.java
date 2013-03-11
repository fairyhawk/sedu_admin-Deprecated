package com.shangde.edu.feed.service.impl;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryPlanningModeCondition;
import com.shangde.edu.feed.domain.PlanningMode;
import com.shangde.edu.feed.service.IPlanningMode;

/**
 * 计划模型，实现类
 * 
 * @author Libg
 * 
 */
public class PlanningModeImpl extends BaseService implements IPlanningMode {

	public Integer addPlanningMode(PlanningMode planningMode) {
		return simpleDao.createEntity("PlanningMode_NS.createPlanningMode",
				planningMode);
	}

	public Integer delPlanningModeById(int id) {
		return simpleDao.delete("PlanningMode_NS.deletePlanningModeById", id);
	}

	public PlanningMode getPlanningModeById(int id) {
		return simpleDao.getEntity("PlanningMode_NS.getPlanningModeById", id);
	}

	public PageResult getPlanningModePageList(
			QueryPlanningModeCondition queryPlanningModeCondition) {
		return simpleDao.getPageResult(
				"PlanningMode_NS.getPlanningModePageList",
				"PlanningMode_NS.getPlanningModePageCount",
				queryPlanningModeCondition);
	}

	public Integer updatePlanningMode(PlanningMode planningMode) {
		return simpleDao.update("PlanningMode_NS.updatePlanningMode",
				planningMode);
	}

	public List<PlanningMode> getPlanningModeList() {
		return simpleDao
				.getForList("PlanningMode_NS.getPlanningModeList", null);
	}

}
