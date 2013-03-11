package com.shangde.edu.stu.service;

import java.util.List;
import com.shangde.edu.stu.domain.Planitem;
import com.shangde.edu.stu.condition.QueryPlanitemCondition;

public interface IPlanitem {

	public Integer addPlanitem(Planitem planitem);

	/**
	 * 
	 */
	public void delPlanitemById(int planitemId);

	/**
	 * 
	 * @param planitem
	 */
	public void updatePlanitem(Planitem planitem);

	/**
	 * 
	 * @return
	 */
	public Planitem getPlanitemById(int planitemId);

	/**
	 * 
	 * @param
	 * @return
	 */
	public List<Planitem> getPlanitemList(QueryPlanitemCondition queryPlanitemCondition);
	
	/**
	 * 
	 * @return
	 */
	public Planitem getPlanitemByPlanId(int planId);
	
	
	
}