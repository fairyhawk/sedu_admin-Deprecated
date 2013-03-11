package com.shangde.edu.feed.service.impl;

import com.shangde.common.service.BaseService;
import com.shangde.edu.feed.domain.TaskSendLog;
import com.shangde.edu.feed.service.ITaskSendLog;

/**
 * 任务发送记录实现类
 * 
 * @author Libg
 * 
 */
public class TaskSendLogImpl extends BaseService implements ITaskSendLog {

	public void addTaskSendLog(TaskSendLog taskSendLog) {

		simpleDao.createEntity("TaskSendLog_NS.createTaskSendLog", taskSendLog);
	}

	public TaskSendLog getTaskSendLogById(int id) {

		return simpleDao.getEntity("TaskSendLog_NS.getTaskSendLogById", id);
	}

}
