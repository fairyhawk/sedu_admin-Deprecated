package com.shangde.edu.cus.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.domain.ReProblem;
import com.shangde.edu.cus.condition.QueryReProblemCondition;


public interface IReProblem {

	/**
     * 回复问题
     * 添加回复信息
     */
    public java.lang.Integer addReProblem(ReProblem reProblem);

    /**
     * 根据id删除回复信息
     * 
     */
    public void delReProblemById(int reId);

    /**
     * 更新回复问题信息
     * 
     */
    public void updateReProblem(ReProblem reProblem);

    /**
     * 根据id获得回复信息对象
     * 
     */
    public ReProblem getReProblemById(int reId);

    /**
     * 根据查询条件获得List
     * 
     */
    public List<ReProblem> getReProblemList(QueryReProblemCondition queryReProblemCondition);
    
    /**
     * 分页查询
     * 
     */
    public PageResult getPageReProblemList(QueryReProblemCondition queryReProblemCondition);
    
    /**
	 * @author cxs
	 * 功能：获取用户回复数量
	 * @param queryReProblemCondition
	 * @return
	 */
	public Integer getCountByCusId(QueryReProblemCondition queryReProblemCondition);
	
	/**
	 * 功能:后台批量删除用户时同时批量删除回馈信息
	 * @param ids
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-6
	 */
	public Integer delBathReProblemByCusIds(String ids);
	

}