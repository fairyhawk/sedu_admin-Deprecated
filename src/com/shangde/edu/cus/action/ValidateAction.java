package com.shangde.edu.cus.action;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
/**
 * Description:用户反馈信息控制器
 * User: Yangning
 * Date: 2011-11-09
 */
public class ValidateAction extends CommonAction{
	
	private static final Logger logger = Logger.getLogger(CustomerFeedbackAction.class);
	
	private static final long serialVersionUID = -2069052577786457522L;
	
	private String username;
	
	public String validateN(){
		String forward = "list";
		try{
			username="yang";
		}catch(Exception e){
			logger.error("CommonAction.validate", e);
			forward = ERROR;
		}
		return forward;
	}
	
	public String validateF(){
		String forward = "validateError";
		try{
			username = "yang";
			addFieldError("username","用户名错误");
		}catch(Exception e){
			logger.error("CommonAction.validate", e);
			forward = ERROR;
		}
		return forward;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
