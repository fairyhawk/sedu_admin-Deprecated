package com.shangde.edu.feed.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;

import com.shangde.common.action.CommonAction;
import com.shangde.common.schedule.Constant;
import com.shangde.common.schedule.service.SchedulerService;
import com.shangde.common.vo.SelectModel;
import com.shangde.edu.feed.condition.QueryAdCondition;
import com.shangde.edu.feed.condition.QueryAdStatCondition;
import com.shangde.edu.feed.condition.QueryBrowseLogCondition;
import com.shangde.edu.feed.condition.QueryPlanningModeCondition;
import com.shangde.edu.feed.condition.QueryReviewCondition;
import com.shangde.edu.feed.condition.QueryStatCommonCondition;
import com.shangde.edu.feed.condition.QueryTaskListCondition;
import com.shangde.edu.feed.condition.QueryTaskLogCondition;
import com.shangde.edu.feed.condition.QueryUserUseCondition;
import com.shangde.edu.feed.condition.QueryVideoLogCondition;
import com.shangde.edu.feed.domain.Ad;
import com.shangde.edu.feed.domain.Course;
import com.shangde.edu.feed.domain.From;
import com.shangde.edu.feed.domain.MicroVideo;
import com.shangde.edu.feed.domain.PlanningMode;
import com.shangde.edu.feed.domain.Review;
import com.shangde.edu.feed.domain.SubscribeUser;
import com.shangde.edu.feed.domain.TaskList;
import com.shangde.edu.feed.domain.TaskLog;
import com.shangde.edu.feed.domain.Template;
import com.shangde.edu.feed.domain.VideoLog;
import com.shangde.edu.feed.dto.TaskListDTO;
import com.shangde.edu.feed.service.IAd;
import com.shangde.edu.feed.service.IAdStat;
import com.shangde.edu.feed.service.IBrowseLog;
import com.shangde.edu.feed.service.ICourse;
import com.shangde.edu.feed.service.IFrom;
import com.shangde.edu.feed.service.IMicroVideo;
import com.shangde.edu.feed.service.IPlanningMode;
import com.shangde.edu.feed.service.IReview;
import com.shangde.edu.feed.service.ISubscribeUser;
import com.shangde.edu.feed.service.ITaskList;
import com.shangde.edu.feed.service.ITaskLog;
import com.shangde.edu.feed.service.ITemplate;
import com.shangde.edu.feed.service.IUserUse;
import com.shangde.edu.feed.service.IVideoLog;
import com.shangde.edu.feed.utils.Constants;
import com.shangde.edu.feed.utils.PropertiesUtil;
import com.shangde.edu.feed.utils.Utils;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;

/**
 * 后台业务
 * 
 * @author Libg
 * 
 */
public class FeedBackAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(FeedBackAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 邮件服务接口 */
	@Autowired
	private SchedulerService schedulerService;

	/** 服务接口 */
	private ITaskList taskListService;
	private ITemplate feedTempageService;
	private ITaskLog taskLogService;
	private IVideoLog videoLogService;
	private ISubject subjectService;
	private IPlanningMode planningModeService;
	private ISubscribeUser subscribeUserService;
	private IReview reviewService;
	private IAdStat adStatService;
	private IUserUse userUseService;
	private IBrowseLog browseLogService;
	private IFrom fromService;
	private IAd adService;
	private ICourse feedCourseService;
	private IMicroVideo microVideoService;

	/** domain接口 */
	private TaskList task;
	private Template template;
	private VideoLog videoLog;
	private Review review;

	/** 查询domain接口 */
	private QueryTaskListCondition queryTaskListCondition;
	private QueryTaskLogCondition queryTaskLogCondition;
	private QueryPlanningModeCondition queryPlanningModeCondition;
	private QueryVideoLogCondition queryVideoLogCondition;
	private QueryReviewCondition queryReviewCondition;
	private QueryAdStatCondition queryAdStatCondition;
	private QueryUserUseCondition queryUserUseCondition;
	private QueryBrowseLogCondition queryBrowseLogCondition;
	private QueryStatCommonCondition queryStatCommonCondition;
	private QueryAdCondition queryAdCondition;

	/** 常量 */
	private int id;// id
	private String jsonResult;// json返回值
	private String charEncoding = "UTF-8";
	private String urlAction = "http://" + Constants.DOMAIN + "/template!getTemplateContent.action";// 请求模板内容actionUrl

	private String videoStr;// 视频字符串集,[id:name,id:name]视频id:视频名称
	private String regularlySentTime;//发送时间
	private String msg;//提示信息[成功/失败]等信息提示
	private String downFileURL = "/back/jsp/feed/export/task.csv";//下载文件地址
	private Integer targetReviewId;
	private Integer courseId;
	/*********数据统计参数，使用**********/
	private Integer type;//类型[1=广告,2=页面访问,3=登录/注册]
	private Integer where;//条件[1=日，2=周，3=月]
	private Integer count;//数值
	private Integer regClickCount;//注册点击数
	private Integer regCount;//注册成功数
	private Integer loginClickCount;//登录点击数
	private Integer loginCount;//登录成功数
	private Integer status;//状态

	/** 集合对象 */
	private List<Subject> subjectList;// 专业列表
	private List<SelectModel> sendModeList;// 发送模式选项
	private List<SelectModel> taskStatusList;// 任务->状态
	private List<SelectModel> sendObjectList;// 发送对象选项

	private List<TaskList> taskList;// 任务列表
	private List<Template> templateList;// 模板列表
	private List<PlanningMode> planningModeList;// 计划模式列表
	private List<SubscribeUser> subscribeUserList;// 用户列表
	private List<String> commonList;//[存储不同渠道注册/登录统计数][存储不同广告统计数]
	private List<Course> courseList;//课程列表

	/**
	 * 进入添加任务页面
	 * 
	 * @return
	 */
	public String addTask() {
		try{
		//初始化基本辅助信息
		subjectList = subjectService.getAllSubject();// 专业列表
		templateList = feedTempageService.getTemplateList();// 模板列表
		planningModeList = planningModeService.getPlanningModeList();// 计划模式,列表

		//System.out.println("=----------------------=");
		//Test.w("task_stop_time.properties", getRealPath("//WEB-INF//classes//task_stop_time.properties"));
		}catch(Exception e){
			logger.error("FeedBackAction.addTask", e);
			return ERROR;
		}
		return "task_add";
	}

	/**
	 * 添加任务
	 * 
	 * @return
	 */
	public String doAddTask() {
		try{
			// 暂时不使用模板，如下代码暂时注释
			// String url = urlAction + "?id=" + task.getTemplateId() + "&videoIds="
			// + task.getVideoId();
			// task.setContent(Utils.getFileContent(Utils.getInputStream(url),charEncoding));
	
			Date now = new Date();
			StringBuffer userData = new StringBuffer();
			StringBuffer videoIds = new StringBuffer();
			String[] videoArr = videoStr.split(",");
			String[][] video_id_name = new String[videoArr.length][2];
			String[] temp = null;
			int i = 0;
			int size = 0;
			for (String s : videoArr) {
				temp = s.split(":");
				video_id_name[i][0] = temp[0];
				video_id_name[i][1] = temp[1];
				i++;
	
				videoIds.append(temp[0]).append(",");
			}
	
			task.setPubdate(now);
			task.setModified(now);
			task.setStatus(1);// 设置状态
			task.setVideoId(videoIds.toString());// 设置视频id串
			// 根据所选发送对象，查询对应的用户列表
			if (task.getSendObject() == 2) {// 购买用户
				subscribeUserList = subscribeUserService.getCustomerBuyList(task.getSubjectId());
			} else if (task.getSendObject() == 3) {// 注册用户
				subscribeUserList = subscribeUserService.getCustomerRegList(task.getSubjectId());
			} else if (task.getSendObject() == 99) {// 测试用户
	
				String[] testAddr = null;
				try {
					testAddr = PropertiesUtil.getEntryValue("email.testlist", "email.properties").split(",");
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (testAddr != null) {
					subscribeUserList = new ArrayList<SubscribeUser>();
					SubscribeUser subUser = null;
					int len = testAddr.length;
					int userCount = 1;
					for (int u = 0; u < userCount; u++) {
						for (int k = 0; k < len; k++) {
							subUser = new SubscribeUser();
							subUser.setEmail(testAddr[k]);
							subscribeUserList.add(subUser);
						}
					}
				}
			} else if (task.getSendObject() == 100) {// 默认用户列表
				/******************** 临时代码，发默认用户列表 *********************************/
				try {
					task.setUserData(PropertiesUtil.getEntryValue("user_data","user_data.properties"));
					size = task.getUserData().toString().split(",").length;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
			if (task.getSendObject() != 100) {// 当条件成立时,正常业务代码处理
				size = subscribeUserList.size();// 取得用户列表大小
				// 将用户列表组装成字符串格式进行存储
				for (SubscribeUser su : subscribeUserList) {
					userData.append(su.getEmail()).append(",");
				}
				task.setUserData(userData.substring(0, userData.lastIndexOf(",")));// 设置属性，首先截取串中最后一个","符号
			}
	
			if (task.getSendMode() == 2) {// 定时发送
				regularlySentTime = Utils.getMinuteIncremental(regularlySentTime, 10);
				task.setRegularlySentTime(regularlySentTime);// 这里需要根据时间进行转换
				// 此时方知一个0，无效值
				PlanningMode planningMode = new PlanningMode();
				planningMode.setId(0);
				task.setPlanningMode(planningMode);
			} else {
				task.setRegularlySentTime(null);
			}
			// 添加到数据库中,返回记录id
			int id = taskListService.addTaskList(task);
			if (id > 0) {
				// 与任务记录关联的log增加记录
	
				TaskLog taskLog = new TaskLog();
				taskLog.setPubdate(now);
				taskLog.setModified(now);
				taskLog.setSendCount(0);
				taskLog.setSendErrorCount(0);
				taskLog.setSendNum(0);
				taskLog.setStatus(0);
				taskLog.setTaskListId(id);
				taskLog.setUrlClickNum(0);
				taskLog.setTotal(size);// 一共要发送的人数
	
				taskLogService.addTaskLog(taskLog);// 添加与任务关联的日志记录
				// 与任务记录关联的视频log增加/修改记录
				int length = video_id_name.length;
				// 视频日志记录处理业务[没有则添加，有则修改,发送总次数++]
				for (int j = 0; j < length; j++) {
					// 校验是否该视频已被记录，如果没有记录则增加记录，否则修改
					VideoLog vl = videoLogService.getVideoLogByVideoId(Integer.parseInt(video_id_name[j][0]));
					if (vl == null) {
						VideoLog videoLog = new VideoLog();
						videoLog.setActiveNum(0);
						videoLog.setBuyNum(0);
						videoLog.setClickBuyNum(0);
						videoLog.setClickNum(0);
						videoLog.setSubjectId(task.getSubjectId());
						videoLog.setTotal(size);// 视频预计发送次数与就等于，任务发送的次数
						videoLog.setVideoId(Integer.valueOf(video_id_name[j][0]));
						videoLog.setVideoName(video_id_name[j][1]);
						videoLog.setPubdate(now);
						videoLog.setModified(now);
						// 添加视频记录
						videoLogService.addVideoLog(videoLog);
					} else {
						// 执行修改操作
						vl.setModified(now);
						vl.setTotal(vl.getTotal() + size);// 预计发送总数增加
						videoLogService.updateVideoLog(vl);
					}
				}
			}
		}catch(Exception e){
			logger.error("FeedBackAction.doAddTask", e);
			return ERROR;
		}

		return "to_task_list";
	}

	/**
	 * 任务列表页面,这里查询任务日志列表
	 * 
	 * @return
	 */
	public String taskList() {
		try{
			subjectList = subjectService.getAllSubject();
	
			
			this.getQueryTaskLogCondition().setPageSize(30);
			
			if(queryTaskLogCondition.getSubjectId() == null || queryTaskLogCondition.getSubjectId().intValue() == -1){
				queryTaskLogCondition.setSubjectId(null);
			}
			if(queryTaskLogCondition.getStartTime() == null || queryTaskLogCondition.getStartTime().equals("")){
				queryTaskLogCondition.setStartTime(null);
			}
			if(queryTaskLogCondition.getEndTime() == null || queryTaskLogCondition.getEndTime().equals("")){
				queryTaskLogCondition.setEndTime(null);
			}
			
			setPage(taskLogService.getTaskLogConditionList(queryTaskLogCondition));
			/********************************开始**************/
			/**
			 * 新算法,这里关联支撑平台
			 */
			/**
			PageResult pr = getPage();
			List<TaskLog> list = pr.getPageResult();
			//这里通过支撑平台取得邮件的发送失败数，然后计算出成功数
			for(TaskLog tl : list){
				try{
					int sendCount = PlatformService.getTaskSendConditionByTaskId(tl.getTaskListId(), "error");
					int sendErrorCount = PlatformService.getTaskSendConditionByTaskId(tl.getTaskListId(), "success");
					tl.setSendCount(sendCount);
					tl.setSendErrorCount(sendErrorCount);
				}catch (Exception e) {
					logger.error("调用远程支撑平台接口--计算失败",e);
				}
				System.out.println("---------------------" + tl.getId());
			}
			pr.setPageResult(list);
			setPage(pr);
			*/
			/********************************结束**************/
			
			
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(30);
			}
			setPageUrlParms();
		}catch(Exception e){
			logger.error("FeedBackAction.taskList", e);
			return ERROR;
		}
		return "task_list";
	}

	/**
	 * 发送任务
	 * 
	 * 这里出发接口发送
	 * 
	 * @return
	 */
	public String sendTask() {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", id);
		map.put("status", 2);

		try {

			//修改状态
			if (taskListService.updateTaskListStatus(map) == 0) {
				jsonResult = "3";// 修改失败(修改记录状态失败，修改为'发送'状态)
				return "jsonResult";
			}

			TaskList taskList = taskListService.getTaskListById(id);

			
			// 这里需要判断当前任务id是否在，邮箱服务中已发送完, 查询出的数据为null空则表示发送完毕
			// 从某组所有列表中取得当前/待执行任务名称,如果存在则表示正在发送
			boolean flag = false;
			String[] triggerNames = schedulerService.getTriggerNames(Constant.PLAN_TASK);
			for (String s : triggerNames) {
				if (s.split("&")[0].equals(String.valueOf(id))) {
					flag = true;
				}
			}
			if (!flag) {
				addSchedule(taskList);
				jsonResult = "1";// 成功，表示已将任务触发
			} else {// 不可以发送
				jsonResult = "2";// 该任务正在发送中
			}
			
			/*****************调用支撑平台接口实现发送任务************/
			/**
			//发送任务，丢给线程
			try{
				String videoId = task.getVideoId();
				try{
					if(videoId != null){
						videoId = videoId.split(",")[0];
					}
				}catch (Exception e) {
					logger.error("解析视频id---取得一个返回",e);
				}
				String sendTo = PropertiesUtil.getEntryValue("email.exlist", "email.properties") + "," + taskList.getUserData(); 
				String templateContent = feedTempageService.getTemplateById(taskList.getTemplateId()).getContent();
				AsyncProcPool.putTask(
						new TaskSendThread(taskList.getId(),
								videoId,
								sendTo,
								taskList.getName(),
								taskList.getContent(),
								templateContent,
								taskList.getRegularlySentTime()));
			}catch (Exception e) {
				logger.error("调用远程支撑平台接口--计算失败",e);
			}*/
			
			
		} catch (Exception e) {
			jsonResult = "0";// 异常
			logger.error("FeedBackAction.sendTask", e);
		}

		return "jsonResult";
	}

	/**
	 * 删除任务,修改状态值，status=3
	 * 
	 * 需要判断如下
	 * 
	 * 1、如果正在发送任务不允许删除
	 * 
	 * @return
	 */
	public String doDelTask() {
		try{
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("id", id);
			map.put("status", 3);
	
			if (taskListService.updateTaskListStatus(map) > 0) {
				jsonResult = "1";
			} else {
				jsonResult = "0";
			}
		}catch(Exception e){
			logger.error("FeedBackAction.doDelTask", e);
		}
		return "jsonResult";
	}

	public String updateTask() {
		try{
			subjectList = subjectService.getAllSubject();// 专业列表
			templateList = feedTempageService.getTemplateList();// 模板列表
			planningModeList = planningModeService.getPlanningModeList();// 计划模式,列表
	
			task = taskListService.getTaskListById(id);
		}catch(Exception e){
			logger.error("FeedBackAction.updateTask", e);
			return ERROR;
		}
		return "task_update";
	}

	/**
	 * 修改
	 * 
	 * 1、正在发送任务不允许修改
	 * 
	 * @return
	 */
	public String doUpdateTask() {
		try{
			if (taskListService.updateTaskList(task) > 0) {
				jsonResult = "1";
			} else {
				jsonResult = "0";
			}
		}catch(Exception e){
			logger.error("FeedBackAction.doUpdateTask", e);
		}
		return "jsonResult";
	}

	/**
	 * 视频日志列表查询
	 * 
	 * @return
	 */
	public String videoLogList() {
		try{
			queryVideoLogCondition.setPageSize(30);
			setPage(videoLogService.getVideoLogList(queryVideoLogCondition));
	
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(30);
			}
			setPageUrlParms();
		}catch(Exception e){
			logger.error("FeedBackAction.videoLogList", e);
			return ERROR;
		}
		return "video_log_list";
	}
	
	/**
	 * 任务详情统计列表
	 * @return
	 */
	public String taskListV(){
		try{
			getQueryTaskListCondition().setPageSize(30);
			setPage(taskListService.getTaskListVList(queryTaskListCondition));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(30);
			}
			setPageUrlParms();
			
			/********************************开始**************/
			/**
			//新算法,这里关联支撑平台
			PageResult pr = getPage();
			List<TaskListDTO> list = pr.getPageResult();
			
			Map<String,Object> map = new HashMap<String, Object>();
			List<TaskListDTO> l = new ArrayList<TaskListDTO>();
			for(TaskListDTO tlDTO : list){
				l.add(tlDTO);
			}
			map.put("idsList", l);
			//根据任务id集合查询出id,RegularlySentTime结果集
			Map<Integer,String> resultMap = taskListService.getTaskIdRegularlySentTime(map);
			
			//这里通过支撑平台取得邮件的发送失败数，然后计算出成功数
			for(TaskListDTO tlDTO : list){
				try{
					String taskSendDate = resultMap.get(tlDTO.getId());//任务发送日期
					String day24 = DateUtil.addDays(taskSendDate, "yyyy-MM-dd HH:mm:ss", 1);//天数添加
					String day48 = DateUtil.addDays(taskSendDate, "yyyy-MM-dd HH:mm:ss", 2);
					String day72 = DateUtil.addDays(taskSendDate, "yyyy-MM-dd HH:mm:ss", 3);
					try{
						tlDTO.setDay24Count(PlatformService.getTaskDateSendNum(tlDTO.getId(), taskSendDate, day24));
					}catch (Exception e) {
						logger.error("调用远程支撑平台接口--计算失败--->taskId" + tlDTO.getId() + "->day24",e);
					}
					try{
						tlDTO.setDay48Count(PlatformService.getTaskDateSendNum(tlDTO.getId(), taskSendDate, day48));
					}catch (Exception e) {
						logger.error("调用远程支撑平台接口--计算失败--->taskId" + tlDTO.getId() + "->day48",e);
					}
					try{
						tlDTO.setDay72Count(PlatformService.getTaskDateSendNum(tlDTO.getId(), taskSendDate, day72));
					}catch (Exception e) {
						logger.error("调用远程支撑平台接口--计算失败--->taskId" + tlDTO.getId() + "->day72",e);
					}
				}catch (Exception e) {
					logger.error("调用远程支撑平台接口--计算失败",e);
				}
				System.out.println("---------------------" + tlDTO.getId());
			}
			pr.setPageResult(list);
			setPage(pr);
			*/
			/********************************结束**************/
			
			
			//取出当前页数据保存到session中,为导出功能提供数据
			setSession("taskListVDataList", getPage().getPageResult());
		}catch(Exception e){
			logger.error("FeedBackAction.taskListV", e);
			return ERROR;
		}
		return "task_list_v";
	}
	
	/**
	 * 导出当前页统计数据
	 * @return
	 */
	public String exportStatisticsPageData(){
		try{
			int count = 0;
			int size = 0;
			String fileURL = null;//文件路径
			String[] title = null;
			String[][] body = null;//
			
			List<TaskListDTO> taskList = null;
			
			try{
				taskList = getSession("taskListVDataList");
				size = taskList.size();
			}catch (Exception e) {
				logger.error("获取导出数据异常",e);
			}
			
			//没有数据，返回提示信息
			if(size == 0){
				
				msg = "没有数据，无法导出!";
				return "msg";
			}
			
			fileURL = getRealPath(downFileURL);//设置保存路径
			//14个大小
			title = new String[] { "任务ID","任务名称","计划总次数","1_实际发出数","1_激活数","2_实际发出数","2_激活数","3_实际发出数","3_激活数","3天发出邮件数","3天累计激活数","3天累计激活数率","总激活数","总激活率"};
			body = new String[size][title.length];
			
			for(TaskListDTO tld : taskList){
				
				body[count][0] = String.valueOf(tld.getId());//任务ID
				body[count][1] = String.valueOf(tld.getName());//任务名称
				body[count][2] = String.valueOf(tld.getPlanTotal());//计划总次数
				body[count][3] = String.valueOf(tld.getDay24Count());//1_实际发出数
				body[count][4] = String.valueOf(tld.getUac24Count());//1_激活数
				body[count][5] = String.valueOf(tld.getDay48Count());//2_实际发出数
				body[count][6] = String.valueOf(tld.getUac48Count());//2_激活数
				body[count][7] = String.valueOf(tld.getDay72Count());//3_实际发出数
				body[count][8] = String.valueOf(tld.getUac72Count());//3_激活数
				body[count][9] = String.valueOf(tld.getDay72Count());//3天发出邮件数
				body[count][10] = String.valueOf(tld.getUac72Count());//3天累计激活数
				if(tld.getDay72Count() > 0){
					body[count][11] = String.valueOf(Utils.percentage(tld.getUac72Count(), tld.getDay72Count(), 1) + "%");//3天累计激活数率
				}else{
					body[count][11] = "0.0%";//3天累计激活数率
				}
				body[count][12] = String.valueOf(tld.getTotals());//总激活数
				body[count][13] = String.valueOf(Utils.percentage(tld.getTotals(), tld.getPlanTotal(), 1) + "%");//总激活率
				
				count++;
			}
			
				Utils.writeCSV(title, body, fileURL);
			
			msg = "数据已导出，可以下载<a href='" + downFileURL + "'>点击下载</a>";
		}catch(Exception e){
			logger.error("FeedBackAction.exportStatisticsPageData", e);
			return ERROR;
		}
		return "msg";
	}
	
	/**
	 * 下载导出的数据(任务详情统计数据)
	 * 
	 * @return
	 */
	public String downStatisticsData(){
		
		File file = null;
		String fileURL = null;
		try{
			fileURL = getRealPath(downFileURL);
			file = new File(fileURL);
			//校验是否存在
			if(file != null && !file.exists()){
				
				msg = "下载文件不存在,还未导出!";
				return "msg";
			}
		}catch (Exception e) {
			logger.error("微学习->详细统计数据->下载->", e);
		}
		return "downFile";
	}
	
	/**
	 * 添加任务到邮箱服务表中
	 * 
	 * @param taskList
	 */
	private void addSchedule(TaskList taskList) throws Exception {

		String cronStr = null;
		Date time = null;
		CronExpression cron;// 添加定时任务
		try {
			if (taskList.getSendMode() == 2) {// 定时发送
				time = DateUtils.parseDate(taskList.getRegularlySentTime(), new String[] { "yyyy-MM-dd HH:mm:ss" });
				schedulerService.schedule(String.valueOf(taskList.getId()), time, Constant.PLAN_TASK);// 时间接口
			} /*
			 * else {// 计划任务,这里暂时注释,需求没有确定 cronStr =
			 * taskList.getPlanningMode().getCronExpression(); cron = new
			 * CronExpression(cronStr);
			 * schedulerService.schedule(String.valueOf(taskList.getId()),
			 * cron,Constant.PLAN_TASK);//表达式接口 }
			 */
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	/**
	 * 查询留言列表
	 * 
	 * @return
	 */
	public String getReviewList(){
		try{
			courseList = feedCourseService.getAllCourse();
			
			int pageSize = 50;
			
			getQueryReviewCondition().setPageSize(pageSize);
			if(queryReviewCondition.getStartTime() == null || queryReviewCondition.getStartTime().equals("")){
				queryReviewCondition.setStartTime(null);
			}
			if(queryReviewCondition.getEndTime() == null || queryReviewCondition.getEndTime().equals("")){
				queryReviewCondition.setEndTime(null);
			}
			setPage(reviewService.getReviewListBack(queryReviewCondition));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(pageSize);
			}
			setPageUrlParms();
		}catch(Exception e){
			logger.error("FeedBackAction.getReviewList", e);
			return ERROR;
		}
		return "review_list";
	}
	
	/**
	 * 修改状态
	 * @return
	 */
	public String updateReviewStatus(){
		
		getQueryReviewCondition().setStatus(status);
		getQueryReviewCondition().setId(id);
		
		try{
			jsonResult = "0";
			if(reviewService.updateReviewStatus(queryReviewCondition) > 0){
				jsonResult = "1";	
			}
		}catch (Exception e) {
			logger.error("修改提问状态",e);
		}
		
		return "jsonResult";
	}

	/**
	 * 根据课程id
	 * @return
	 */
	public String getVideoListByCourseId() {

		List<MicroVideo> mvList = microVideoService.getVideoByCourseId(courseId);
		StringBuffer s = new StringBuffer();
		
		try{
			for (MicroVideo mv : mvList) {
				s.append(mv.getId()).append(",").append(mv.getName()).append("|");
			}
		}catch (Exception e) {
			logger.error("根据课程id查询视频列表，组合失败",e);
		}
		jsonResult = s.toString();
		
		return "jsonResult";
	}
	
	
	/**
	 * 根据修改id查询留言记录
	 * 
	 * @return
	 */
	public String toEditReview(){
		
		review = reviewService.getReviewById(id);
		
		return "review_edit";
	}
	
	/**
	 * 修改留言记录,只修改status+detail字段
	 * 
	 * @return
	 */
	public String doEditReview(){
		
		//review 对象来自页面，请求来自会创建
		review.setModified(new Date());
		//修改status+detail字段,条件成立，执行失败
		if(reviewService.updateReviewStatusDetail(review) <= 0){
			return ERROR;
		}
		
		return "to_review_list";
	}
	
	/**
	 * 根据留言记录id删除数据
	 * 
	 * 需要参数：id记录,targetReviewId目标记录id(表示id记录针对本字段值回复内容)
	 * 
	 * @return
	 */
	public String doDelReviewById(){
		
		logger.info("---删除留言业务开始---");
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		try{
			
			map.put("id", id);
			map.put("incremental", -1);
			
			logger.info("修改留言记录---id" + id);
			//修改id记录所关联的目标记录counts值,修改成功在删除id记录
			if(reviewService.updateReviewCounts(map) > 0){
				if(reviewService.delReviewById(id) > 0){
					jsonResult = "1";		
				}else{
					jsonResult = "2";//删除失败
					logger.info("删除留言记录失败id=" + id);
				}	
			}else{
				jsonResult = "3";//修改失败
				logger.info("修改留言记录counts字段值失败id=" + id);
			}
		}catch (Exception e) {
			jsonResult = "0";//系统异常
			logger.error("删除留言业务失败!" ,e);
		}
		return "jsonResult";
	}
	
	
	/**
	 * 流量统计查询
	 * 
	 * @return
	 */
	public String queryTrafficStatistics(){
		try{
			int pageSize = 50;
			String gotoURL = null;
			//公共查询接口类
			getQueryStatCommonCondition().setPageSize(pageSize);
			if(queryStatCommonCondition.getStartTime() == null || queryStatCommonCondition.getStartTime().equals("")){
				queryStatCommonCondition.setStartTime(null);
			}
			if(queryStatCommonCondition.getEndTime() == null || queryStatCommonCondition.getEndTime().equals("")){
				queryStatCommonCondition.setEndTime(null);
			}
			if(type == 1){//ad广告统计
				//查询出所有正常status=1广告
				getQueryAdCondition().setStatus(1);
				List<Ad> adList = adService.getAdAllList(queryAdCondition);
				getQueryAdStatCondition().setPageSize(pageSize);
				if(where == 1){//日查询
					setPage(adStatService.getAdStatDATEList(queryAdStatCondition));
					count = adStatService.getAdStatDATEGroupCount(queryAdStatCondition);
					//这里查询所有广告日量					
					commonList = getAdStatRelatedData(adList,"DATE",null);
				}else if(where == 2){//周
					setPage(adStatService.getAdStatWEEKList(queryAdStatCondition));
					count = adStatService.getAdStatWEEKGroupCount(queryAdStatCondition);
					//这里查询所有广告周量
					commonList = getAdStatRelatedData(adList,"WEEK",null);
				}else if(where == 3){//月
					setPage(adStatService.getAdStatMONTHList(queryAdStatCondition));
					count = adStatService.getAdStatMONTHGroupCount(queryAdStatCondition);
					//这里查询所有广告月量
					commonList = getAdStatRelatedData(adList,"MONTH",null);
				}else{
					//这里表示，根据时间段查询
					setPage(adStatService.getAdList(queryStatCommonCondition));
					count = adStatService.getAdStatGroupCount(queryStatCommonCondition);
					//这里查询所有广告时间段量
					commonList = getAdStatRelatedData(adList,null,queryStatCommonCondition);
				}
				gotoURL = "ad_stat_list";
			}else if(type == 2){//页面访问统计
				getQueryBrowseLogCondition().setPageSize(pageSize);
				if(where == 1){//日查询
					setPage(browseLogService.getBrowseLogDATEList(queryBrowseLogCondition));
					count = browseLogService.getBrowseLogDATEGroupCount(queryBrowseLogCondition);
				}else if(where == 2){//周
					setPage(browseLogService.getBrowseLogWEEKList(queryBrowseLogCondition));
					count = browseLogService.getBrowseLogWEEKGroupCount(queryBrowseLogCondition);
				}else if(where == 3){//月
					setPage(browseLogService.getBrowseLogMONTHList(queryBrowseLogCondition));
					count = browseLogService.getBrowseLogMONTHGroupCount(queryBrowseLogCondition);
				}else{
					//这里表示，根据时间段查询
					setPage(browseLogService.getBrowseLogList(queryStatCommonCondition));
					count = browseLogService.getBrowseLogGroupCount(queryStatCommonCondition);
				}
				gotoURL = "page_stat_list";
			}else if(type == 3){//登录/注册统计
				//查询所有来源
				List<From> fromList = fromService.queryFromList();
				getQueryUserUseCondition().setPageSize(pageSize);
				if(where == 1){//日查询
					regClickCount = userUseService.getUserUseDATECount(1);
					regCount = userUseService.getUserUseDATECount(2);
					loginClickCount = userUseService.getUserUseDATECount(3);
					loginCount = userUseService.getUserUseDATECount(4);
					count = userUseService.getUserUseDATEGroupCount();
					
					//这里查询所有来源数据(根据不同来源查询注册/点击数据),日查询
					commonList = getRegLoginRelatedData(fromList, "DATE", queryStatCommonCondition);
				}else if(where == 2){//周
					regClickCount = userUseService.getUserUseWEEKCount(1);
					regCount = userUseService.getUserUseWEEKCount(2);
					loginClickCount = userUseService.getUserUseWEEKCount(3);
					loginCount = userUseService.getUserUseWEEKCount(4);
					count = userUseService.getUserUseWEEKGroupCount();
					
					//这里查询所有来源数据(根据不同来源查询注册/点击数据),周查询
					commonList = getRegLoginRelatedData(fromList, "WEEK", queryStatCommonCondition);
				}else if(where == 3){//月
					regClickCount = userUseService.getUserUseMONTHCount(1);
					regCount = userUseService.getUserUseMONTHCount(2);
					loginClickCount = userUseService.getUserUseMONTHCount(3);
					loginCount = userUseService.getUserUseMONTHCount(4);
					count = userUseService.getUserUseMONTHGroupCount();
					
					//这里查询所有来源数据(根据不同来源查询注册/点击数据),月查询
					commonList = getRegLoginRelatedData(fromList, "MONTH", queryStatCommonCondition);
				}else{
					//这里表示，根据时间段查询
					queryStatCommonCondition.setType(1);
					regClickCount = userUseService.getUserUseCount(queryStatCommonCondition);
					queryStatCommonCondition.setType(2);
					regCount = userUseService.getUserUseCount(queryStatCommonCondition);
					queryStatCommonCondition.setType(3);
					loginClickCount = userUseService.getUserUseCount(queryStatCommonCondition);
					queryStatCommonCondition.setType(4);
					loginCount = userUseService.getUserUseCount(queryStatCommonCondition);
					count = userUseService.getUserUseGroupCount(queryStatCommonCondition);
					//这里查询所有来源数据(根据不同来源查询注册/点击数据),时间段查询
					commonList = getRegLoginRelatedData(fromList, null, queryStatCommonCondition);
				}
				gotoURL = "regLogin_stat_list";
			}
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(pageSize);
			}
			setPageUrlParms();
			
			return gotoURL;
		}catch(Exception e){
			logger.error("FeedBackAction.queryTrafficStatistics", e);
			return ERROR;
		}
	}
	
	/**
	 * 根据广告数据，查询出相关“独立，点击数”
	 * 
	 * @param adList
	 * @param sign [DATE,WEEK,MONTH]
	 * 
	 * @return
	 */
	private List<String> getAdStatRelatedData(List<Ad> adList,String sign,QueryStatCommonCondition queryStatCommonCondition){
		
		StringBuffer str = null;
		List<String> list = new ArrayList<String>();
		
		//这里查询所有广告日量
		for(Ad ad : adList){
			str = new StringBuffer();
			
			if(sign == null){//根据时间段查询数据
				queryStatCommonCondition.setAdId(ad.getId());
				str.append(ad.getName() + "：独立点击数=" + adStatService.getAdStatAndAdIdGroupCount(queryStatCommonCondition));
				str.append("/点击数=" + adStatService.getAdStatAndAdIdCount(queryStatCommonCondition));
			}else if(sign.equals("DATE")){//日查询
				str.append(ad.getName() + "：独立点击数=" + adStatService.getAdStatDATEAndAdIdGroupCount(ad.getId()));
				str.append("/点击数=" + adStatService.getAdStatDATEAndAdIdCount(ad.getId()));	
			}else if(sign.equals("WEEK")){//周查询
				str.append(ad.getName() + "：独立点击数=" + adStatService.getAdStatWEEKAndAdIdGroupCount(ad.getId()));
				str.append("/点击数=" + adStatService.getAdStatWEEKAndAdIdCount(ad.getId()));
			}else if(sign.equals("MONTH")){//月查询
				str.append(ad.getName() + "：独立点击数=" + adStatService.getAdStatMONTHAndAdIdGroupCount(ad.getId()));
				str.append("/点击数=" + adStatService.getAdStatMONTHAndAdIdCount(ad.getId()));
			}
			list.add(str.toString());
		}
		return list;
	}
	
	/**
	 * 根据来源渠道，查询出相关“注册,登录数”
	 * 
	 * @param adList
	 * @param sign [DATE,WEEK,MONTH]
	 * 
	 * @return
	 */
	private List<String> getRegLoginRelatedData(List<From> fromList,String sign,QueryStatCommonCondition queryStatCommonCondition){
		
		StringBuffer str = null;
		List<String> list = new ArrayList<String>();
		
		//这里查询所有广告日量
		for(From from : fromList){
			str = new StringBuffer();
			
			queryStatCommonCondition.setType(1);
			queryStatCommonCondition.setFromId(from.getId());
			
			if(sign == null){//根据时间段查询数据
				str = new StringBuffer();
				str.append(from.getName() + "：注册点击数=" + userUseService.getUserUseAndTypeFromIdCount(queryStatCommonCondition)).append("/");
				queryStatCommonCondition.setType(3);
				str.append("登录点击数=" + userUseService.getUserUseAndTypeFromIdCount(queryStatCommonCondition));
			}else if(sign.equals("DATE")){//日查询
				str.append(from.getName() + "：注册点击数=" + userUseService.getUserUseDATEAndTypeFromIdCount(queryStatCommonCondition)).append("/");
				queryStatCommonCondition.setType(3);
				str.append("登录点击数=" + userUseService.getUserUseDATEAndTypeFromIdCount(queryStatCommonCondition));
			}else if(sign.equals("WEEK")){//周查询
				str = new StringBuffer();
				str.append(from.getName() + "：注册点击数=" + userUseService.getUserUseWEEKAndTypeFromIdCount(queryStatCommonCondition)).append("/");
				queryStatCommonCondition.setType(3);
				str.append("登录点击数=" + userUseService.getUserUseWEEKAndTypeFromIdCount(queryStatCommonCondition));
			}else if(sign.equals("MONTH")){//月查询
				str.append(from.getName() + "：注册点击数=" + userUseService.getUserUseMONTHAndTypeFromIdCount(queryStatCommonCondition)).append("/");
				queryStatCommonCondition.setType(3);
				str.append("登录点击数=" + userUseService.getUserUseMONTHAndTypeFromIdCount(queryStatCommonCondition));
			}
			list.add(str.toString());
		}
		return list;
	}
	

	public ITaskList getTaskListService() {
		return taskListService;
	}

	public void setTaskListService(ITaskList taskListService) {
		this.taskListService = taskListService;
	}

	public TaskList getTask() {
		return task;
	}

	public void setTask(TaskList task) {
		this.task = task;
	}

	public ITemplate getFeedTempageService() {
		return feedTempageService;
	}

	public void setFeedTempageService(ITemplate feedTempageService) {
		this.feedTempageService = feedTempageService;
	}

	public ITaskLog getTaskLogService() {
		return taskLogService;
	}

	public void setTaskLogService(ITaskLog taskLogService) {
		this.taskLogService = taskLogService;
	}

	public IVideoLog getVideoLogService() {
		return videoLogService;
	}

	public void setVideoLogService(IVideoLog videoLogService) {
		this.videoLogService = videoLogService;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public VideoLog getVideoLog() {
		return videoLog;
	}

	public void setVideoLog(VideoLog videoLog) {
		this.videoLog = videoLog;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<SelectModel> getSendModeList() {
		return sendModeList;
	}

	public void setSendModeList(List<SelectModel> sendModeList) {
		this.sendModeList = sendModeList;
	}

	public QueryTaskListCondition getQueryTaskListCondition() {
		if (queryTaskListCondition == null) {
			queryTaskListCondition = new QueryTaskListCondition();
		}
		return queryTaskListCondition;
	}

	public void setQueryTaskListCondition(QueryTaskListCondition queryTaskListCondition) {
		this.queryTaskListCondition = queryTaskListCondition;
	}

	public List<TaskList> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskList> taskList) {
		this.taskList = taskList;
	}

	public List<Template> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List<Template> templateList) {
		this.templateList = templateList;
	}

	public QueryTaskLogCondition getQueryTaskLogCondition() {
		if (queryTaskLogCondition == null) {
			queryTaskLogCondition = new QueryTaskLogCondition();
		}
		return queryTaskLogCondition;
	}

	public void setQueryTaskLogCondition(QueryTaskLogCondition queryTaskLogCondition) {
		this.queryTaskLogCondition = queryTaskLogCondition;
	}

	public IPlanningMode getPlanningModeService() {
		return planningModeService;
	}

	public void setPlanningModeService(IPlanningMode planningModeService) {
		this.planningModeService = planningModeService;
	}

	public QueryPlanningModeCondition getQueryPlanningModeCondition() {
		if (queryPlanningModeCondition == null) {
			queryPlanningModeCondition = new QueryPlanningModeCondition();
		}
		return queryPlanningModeCondition;
	}

	public void setQueryPlanningModeCondition(QueryPlanningModeCondition queryPlanningModeCondition) {
		this.queryPlanningModeCondition = queryPlanningModeCondition;
	}

	public List<PlanningMode> getPlanningModeList() {
		return planningModeList;
	}

	public void setPlanningModeList(List<PlanningMode> planningModeList) {
		this.planningModeList = planningModeList;
	}

	public String getVideoStr() {
		return videoStr;
	}

	public void setVideoStr(String videoStr) {
		this.videoStr = videoStr;
	}

	public ISubscribeUser getSubscribeUserService() {
		return subscribeUserService;
	}

	public void setSubscribeUserService(ISubscribeUser subscribeUserService) {
		this.subscribeUserService = subscribeUserService;
	}

	public String getRegularlySentTime() {
		return regularlySentTime;
	}

	public void setRegularlySentTime(String regularlySentTime) {
		this.regularlySentTime = regularlySentTime;
	}

	public List<SelectModel> getTaskStatusList() {
		return taskStatusList;
	}

	public void setTaskStatusList(List<SelectModel> taskStatusList) {
		this.taskStatusList = taskStatusList;
	}

	public List<SelectModel> getSendObjectList() {
		return sendObjectList;
	}

	public void setSendObjectList(List<SelectModel> sendObjectList) {
		this.sendObjectList = sendObjectList;
	}

	public QueryVideoLogCondition getQueryVideoLogCondition() {
		if (queryVideoLogCondition != null) {
			queryVideoLogCondition = new QueryVideoLogCondition();
		}
		return queryVideoLogCondition;
	}

	public void setQueryVideoLogCondition(QueryVideoLogCondition queryVideoLogCondition) {
		this.queryVideoLogCondition = queryVideoLogCondition;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDownFileURL() {
		return downFileURL;
	}

	public void setDownFileURL(String downFileURL) {
		this.downFileURL = downFileURL;
	}

	public IReview getReviewService() {
		return reviewService;
	}

	public void setReviewService(IReview reviewService) {
		this.reviewService = reviewService;
	}

	public QueryReviewCondition getQueryReviewCondition() {
		if(queryReviewCondition == null){
			queryReviewCondition = new QueryReviewCondition();
		}
		return queryReviewCondition;
	}

	public void setQueryReviewCondition(QueryReviewCondition queryReviewCondition) {
		this.queryReviewCondition = queryReviewCondition;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public Integer getTargetReviewId() {
		return targetReviewId;
	}

	public void setTargetReviewId(Integer targetReviewId) {
		this.targetReviewId = targetReviewId;
	}

	/**
	 * @return the adStatService
	 */
	public IAdStat getAdStatService() {
		return adStatService;
	}

	/**
	 * @param adStatService the adStatService to set
	 */
	public void setAdStatService(IAdStat adStatService) {
		this.adStatService = adStatService;
	}

	/**
	 * @return the userUseService
	 */
	public IUserUse getUserUseService() {
		return userUseService;
	}

	/**
	 * @param userUseService the userUseService to set
	 */
	public void setUserUseService(IUserUse userUseService) {
		this.userUseService = userUseService;
	}

	/**
	 * @return the browseLogService
	 */
	public IBrowseLog getBrowseLogService() {
		return browseLogService;
	}

	/**
	 * @param browseLogService the browseLogService to set
	 */
	public void setBrowseLogService(IBrowseLog browseLogService) {
		this.browseLogService = browseLogService;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the queryAdStatCondition
	 */
	public QueryAdStatCondition getQueryAdStatCondition() {
		
		if(queryAdStatCondition == null){
			queryAdStatCondition = new QueryAdStatCondition();
		}
		
		return queryAdStatCondition;
	}

	/**
	 * @param queryAdStatCondition the queryAdStatCondition to set
	 */
	public void setQueryAdStatCondition(QueryAdStatCondition queryAdStatCondition) {
		this.queryAdStatCondition = queryAdStatCondition;
	}

	/**
	 * @return the queryUserUseCondition
	 */
	public QueryUserUseCondition getQueryUserUseCondition() {
		if(queryUserUseCondition == null){
			queryUserUseCondition = new QueryUserUseCondition();
		}
		return queryUserUseCondition;
	}

	/**
	 * @param queryUserUseCondition the queryUserUseCondition to set
	 */
	public void setQueryUserUseCondition(QueryUserUseCondition queryUserUseCondition) {
		this.queryUserUseCondition = queryUserUseCondition;
	}

	/**
	 * @return the queryBrowseLogCondition
	 */
	public QueryBrowseLogCondition getQueryBrowseLogCondition() {
		if(queryBrowseLogCondition == null){
			queryBrowseLogCondition = new QueryBrowseLogCondition();
		}
		return queryBrowseLogCondition;
	}

	/**
	 * @param queryBrowseLogCondition the queryBrowseLogCondition to set
	 */
	public void setQueryBrowseLogCondition(
			QueryBrowseLogCondition queryBrowseLogCondition) {
		this.queryBrowseLogCondition = queryBrowseLogCondition;
	}

	/**
	 * @return the where
	 */
	public Integer getWhere() {
		return where;
	}

	/**
	 * @param where the where to set
	 */
	public void setWhere(Integer where) {
		this.where = where;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * @return the queryStatCommonCondition
	 */
	public QueryStatCommonCondition getQueryStatCommonCondition() {
		
		if(queryStatCommonCondition == null){
			queryStatCommonCondition = new QueryStatCommonCondition();
		}
		return queryStatCommonCondition;
	}

	/**
	 * @param queryStatCommonCondition the queryStatCommonCondition to set
	 */
	public void setQueryStatCommonCondition(
			QueryStatCommonCondition queryStatCommonCondition) {
		this.queryStatCommonCondition = queryStatCommonCondition;
	}

	/**
	 * @return the regClickCount
	 */
	public Integer getRegClickCount() {
		return regClickCount;
	}

	/**
	 * @param regClickCount the regClickCount to set
	 */
	public void setRegClickCount(Integer regClickCount) {
		this.regClickCount = regClickCount;
	}

	/**
	 * @return the regCount
	 */
	public Integer getRegCount() {
		return regCount;
	}

	/**
	 * @param regCount the regCount to set
	 */
	public void setRegCount(Integer regCount) {
		this.regCount = regCount;
	}

	/**
	 * @return the loginClickCount
	 */
	public Integer getLoginClickCount() {
		return loginClickCount;
	}

	/**
	 * @param loginClickCount the loginClickCount to set
	 */
	public void setLoginClickCount(Integer loginClickCount) {
		this.loginClickCount = loginClickCount;
	}

	/**
	 * @return the loginCount
	 */
	public Integer getLoginCount() {
		return loginCount;
	}

	/**
	 * @param loginCount the loginCount to set
	 */
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	/**
	 * @return the fromService
	 */
	public IFrom getFromService() {
		return fromService;
	}

	/**
	 * @param fromService the fromService to set
	 */
	public void setFromService(IFrom fromService) {
		this.fromService = fromService;
	}

	/**
	 * @return the adService
	 */
	public IAd getAdService() {
		return adService;
	}

	/**
	 * @param adService the adService to set
	 */
	public void setAdService(IAd adService) {
		this.adService = adService;
	}

	/**
	 * @return the queryAdCondition
	 */
	public QueryAdCondition getQueryAdCondition() {
		if(queryAdCondition == null){
			queryAdCondition = new QueryAdCondition();
		}
		return queryAdCondition;
	}

	/**
	 * @param queryAdCondition the queryAdCondition to set
	 */
	public void setQueryAdCondition(QueryAdCondition queryAdCondition) {
		this.queryAdCondition = queryAdCondition;
	}

	/**
	 * @return the commonList
	 */
	public List<String> getCommonList() {
		return commonList;
	}

	/**
	 * @param commonList the commonList to set
	 */
	public void setCommonList(List<String> commonList) {
		this.commonList = commonList;
	}

	/**
	 * @return the feedCourseService
	 */
	public ICourse getFeedCourseService() {
		return feedCourseService;
	}

	/**
	 * @param feedCourseService the feedCourseService to set
	 */
	public void setFeedCourseService(ICourse feedCourseService) {
		this.feedCourseService = feedCourseService;
	}

	/**
	 * @return the microVideoService
	 */
	public IMicroVideo getMicroVideoService() {
		return microVideoService;
	}

	/**
	 * @param microVideoService the microVideoService to set
	 */
	public void setMicroVideoService(IMicroVideo microVideoService) {
		this.microVideoService = microVideoService;
	}

	/**
	 * @return the courseId
	 */
	public Integer getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the courseList
	 */
	public List<Course> getCourseList() {
		return courseList;
	}

	/**
	 * @param courseList the courseList to set
	 */
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
