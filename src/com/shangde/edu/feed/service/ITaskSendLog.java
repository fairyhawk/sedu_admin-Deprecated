package com.shangde.edu.feed.service;

import com.shangde.edu.feed.domain.TaskSendLog;

/**
 * 任务发送记录接口
 * 
 * @author Libg
 * 
 */
public interface ITaskSendLog {

	/**
	 * 添加任务log记录
	 * 
	 * @param taskSendLog
	 */
	public void addTaskSendLog(TaskSendLog taskSendLog);

	/**
	 * 根据任务记录id取得数据
	 * 
	 * @param id
	 * @return
	 */
	public TaskSendLog getTaskSendLogById(int id);

}
