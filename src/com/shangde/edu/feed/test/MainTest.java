package com.shangde.edu.feed.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shangde.common.schedule.Constant;
import com.shangde.common.schedule.service.SchedulerService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryTaskListCondition;
import com.shangde.edu.feed.dto.TaskListDTO;
import com.shangde.edu.feed.service.ITaskList;

public class MainTest {
	
	public static void getTaskList(ITaskList taskList){
		
		QueryTaskListCondition queryTaskListCondition = new QueryTaskListCondition();
		queryTaskListCondition.setCurrentPage(1);
		queryTaskListCondition.setPageSize(10);
		
		PageResult pr = taskList.getTaskListVList(queryTaskListCondition);
		List<TaskListDTO> list = pr.getPageResult();
		
		
		System.out.println("maxPage->" + pr.getTotalPage());
		for(TaskListDTO tl : list){
			System.out.println(tl.getId() + "\t" + tl.getUac72Count() + "\t" + tl.getTotals());
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext springContext = new ClassPathXmlApplicationContext(new String[] { "classpath*:/config/spring/applicationContext.xml", "classpath:/config/spring/applicationContext-quartz.xml" });
		SchedulerService schedulerService = (SchedulerService) springContext.getBean("schedulerService");
		
		//ITaskList taskList = (ITaskList)springContext.getBean("taskListService");
		//getTaskList(taskList);
		
/*		//TODO: 取任务列表
		ITaskList taskListService = (ITaskList) springContext.getBean("taskListService");
		TaskList task = taskListService.getTaskListById(1000);
		*/
		//TODO: 添加任务ID与时间到定时器 SimpleTrigger
/*		Map<String, String> map = new HashMap<String, String>();
		map.put(Constant.TRIGGERNAME, String.valueOf(task.getId()));
		map.put(Constant.TRIGGERGROUP, "email");
		map.put(Constant.STARTTIME, "2011-09-13 10:42");
		schedulerService.schedule(map);
		*/
		try {
//			task.getPlanningMode().getCronExpression();
/*			String cronStr = task.getPlanningMode().getCronExpression();
			System.out.println(cronStr);
			// 添加定时任务
			CronExpression cron;
			cron = new CronExpression(cronStr);*/
			
			Date testDate = DateUtils.parseDate("2011-10-10 16:53:00", new String[]{"yyyy-MM-dd HH:mm:ss"});
			schedulerService.schedule("3", testDate, Constant.PLAN_TASK);
			
			//CronExpression cron = new CronExpression(Utils.getCronExpression("2011-10-10 14:47:00"));
			//schedulerService.schedule("3", cron, Constant.PLAN_TASK);
	
//			System.out.println(schedulerService.removeTrigdger("1000&c771dd76-1089-41c9-9b5b-1b69a62c0a6f", Constant.PLAN_TASK));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
/*		ITaskList taskListService = (ITaskList) springContext.getBean("taskListService");
		TaskList task = null;
		task = taskListService.getTaskListById(1000);
		
		System.out.println(task.getContent());*/
/*		
		
		System.out.println();*/
/*		ITaskList taskListService = (ITaskList) springContext.getBean("taskListService");
		
		TaskList task = new TaskList();
//		task.setId(1000);
		task.setModified(new Date());
		task.setName("10");
		task.setSubjectId(13);
		task.setSendMode(2);
		task.setUserData("chensong@sunland.org.cn,luibaisheng@sunland.org.cn");*/
		
		// 执行任务添加
//		taskListService.addTaskList(task);
		
/*		CronExpression cron;
		try {
			cron = new CronExpression("0/10 * * ? * * *");
			Map<String, String> map = new HashMap<String, String>();
			map.put(Constant.TRIGGERNAME, "24time");
			map.put(Constant.TRIGGERGROUP, "email");
			map.put(Constant.STARTTIME, "2011-09-10 16:18");
			schedulerService.schedule(map);
			schedulerService.schedule(null, cron, "plan");
//			schedulerService.schedule("job", "day", cron);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		
*/		// 执行业务逻辑...

		// 设置调度任务
		// 每10秒中执行调试一次
//		schedulerService.schedule("0/50 * * ? * * *");


	}

	private static Date parse(String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
