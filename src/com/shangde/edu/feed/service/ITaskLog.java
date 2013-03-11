package com.shangde.edu.feed.service;

import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryTaskLogCondition;
import com.shangde.edu.feed.domain.TaskLog;

/**
 * TaskLog管理接口
 * 
 * @author Libg
 * 
 */
public interface ITaskLog {

	/**
	 * 添加TaskLog
	 * 
	 *
     * @param taskLog
     *            要添加的TaskLog
     * @return id
	 */
	public Integer addTaskLog(TaskLog taskLog);

	/**
	 * 根据id删除一个TaskLog
	 * 
	 *
     * @param id
     * @return int
	 */
	public Integer delTaskLogById(int id);

	/**
	 * 修改TaskLog
	 * 
	 *
     * @param taskLog
     *            要修改的TaskLog
     *
     * @return int
	 */
	public Integer updateTaskLog(TaskLog taskLog);

	/**
	 * 根据id获取单个TaskLog对象
	 * 
	 * @param id
	 * 
	 * @return 年级
	 */
	public TaskLog getTaskLogById(int id);

	/**
	 * 根据条件获取TaskLog列表
	 * 
	 * @param queryTaskLogCondition
	 * @return
	 */
	public PageResult getTaskLogList(QueryTaskLogCondition queryTaskLogCondition);

	/**
	 * 修改激活字段++
	 * 
	 *
     * @param map
     *            map的key = incremental表示增量值,id记录id
     * @return
	 */
	public Integer updateTaskLogUrlClickNum(Map<String, String> map);

	/**
	 * 修改当前发送次数
	 * 
	 *
     * @param taskListId
     * @return
	 */
	public Integer updateTaskLogSendNum(int taskListId);

	/**
	 * 成功次数
	 * 
	 *
     * @param taskListId
     * @return
	 */
	public Integer updateTaskLogSendCount(int taskListId);

	/**
	 * 失败次数
	 * 
	 *
     * @param taskListId
     * @return
	 */
	public Integer updateTaskLogSendErrorCount(int taskListId);

	/**
	 * 后台条件查询
	 * 
	 * @param queryTaskLogCondition
	 * @return
	 */
	public PageResult getTaskLogConditionList(
			QueryTaskLogCondition queryTaskLogCondition);

}