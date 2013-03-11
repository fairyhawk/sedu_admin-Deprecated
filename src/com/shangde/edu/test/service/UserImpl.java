package com.shangde.edu.test.service;

import java.util.Date;

import com.shangde.common.service.BaseService;
import com.shangde.edu.test.domain.User;

public class UserImpl extends BaseService implements IUser{

	public java.lang.Integer addTestUser(User user) {
		user.setLastLoginTime(new Date());
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		return simpleDao.createEntity("TestUser_NS.createUser", user);
	}

	public void delTestUserById(int userId) {
		simpleDao.deleteEntity("TestUser_NS.deleteUserById", userId);	
	}

	public User getTestUserById(int userId) {
		return simpleDao.getEntity("TestUser_NS.getUserById", userId);
	}

	public void updateTestUser(User user) {
		user.setUpdateTime(new Date());
		simpleDao.updateEntity("TestUser_NS.updateUser", user);
		
	}


}
;