package com.shangde.edu.stu.service;

import java.util.List;
import com.shangde.edu.stu.domain.Planitem;
import com.shangde.edu.stu.condition.QueryPlanitemCondition;
import com.shangde.common.service.BaseService;

@SuppressWarnings("unchecked")
public class PlanitemImpl extends BaseService implements IPlanitem {
	
	public Integer addPlanitem(Planitem planitem) {
		return simpleDao.createEntity("Planitem_NS.createPlanitem", planitem);
	}

	public void delPlanitemById(int planitemId) {
		simpleDao.deleteEntity("Planitem_NS.deletePlanitemById",planitemId);
	}

	public void updatePlanitem(Planitem planitem) {
		simpleDao.updateEntity("Planitem_NS.updatePlanitem", planitem);
	}

	public Planitem getPlanitemById(int planitemId) {
		return simpleDao.getEntity("Planitem_NS.getPlanitemById",planitemId);
	}

	public List<Planitem> getPlanitemList(
			QueryPlanitemCondition queryPlanitemCondition) {
		return simpleDao.getForList("Planitem_NS.getPlanitemList",
				queryPlanitemCondition);
	}
	
	
	public Planitem getPlanitemByPlanId(int planId){
		return simpleDao.getEntity("Planitem_NS.getPlanitemByPlanId",planId);
	}
}
