package com.shangde.edu.tk.web;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.cus.domain.TotolsScore;
import com.shangde.edu.cus.domain.TsRecord;
import com.shangde.edu.cus.service.ITotolsScore;
import com.shangde.edu.cus.service.ITsRecord;
import com.shangde.edu.tk.condition.QueryTaskCondition;
import com.shangde.edu.tk.condition.QueryTaskCusCondition;
import com.shangde.edu.tk.domain.Task;
import com.shangde.edu.tk.domain.TaskCus;
import com.shangde.edu.tk.service.ITask;
import com.shangde.edu.tk.service.ITaskCus;

public class TaskWebAction extends CommonAction {
	/**
	 * 任务
	 */
	private Task task;
	
	private TaskCus taskCus;
	/**
	 * 任务服务
	 */
	private ITask taskService;
	
	private ITaskCus taskCusService;
	
	//总积分服务
	private ITotolsScore totolsScoreService;
	
	//积分记录服务
	private ITsRecord tsRecordService;
	
	/**
	 * 任务集合
	 */
	private List<Task> taskList;
	
	/**
	 * 用户任务集合
	 */
	private List<TaskCus> taskCusList;
	
	/**
	 * 任务查询条件
	 */
	private QueryTaskCondition queryTaskCondition;
	
	/**
	 * @author chenshuai
	 * 功能：跳转用户任务页面
	 * @param args
	 * @return
	 */
	public String getWebTaskList(){
		try{
			QueryTaskCusCondition  queryTaskCusCondition = new QueryTaskCusCondition();
			queryTaskCusCondition.setCusId(this.getLoginUserId());
			taskCusList = taskCusService.getWebTaskCusList(queryTaskCusCondition);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "getWebTaskList";
	}
	
	/**
	 * @author chenshuai
	 * 功能：获取任务奖励
	 * @param args
	 * @return
	 */
	public String getTaskEncourage(){
		try{
			if(taskCus != null && taskCus.getId() > 0){
				taskCus = taskCusService.getTaskCusById(taskCus.getId());
				Task temp = taskService.getTaskById(taskCus.getTaskId());
				if(taskCus.getIsComplete() > 0 && taskCus.getIsReceived() == 0 && taskCus.getIsExpired() == 0){
					
					TotolsScore total = totolsScoreService.getTotolsScore(this.getLoginUserId());
					if(total != null){
						Date date=new Date();
						
						//积分表进行记录
						int jifen=total.getTsCurrent();
						int ep =total.getTsAction();
						
						jifen=jifen+temp.getJifen();
						ep=ep+temp.getExperience();
						
						total.setTsCurrent(jifen);
						total.setTsAction(ep);
						totolsScoreService.updateTotolsScore(total);
						
						//积分记录表进行记录
						TsRecord tsRecord=new TsRecord();
						tsRecord.setCusId(this.getLoginUserId());
						tsRecord.setTrType(TsRecord.TRTYPE_FOR);
						tsRecord.setAccessType(TsRecord.ACCESSTYPE_FOR_TASK);
						tsRecord.setAccessTime(date);

						tsRecord.setTsId(total.getTsId());
						tsRecord.setTrNum(temp.getJifen());
						this.tsRecordService.addTsRecord(tsRecord);
						//经验的添加
						TsRecord tsR=new TsRecord();
						tsR.setCusId(this.getLoginUserId());
						tsR.setTrType(TsRecord.TRTYPE_ACTION);
						tsR.setAccessType(TsRecord.ACCESSTYPE_FOR_TASK_ACTION);
						tsR.setAccessTime(date);

						tsR.setTsId(total.getTsId());
						tsR.setTrNum(temp.getExperience());
						this.tsRecordService.addTsRecord(tsR);
					}
					
					taskCus.setIsReceived(1);
					taskCusService.updateTaskCus(taskCus);//更新状态
					
					JSONArray obj = JSONArray.fromObject(temp) ;
					this.setResult(new Result<Object>(true, obj.toString(), null, null));
				}else{
					this.setResult(new Result<Object>(true, "false", null, null));
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		
		return "json";
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

	public QueryTaskCondition getQueryTaskCondition() {
		if(queryTaskCondition == null){
			queryTaskCondition = new QueryTaskCondition();
		}
		return queryTaskCondition;
	}

	public void setQueryTaskCondition(QueryTaskCondition queryTaskCondition) {
		this.queryTaskCondition = queryTaskCondition;
	}


	public ITaskCus getTaskCusService() {
		return taskCusService;
	}


	public void setTaskCusService(ITaskCus taskCusService) {
		this.taskCusService = taskCusService;
	}


	public List<TaskCus> getTaskCusList() {
		return taskCusList;
	}


	public void setTaskCusList(List<TaskCus> taskCusList) {
		this.taskCusList = taskCusList;
	}

	public TaskCus getTaskCus() {
		return taskCus;
	}

	public void setTaskCus(TaskCus taskCus) {
		this.taskCus = taskCus;
	}

	public ITotolsScore getTotolsScoreService() {
		return totolsScoreService;
	}

	public void setTotolsScoreService(ITotolsScore totolsScoreService) {
		this.totolsScoreService = totolsScoreService;
	}

	public ITsRecord getTsRecordService() {
		return tsRecordService;
	}

	public void setTsRecordService(ITsRecord tsRecordService) {
		this.tsRecordService = tsRecordService;
	}
}
