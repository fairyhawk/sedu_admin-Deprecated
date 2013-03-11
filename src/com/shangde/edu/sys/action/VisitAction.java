package com.shangde.edu.sys.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.res.condition.QueryBooksCondition;
import com.shangde.edu.sys.condition.QueryUserCondition;
import com.shangde.edu.sys.condition.QueryVisitHistoryCondition;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.domain.VisitHistory;
import com.shangde.edu.sys.service.IUser;
import com.shangde.edu.sys.service.IVisitHistory;

public class VisitAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(VisitAction.class);
	
	/** 行为服务*/
	private IVisitHistory visitHistoryService;
	/** 行为查询条件*/
	private QueryVisitHistoryCondition queryVisitHistoryCondition;
	/**实体对象*/
	private VisitHistory visitHistory;
	/**
	 * 前台学员
	 */
	private Customer customer;
	private ICustomer customerService;
	private QueryCustomerCondition queryCustomerCondition;
	private List<Customer> customerList=new ArrayList<Customer>();
	/**
	 * 后台用户
	 */
	private User user;
	private IUser userService;
	private QueryUserCondition queryUserCondition;
	private List<User> userList=new ArrayList<User>();
	/**
	 * 查询条件
	 */
	private String startTime;
	private String endTime;
	private String userName;
	
	
	/**
	 * 用户列表
	 * @return String
	 * @throws Exception
	 */
	public String getVisitList() {
		try{
			//查询条件
			if(visitHistory!=null){
			if(visitHistory.getVisitIp()!=null&&!"".equals(visitHistory.getVisitIp())){
				this.getQueryVisitHistoryCondition().setVisitIp(visitHistory.getVisitIp());
			}
			if(visitHistory.getNameSpace()!=null&&!"".equals(visitHistory.getNameSpace())){
				this.getQueryVisitHistoryCondition().setNameSpace(visitHistory.getNameSpace());
			}
			if(visitHistory.getActionName()!=null&&!"".equals(visitHistory.getActionName())){
				this.getQueryVisitHistoryCondition().setActionName(visitHistory.getActionName());
			}
			if(visitHistory.getVisitType()!=null&&!"".equals(visitHistory.getVisitType())){
				this.getQueryVisitHistoryCondition().setVisitType(visitHistory.getVisitType());			
			}
			if(visitHistory.getUserType()!=0){
				this.getQueryVisitHistoryCondition().setUserType(visitHistory.getUserType());
			}
			if(visitHistory.getUserId()!=0){
				this.getQueryVisitHistoryCondition().setUserId(visitHistory.getUserId());
			}
			
			}
			if(startTime!=null&&!"".equals(startTime)){
				this.getQueryVisitHistoryCondition().setStartTime(startTime);
			}
			if(endTime!=null&&!"".equals(endTime)){
				this.getQueryVisitHistoryCondition().setEndTime(endTime);
			}
			if(userName!=null&&!"".equals(userName))
			{   
				if(visitHistory!=null){
					if(visitHistory.getUserType()!=0){
						if(visitHistory.getUserType()==2){
							this.getQueryCustomerCondition().setCusName(userName);
							customerList=this.customerService.getCustomerList(queryCustomerCondition);
							for(int i=0;customerList!=null&&i<customerList.size();i++){
								this.getQueryVisitHistoryCondition().setUserId(customerList.get(i).getCusId());
							}
						}
//						if(visitHistory.getUserType()==3){
//							this.getQueryUserCondition().
//						}
					}
				}
				
			}
			//前台用户list
			customerList=this.customerService.getCustomerList(getQueryCustomerCondition());
			//后台用户list
			userList=this.userService.getUsetByList(getQueryUserCondition());
			
			setPage(this.visitHistoryService.getVisitHistoryByList(getQueryVisitHistoryCondition()));
			setPageUrlParms();
		}catch(Exception e){
			logger.error("VisitAction.getVisitList", e);
			return ERROR;
		}
		return "visitlist";
	}
	public String deleteVisit(){
		try{
			if(visitHistory.getId()!=0){
				this.visitHistoryService.delVisitHistoryById(visitHistory.getId());
			}
			
		}catch(Exception e){
			logger.error("VisitAction.deleteVisit", e);
			return ERROR;
		}
		
	return "deletevisit";	
	}
   
	public IVisitHistory getVisitHistoryService() {
		return visitHistoryService;
	}

	public void setVisitHistoryService(IVisitHistory visitHistoryService) {
		this.visitHistoryService = visitHistoryService;
	}

	public QueryVisitHistoryCondition getQueryVisitHistoryCondition() {
		if (queryVisitHistoryCondition == null) {
			queryVisitHistoryCondition = new QueryVisitHistoryCondition();
		}
		return queryVisitHistoryCondition;
	}

	public void setQueryVisitHistoryCondition(
			QueryVisitHistoryCondition queryVisitHistoryCondition) {
		this.queryVisitHistoryCondition = queryVisitHistoryCondition;
	}

	public VisitHistory getVisitHistory() {
		return visitHistory;
	}

	public void setVisitHistory(VisitHistory visitHistory) {
		this.visitHistory = visitHistory;
	}
	public ICustomer getCustomerService() {
		return customerService;
	}
	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}
	public QueryCustomerCondition getQueryCustomerCondition() {
		if(queryCustomerCondition == null) {
			queryCustomerCondition = new QueryCustomerCondition();
		}
		return queryCustomerCondition;
	}
	public void setQueryCustomerCondition(
			QueryCustomerCondition queryCustomerCondition) {
		this.queryCustomerCondition = queryCustomerCondition;
	}
	public List<Customer> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}
	public IUser getUserService() {
		return userService;
	}
	public void setUserService(IUser userService) {
		this.userService = userService;
	}
	public QueryUserCondition getQueryUserCondition() {
		if (queryUserCondition == null) {
			queryUserCondition = new QueryUserCondition();
		}
		return queryUserCondition;
		
	}
	public void setQueryUserCondition(QueryUserCondition queryUserCondition) {
		this.queryUserCondition = queryUserCondition;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
}
