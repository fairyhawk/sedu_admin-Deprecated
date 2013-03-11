package com.shangde.edu.cus.service;

import java.util.List;


import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.domain.Problem;
import com.shangde.edu.cus.condition.QueryProblemCondition;

public interface IProblem {
	/**
     * 添加Problem
     * 
     */
    public java.lang.Integer addProblem(Problem problem);

    /**
     * 删除Problem
     * 根据问题 id
     */
    public void delProblemById(int pblId);
    
    /**
     * 更新Problem
     */
    public void updateProblem(Problem problem);

    /**
     * 根据问题id获得整个Problem对象
     */
    public Problem getProblemById(int pblId);

    /**
     * 根据条件查询获得,List
     * 
     */
    public List<Problem> getProblemList(QueryProblemCondition queryProblemCondition);
    
    /**
     * 
     * 后台分页查询
     * 
     */
    public PageResult getPageProblemList(QueryProblemCondition queryProblemCondition);
    
    /**
     * 
     * @Title: getPageProblemListForExcel 
     * @Description: TODO(后台分页导出excel查询) 
     * @param @param queryProblemCondition
     * @param @return    设定文件 
     * @return PageResult    返回类型
     * @author shixiaofeng@sunland.org.cn 
     * @throws
     */
    public PageResult getPageProblemListForExcel(QueryProblemCondition queryProblemCondition);
    
    /**
     * 
     * 前台分页查询
     * 
     */
    public PageResult getPageProblemListById(QueryProblemCondition queryProblemCondition);
    /**
     * 
     * 查最新的十条热门问题
     * 
     */
    public List<Problem> getProblemByHost(QueryProblemCondition queryProblemCondition);
    
	/**
	 * 
	 * 根据用户id返回回答数
	 */
	public PageResult getReProblemByCusId(QueryProblemCondition queryProblemCondition);
	/**
	 * 
	 * 查出最新，高分，已解决，的六个问题
	 */
	public List<Problem> getNewProblem(QueryProblemCondition queryProblemCondition);
	
	/**
	 * 功能:后台批量删除用户时批量删除问题信息
	 * @param ids
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-6
	 */
    public Integer delBathProblemByCusIds(String ids);
    /**
     * 
     * @Title: getProblemCount 
     * @Description: TODO(查询highso问答的数量) 
     * @param @param queryProblemCondition
     * @param @return    设定文件 
     * @return Integer    返回类型
     * @author shixiaofeng@sunland.org.cn 
     * @throws
     */
    public Integer getProblemCount(QueryProblemCondition queryProblemCondition);
	
}