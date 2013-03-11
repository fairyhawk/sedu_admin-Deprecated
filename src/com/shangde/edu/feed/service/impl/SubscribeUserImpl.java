package com.shangde.edu.feed.service.impl;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.feed.condition.QuerySubscribeUserCondition;
import com.shangde.edu.feed.domain.SubscribeUser;
import com.shangde.edu.feed.service.ISubscribeUser;

/**
 * 订阅用户接口实现类
 * 
 * @author Libg
 * 
 */
@SuppressWarnings("unchecked")
public class SubscribeUserImpl extends BaseService implements ISubscribeUser {

	public Integer addSubscribeUser(SubscribeUser subscribeUser) {
		return simpleDao.createEntity("SubscribeUser_NS.createSubscribeUser",
				subscribeUser);
	}

	public int delSubscribeUserById(int id) {
		return simpleDao.delete("SubscribeUser_NS.deleteSubscribeUserById", id);
	}

	public int updateSubscribeUser(SubscribeUser subscribeUser) {
		return simpleDao.update("SubscribeUser_NS.updateSubscribeUser",
				subscribeUser);
	}

	public SubscribeUser getSubscribeUserById() {
		return null;
	}

	public List<SubscribeUser> getSubscribeUserList(
			QuerySubscribeUserCondition querySubscribeUserCondition) {
		return simpleDao.getForList("SubscribeUser_NS.getSubscribeUserList",
				querySubscribeUserCondition);
	}

	public List<SubscribeUser> getCustomerBuyList(int subjectId) {
		return simpleDao.getForList("SubscribeUser_NS.getCustomerBuyList",
				subjectId);
	}

	public List<SubscribeUser> getCustomerRegList(int subjectId) {
		return simpleDao.getForList("SubscribeUser_NS.getCustomerRegList",
				subjectId);
	}
}
