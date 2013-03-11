package com.shangde.edu.sys.service;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QueryLoginLogCondition;
import com.shangde.edu.sys.domain.LoginLog;
import com.shangde.edu.sys.service.ILoginLog;

public class LoginLogImpl extends BaseService implements ILoginLog {
	
	public Integer add(LoginLog loginLog){
		return simpleDao.createEntity("LoginLog_NS.add", loginLog);
	}

	public PageResult getAll(QueryLoginLogCondition queryLoginLogCondition) {
		PageResult pr = simpleDao.getPageResult("LoginLog_NS.getAll",
				"LoginLog_NS.getAllCount", queryLoginLogCondition);
		return pr;
	}

	public PageResult getByUserId(QueryLoginLogCondition queryLoginLogCondition) {
		PageResult pr = simpleDao.getPageResult("LoginLog_NS.getByUserId",
				"LoginLog_NS.getByUserIdCount", queryLoginLogCondition);
		return pr;
	}

	public PageResult search(QueryLoginLogCondition queryLoginLogCondition) {
		PageResult pr = simpleDao.getPageResult("LoginLog_NS.search",
				"LoginLog_NS.searchCount", queryLoginLogCondition);
		return pr;
	}
}
