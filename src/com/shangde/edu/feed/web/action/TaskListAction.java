package com.shangde.edu.feed.web.action;

import java.text.ParseException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;


import com.shangde.common.action.CommonAction;
import com.shangde.common.schedule.service.SchedulerService;

/**
 * 
 * @author Libg
 * 
 */

public class TaskListAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(TaskListAction.class);
	@Resource
	private SchedulerService schedulerService;
	
	public void start()
	{
		System.out.println("Task starting...");
		CronExpression cron;
		try {
			cron = new CronExpression("0/50 * * ? * * *");
			schedulerService.schedule(null, cron, "email");
//			schedulerService.schedule("job", "day", cron);
		} catch (ParseException e1) {
			logger.error("TaskListAction.start", e1);
		}
	}
}
