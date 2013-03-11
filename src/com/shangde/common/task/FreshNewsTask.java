package com.shangde.common.task;

import org.apache.log4j.Logger;

import com.shangde.edu.freshnews.service.IActionRecord;

public class FreshNewsTask {
	private static final Logger logger = Logger.getLogger(FreshNewsTask.class);
	private IActionRecord actionRecordService;

	public IActionRecord getActionRecordService() {
		return actionRecordService;
	}

	public void setActionRecordService(IActionRecord actionRecordService) {
		this.actionRecordService = actionRecordService;
	}
	
	public void addFreshNewsByWatchRecord(){
		try{
			logger.info("******************新鲜事定时开始*****************");
			//增加观看视频记录到新鲜事
			actionRecordService.addActionRecordByWatchRecord();
			//增加学员注册到新鲜事
			actionRecordService.addActionRecordByRegister();
			//增加下单到新鲜事
			actionRecordService.addActionRecordByOrder();
			//增加考试到新鲜事
			actionRecordService.addActionRecordByExam();
			//增加用户反馈新鲜事 
			//actionRecordService.addActionRecordByComment();
			logger.info("******************新鲜事定时结束*****************");
		}catch(Exception e){
			logger.info("******************新鲜事定时异常*****************");
			logger.error("FreshNewsTask.addFreshNewsByWatchRecord", e);
		}
	}
	
}
