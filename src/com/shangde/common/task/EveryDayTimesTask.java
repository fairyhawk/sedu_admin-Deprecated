package com.shangde.common.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICpCus;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.condition.QueryLoginRecordCondition;
import com.shangde.edu.cus.condition.QueryProblemCondition;
import com.shangde.edu.cus.condition.QueryReProblemCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.LoginRecord;
import com.shangde.edu.cus.domain.Problem;
import com.shangde.edu.cus.domain.ReProblem;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.service.ILoginRecord;
import com.shangde.edu.cus.service.IProblem;
import com.shangde.edu.cus.service.IReProblem;
import com.shangde.edu.cus.service.IStudyPlan;
import com.shangde.edu.cus.service.ITotolsScore;
import com.shangde.edu.cus.service.ITsRecord;
import com.shangde.edu.cusmgr.condition.QueryCusCouKpointCondition;
import com.shangde.edu.cusmgr.condition.QueryCusMgrCondition;
import com.shangde.edu.cusmgr.domain.CusCouKpoint;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.exam.service.IExampaperRecord;
import com.shangde.edu.exam.service.IOptRecord;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.res.service.INotes;
import com.shangde.edu.tk.service.ITaskCus;


/**
 * 每天执行定时任务
 * @author yuJinDong
 *
 */
public class EveryDayTimesTask {
	
	private ITotolsScore totolsScoreService;
	private ILoginRecord loginRecordService;
	private ICourse courseService;
	private INotes notesService; 
	private ICpCus cpCusService;
	private IStudyPlan studyPlanService;
	private IExampaperRecord exampaperRecordService;
	private IOptRecord optRecordService;
	private ITsRecord tsRecordService;
	private ITaskCus taskCusService;
	
	private ICashRecord  cashRecordService;
	private IContract contractService;
	private ICusCouKpoint cusCouKpointService;
	private ICustomer customerService;
	private List<Customer> customerList=new ArrayList<Customer>();
	private QueryCustomerCondition queryCustomerCondition;
	private List<Contract> contractList=new ArrayList<Contract>();
	
	private IProblem problemService;
	private IReProblem reProblemService;
	private QueryContractCondition queryContractCondition;
	
	private QueryLoginRecordCondition queryLoginRecordCondition;
	/**
	 * 知识点服务
	 */
	private IKpoint kpointService;
	/**
	 * 每天凌晨3点检查一次后台开通的用户，如果超过十天则删除
	 * 
	 */
	 
	protected void checkHTCustomerEveryDay()
	{
		try{
			//十天处理cus_type=3的
			anyDaysCustomer(-10,"3");
			//三十天处理cus_type=4的
			anyDaysCustomer(-30,"4");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * N天删除的用户  保留的天数和用户的类型 3内部十天，4内部三十天
	 */
	private void anyDaysCustomer(int days,String cusType) {
		//先查所有用户，再查所有不该删除的用户，
//		如果有订单的用户的集合长度等于不该删的用户，才执行
		this.getQueryCustomerCondition().setCusType(cusType);
		customerList=customerService.getCustomerList(this.getQueryCustomerCondition());
		Calendar curCalendar=Calendar.getInstance();
		curCalendar.setTime(new Date());
		curCalendar.add(Calendar.DAY_OF_MONTH, days);
		//curCalendar.add(Calendar.MINUTE, -5); 
		Date beginDate=null;
		for(int i=0;i<customerList.size();i++)
		{	
			this.getQueryLoginRecordCondition().setCusId(customerList.get(i).getCusId());
			List<LoginRecord> loginRecordList=loginRecordService.getLoginRecordListASC(queryLoginRecordCondition);
			if(loginRecordList.size()>1)
			{
				beginDate=loginRecordList.get(1).getLoginTime();
			}
			if(beginDate.getTime()<curCalendar.getTime().getTime())
			{
				this.getQueryContractCondition().setUserId(customerList.get(i).getCusId());
				this.getQueryContractCondition().setStatus(1);
				contractList=contractService.getContractByList(queryContractCondition);
				if(contractList==null&&contractList.size()==0)
				{
					clearCustomer(customerList.get(i).getCusId());
					continue;
				}
					for(int a=2;a<=4;){
						//查出每个用户赠送的和修复的订单	
						this.getQueryContractCondition().setStatus(a);
						List<Contract> contractTemp=new ArrayList<Contract>();
						contractTemp= contractService.getContractByList(queryContractCondition);
						for(int j=0;contractTemp!=null&&j<contractTemp.size();j++){
							//根据订单查出流水
							QueryCashRecordCondition queryCashRecordCondition=new QueryCashRecordCondition();
							queryCashRecordCondition.setCtId(contractTemp.get(j).getId());
							queryCashRecordCondition.setUserId(customerList.get(i).getCusId());
							List<CashRecord> cashRecordList=cashRecordService.getCashRecordByList(queryCashRecordCondition);
							for(int k=0;cashRecordList!=null&&k<cashRecordList.size();k++){																
								//删除流水
								this.cashRecordService.delCashRecordById(cashRecordList.get(k).getId());
							}
							//删除订单
							this.contractService.delContractById(contractTemp.get(j).getId());
						}
						a=a+2;
					}
				
				if(contractList!=null&&contractList.size()!=0){
					//更改用户状态
					Customer customerTemp=customerService.getCustomerById(contractList.get(0).getCusId());
					customerTemp.setCusType(0);
					customerService.updateCustomer(customerTemp);
				}
			}
		}
	}
	
	private void clearCustomer(int cusId)
	{
		try{
			//删除积分记录
			tsRecordService.delTsRecordByCusId(cusId);
			//删除流水
			cashRecordService.delCashRecordByCusId(cusId);
			//删除订单
			contractService.delContractByCusId(cusId);
			//删除积分				
			totolsScoreService.delTotolsScoreByCusId(cusId);	
			//删除登录信息
			loginRecordService.delLoginRecordByCusId(cusId);
			//删除笔记
			notesService.delNotesByCusId(cusId);			
			//删除优惠卷
			cpCusService.delCpCusByCusId(cusId);
			//删除学习计划
			studyPlanService.delStudyPlanByCusId(cusId);
			//删除考试
			exampaperRecordService.delERecordByCusId(cusId);
			//删除考试并联
			optRecordService.delOptRecordByCusId(cusId);
			//删除任务
			taskCusService.deleteTaskCusByCusId(cusId);		
			//删除问题及问题的回复
			QueryProblemCondition queryProblemCondition=new QueryProblemCondition();
			queryProblemCondition.setCusId(cusId);
			List<Problem> problemList=this.problemService.getProblemList(queryProblemCondition);
			for(int j=0;problemList!=null&&j<problemList.size();j++){
				  for(int n=0;problemList.get(j).getReProblemList()!=null&&n<problemList.get(j).getReProblemList().size();n++){
					  //删除回复
					  this.reProblemService.delReProblemById(problemList.get(j).getReProblemList().get(n).getReId());
				  }
				// 删除问题
				this.problemService.delProblemById(problemList.get(j).getPblId());
			}
			
			//删除回复
			QueryReProblemCondition queryReProblemCondition=new QueryReProblemCondition();
			queryReProblemCondition.setReId(cusId);
			List<ReProblem> reProblemList=this.reProblemService.getReProblemList(queryReProblemCondition);
			for(int k=0 ; reProblemList != null && k<reProblemList.size(); k++){
				this.reProblemService.delReProblemById(reProblemList.get(k).getReId());
			}
			//删除用户
			customerService.delCustomerById(cusId);			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 每天查询，删除赠送超过5天的课程 暂不实现该功能
	 * @param 
	 */
	protected void checkCustomerWeeks(){
		
		try{	
			//查询赠送订单
//				List<Contract> giveContractList=contractService.getContractBystatus(2);
//				Calendar calendar=Calendar.getInstance();
//				calendar.setTime(new Date());
//				calendar.add(Calendar.DATE, -5);
//				
//				//超过五天的赠送课程，删除该用户的这个课程
//				for(int i=0;i<giveContractList.size();i++)
//				{
//					if(calendar.getTime().getTime()-giveContractList.get(i).getCreateTime().getTime()>0)
//					{
//						//如果当前日期-5天比订单创建日期晚，则该赠送课程到期，删除赠送课程
//						//根据订单号查流水
//						QueryCashRecordCondition queryCashRecordCondition =new QueryCashRecordCondition();
//						System.out.println("订单号"+giveContractList.get(i).getContractId());
//						queryCashRecordCondition.setContractId(giveContractList.get(i).getContractId());						
//						List<CashRecord> cashRecordList=cashRecordService.getAllCash(giveContractList.get(i).getContractId());
//						//根据流水中的课程ID和订单中的用户ID删除用户课程						
//						for(int j=0;j<cashRecordList.size();j++)
//						{
//						
//							QueryCusCouKpointCondition queryCusCouKpointCondition=new QueryCusCouKpointCondition();
//							queryCusCouKpointCondition.setCusId(giveContractList.get(i).getCusId());
//							queryCusCouKpointCondition.setCourseId(cashRecordList.get(j).getCourseId());
//							cusCouKpointService.delCusCouKpointByCus(queryCusCouKpointCondition);
//							//把订单状态改为内部过期课程
//							Contract contract=giveContractList.get(i);
//							contract.setStatus(6);						
//							contractService.updateContract(contract);
//						}
//					}
//				}
//			//查询等待支付的订单，如果超过7天仍未付款，则设为无效状态 status=5
//				Calendar cal=Calendar.getInstance();
//				cal.setTime(new Date());
//				
//				cal.add(Calendar.DATE, -7);
//				
//				List<Contract> waitPayContractList=contractService.getContractBystatus(0);	
//				for(int i=0;i<waitPayContractList.size();i++)
//				{
//					//如果7天仍未支付
//					if(cal.getTime().getTime()>waitPayContractList.get(i).getCreateTime().getTime())
//					{
//						Contract contract=waitPayContractList.get(i);
//						//将订单状态置于无效
//						contract.setStatus(5);
//						//修改订单
//							//	System.out.println("修改订单置于过期");
//						contractService.updateContract(contract);
//					}
//				}
				
				
		}catch (Exception e) {
			e.printStackTrace();
		}


		
		
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
	public ICusCouKpoint getCusCouKpointService() {
		return cusCouKpointService;
	}
	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}
	public ICustomer getCustomerService() {
		return customerService;
	}
	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}
	public List<Customer> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}
	public QueryCustomerCondition getQueryCustomerCondition() {
		if(this.queryCustomerCondition==null){
			this.queryCustomerCondition= new QueryCustomerCondition();
		}
		return queryCustomerCondition;
	}
	public void setQueryCustomerCondition(
			QueryCustomerCondition queryCustomerCondition) {
		this.queryCustomerCondition = queryCustomerCondition;
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
	public ICourse getCourseService() {
		return courseService;
	}
	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
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
	public ITaskCus getTaskCusService() {
		return taskCusService;
	}
	public void setTaskCusService(ITaskCus taskCusService) {
		this.taskCusService = taskCusService;
	}
	public List<Contract> getContractList() {
		return contractList;
	}
	public void setContractList(List<Contract> contractList) {
		this.contractList = contractList;
	}

	public QueryContractCondition getQueryContractCondition() {
		if(this.queryContractCondition==null)
		{
			queryContractCondition=new QueryContractCondition();
		}
		return queryContractCondition;
	}

	public void setQueryContractCondition(
			QueryContractCondition queryContractCondition) {
		this.queryContractCondition = queryContractCondition;
	}

	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	public IProblem getProblemService() {
		return problemService;
	}

	public void setProblemService(IProblem problemService) {
		this.problemService = problemService;
	}

	public IReProblem getReProblemService() {
		return reProblemService;
	}

	public void setReProblemService(IReProblem reProblemService) {
		this.reProblemService = reProblemService;
	}


	public QueryLoginRecordCondition getQueryLoginRecordCondition() {
		if(queryLoginRecordCondition==null)
		{
			queryLoginRecordCondition=new QueryLoginRecordCondition();
		}
		return queryLoginRecordCondition;
	}


	public void setQueryLoginRecordCondition(
			QueryLoginRecordCondition queryLoginRecordCondition) {
		this.queryLoginRecordCondition = queryLoginRecordCondition;
	}
	


}
