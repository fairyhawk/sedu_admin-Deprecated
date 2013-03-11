package com.shangde.edu.feed.utils.thread;

import com.shangde.edu.feed.utils.PlatformService;

/**
 * 发送任务线程
 * 
 * @author Libg
 * 
 */
public class TaskSendThread implements Runnable {

	/**
	 * 任务id
	 */
	private int taskId;
	/**
	 * 视频id
	 */
	private String videoId;
	/**
	 * 发送人
	 */
	private String sendTo;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 发送的内容
	 */
	private String taskContent;
	/**
	 * 模板内容
	 */
	private String templateContent;
	/**
	 * 发送时间
	 */
	private String sendTime;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		executeSendTask();
	}

	/**
	 * 执行任务
	 */
	private void executeSendTask() {
		System.out.println("-------------------------------");
		PlatformService.sendTask(taskId, videoId, sendTo, title, taskContent,
				templateContent, sendTime);
	}

	public TaskSendThread(int taskId, String videoId, String sendTo,
			String title, String taskContent, String templateContent,
			String sendTime) {

		this.taskId = taskId;
		this.videoId = videoId;
		this.sendTo = sendTo;
		this.title = title;
		this.taskContent = taskContent;
		this.templateContent = templateContent;
		this.sendTime = sendTime;
	}

}
