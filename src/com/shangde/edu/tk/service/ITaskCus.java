package com.shangde.edu.tk.service;

import java.util.List;

import com.shangde.edu.tk.domain.Task;
import com.shangde.edu.tk.domain.TaskCus;
import com.shangde.edu.tk.condition.QueryTaskCusCondition;


public interface ITaskCus {
  
	/**
	 * 添加
	 * @param taskCus
	 */
    public Integer addTaskCus(TaskCus taskCus);

     /**
	 *删除
	 *@param id
	 *@return void 
	 */
    public void delTaskCusById(int id);
    
    /**
	 *删除
	 *@param taskId
	 *@return void 
	 */
    public void delTaskCusByTaskId(int taskId);

    /**
	 *更新
	 *@param taskCus
	 *@return Integer 
	 */
    public void updateTaskCus(TaskCus taskCus);
    /**
     * @author yjd
     * 通过用户ID删除任务
     * @param cusId
     */
    public void deleteTaskCusByCusId(int cusId);

    /**
	 *根据id查询
	 *@param id
	 *@return TaskCus
	 */
    public TaskCus getTaskCusById(int id);
    
    /**
     * @author cxs
     * 功能：通过关键字获取任务
     * @param queryTaskCusCondition
     * @return
     */
    public TaskCus getTaskCusByKey(QueryTaskCusCondition queryTaskCusCondition);


    /**
     * 
     * 功能：获取用户任务
     * @param queryTaskCusCondition
     * @return
     */
    public List<TaskCus> getTaskCusList(QueryTaskCusCondition queryTaskCusCondition);
    
    /**
     * 
     * @author chenshuai
     * 功能：获取用户前台任务
     * @param queryTaskCusCondition
     * @return
     */
    public List<TaskCus> getWebTaskCusList(QueryTaskCusCondition queryTaskCusCondition);
    
    /**
     * @author chenshuai
     * 功能：根据用户的Id和任务Id查询记录条数
     * @param queryTaskCusCondition
     * @return
     */
    public Integer getCountByCusIdAndTaskId(QueryTaskCusCondition queryTaskCusCondition);
    
    /**
     * @author chenshuai
     * 功能：根据任务ID及学员ID获取用户任务
     * @param queryTaskCusCondition
     * @return
     */
    public TaskCus getTaskCusByTaskIdAndCusId(QueryTaskCusCondition queryTaskCusCondition);
    
    /**
     * @author chenshuai
     * 功能：获取用户第一个任务
     * @param queryTaskCusCondition
     * @return
     */
    public TaskCus getFirstWebTaskCus(QueryTaskCusCondition queryTaskCusCondition);
    
    /**
     * 功能:后台删除用户时级联批量删除用户信息
     * @param cusIds
     * @return
     * Author:Yangning
     * CreateDate:2011-12-6
     */
    public Integer deleteBathTaskCusByCusIds(String cusIds);
}