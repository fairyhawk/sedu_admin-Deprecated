package com.shangde.edu.tk.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.dto.KeyValueDTO;
import com.shangde.edu.tk.domain.Task;
import com.shangde.edu.tk.condition.QueryTaskCondition;


public interface ITask {

	/**
	 *添加
	 *@param task
	 *@return Integer 
	 */
    public java.lang.Integer addTask(Task task);

    /**
	 *删除
	 *@param taskId
	 *@return void 
	 */
    public void delTaskById(int taskId);

    /**
	 *更新
	 *@param task
	 *@return Integer 
	 */
    public void updateTask(Task task);
    /**
	 *根据taskId查询
	 *@param taskId
	 *@return Task
	 */
    public Task getTaskById(int taskId);

    /**
	 *根据查询条件查询
	 *@param queryTaskCondition
	 *@return List<Task>集合
	 */
    public List<Task> getTaskList(QueryTaskCondition queryTaskCondition);
    
    public PageResult getTaskListByCondition(QueryTaskCondition queryTaskCondition);
    
    public List<KeyValueDTO> getTaskKeyValueList(QueryTaskCondition queryTaskCondition);
}