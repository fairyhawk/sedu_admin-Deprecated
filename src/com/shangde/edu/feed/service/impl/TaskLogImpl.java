package com.shangde.edu.feed.service.impl;

import java.util.Map;


import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryTaskLogCondition;
import com.shangde.edu.feed.domain.TaskLog;
import com.shangde.edu.feed.service.ITaskLog;

/**
 * TaskLog 任务日志表实现
 * 
 * @author Libg
 */
@SuppressWarnings("unchecked")
public class TaskLogImpl extends BaseService implements ITaskLog {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskLog#addTaskLog(com.shangde.edu.feed.domain.TaskLog)
	 */
	public Integer addTaskLog(TaskLog taskLog) {
		return simpleDao.createEntity("TaskLog_NS.createTaskLog", taskLog);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskLog#delTaskLogById(int)
	 */
	public Integer delTaskLogById(int id) {
		return simpleDao.delete("TaskLog_NS.deleteTaskLogById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskLog#updateTaskLog(com.shangde.edu.feed.domain.TaskLog)
	 */
	public Integer updateTaskLog(TaskLog taskLog) {
		return simpleDao.update("TaskLog_NS.updateTaskLog", taskLog);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskLog#getTaskLogById(int)
	 */
	public TaskLog getTaskLogById(int id) {

		return simpleDao.getEntity("TaskLog_NS.getTaskLogById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskLog#getTaskLogList(com.shangde.edu.feed.condition.QueryTaskLogCondition)
	 */
	public PageResult getTaskLogList(QueryTaskLogCondition queryTaskLogCondition) {
		return simpleDao.getPageResult("TaskLog_NS.getTaskLogList",
				"TaskLog_NS.getTaskLogCount", queryTaskLogCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskLog#updateTaskLogUrlClickNum(java.util.Map)
	 */
	public Integer updateTaskLogUrlClickNum(Map map) {
		return simpleDao.update("TaskLog_NS.updateTaskLogUrlClickNum", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskLog#updateTaskLogSendNum(int)
	 */
	public Integer updateTaskLogSendNum(int taskListId) {

		return simpleDao.update("TaskLog_NS.updateTaskLogSendNum", taskListId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskLog#updateTaskLogSendCount(int)
	 */
	public Integer updateTaskLogSendCount(int taskListId) {
		return simpleDao
				.update("TaskLog_NS.updateTaskLogSendCount", taskListId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskLog#updateTaskLogSendErrorCount(int)
	 */
	public Integer updateTaskLogSendErrorCount(int taskListId) {
		return simpleDao.update("TaskLog_NS.updateTaskLogSendErrorCount",
				taskListId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskLog#getTaskLogConditionList(com.shangde.edu.feed.condition.QueryTaskLogCondition)
	 */
	public PageResult getTaskLogConditionList(
			QueryTaskLogCondition queryTaskLogCondition) {
		return simpleDao.getPageResult("TaskLog_NS.getTaskLogConditionList",
				"TaskLog_NS.getTaskLogConditionCount", queryTaskLogCondition);
	}

}
