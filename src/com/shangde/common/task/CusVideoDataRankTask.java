package com.shangde.common.task;

import org.apache.log4j.Logger;

import com.shangde.edu.cus.util.CusUtil;
import com.shangde.edu.cus.web.AddressWebAction;


/**
 * 主体功能:视频排名
 *
 * @author		HQL
 * @date		2012-6-27
 * @version 	修改人:
 * 				修改日期:
 */
public class CusVideoDataRankTask {
	
	private static final Logger logger = Logger.getLogger(AddressWebAction.class);
	
	/**
	 * 统计数据插入数据统计表
	 */
	public void addCusRankToTable(){
		try {
			logger.info("User videoCount rank start ....");
			CusUtil.addCusVideoToTable();
			logger.info("User videoCount rank end ....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
