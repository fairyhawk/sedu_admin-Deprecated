package com.shangde.edu.feed.service.impl;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryUserStatCondition;
import com.shangde.edu.feed.domain.UserStat;
import com.shangde.edu.feed.dto.UserStatDTO;
import com.shangde.edu.feed.service.IUserStat;

public class UserStatImpl extends BaseService implements IUserStat {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserStat#addUserStat(com.shangde.edu.feed.domain.UserStat)
	 */
	public Integer addUserStat(UserStat userStat) {
		return simpleDao.createEntity("Feed_UserStat_NS.createUserStat",
				userStat);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserStat#getUserStatById(java.lang.Integer)
	 */
	public UserStat getUserStatById(Integer id) {
		return simpleDao.getEntity(
				"Feed_UserStat_NS.getUserStatCountByCusIdEmail", id);
	}

	
	public UserStat getUserStatCountByCusIdEmail(
			QueryUserStatCondition queryUserStatCondition) {
		return simpleDao.getEntity(
				"Feed_UserStat_NS.getUserStatCountByCusIdEmail",
				queryUserStatCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserStat#updateUserStat(com.shangde.edu.feed.domain.UserStat)
	 */
	public Integer updateUserStat(UserStat userStat) {
		return simpleDao.update("Feed_UserStat_NS.updateUserStat", userStat);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.shangde.edu.feed.service.IUserStat#getUserStatList(com.shangde.edu.feed.condition.QueryUserStatCondition)
	 */
	public PageResult getUserStatList(
			QueryUserStatCondition queryUserStatCondition) {
		return simpleDao.getPageResult("Feed_UserStat_NS.getUserStatList", "Feed_UserStat_NS.getUserStatListCount", queryUserStatCondition);
	}
	
	

	/*
	 * (non-Javadoc)
	 * @see com.shangde.edu.feed.service.IUserStat#getUserStatListBYSreach(com.shangde.edu.feed.condition.QueryUserStatCondition)
	 */
	public PageResult getUserStatListBYSreach(
			QueryUserStatCondition queryUserStatCondition) {
		return this.simpleDao.getPageResult("Feed_UserStat_NS.getUserStatListByTerm", "Feed_UserStat_NS.getUserStatListCountByTerm", queryUserStatCondition);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shangde.edu.feed.service.IUserStat#exportUserStatistics()
	 */
	public List<UserStatDTO> exportUserStatistics() {
		return simpleDao.getForList("Feed_UserStat_NS.ExportUserStatistics", null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shangde.edu.feed.service.IUserStat#exportSearchUserStatList(com.shangde.edu.feed.condition.QueryUserStatCondition)
	 */
	public List<UserStatDTO> exportSearchUserStatList(QueryUserStatCondition queryUserStatCondition) {
		return simpleDao.getForList("Feed_UserStat_NS.ExportSearchUserStatList", queryUserStatCondition);
	}
}
