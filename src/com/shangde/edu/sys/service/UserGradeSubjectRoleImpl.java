package com.shangde.edu.sys.service;

import java.util.List;
import com.shangde.common.service.BaseService;
import com.shangde.edu.sys.condition.QueryUserGradeSubjectRoleCondition;
import com.shangde.edu.sys.domain.UserGradeSubjectRole;

/**
 * UserGradeSubjectRole对象操作实现类 
 * User: guoqiang.liu 
 * Date: 2010-01-14
 */
@SuppressWarnings("unchecked")
public class UserGradeSubjectRoleImpl extends BaseService implements
		IUserGradeSubjectRole {
	public void addUserGradeSubjectRole(
			UserGradeSubjectRole userGradeSubjectRole) {
		simpleDao.createEntity(
				"UserGradeSubjectRole_NS.createUserGradeSubjectRole",
				userGradeSubjectRole);
	}

	public void delUserGradeSubjectRoleById(int roleId, int userId,
			int gradeId, int subjectId) {
		java.util.Map keyMap = new java.util.HashMap();
		keyMap.put("roleId", roleId);
		keyMap.put("userId", userId);
		keyMap.put("gradeId", gradeId);
		keyMap.put("subjectId", subjectId);
		simpleDao.deleteEntity(
				"UserGradeSubjectRole_NS.deleteUserGradeSubjectRoleById",
				keyMap);
	}

	public void updateUserGradeSubjectRole(
			UserGradeSubjectRole userGradeSubjectRole) {
		simpleDao.updateEntity(
				"UserGradeSubjectRole_NS.updateUserGradeSubjectRole",
				userGradeSubjectRole);
	}

	public UserGradeSubjectRole getUserGradeSubjectRoleById(int roleId,
			int userId, int gradeId, int subjectId) {
		java.util.Map keyMap = new java.util.HashMap();
		keyMap.put("roleId", roleId);
		keyMap.put("userId", userId);
		keyMap.put("gradeId", gradeId);
		keyMap.put("subjectId", subjectId);
		return simpleDao.getEntity(
				"UserGradeSubjectRole_NS.getUserGradeSubjectRoleById", keyMap);
	}

	public List<UserGradeSubjectRole> getUserGradeSubjectRoleList(
			QueryUserGradeSubjectRoleCondition queryUserGradeSubjectRoleCondition) {
		return simpleDao.getForList(
				"UserGradeSubjectRole_NS.getUserGradeSubjectRoleList",
				queryUserGradeSubjectRoleCondition);
	}

	public List<UserGradeSubjectRole> getUGSRByUserId(int userId) {
		return simpleDao.getForList("UserGradeSubjectRole_NS.getUGSRByUserId",
				userId);
	}
}
