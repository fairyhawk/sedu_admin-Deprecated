package com.shangde.edu.sys.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.sys.condition.QueryLoginLogCondition;
import com.shangde.edu.sys.service.ILoginLog;

public class LoginLogAction extends CommonAction {
	
	private Logger logger  = Logger.getLogger(LoginLogAction.class);
	
	private String userName;
	private ILoginLog loginLogService;
	private QueryLoginLogCondition queryLoginLogCondition ;

	public void setLoginLogService(ILoginLog loginLogService) {
		this.loginLogService = loginLogService;
	}
	
	public QueryLoginLogCondition getQueryLoginLogCondition() {
		if(queryLoginLogCondition == null){
			queryLoginLogCondition = new QueryLoginLogCondition();
		}
		return queryLoginLogCondition;
	}

	public void setQueryLoginLogCondition(
			QueryLoginLogCondition queryLoginLogCondition) {
		this.queryLoginLogCondition = queryLoginLogCondition;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 查看所有登录日志记录
	 * @return
	 */
	public String getAll(){
		try{
			this.getQueryLoginLogCondition().setPageSize(30);
			setPage(loginLogService.getAll(queryLoginLogCondition));
			getPage().setPageSize(30);
			setPageUrlParms();
		}catch(Exception e){
			logger.error("LoginLogAction.getAll", e);
			return ERROR;
		}
		return "loginLog_list";
	}
	
	/**
	 * 根据用户登录ID查看所有登录日志记录
	 * @return
	 */
	public String getByUserId(){
		try{
			this.getQueryLoginLogCondition().setPageSize(30);
			setPage(loginLogService.getByUserId(queryLoginLogCondition));
			getPage().setPageSize(30);
			setPageUrlParms();
		}catch(Exception e){
			logger.error("LoginLogAction.getByUserId", e);
			return ERROR;
		}
		return "loginLog_list";
	}
	
	/**
	 * 根据用户名搜索查看登录日志
	 * @return
	 */
	public String search(){
		try {
			userName = URLDecoder.decode(userName, "UTF-8");
			this.getQueryLoginLogCondition().setUserName(userName);
			this.getQueryLoginLogCondition().setPageSize(30);
			setPage(loginLogService.search(queryLoginLogCondition));
			getPage().setPageSize(30);
			setPageUrlParms();
		} catch (UnsupportedEncodingException e) {
			logger.error("LoginLogAction.search", e);
			return ERROR;
		}
		return "loginLog_list";
	}
}
