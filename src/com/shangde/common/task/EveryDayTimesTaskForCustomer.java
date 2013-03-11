package com.shangde.common.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.edu.tk.condition.QueryAutoTaskCondition;
import com.shangde.edu.tk.dto.AutoTaskDTO;
import com.shangde.edu.tk.service.IAutoTask;

/**
 * <br>
 * <b>功能：</b>定时任务：把用户当天支付成功的第一笔订单(finance_contract_tbl)的第一笔流水(finance_cash_record_tbl)的cr_subject_id更新到用户(cus_customer_tbl)的subject_id字段<br>
 * <b>作者：</b>李志强 Kobe.Lee<br>
 * <b>日期：</b> 2012.06.05 <br>
 * 
 * @return
 */
public class EveryDayTimesTaskForCustomer {
	
	private static final Logger logger = Logger.getLogger(EveryDayTimesTaskForCustomer.class);

	private IAutoTask autoTaskService;
	private QueryAutoTaskCondition queryAutoTaskCondition;

	public QueryAutoTaskCondition getQueryAutoTaskCondition() {
		if (this.queryAutoTaskCondition == null) {
			this.queryAutoTaskCondition = new QueryAutoTaskCondition();
		}
		return queryAutoTaskCondition;
	}

	public void setQueryAutoTaskCondition(QueryAutoTaskCondition queryAutoTaskCondition) {
		this.queryAutoTaskCondition = queryAutoTaskCondition;
	}

	public IAutoTask getAutoTaskService() {
		return autoTaskService;
	}

	public void setAutoTaskService(IAutoTask autoTaskService) {
		this.autoTaskService = autoTaskService;
	}

	// 定时任务
	public void updateCustomerEveryDay() {
		try{
		logger.info("------execute EveryDayTimesTaskForCustomer--------");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(date);
		// 1.获取需要更新的数据
		List<AutoTaskDTO> dataList = this.getDataForUpdate(nowDate);
		if (null != dataList && dataList.size() > 0) {
			// 2.对数据进行更新
			updateCustomer(dataList, nowDate);
		}
		logger.info("------execute EveryDayTimesTaskForCustomer over--------updateDateCount:"+dataList.size());
		}catch (Exception e){
			logger.error("---------ERROR:execute EveryDayTimesTaskForCustomer Fail-------------------------");
		}
	}

	/**
	 * 1.获取需要更新的数据
	 * 
	 * @param nowDate 当前系统时间的前一天(yyyy-MM-dd)
	 */
	private List<AutoTaskDTO> getDataForUpdate(String nowDate) throws Exception{
		this.getQueryAutoTaskCondition().setNowDate(nowDate);
		// 想本地测试的时候放开注释 
//		BeanFactory bf = new ClassPathXmlApplicationContext("config/spring/applicationContext.xml");
//		IAutoTask autoTaskService = (IAutoTask) bf.getBean("autoTaskService");
		return autoTaskService.getDataList(queryAutoTaskCondition);
	}

	/**
	 * 更新数据
	 * 
	 * @param dataList
	 * @param nowDate
	 */
	private void updateCustomer(List<AutoTaskDTO> dataList, String nowDate) throws Exception{
		//本地测试的时候可以放开。
//		BeanFactory bf = new ClassPathXmlApplicationContext("config/spring/applicationContext.xml");
//		IAutoTask autoTaskService = (IAutoTask) bf.getBean("autoTaskService");
		// 更新数据前先清空临时表finance_subject
		autoTaskService.deleteFinanceSubject();
		// 执行sql在临时表中插入数据
		autoTaskService.insertFinanceSubject(nowDate);
		// 进行更新
		autoTaskService.updateCostomer();
	}

	public static void main(String args[]) {
		EveryDayTimesTaskForCustomer e = new EveryDayTimesTaskForCustomer();
		e.updateCustomerEveryDay();
	}

}
