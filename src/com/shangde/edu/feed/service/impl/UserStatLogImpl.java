/**
 * 
 */
package com.shangde.edu.feed.service.impl;

import com.shangde.common.service.BaseService;
import com.shangde.edu.feed.condition.QueryUserStatLogCondition;
import com.shangde.edu.feed.domain.UserStatLog;
import com.shangde.edu.feed.service.IUserStatLog;

/**
 * @author Libg
 * 
 */
public class UserStatLogImpl extends BaseService implements IUserStatLog {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserStatLog#addUserStatLog(com.shangde.edu.feed.domain.UserStatLog)
	 */
	public Integer addUserStatLog(UserStatLog userStatLog) {
		return simpleDao.createEntity("Feed_UserStatLog_NS.createUserStatLog",userStatLog);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserStatLog#getByCusIdEmailDateCount(com.shangde.edu.feed.condition.QueryUserStatLogCondition)
	 */
	public Integer getByCusIdEmailDateCount(QueryUserStatLogCondition queryUserStatLogCondition) {
		return simpleDao.getEntity("Feed_UserStatLog_NS.getByCusIdEmailDateCount",queryUserStatLogCondition);
	}

}
