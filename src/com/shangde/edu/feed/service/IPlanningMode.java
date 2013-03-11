package com.shangde.edu.feed.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryPlanningModeCondition;
import com.shangde.edu.feed.domain.PlanningMode;

/**
 * 计划模式接口
 * 
 * @author Libg
 * 
 */
public interface IPlanningMode {

	/**
	 * 添加PlanningMode
	 * 
	 *
     * @param planningMode
     * @return
	 */
	public Integer addPlanningMode(PlanningMode planningMode);

	/**
	 * 根据id删除一个PlanningMode
	 * 
	 *
     * @param id
     * @return
	 */
	public Integer delPlanningModeById(int id);

	/**
	 * 修改PlanningMode
	 * 
	 *
     * @param planningMode
     * @return
	 */
	public Integer updatePlanningMode(PlanningMode planningMode);

	/**
	 * 根据id获取单个PlanningMode对象
	 * 
	 * @param id
	 * @return
	 */
	public PlanningMode getPlanningModeById(int id);

	/**
	 * 查询列表
	 * 
	 * @param queryPlanningModeCondition
	 * @return
	 */
	public PageResult getPlanningModePageList(
			QueryPlanningModeCondition queryPlanningModeCondition);

	/**
	 * 查询列表
	 * 
	 * @return
	 */
	public List<PlanningMode> getPlanningModeList();
}
