package com.shangde.common.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.crm.condition.QueryChanceCondition;
import com.shangde.edu.crm.service.IChance;
import com.shangde.edu.sms.service.IsmsService;

public class ExpiredChanceCheckedTask extends CommonAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QueryChanceCondition queryChanceCondition;
	private IChance chanceService;
	private IsmsService smsService;
	
	public void expiredChanceChecked(){
		//设置时间
		try { 
			//设置时间格式
			queryChanceCondition= new QueryChanceCondition();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("定时机会入库已启动");
			chanceService.updateCheckIsBuy();
			cal.add(Calendar.DAY_OF_MONTH, -7);
			this.getQueryChanceCondition().setEndTime(sdf.format(cal.getTime()));
			chanceService.updateCheckChance(queryChanceCondition);
			cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -3);
			this.getQueryChanceCondition().setDrawStatus(1);
			this.getQueryChanceCondition().setEndTime(sdf.format(cal.getTime()));
			chanceService.updateCheckChance(queryChanceCondition);
			smsService.sendEx("何海强您好,机会已经入库","18001263125","", "", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public QueryChanceCondition getQueryChanceCondition() {
		if(queryChanceCondition==null){
			queryChanceCondition=new QueryChanceCondition();
		}
		return queryChanceCondition;
	}

	public void setQueryChanceCondition(QueryChanceCondition queryChanceCondition) {
		this.queryChanceCondition = queryChanceCondition;
	}

	public IChance getChanceService() {
		return chanceService;
	}

	public void setChanceService(IChance chanceService) {
		this.chanceService = chanceService;
	}

	public IsmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(IsmsService smsService) {
		this.smsService = smsService;
	}
}
