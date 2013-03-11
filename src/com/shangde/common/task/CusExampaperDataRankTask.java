package com.shangde.common.task;

import org.apache.log4j.Logger;

import com.shangde.edu.cus.web.AddressWebAction;
import com.shangde.edu.exam.service.IExampaper;


/**
 * 主体功能:试题排名统计
 *
 * @author		HQL
 * @date		2012-6-27
 * @version 	修改人:
 * 				修改日期:
 */
public class CusExampaperDataRankTask {
	
	private static final Logger logger = Logger.getLogger(AddressWebAction.class);
	
	/**
	 * 试卷服务对象
	 */
	private IExampaper exampaperService;
	
	/**
	 * 统计数据插入数据统计表
	 */
	public void addCusRankToTable(){
		try {
			logger.info("User exampaperCount rank start ....");
			exampaperService.addCusExampaperToTable();
			logger.info("User exampaperCount rank end ....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IExampaper getExampaperService() {
		return exampaperService;
	}

	public void setExampaperService(IExampaper exampaperService) {
		this.exampaperService = exampaperService;
	}

}
