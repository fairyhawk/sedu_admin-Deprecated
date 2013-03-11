package com.shangde.edu.feed.service.impl;

import com.shangde.common.service.BaseService;
import com.shangde.edu.feed.condition.QueryStatCommonCondition;
import com.shangde.edu.feed.domain.UserUse;
import com.shangde.edu.feed.service.IUserUse;

public class UserUseImpl extends BaseService implements IUserUse {

	/*
	 * (non-Javadoc)
	 *
	 * @see com.shangde.edu.feed.service.IUserUse#addUserUse(com.shangde.edu.feed.domain.UserUse)
	 */
	public Integer addUserUse(UserUse userUse) {
		return simpleDao
				.createEntity("Feed_User_Use_NS.createUserUse", userUse);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseCount(com.shangde.edu.feed.condition.QueryStatCommonCondition)
	 */
	public Integer getUserUseCount(QueryStatCommonCondition queryStatCommonCondition) {
		return simpleDao.getEntity("Feed_User_Use_NS.getUserUseCount",
				queryStatCommonCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseGroupCount(com.shangde.edu.feed.condition.QueryStatCommonCondition)
	 */
	public Integer getUserUseGroupCount(
            QueryStatCommonCondition queryStatCommonCondition) {
		return simpleDao.getEntity("Feed_User_Use_NS.getUserUseGroupCount",
				queryStatCommonCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseAndTypeFromIdCount(com.shangde.edu.feed.condition.QueryStatCommonCondition)
	 */
	public Integer getUserUseAndTypeFromIdCount(
            QueryStatCommonCondition queryStatCommonCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity(
				"Feed_User_Use_NS.getUserUseAndTypeFromIdCount",
				queryStatCommonCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseDATECount(int)
	 */
	public Integer getUserUseDATECount(int type) {
		return simpleDao.getEntity("Feed_User_Use_NS.getUserUseDATECount", type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseDATEGroupCount()
	 */
	public Integer getUserUseDATEGroupCount() {
		return simpleDao.getEntity("Feed_User_Use_NS.getUserUseDATEGroupCount",
				null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseDATEAndTypeFromIdCount(com.shangde.edu.feed.condition.QueryStatCommonCondition)
	 */
	public Integer getUserUseDATEAndTypeFromIdCount(
            QueryStatCommonCondition queryStatCommonCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity(
				"Feed_User_Use_NS.getUserUseDATEAndTypeFromIdCount",
				queryStatCommonCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseMONTHCount(int)
	 */
	public Integer getUserUseMONTHCount(int type) {
		return simpleDao.getEntity("Feed_User_Use_NS.getUserUseMONTHCount",
				type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseMONTHGroupCount()
	 */
	public Integer getUserUseMONTHGroupCount() {
		return simpleDao.getEntity(
				"Feed_User_Use_NS.getUserUseMONTHGroupCount", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseMONTHAndTypeFromIdCount(com.shangde.edu.feed.condition.QueryStatCommonCondition)
	 */
	public Integer getUserUseMONTHAndTypeFromIdCount(
            QueryStatCommonCondition queryStatCommonCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity(
				"Feed_User_Use_NS.getUserUseMONTHAndTypeFromIdCount",
				queryStatCommonCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseWEEKCount(int)
	 */
	public Integer getUserUseWEEKCount(int type) {
		return simpleDao
				.getEntity("Feed_User_Use_NS.getUserUseWEEKCount", type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseWEEKGroupCount()
	 */
	public Integer getUserUseWEEKGroupCount() {
		return simpleDao.getEntity("Feed_User_Use_NS.getUserUseWEEKGroupCount",
				null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseWEEKAndTypeFromIdCount(com.shangde.edu.feed.condition.QueryStatCommonCondition)
	 */
	public Integer getUserUseWEEKAndTypeFromIdCount(
            QueryStatCommonCondition queryStatCommonCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity(
				"Feed_User_Use_NS.getUserUseWEEKAndTypeFromIdCount",
				queryStatCommonCondition);
	}

}
