package com.shangde.edu.feed.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryTaskListCondition;
import com.shangde.edu.feed.domain.TaskList;
import com.shangde.edu.feed.service.ITaskList;

/**
 * TaskList实现类
 * 
 * @author Libg
 */

public class TaskListImpl extends BaseService implements ITaskList,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskList#addTaskList(com.shangde.edu.feed.domain.TaskList)
	 */
	public Integer addTaskList(TaskList taskList) {
		return simpleDao.createEntity("TaskList_NS.createTaskList", taskList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskList#delTaskListById(int)
	 */
	public Integer delTaskListById(int id) {
		return simpleDao.delete("TaskList_NS.deleteTaskListById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskList#updateTaskList(com.shangde.edu.feed.domain.TaskList)
	 */
	public Integer updateTaskList(TaskList taskList) {
		return simpleDao.update("TaskList_NS.updateTaskList", taskList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskList#getTaskListById(int)
	 */
	public TaskList getTaskListById(int id) {

		return simpleDao.getEntity("TaskList_NS.getTaskListById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskList#getTaskListByIdEmail(java.util.Map)
	 */
	public Integer getTaskListByIdEmail(Map map) {
		return simpleDao.getEntity("TaskList_NS.getTaskListByIdEmail", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskList#getTaskListList(com.shangde.edu.feed.condition.QueryTaskListCondition)
	 */
	public PageResult getTaskListList(
			QueryTaskListCondition queryTaskListCondition) {
		return simpleDao.getPageResult("TaskList_NS.getTaskListList",
				"TaskList_NS.getTaskListCount", queryTaskListCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskList#updateTaskListStatus(java.util.Map)
	 */
	public Integer updateTaskListStatus(Map map) {
		return simpleDao.update("TaskList_NS.updateTaskListStatus", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskList#getTaskListVList(com.shangde.edu.feed.condition.QueryTaskListCondition)
	 */
	public PageResult getTaskListVList(
			QueryTaskListCondition queryTaskListCondition) {
		return simpleDao.getPageResult("TaskList_NS.getTaskListPageList",
				"TaskList_NS.getTaskListPageCount", queryTaskListCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.ITaskList#getTaskIdRegularlySentTime(java.util.Map)
	 */
	public Map<Integer, String> getTaskIdRegularlySentTime(
			Map<String, Object> map) {

		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		List<TaskList> list = simpleDao.getForList(
				"TaskList_NS.getTaskIdRegularlySentTime", map);

		if (list != null && list.size() > 0) {
			for (TaskList tl : list) {
				resultMap.put(tl.getId(), tl.getRegularlySentTime());
			}
		}
		return resultMap;
	}

}
