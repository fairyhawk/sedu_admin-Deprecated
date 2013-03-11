package com.shangde.common.task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICpCus;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.LoginRecord;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.service.ILoginRecord;
import com.shangde.edu.cus.service.IStudyPlan;
import com.shangde.edu.cus.service.ITotolsScore;
import com.shangde.edu.cus.service.ITsRecord;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.exam.service.IExampaperRecord;
import com.shangde.edu.exam.service.IOptRecord;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.res.service.INotes;
import com.shangde.edu.tk.service.ITaskCus;

/**
 * 每周日执行定时任务
 * @author yuJinDong
 *
 */
public class EveryWeakTimesTask {
	
	private ICustomer customerService;
	private ICashRecord  cashRecordService;
	private ICourse courseService;
	private IContract contractService;
	private INotes notesService; 
	private ICpCus cpCusService;
	private IStudyPlan studyPlanService;
	private IExampaperRecord exampaperRecordService;
	private IOptRecord optRecordService;
	private ITsRecord tsRecordService;
	private ITotolsScore totolsScoreService;
	private ILoginRecord loginRecordService;
	private ITaskCus taskCusService;
	
	/**
	 * 判断临时用户激活时间是否超过N天
	 */
	public void initTempCus() {
		QueryCustomerCondition queryCustomerCondition = new QueryCustomerCondition();		
		queryCustomerCondition.setCusType(Customer.CUS_CUS_TYPE_TEMP + "");
		List<Customer> cusList = customerService.getCustomerList(queryCustomerCondition);
		//处理五天的用户
		equalTimes(cusList,-5);
	}
	/**
	 * 比较相差的天数，处理用户
	 * @param cusList 用户集合
	 * @param days 	  相差的天数
	 */
	private void equalTimes(List<Customer> cusList,int days) {
		Date curDate = new Date();
		Calendar curCalendar = Calendar.getInstance();
		curCalendar.setTime(curDate);
		curCalendar.add(Calendar.DAY_OF_MONTH, days);	
		for(int i=0; i<cusList.size(); i++) {
			int cusId=cusList.get(i).getCusId();
			LoginRecord loginRecord=loginRecordService.getFirstLoginRecordByCusId(cusId);			 
			if(loginRecord != null && loginRecord.getLoginTime().getTime() <= curCalendar.getTimeInMillis()) {				
				//积分清零	
//				TotolsScore totolsScore=this.totolsScoreService.getTotolsScore(cusId);
//				totolsScore.setTsAction(0);
//				totolsScore.setTsCurrent(0);		
//				this.totolsScoreService.updateTotolsScore(totolsScore);
				clearCusInfo(cusId);				
			}
		}
	}
	/**
	 * 每周查询一次用户，删除三年未上线的用户	 
	 * 
	 * 课程到期删除
	 * @param 
	 */
	protected void checkCustomerWeeks(){		
//		try{
//			Date curDate=new Date();
//			Calendar curCalendar=Calendar.getInstance();
//			curCalendar.setTime(curDate);
//			//每周日执行
//			if(curCalendar.get(Calendar.DAY_OF_WEEK)==1){
//				QueryCustomerCondition queryCustomerCondition=new QueryCustomerCondition();				
//				List<Customer> cusList = customerService.getCustomerList(queryCustomerCondition);
//				
//				Calendar calendar= Calendar.getInstance();
//				calendar.setTime(new Date());
//				calendar.add(Calendar.YEAR, -3);
//				for(int i=0;i<cusList.size();i++)
//				{
//					if(cusList.get(i).getCusType()==0)
//					{
//						//注册用户处理
//						if(cusList.get(i).getLastloginTime().getTime()<=calendar.getTimeInMillis())
//						{
//							/*System.out.println("删除用户"+cusList.get(i).getCusId());*/		
//							int cusId=cusList.get(i).getCusId();			
//							
//							//删除学员登录信息
//							this.loginRecordService.delLoginRecordByCusId(cusId);		
//							//删除积分
//							this.totolsScoreService.delTotolsScoreByCusId(cusId);
							//删除消息关联
//							
//							clearCusInfo(cusId);
//							删除学员
//							this.customerService.delCustomerById(cusId);
//							
//						}
//					}else if(cusList.get(i).getCusType()==1)
//					{
//						//内部用户处理
//						
//					}
//				}
//			}
//			根据登录IP处理
			//checkCustomerIP();
//			-------------------------------------------------------------------------------------------------	
				//删除过期课程
					
//				
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
	}
	private void clearCusInfo(int cusId) {
		
		
		//删除笔记
		this.notesService.delNotesByCusId(cusId);
		//删除流水
		this.cashRecordService.delCashRecordByCusId(cusId);
		//删除订单
		this.contractService.delContractByCusId(cusId);
		//删除优惠券
		this.cpCusService.delCpCusByCusId(cusId);
		//删除学习计划
		this.studyPlanService.delStudyPlanByCusId(cusId);
		//删除考试
		this.exampaperRecordService.delERecordByCusId(cusId);
		//删除考试关联
		this.optRecordService.delOptRecordByCusId(cusId);
		//删除积分记录
		//this.tsRecordService.delTsRecordByCusId(cusId);	
		//删除task_cus表
		//this.taskCusService.deleteTaskCusByCusId(cusId);
		
		Customer tempCustomer=this.customerService.getCustomerById(cusId);
		if(tempCustomer.getCusType()==2){
			tempCustomer.setCusType(0);
			this.customerService.updateCustomer(tempCustomer);
		}
	}
	
	/**
	 * 每周查看用户登录IP，是否超过**个不同IP
	 * @param args
	 */
	public void checkCustomerIP() {
		
	}
	
	public ICustomer getCustomerService() {
		return customerService;
	}
	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public ICourse getCourseService() {
		return courseService;
	}
	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}
	public IContract getContractService() {
		return contractService;
	}
	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}
	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}
	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}
	public INotes getNotesService() {
		return notesService;
	}
	public void setNotesService(INotes notesService) {
		this.notesService = notesService;
	}
	public ICpCus getCpCusService() {
		return cpCusService;
	}
	public void setCpCusService(ICpCus cpCusService) {
		this.cpCusService = cpCusService;
	}
	public IStudyPlan getStudyPlanService() {
		return studyPlanService;
	}
	public void setStudyPlanService(IStudyPlan studyPlanService) {
		this.studyPlanService = studyPlanService;
	}
	public IExampaperRecord getExampaperRecordService() {
		return exampaperRecordService;
	}
	public void setExampaperRecordService(IExampaperRecord exampaperRecordService) {
		this.exampaperRecordService = exampaperRecordService;
	}
	public IOptRecord getOptRecordService() {
		return optRecordService;
	}
	public void setOptRecordService(IOptRecord optRecordService) {
		this.optRecordService = optRecordService;
	}
	public ITsRecord getTsRecordService() {
		return tsRecordService;
	}
	public void setTsRecordService(ITsRecord tsRecordService) {
		this.tsRecordService = tsRecordService;
	}
	public ITotolsScore getTotolsScoreService() {
		return totolsScoreService;
	}
	public void setTotolsScoreService(ITotolsScore totolsScoreService) {
		this.totolsScoreService = totolsScoreService;
	}
	public ILoginRecord getLoginRecordService() {
		return loginRecordService;
	}
	public void setLoginRecordService(ILoginRecord loginRecordService) {
		this.loginRecordService = loginRecordService;
	}
	public ITaskCus getTaskCusService() {
		return taskCusService;
	}
	public void setTaskCusService(ITaskCus taskCusService) {
		this.taskCusService = taskCusService;
	}
}
