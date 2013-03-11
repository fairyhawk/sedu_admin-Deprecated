package com.shangde.edu.feed.schedule;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.shangde.edu.feed.domain.TaskList;
import com.shangde.edu.feed.domain.TaskSendLog;
import com.shangde.edu.feed.domain.Template;
import com.shangde.edu.feed.mail.EmailExecutor;
import com.shangde.edu.feed.mail.EmailInfo;
import com.shangde.edu.feed.mail.MimeMailService;
import com.shangde.edu.feed.service.IMicroVideo;
import com.shangde.edu.feed.service.ITaskList;
import com.shangde.edu.feed.service.ITaskLog;
import com.shangde.edu.feed.service.ITaskSendLog;
import com.shangde.edu.feed.service.ITemplate;
import com.shangde.edu.feed.utils.PropertiesUtil;

/**
 * 任务线程类
 * 
 * @author Basil
 *
 */
public class TaskThread implements Runnable{

	ITaskList taskListService;//任务服务
	ITaskLog taskLogService;//任务log服务
	ITaskSendLog taskSendLogService;//任务发送log服务
	MimeMailService mimeMailService;//邮件模板引擎服务
	IMicroVideo microVideoService;//微学习视频服务
	TaskList task;//任务对象
	ITemplate feedTempageService;//模板接口
	private String templateContent;//模板内容
	private static Logger logger = LoggerFactory.getLogger(TaskThread.class);
	public TaskThread(int taskId, ApplicationContext applicationContext)
	{
		logger.info("task trhead");
		
		this.taskListService = (ITaskList) applicationContext.getBean("taskListService");//任务服务
		this.taskLogService = (ITaskLog) applicationContext.getBean("taskLogService");//任务log服务
		this.taskSendLogService = (ITaskSendLog)applicationContext.getBean("taskSendLogService");//任务发送log服务
		this.mimeMailService = (MimeMailService)applicationContext.getBean("mimeMailService");//邮件模板引擎服务
		this.microVideoService =(IMicroVideo)applicationContext.getBean("microVideoService");//微学习视频服务
		this.task = taskListService.getTaskListById(taskId);//任务对象
		this.feedTempageService=(ITemplate)applicationContext.getBean("feedTempageService");//微学习模板服务
	}
	
	private void postMail()
	{
		
		logger.info("post mail.....");
		EmailInfo email = new EmailInfo();
		//TODO: 99=test user
		if (task.getSendObject() != 99)
		{
			try {
				email.setAddr(PropertiesUtil.getEntryValue("email.exlist", "email.properties") + "," + task.getUserData());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			email.setAddr(task.getUserData());
		}
		email.setMark(String.valueOf(task.getId()));
		email.setSubject(task.getName());

		email.setContent(task.getContent());

		Template template = this.feedTempageService.getTemplateById(task.getTemplateId());
		if(template != null){
			this.templateContent = template.getContent();
		}
		
		logger.info("Email sending...");

		String videoId = "";
		if (email.getAddress().length > 0) {
			logger.info("Send email Size: {}", email.getAddress().length);
			for (String addr : email.getAddress()) {
				// TODO: 多个视频上传显示名称
				videoId = task.getVideoId();
				try{
					if(videoId != null){
						videoId = videoId.split(",")[0];
					}
				}catch (Exception e) {
					logger.error("解析视频id---取得一个返回",e);
				}
				EmailExecutor.send(email, addr, taskLogService, task.getId(), mimeMailService, microVideoService, videoId,templateContent);
				
				//任务发送记录，[某个任务发送至某个人]
				taskSendLogService.addTaskSendLog(getTaskSendLog(task.getId(),addr));
			}
		}
	}
	public void run() {
		// TODO Auto-generated method stub
		postMail();
	}
	/**
	 * 创建对象
	 * 
	 * @param email
	 * @param taskId
	 * @return
	 */
	private TaskSendLog getTaskSendLog(int taskId,String email){
		
		TaskSendLog taskSendLog = new TaskSendLog();
		taskSendLog.setTaskListId(taskId);
		taskSendLog.setEmail(email);
		taskSendLog.setPubDate(new Date());
		
		return taskSendLog;
	}
}
