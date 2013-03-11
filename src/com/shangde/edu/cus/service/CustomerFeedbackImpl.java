package com.shangde.edu.cus.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.condition.QueryCustomerFeedbackCondition;
import com.shangde.edu.cus.domain.CustomerFeedback;

/**
 * Description:用户反馈信息接口
 * User: Yangning
 * Date: 2011-11-09
 */
public class CustomerFeedbackImpl extends BaseService implements ICustomerFeedback {
	
	public int addCustomerFeedback(CustomerFeedback feedback) throws Exception {
		int result = 0;
		try{
			result = simpleDao.createEntity("CustoerFeedback_NS.createCustomerFeedback", feedback);
		}catch(Exception e){
			logger.error("CustomerFeedbackImpl.addCustomerFeedback", e);
			throw new Exception (e.getMessage());
		}
		return result;
	}

	public List<CustomerFeedback> getCusotmerList(
			QueryCustomerFeedbackCondition condition) throws Exception {
		List<CustomerFeedback> list = null;
		try{
			list = simpleDao.getForList("CustoerFeedback_NS.getCustomerFeedbackList", condition);
		}catch(Exception e){
			logger.error("CustomerFeedbackImpl.getCusotmerList", e);
			throw new Exception(e.getMessage());
		}
		// TODO Auto-generated method stub
		return list;
	}

	public int updateCustomerFeedback(CustomerFeedback feedback) throws Exception {
		int result = 0;
		try{
			result = simpleDao.update("CustoerFeedback_NS.updateCustomerFeedback", feedback);
		}catch(Exception e){
			logger.error("CustomerFeedbackImpl.getCusotmerList",e);
			throw new Exception(e.getMessage());
		}
		return result;
	}

	public int delCustomerFeedbackById(Integer feedid) throws Exception {
		int result = 0;
		try{
			result = simpleDao.delete("CustoerFeedback_NS.delCustomerFeedbackById", feedid);
		}catch(Exception e){
			logger.error("CustomerFeedbackImpl.delCustomerById", e);
			throw new Exception(e.getMessage());
		}
		return result;
	}

	public CustomerFeedback getCustomerById(Integer feedid) throws Exception {
		CustomerFeedback feedback = null;
		try{
			feedback = simpleDao.getEntity("CustoerFeedback_NS.getCustomerFeedbackById", feedid);
		}catch(Exception e){
			logger.error("CustomerFeedbackImpl.getCustomerFeedbackById", e);
			throw new Exception(e.getMessage());
		}
		return feedback;
	}

	public PageResult getCustomerPageResult(
			QueryCustomerFeedbackCondition condition) throws Exception {
		PageResult page = null;
		try{
			page = simpleDao.getPageResult("CustoerFeedback_NS.getCustomerFeedbackList", "CustoerFeedback_NS.getCustomerFeedbackCount", condition);
		}catch(Exception e){
			//logger.error("CustomerFeedbackImpl.getCustomerPageResult", e);
			throw new Exception(e);
		}
		return page;
	}
	
	/**
	 * 客服人员填写解决方法
	 * @param feedback
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-11
	 */
	public int updateAnwser(CustomerFeedback feedback) throws Exception {
		int result = 0;
		try{
			result = simpleDao.update("CustoerFeedback_NS.ansCustomerFeedByid",feedback);
		}catch(Exception e){
			logger.error("CustomerFeedbackImpl.addAnwser", e);
			throw new Exception(e.getMessage());
		}
		return result;
	}
}
