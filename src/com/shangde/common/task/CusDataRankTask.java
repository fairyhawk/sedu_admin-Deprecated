package com.shangde.common.task;

import org.apache.log4j.Logger;

import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.web.AddressWebAction;


/**
 * 主体功能:用户登录排名过程,视频排名,做题排名统计
 *
 * @author		HQL
 * @date		2012-6-27
 * @version 	修改人:
 * 				修改日期:
 */
public class CusDataRankTask {
	
	private static final Logger logger = Logger.getLogger(AddressWebAction.class);

	/**
	 * 用户服务对象
	 */
	private ICustomer customerService;
	
	/**
	 * 统计数据插入数据统计表
	 */
	public void addCusRankToTable(){
		try {
			logger.info("User loginCount rank start ....");
			customerService.addCusRankToTable();
			logger.info("User loginCount rank end ....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}	
}
