package com.shangde.edu.stu.service;

import java.util.Date;
import java.util.List;
import com.shangde.edu.stu.domain.Plan;
import com.shangde.edu.stu.condition.QueryPlanCondition;
import com.shangde.edu.stu.condition.QueryPlanclockCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;

@SuppressWarnings("unchecked")
public class PlanImpl extends BaseService implements IPlan {

	public Integer addPlan(Plan plan) {
		return simpleDao.createEntity("Plan_NS.createPlan", plan);
	}

	public void delPlanById(int planId) {
		simpleDao.deleteEntity("Plan_NS.deletePlanById",planId);
	}

	public void updatePlan(Plan plan) {
		simpleDao.updateEntity("Plan_NS.updatePlan", plan);
	}

	public Plan getPlanById(int planId) {
		return simpleDao.getEntity("Plan_NS.getPlanById",planId);
	}

	public List<Plan> getPlanList(QueryPlanCondition queryPlanCondition) {
		return simpleDao.getForList("Plan_NS.getPlanList", queryPlanCondition);
	}
	
    /**
	 * 根据 条件 如：开启时间（添加时间）查找 Plan
	 * @author fanxin
     * @time   2011.05.26
     * @version 1.0
     */
    public Plan getPlanByDate(QueryPlanCondition queryPlanCondition){
    	return simpleDao.getEntity("Plan_NS.getPlanByDate", queryPlanCondition);
    }
	
	public List<Plan> getPlanListByCalendar(QueryPlanCondition queryPlanCondition) {
		return simpleDao.getForList("Plan_NS.getPlanListByCalendar", queryPlanCondition);
	}

	// 分页查询结果
	public PageResult getPlanPaperByCondition(QueryPlanCondition queryPlanCondition) {
		return simpleDao.getPageResult("Plan_NS.getPlanList", "Plan_NS.getPlanListCount", queryPlanCondition);
	}
	
    /**
     *   范昕
     * 查询全部学习计划对象
     * 			且做分页处理
     * Date : 2011-7-14 16:49:16
     * @param queryPlanclockCondition 查询对象
     * @return 分页对象
     */
    public PageResult getPlanBackPaperByCondition(QueryPlanCondition queryPlanCondition){
    	return simpleDao.getPageResult("Plan_NS.getBackPlanList", "Plan_NS.getBcakPlanListCount", queryPlanCondition);
    }
	
    /**
     *   范昕
     * 查询学习计划统计页面所需数据对象
     * Date : 2011-7-19 16:49:16
     */
    public int getCustomerAllStat(QueryPlanCondition queryPlanCondition){
    	return simpleDao.getEntity("Plan_NS.getAllCustomerCount", null);
    }
    
    public int getCustomerUser(QueryPlanCondition queryPlanCondition){
    	return simpleDao.getEntity("Plan_NS.getUserCustomerCount", null);
    }
    public int getCustomerUserMoreTen(QueryPlanCondition queryPlanCondition){
    	return simpleDao.getEntity("Plan_NS.getCustomerUserMoreTen", null);
    }
	
    
	// 按条件检索结果，已做分页
	public PageResult searchPlanListByParamCondition(QueryPlanCondition queryPlanCondition) {
		return simpleDao.getPageResult("Plan_NS.searchPlanByParam", "Plan_NS.searchPlanByParamCount", queryPlanCondition);
	}
}
