package com.shangde.edu.feed.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.shangde.common.schedule.Constant;
import com.shangde.edu.feed.utils.AsyncProcPool;

/**
 * 订阅服务执行实现
 * 
 * @author Basil
 *
 */
public class FeedJobBean extends QuartzJobBean{
	private FeedService feedService;
	
	public void setFeedService(FeedService feedService)
	{
		this.feedService = feedService;
	}

	private static Logger logger = LoggerFactory.getLogger(FeedJobBean.class);

	private ApplicationContext applicationContext;

	/**
	 * 从SchedulerFactoryBean注入的applicationContext.
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * 重写实现
	 * 回调
	 */
	protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
		
		logger.debug("DEBUG ---------------------------------------------------------");
		Trigger trigger = jobexecutioncontext.getTrigger(); 
		// 触发器名称
		String triggerName = trigger.getName();
		// 触发器任务服
		String group = trigger.getGroup();
		
		if (triggerName != null && !triggerName.equals("") )
		{
			// 邮件组任务
			if (group.equals(Constant.PLAN_TASK) && triggerName.split("&")[0] != null)
			{
/*				ITaskList taskListService = (ITaskList) applicationContext.getBean("taskListService");//任务服务
				ITaskLog taskLogService = (ITaskLog) applicationContext.getBean("taskLogService");//任务log服务
				ITaskSendLog taskSendLogService = (ITaskSendLog)applicationContext.getBean("taskSendLogService");//任务发送log服务
				MimeMailService mimeMailService = (MimeMailService)applicationContext.getBean("mimeMailService");
				IMicroVideo microVideoService =(IMicroVideo)applicationContext.getBean("microVideoService");
					
				
				TaskList task = taskListService.getTaskListById(Integer.parseInt(triggerName.split("&")[0]));
				logger.info("There are trigger name is {} and Trigger group is {}", triggerName, group);
				
				// TODO: 加入日志记录 service, 邮件模板 service
				try {
					feedService.postEmail(triggerName, task, taskLogService,taskSendLogService, mimeMailService,microVideoService);
					
				} catch (Exception e) {
					logger.error(e.getMessage());
				}*/
				
				logger.info("trigger start");
				
				AsyncProcPool.putTask(new TaskThread(Integer.parseInt(triggerName.split("&")[0]), applicationContext));
//				AsyncProcPool.shutdown();
			}
		}

	}
}
