package com.shangde.edu.cus.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.condition.QueryCustomerFeedbackCondition;
import com.shangde.edu.cus.domain.CustomerFeedback;

/**
 * Description:用户反馈信息接口
 * User: Yangning
 * Date: 2011-11-09
 */
public interface ICustomerFeedback {
	public int addCustomerFeedback(CustomerFeedback feedback) throws Exception ;
	
	public List<CustomerFeedback> getCusotmerList(QueryCustomerFeedbackCondition condition) throws Exception;
	
	public int updateCustomerFeedback(CustomerFeedback feedback) throws Exception;
	
	public int delCustomerFeedbackById(Integer feedid) throws Exception;
	
	public CustomerFeedback getCustomerById(Integer feedid) throws Exception;
	
	public PageResult getCustomerPageResult(QueryCustomerFeedbackCondition condition) throws Exception;
	
	/**
	 * 客服人员填写解决方法
	 * @param feedback
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-11
	 */
	public int updateAnwser(CustomerFeedback feedback) throws Exception;
}
