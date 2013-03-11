package com.shangde.edu.stu.action;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.shangde.edu.cms.action.CommentAction;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.CustomerImpl;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.stu.condition.QueryPlanCondition;
import com.shangde.edu.stu.domain.Plan;
import com.shangde.edu.stu.domain.Planitem;
import com.shangde.edu.stu.service.IPlan;
import com.shangde.edu.stu.service.IPlanitem;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;

/**
 * Author: fanxin <br>
 * Date: 7 18 2011 <br>
 * 查看 学习计划 使用情况的 plansta.jsp 统计页面
 */
public class PlanAction extends CommentAction {
	
	private static final Logger logger = Logger.getLogger(PlanAction.class);
	/**
	 * 计划提醒Service层
	 */
	private IPlan planService;

	/**
	 *   学习计划内容服务
	 */
	private IPlanitem planitemService;

	public IPlanitem getPlanitemService() {
		return planitemService;
	}

	public void setPlanitemService(IPlanitem planitemService) {
		this.planitemService = planitemService;
	}

	public IPlan getPlanService() {
		return planService;
	}

	public void setPlanService(IPlan planService) {
		this.planService = planService;
	}

	private QueryPlanCondition queryPlanCondition;
	
	public QueryPlanCondition getQueryPlanCondition() {
		if (queryPlanCondition ==null){
			queryPlanCondition = new QueryPlanCondition();
		}
		return queryPlanCondition;
	}

	public void setQueryPlanCondition(QueryPlanCondition queryPlanCondition) {
		this.queryPlanCondition = queryPlanCondition;
	}

	//所有用户数
	private int allCostomerNum;
	//已使用用户数
	private int customerUserNum;
	// 未使用 用户数 = 所有用户数-已使用用户数
	private int customerNoUserNum;
	// 用过次数 超过10次 的用户数量
	private int customerUserMoreTenNum;
	//用过次数 少于10次 多余0次 的用户数量 = 已使用用户数 - 超过10次 的用户数量
	private int customerUserLessTenNum;
	
	public int getAllCostomerNum() {
		return allCostomerNum;
	}

	public void setAllCostomerNum(int allCostomerNum) {
		this.allCostomerNum = allCostomerNum;
	}

	public int getCustomerUserNum() {
		return customerUserNum;
	}

	public void setCustomerUserNum(int customerUserNum) {
		this.customerUserNum = customerUserNum;
	}

	public int getCustomerNoUserNum() {
		return customerNoUserNum;
	}

	public void setCustomerNoUserNum(int customerNoUserNum) {
		this.customerNoUserNum = customerNoUserNum;
	}

	public int getCustomerUserMoreTenNum() {
		return customerUserMoreTenNum;
	}

	public void setCustomerUserMoreTenNum(int customerUserMoreTenNum) {
		this.customerUserMoreTenNum = customerUserMoreTenNum;
	}

	public int getCustomerUserLessTenNum() {
		return customerUserLessTenNum;
	}

	public void setCustomerUserLessTenNum(int customerUserLessTenNum) {
		this.customerUserLessTenNum = customerUserLessTenNum;
	}

	/**
	 * 学科集合
	 */
	private List<Subject> subjectList;
	/**
	 * 学科服务
	 */
	private ISubject subjectService;
	
	
	
	
	/**
	 *   学习计划
	 */
	private Plan plan;
	
	/**
	 *   学习计划内容
	 */
	private Planitem planitem;
	
	/**
	 *   学习计划内容的详情数组
	 */
	private String[] strArr;
	
	
	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Planitem getPlanitem() {
		return planitem;
	}

	public void setPlanitem(Planitem planitem) {
		this.planitem = planitem;
	}
	
	
    public String[] getStrArr() {
		return strArr;
	}

	public void setStrArr(String[] strArr) {
		this.strArr = strArr;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	/** 
	 * Author: fanxin <br>
	 * Date: 7 18 2011 <br>
	 * 查看 学习计划 使用情况的 plansta.jsp 统计页面
	 */
	// 跳转到添加页面
	public String toGoPlansta() {
		try {
			QueryPlanCondition queryPlanCondition = getQueryPlanCondition();
			allCostomerNum = planService.getCustomerAllStat(queryPlanCondition);
			customerUserNum = planService.getCustomerUser(queryPlanCondition);
			customerNoUserNum = allCostomerNum - customerUserNum;
			
			customerUserMoreTenNum = planService.getCustomerUserMoreTen(queryPlanCondition);
			customerUserLessTenNum = customerUserNum - customerUserMoreTenNum;
			
			return "toGoPlansta";
			
		} catch (Exception e) {
			logger.error("PlanAction.toGoPlansta",e);
			return "error";
		}
		
	}

	
	
	//=============planupdate.jsp=====================
	/**
	 * 计划的ID
	 */
	private Integer planId;
	
	private Customer customer;

	private ICustomer customerService;
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	/** 
	 * Author: fanxin <br>
	 * Date: 7 20 2011 <br>
	 * 学习计划  planupdate.jsp 页面
	 *  查询所有的计划
	 */
	public String getPlanById() {
		try {
    		plan = planService.getPlanById(planId);
    		if(plan!=null){
    			planitem = planitemService.getPlanitemByPlanId(plan.getPlanId());
    			
    			String str = planitem.getItemtitle();
    			strArr = str.split(",");
    			
    			int cusId = plan.getCusId();
    			customer = customerService.getCustomerById(cusId);
    			
    		}
    		return "getPlanById";
    		
		} catch (Exception e) {
			logger.error("PlanAction.getPlanById",e);
			return "error";
		}
		
		
	}
	
	/** 
	 * Author: fanxin <br>
	 * Date: 7 20 2011 <br>
	 * 学习计划  planupdate.jsp 页面
	 *  后台修改 更新学习计划 和 学习计划内容
	 */
	public String updatePlan(){
		try{
			
			Planitem upPlanitem = planitemService.getPlanitemByPlanId(planitem.getPlanId());
			upPlanitem.setItemtitle(planitem.getItemtitle());
			
			planitemService.updatePlanitem(upPlanitem);
			
			if(upPlanitem!=null){
				Plan upPlan = planService.getPlanById(planitem.getPlanId());
				
				upPlan.setHandledate(queryPlanCondition.getHandledate());
				upPlan.setPstatus(queryPlanCondition.getPstatus());
				upPlan.setPicon(queryPlanCondition.getPicon());
				upPlan.setContent(queryPlanCondition.getContent());
				upPlan.setPlantitle(queryPlanCondition.getPlantitle());
				upPlan.setPublish(queryPlanCondition.getPublish());
				upPlan.setHandledate(queryPlanCondition.getHandledate());
				
				planService.updatePlan(upPlan);
				
			}
			return "updatePlan";
			
		}catch(Exception e){
			logger.error("PlanAction.updatePlan",e);
			return "error";
		}
		
		
	}

	//===========planlist.jsp=============

	/**
	 * 批处理所有选择ID
	 */
	private String batchParamsId;
	
	private int batchStatus;

	public int getBatchStatus() {
		return batchStatus;
	}

	public void setBatchStatus(int batchStatus) {
		this.batchStatus = batchStatus;
	}

	public String getBatchParamsId() {
		return batchParamsId;
	}

	public void setBatchParamsId(String batchParamsId) {
		this.batchParamsId = batchParamsId;
	}

	
	/** 
	 * Author: fanxin <br>
	 * Date: 7 20 2011 <br>
	 * 学习计划 列表页 planlist.jsp 页面
	 */
	// 跳转到列表页面
	public String toGoPlanList() {
		try {
			
			// 分页设置 PageSize 是 该Condition 所继承的 PageQuery 中的属性
			//subjectList = subjectService.getAllSubject();
			getQueryPlanCondition().setPageSize(20);
			setPage(planService.getPlanBackPaperByCondition(getQueryPlanCondition()));
			// 需要再设置一次分页
			getPage().setPageSize(20);
			setPageUrlParms();
			
			return "toGoPlanList";
			
		} catch (Exception e) {
			logger.error("PlanAction.toGoPlanList",e);
			return "error";
		}
	}
	
	// 批处理
	public String batchProcessPlan() {
		try{
			// 对字符串进行拆分，得到所有ID的集合
			String[] batchIds = batchParamsId.split(",");
			// 遍历该集合，通过ID得到该闹钟，然后设置它的状态，再去Update该闹钟
			for(int i = 0; i < batchIds.length; i++) {
				 Plan plantemp = planService.getPlanById(Integer.parseInt(batchIds[i]));
				 plantemp.setPstatus(this.batchStatus);
				 planService.updatePlan(plantemp);
			}
			
			return "batchProcessPlan";
			
		}catch(Exception e){
			logger.error("PlanAction.batchProcessPlan",e);
			return "error";
		}
	}
	
	// 按条件检索计划
	public String searchPlan() {
		try {
			// 同样的，做分页设置
			getQueryPlanCondition().setPageSize(20);
			setPage(planService.searchPlanListByParamCondition(getQueryPlanCondition()));
			getPage().setPageSize(20);
			setPageUrlParms();
			return "searchPlan";
			
		} catch (Exception e) {
			logger.error("PlanAction.searchPlan",e);
			return "error";
		}
	}
}
