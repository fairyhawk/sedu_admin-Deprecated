package com.shangde.edu.tk.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.service.ConfigService;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.sys.dto.KeyValueDTO;
import com.shangde.edu.tk.condition.QueryTaskCondition;
import com.shangde.edu.tk.condition.QueryTaskCusCondition;
import com.shangde.edu.tk.domain.Task;
import com.shangde.edu.tk.domain.TaskCus;
import com.shangde.edu.tk.service.ITask;
import com.shangde.edu.tk.service.ITaskCus;

public class TaskAction extends CommonAction {
	
	public static final Logger logger = Logger.getLogger(TaskAction.class);
	/**
	 * 任务
	 */
	private Task task;
	/**
	 * 任务服务
	 */
	private ITask taskService;
	
	/**
	 * 学员任务服务
	 */
	private ITaskCus taskCusService;
	
	/**
	 * 用户服务
	 */
	private ICustomer customerService;
	
	/**
	 * 任务集合
	 */
	private List<Task> taskList;
	
	private List<KeyValueDTO> taskKeyValueList;
	/**
	 *配置
	 */
	private ConfigService configurator;
	
	/**
	 * 任务查询条件
	 */
	private QueryTaskCondition queryTaskCondition;
	
	/**
	 * 任务图标
	 */
	private File iconPic;
	
	/**
	 * 任务图标文件名
	 */
	private String iconPicFileName;
	
	/**
	 * 应用分类
	 */
	private Map applicationSortMap;
	
	/**
	 * 用户级别
	 */
	private Map userLevelMap;
	
	/**
	 * 推荐，置顶
	 */
	private int[] recommondTypes;
	
	/**
	 * 特殊条件
	 */
	private int[] specialCondition;
	
	/**
	 * 获取积分
	 */
	private int[] isJiFen;
	
	private String searchKey;
	
	/**
	 * @author chenshuai
	 * 功能：跳转到任务添加页面
	 * @param args
	 * @return
	 */
	public String toAddTask(){
		try{
			applicationSortMap = configurator.getApplicationSortMap();
			userLevelMap = configurator.getUserLevelMap();
			taskKeyValueList = taskService.getTaskKeyValueList(queryTaskCondition);
		}catch(Exception ex){
			logger.error("TaskAction.toAddTask",ex);
			return ERROR;
		}
		return "toAddTask";
	}
	
	/**
	 * @author chenshuai
	 * 功能：添加任务
	 * @param args
	 * @return
	 */
	public String addTask(){
		try{
			if(recommondTypes != null){//推荐置顶
				for(int i = 0; i < recommondTypes.length; i ++){
					if(recommondTypes[i] == 1){
						task.setIsRecommend(Task.TASK_IS_RECOMMOND);
					}
					if(recommondTypes[i] == 2){
						task.setIsTop(Task.TASK_IS_TOP);
					}
				}
			}
			
			if(specialCondition != null){//特殊条件
				for(int i = 0; i < specialCondition.length; i ++){
					if(specialCondition[i] == 1){
						task.setIsHasPreTask(Task.TASK_HAS_PRE_TASK);
					}
					if(specialCondition[i] == 2){
						task.setIsLimitTimeTask(Task.TASK_IS_LIMITTIME_TASK);
					}
				}
			}
			
			if(isJiFen != null){//积分
				if(isJiFen[0] == 1){
					task.setIsJifen(Task.TASK_IS_JIFEN);
				}
			}
			
			String fileName = null;
			String filePath = null;
			
			if(iconPic != null && !iconPic.equals("")){//
				fileName = sdf.format(new Date()) + "-" + UUID.randomUUID() + this.getFileType(this.iconPicFileName);
				filePath = "/upload/task/" + fileName;
				task.setIconUrl(fileName);
				List<File> fileList = new ArrayList<File>();
				List<String> fileNameList = new ArrayList<String>();
				
				fileList.add(iconPic);
				fileNameList.add(fileName);
				this.upload(fileNameList, fileList);
			}
			taskService.addTask(task);
		}catch(Exception ex){
			logger.error("TaskAction.addTask",ex);
			return ERROR;
		}
		
		return "addTask";
	}
	
	/**
	 * @author chenshuai
	 * 功能：更新任务
	 * @param args
	 * @return
	 */
	public String updateTask(){
		
		try{
			if(task.getTaskId() != 0){
				Task temp = taskService.getTaskById(task.getTaskId());
				task.setPublishStatus(temp.getPublishStatus());
				if(recommondTypes != null){//推荐置顶
					for(int i = 0; i < recommondTypes.length; i ++){
						if(recommondTypes[i] == 1){
							task.setIsRecommend(Task.TASK_IS_RECOMMOND);
						}else{
							task.setIsRecommend(0);
						}
						
						if(recommondTypes[i] == 2){
							task.setIsTop(Task.TASK_IS_TOP);
						}else{
							task.setIsTop(0);
						}
					}
				}
				
				if(specialCondition != null){//特殊条件
					for(int i = 0; i < specialCondition.length; i ++){
						if(specialCondition[i] == 1){
							task.setIsHasPreTask(Task.TASK_HAS_PRE_TASK);
						}else{
							task.setIsHasPreTask(0);
						}
						
						if(specialCondition[i] == 2){
							task.setIsLimitTimeTask(Task.TASK_IS_LIMITTIME_TASK);
						}else{
							task.setIsLimitTimeTask(0);
						}
					}
				}
				
				if(isJiFen != null){//积分
					if(isJiFen[0] == 1){
						task.setIsJifen(Task.TASK_IS_JIFEN);
					}else{
						task.setIsJifen(0);
					}
				}
				
				String fileName = null;
				String filePath = null;
				
				if(iconPic != null && !iconPic.equals("")){//
					fileName = sdf.format(new Date()) + "-" + UUID.randomUUID() + this.getFileType(this.iconPicFileName);
					filePath = "/upload/task/" + fileName;
					task.setIconUrl(fileName);
					List<File> fileList = new ArrayList<File>();
					List<String> fileNameList = new ArrayList<String>();
					fileList.add(iconPic);
					fileNameList.add(fileName);
					this.upload(fileNameList, fileList);
				}
				task.setCreateTime(temp.getCreateTime());
				taskService.updateTask(task);
			}
		}catch(Exception ex){
			logger.error("TaskAction.updateTask",ex);
			return ERROR;
		}
		
		
		return "updateTask";
	}
	
	/**
	 * @author cxs
	 * 功能：跳转到更新任务页面
	 * @param args
	 * @return
	 */
	public String toUpdateTask(){
		try{
			task = taskService.getTaskById(task.getTaskId());
			applicationSortMap = configurator.getApplicationSortMap();
			userLevelMap = configurator.getUserLevelMap();
			taskList = taskService.getTaskList(queryTaskCondition);
			taskKeyValueList = taskService.getTaskKeyValueList(queryTaskCondition);
		}catch(Exception ex){
			logger.error("TaskAction.toUpdateTask",ex);
			return ERROR;
		}
		
		return "toUpdateTask";
	}
	
	/**
	 * @author chenshuai
	 * 功能：进入到任务列表
	 * @param args
	 * @return
	 */
	public String toListTasks(){
		try{
			if(searchKey != null && !searchKey.trim().equals("")){
				this.getQueryTaskCondition().setSearchKey(searchKey);
			}
			getQueryTaskCondition().setPageSize(20);
			this.setPage(taskService.getTaskListByCondition(queryTaskCondition));
			this.getPage().setPageSize(20);
			setPageUrlParms();
		}catch(Exception ex){
			logger.error("TaskAction.toListTasks",ex);
			return ERROR;
		}
		return "toListTasks";
	}
	
	public String deleteTask(){
		try{
			if(task.getTaskId() != 0){
				taskService.delTaskById(task.getTaskId());
			}
		}catch(Exception ex){
			logger.error("TaskAction.deleteTask",ex);
			return ERROR;
		}
		return "deleteTask";
	}
	
	/**
	 * 
	 * @author chenshuai
	 * 功能：发布任务
	 * @param args
	 * @return
	 */
	public String publishTask(){
		try{
			if(task.getTaskId() > 0){
				int taskId = task.getTaskId();
				task = taskService.getTaskById(taskId);
				QueryCustomerCondition queryCustomerCondition = new QueryCustomerCondition();
				List<Customer> customerList = customerService.getCustomerList(queryCustomerCondition);
				
				taskCusService.delTaskCusByTaskId(task.getTaskId());
				int count = 0;
				TaskCus tc = null;
				QueryTaskCusCondition queryTaskCusCondition = new QueryTaskCusCondition();
				
				for(int i = 0 ; i < customerList.size(); i ++){//对用户进行循环
					tc = new TaskCus();
					tc.setCusId(customerList.get(i).getCusId());
					tc.setTaskId(taskId);
					
					queryTaskCusCondition.setCusId(tc.getCusId());
	    			queryTaskCusCondition.setTaskId(tc.getTaskId());
	    			
	    			count = taskCusService.getCountByCusIdAndTaskId(queryTaskCusCondition);
	    			if(count == 0){//保证不重复
	    				taskCusService.addTaskCus(tc);//添加学员任务
	    			}
				}
				
				if(task.getPublishStatus() == 0){//更新发布状态
					task.setPublishStatus(Task.TASK_IS_PUBLISH);
					taskService.updateTask(task);
				}
			}
			
		}catch(Exception ex){
			logger.error("TaskAction.publishTask",ex);
			return ERROR;
		}
		return "publishTask";
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public ITask getTaskService() {
		return taskService;
	}

	public void setTaskService(ITask taskService) {
		this.taskService = taskService;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}

	public QueryTaskCondition getQueryTaskCondition() {
		if(queryTaskCondition == null){
			queryTaskCondition = new QueryTaskCondition();
		}
		return queryTaskCondition;
	}

	public void setQueryTaskCondition(QueryTaskCondition queryTaskCondition) {
		this.queryTaskCondition = queryTaskCondition;
	}

	public File getIconPic() {
		return iconPic;
	}

	public void setIconPic(File iconPic) {
		this.iconPic = iconPic;
	}
	
	public Map getApplicationSortMap() {
		return applicationSortMap;
	}

	public Map getUserLevelMap() {
		return userLevelMap;
	}

	public int[] getRecommondTypes() {
		return recommondTypes;
	}

	public void setRecommondTypes(int[] recommondTypes) {
		this.recommondTypes = recommondTypes;
	}

	public int[] getSpecialCondition() {
		return specialCondition;
	}

	public void setSpecialCondition(int[] specialCondition) {
		this.specialCondition = specialCondition;
	}

	public int[] getIsJiFen() {
		return isJiFen;
	}

	public void setIsJiFen(int[] isJiFen) {
		this.isJiFen = isJiFen;
	}

	public String getIconPicFileName() {
		return iconPicFileName;
	}

	public void setIconPicFileName(String iconPicFileName) {
		this.iconPicFileName = iconPicFileName;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public ITaskCus getTaskCusService() {
		return taskCusService;
	}

	public void setTaskCusService(ITaskCus taskCusService) {
		this.taskCusService = taskCusService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public List<KeyValueDTO> getTaskKeyValueList() {
		return taskKeyValueList;
	}

	public void setTaskKeyValueList(List<KeyValueDTO> taskKeyValueList) {
		this.taskKeyValueList = taskKeyValueList;
	}
}
