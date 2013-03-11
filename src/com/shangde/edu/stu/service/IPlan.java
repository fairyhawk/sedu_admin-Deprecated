package com.shangde.edu.stu.service;

import java.util.Date;
import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.stu.domain.Plan;
import com.shangde.edu.stu.condition.QueryPlanCondition;
import com.shangde.edu.stu.condition.QueryPlanclockCondition;

public interface IPlan {

	public Integer addPlan(Plan plan);

    /**
     * 
     */
    public void delPlanById(int planId);

    /**
     * 
     * @param plan 
     */
    public void updatePlan(Plan plan);

    /**
     * 
     * @return
     */
    public Plan getPlanById(int planId);

    /**
     *
     * @param queryPlanCondition
     * @return 
     */
    public List<Plan> getPlanList(QueryPlanCondition queryPlanCondition);
    
    
    /**
	 * 根据 条件 如：开启时间（添加时间）查找 Plan
	 * @author fanxin
     * @time   2011.05.26
     * @version 1.0
     */
    public Plan getPlanByDate(QueryPlanCondition queryPlanCondition);
    
    /**
     * 按照日期导航，搜索某个月的所有计划
     * @author baiang.zhao
     * Date:2011-5-24 16:04:30
     * @param queryPlanCondition
     * @return 某个月的所有计划
     */
    public List<Plan> getPlanListByCalendar(QueryPlanCondition queryPlanCondition);

    /**
     *   范昕
     * 查询全部学习计划对象
     * 			且做分页处理
     * Date : 2011-7-14 16:49:16
     * @param queryPlanclockCondition 查询对象
     * @return 分页对象
     */
    public PageResult getPlanPaperByCondition(QueryPlanCondition queryPlanCondition);
    
    /**
     *   范昕
     * 查询全部学习计划对象
     * 			且做分页处理
     * Date : 2011-7-14 16:49:16
     * @param queryPlanclockCondition 查询对象
     * @return 分页对象
     */
    public PageResult getPlanBackPaperByCondition(QueryPlanCondition queryPlanCondition);
    
    /**
     *   范昕
     * 查询学习计划统计页面所需数据对象
     * Date : 2011-7-19 16:49:16
     */
    public int getCustomerAllStat(QueryPlanCondition queryPlanCondition);
    
    public int getCustomerUser(QueryPlanCondition queryPlanCondition);
    
    public int getCustomerUserMoreTen(QueryPlanCondition queryPlanCondition);
    
    /**
     * 根据检索条件，检索相应的结果
     * 					已做分页处理
     * Date : 2011-7-21 16:56:19
     * @param queryPlanCondition 查询条件
     * @return
     */
    public PageResult searchPlanListByParamCondition(QueryPlanCondition queryPlanCondition);
    
    
}