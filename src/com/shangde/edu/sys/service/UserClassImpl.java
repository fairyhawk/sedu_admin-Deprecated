package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.sys.condition.QueryUserClassCondition;
import com.shangde.edu.sys.domain.UserClass;

/**
 * UserClass对象操作实现类
 * @author guoqiang.liu
 */
@SuppressWarnings("unchecked")
public class UserClassImpl extends BaseService implements IUserClass {
	public void addUserClass(UserClass userClass) {
		simpleDao.createEntity("UserClass_NS.createUserClass", userClass);
	}

	public void delUserClassById(int userId, int classId) {
		java.util.Map keyMap = new java.util.HashMap();
		keyMap.put("userId", userId);
		keyMap.put("classId", classId);
		simpleDao.deleteEntity("UserClass_NS.deleteUserClassById", keyMap);
	}

	public void updateUserClass(UserClass userClass) {
		simpleDao.updateEntity("UserClass_NS.updateUserClass", userClass);
	}

	public UserClass getUserClassById(int userId, int classId) {
		java.util.Map keyMap = new java.util.HashMap();
		keyMap.put("userId", userId);
		keyMap.put("classId", classId);
		return simpleDao.getEntity("UserClass_NS.getUserClassById", keyMap);
	}

	public List<UserClass> getUserClassList(
			QueryUserClassCondition queryUserClassCondition) {
		return simpleDao.getForList("UserClass_NS.getUserClassList",
				queryUserClassCondition);
	}
}
