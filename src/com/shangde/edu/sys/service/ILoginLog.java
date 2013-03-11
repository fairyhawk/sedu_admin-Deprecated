package com.shangde.edu.sys.service;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QueryLoginLogCondition;
import com.shangde.edu.sys.domain.LoginLog;

public interface ILoginLog {
	
	/**
	 * 添加登录日志
	 * @param loginLog
	 * @return
	 */
	public Integer add(LoginLog loginLog);
	
	/**
	 * 查看所有登录日志记录
	 * @param queryLoginLogCondition
	 * @return
	 */
	public PageResult getAll(QueryLoginLogCondition queryLoginLogCondition);
	
	/**
	 * 根据用户登录ID查看所有登录日志记录
	 * @param queryLoginLogCondition
	 * @return
	 */
	public PageResult getByUserId(QueryLoginLogCondition queryLoginLogCondition);
	
	/**
	 * 根据用户名搜索查看登录日志
	 * @param queryLoginLogCondition
	 * @return
	 */
	public PageResult search(QueryLoginLogCondition queryLoginLogCondition);
}
