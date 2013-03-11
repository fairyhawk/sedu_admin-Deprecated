package com.shangde.edu.cus.web;

import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.cus.condition.QueryCustomerFeedbackCondition;
import com.shangde.edu.cus.domain.CustomerFeedback;
import com.shangde.edu.cus.service.ICustomerFeedback;
/**
 * Description:用户反馈信息控制器
 * User: Yangning
 * Date: 2011-11-09
 */
public class CustomerFeedbackAction extends CommonAction{
	
	private static final Logger logger = Logger.getLogger(CustomerFeedbackAction.class);
	
	private static final long serialVersionUID = -2069052577786457522L;
	
	private ICustomerFeedback cfeedbackService; 

	private CustomerFeedback feedback;
	
	private QueryCustomerFeedbackCondition condition;
	
	List<CustomerFeedback> list;
	
	public String addFeed(){
		String forward = SUCCESS;
		try{
			int result = cfeedbackService.addCustomerFeedback(feedback);
			if(result <= 0){
				forward = ERROR;
			}
			this.setResult(new Result<String>(true, forward, null, null));
		}catch(Exception e){
			forward = ERROR;
			logger.error("CustomerFeedbackAction.addFeed", e);
		}
		return "json";
	}
	
	
	
	
	public CustomerFeedback getFeedback() {
		return feedback;
	}

	public void setFeedback(CustomerFeedback feedback) {
		this.feedback = feedback;
	}
	
	public ICustomerFeedback getCfeedbackService() {
		return cfeedbackService;
	}
	public void setCfeedbackService(ICustomerFeedback cfeedbackService) {
		this.cfeedbackService = cfeedbackService;
	}

	public List<CustomerFeedback> getList() {
		return list;
	}

	public void setList(List<CustomerFeedback> list) {
		this.list = list;
	}

	public QueryCustomerFeedbackCondition getCondition() {
		return condition;
	}

	public void setCondition(QueryCustomerFeedbackCondition condition) {
		this.condition = condition;
	}
}
