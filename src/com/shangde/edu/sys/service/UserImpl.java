package com.shangde.edu.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.common.util.StringUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QueryGradeCondition;
import com.shangde.edu.sys.condition.QuerySubjectCondition;
import com.shangde.edu.sys.condition.QueryUserCondition;
import com.shangde.edu.sys.domain.Function;
import com.shangde.edu.sys.domain.Grade;
import com.shangde.edu.sys.domain.Group;
import com.shangde.edu.sys.domain.Role;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.domain.UserClass;
import com.shangde.edu.sys.domain.UserGradeSubjectRole;

/**
 * User对象操作实现类
 * 
 * @author guoqiang.liu
 */
@SuppressWarnings("unchecked")
public class UserImpl extends BaseServiceManyDb implements IUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public java.lang.Integer addUser(User user) {
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setLastLoginTime(new Date());
		return simpleDao.createEntity("User_NS.createUser", user);
	}

	public void delUserById(int userId) {
		simpleDao.deleteEntity("User_NS.deleteUserById", userId);
	}

	public void updateUser(User user) {
		user.setLastLoginTime(new Date());
		simpleDao.updateEntity("User_NS.updateUser", user);
	}

	public User getUserById(int userId) {
		return simpleDao.getEntity("User_NS.getUserById", userId);
	}

	public User getUserByLoginname(String loginname) {
		return simpleDao.getEntity("User_NS.getUserByLoginname", loginname);
	}

	public PageResult getUserList(QueryUserCondition queryUserCondition) {
		return simpleDao.getPageResult("User_NS.getUserList",
				"User_NS.getUserListCount", queryUserCondition);
	}

	public PageResult getUserListByGroupId(QueryUserCondition queryUserCondition) {
		List<Group> groupList = this
		.getAllGroupListByParentId(queryUserCondition.getGroupId());
		queryUserCondition.setGroupList(groupList);
		return simpleDao.getPageResult("User_NS.getUserListByGroupId",
				"User_NS.getUserListByGroupIdCount", queryUserCondition);
	}

	public List<Group> getAllGroupListByParentId(int groupId) {
		// 当前一级组
		Group group = simpleDao.getEntity("Group_NS.getGroupById", new Integer(
				groupId));
		List<Group> groupList1 = new ArrayList<Group>();
		groupList1.add(group);
		if (group.getLevel() == 1) {
			// 一级用户组
			// 当前二级组列表
			List<Group> groupList2 = simpleDao.getForList(
					"Group_NS.getChildGroupById", new Integer(group
							.getGroupId()));
			groupList1.addAll(groupList2);
			// 三级下所有用户
			for (int i = 0; i < groupList2.size(); i++) {
				List<Group> groupList3 = simpleDao.getForList(
						"Group_NS.getChildGroupById", new Integer(groupList2
								.get(i).getGroupId()));
				// 添加三级下所有用户
				groupList1.addAll(groupList3);
			}
		} else if (group.getLevel() == 2) {
			// 二级用户组
			List<Group> groupList2 = simpleDao.getForList(
					"Group_NS.getChildGroupById", new Integer(groupId));
			groupList1.addAll(groupList2);
		} else {
		}
		return groupList1;
	}

	public PageResult getUserListByKey(QueryUserCondition queryUserCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("User_NS.getUserListByKey",
				"User_NS.getUserListByKeyCount", queryUserCondition);
	}

	public User getUserDtoByUserId(int userId) {
		// TODO Auto-generated method stub
		// copy user
		User ttuser = this.getUserById(userId);
		// 定义roleList
		List<Role> roleList = ttuser.getRoleList();
		for (int i = 0; i < roleList.size(); i++) {
			// 定义角色
			Role ttrole = roleList.get(i);
			// 得到角色对应的专业，根据userId和roleId
			QuerySubjectCondition qsc = new QuerySubjectCondition();
			qsc.setRoleId(ttrole.getRoleId());
			qsc.setUserId(ttuser.getUserId());
			List<Subject> subjectList = this.simpleDao.getForList(
					"Subject_NS.getSubjectListByRoleId", qsc);
			// 得到专业对应的入学年份，根据专业
			for (Subject ss : subjectList) {
				QueryGradeCondition qgc = new QueryGradeCondition();
				qgc.setSubjectId(ss.getSubjectId());
				qgc.setUserId(ttuser.getUserId());
				qgc.setRoleId(ttrole.getRoleId());
				List<Grade> gradeList = this.simpleDao.getForList(
						"Grade_NS.getGradeListBySubjectId", qgc);
				ss.setGradeList(gradeList);
			}
			ttrole.setSubjectList(subjectList);
		}
		ttuser.setRoleList(roleList);
		return ttuser;
	}

	public List getUserFunctionMap(List<Role> roleList) {
		Map<String, String> systemFunctionMap = new HashMap<String, String>();
		for (Role role : roleList) {
			for (int i = 0; i < role.getFunctionList().size(); i++) {
				Function tsf = role.getFunctionList().get(i);
				systemFunctionMap.put(tsf.getFunctionUrl(), tsf
						.getFunctionUrl());
			}
		}
		List functionList = new ArrayList<String>(systemFunctionMap.values());
		return functionList;
	}


	public List<Function> getUserFunction(List<Role> roleList) {
		Map<String, Function> systemFunctionMap = new HashMap<String, Function>();
		List<Function> fcList = new ArrayList<Function>();
		for (Role role : roleList) {
			for (int i = 0; i < role.getFunctionList().size(); i++) {
				//去重复
				Function tsf = role.getFunctionList().get(i);
				systemFunctionMap.put(String.valueOf(tsf.getFunctionId()), tsf);
			}
		}
		List functionList = new ArrayList<Function>(systemFunctionMap.values());
		return functionList;
	}

	public void updateUserPwd(User user) {
		simpleDao.updateEntity("User_NS.changeUserPwd", user);
	}
	
	public void updateContentRole(User user){
		simpleDao.updateEntity("User_NS.changeContentRole", user);
	}

	public String getUserCountByLoginName(String loginName) {
		Integer i = simpleDao.getEntity("User_NS.getUserCountByLoginName",
				loginName);
		if (i < 1) {
			return "";
		} else {
			return "用户名已存在!";
		}
	}

	public void addUserStu(User user, String[] classIds, String[] gradeIds,
			String[] subjectIds, int roleId) {
		this.addUser(user);
		int userId = user.getUserId();
		for (int i = 0; i < classIds.length; i++) {
			int classId = Integer.parseInt(classIds[i]);
			UserClass userClass = new UserClass();
			userClass.setClassId(classId);
			userClass.setUserId(userId);
			userClass.setStatus(0);
			userClass.setCreateTime(new Date(System.currentTimeMillis()));
			userClass.setUpdateTime(new Date(System.currentTimeMillis()));
			this.simpleDao.createEntity("UserClass_NS.createUserClass",
					userClass);
		}
		for (int j = 0; j < gradeIds.length; j++) {
			int gradeId = Integer.parseInt(gradeIds[j]);
			for (int i = 0; i < subjectIds.length; i++) {
				int subjectId = Integer.parseInt(subjectIds[i]);
				UserGradeSubjectRole ugsr = new UserGradeSubjectRole();
				ugsr.setUserId(userId);
				ugsr.setGradeId(gradeId);
				ugsr.setRoleId(roleId);
				ugsr.setSubjectId(subjectId);
				ugsr.setStatus(0);
				ugsr.setLimitScopeId(1);
				ugsr.setCreateTime(new Date(System.currentTimeMillis()));
				ugsr.setUpdateTime(new Date(System.currentTimeMillis()));
				this.simpleDao.createEntity(
						"UserGradeSubjectRole_NS.createUserGradeSubjectRole",
						ugsr);
			}
		}
	}

	public void updateUserStr(User user, String[] classIds, String[] gradeIds,
			String[] subjectIds) {
		this.updateUser(user);
		int userId = user.getUserId();
		int roleId = 44;
		Map map1 = new HashMap();
		map1.put("userId", userId);
		map1.put("roleId", roleId);
		List<UserClass> ucList = simpleDao.getForList(
				"UserClass_NS.getUserClassListByUserId", userId);
		List<UserGradeSubjectRole> ugsrList = simpleDao
		.getForList(
				"UserGradeSubjectRole_NS.getUserGradeSubjectRoleByUserIdRoleId",
				map1);
		for (Iterator<UserClass> iter = ucList.iterator(); iter.hasNext();) {
			UserClass uc = iter.next();
			Map keyMap = new HashMap();
			keyMap.put("userId", uc.getUserId());
			keyMap.put("classId", uc.getClassId());
			simpleDao.deleteEntity("UserClass_NS.deleteUserClassById", keyMap);
		}
		if(!StringUtil.isNullString(classIds)){
			for (int i = 0; i < classIds.length; i++) {
				int classId = Integer.parseInt(classIds[i]);
				UserClass userClass = new UserClass();
				userClass.setClassId(classId);
				userClass.setUserId(userId);
				userClass.setStatus(0);
				userClass.setCreateTime(new Date(System.currentTimeMillis()));
				userClass.setUpdateTime(new Date(System.currentTimeMillis()));
				this.simpleDao.createEntity("UserClass_NS.createUserClass",
						userClass);
			}
		}
		for (Iterator<UserGradeSubjectRole> iter = ugsrList.iterator(); iter
		.hasNext();) {
			UserGradeSubjectRole ugsr = iter.next();
			Map keyMap = new HashMap();
			keyMap.put("roleId", ugsr.getRoleId());
			keyMap.put("userId", ugsr.getUserId());
			keyMap.put("gradeId", ugsr.getGradeId());
			keyMap.put("subjectId", ugsr.getSubjectId());
			simpleDao.deleteEntity(
					"UserGradeSubjectRole_NS.deleteUserGradeSubjectRoleById",
					keyMap);
		}
		if(!StringUtil.isNullString(gradeIds)&&!StringUtil.isNullString(subjectIds)){
			for (int j = 0; j < gradeIds.length; j++) {
				int gradeId = Integer.parseInt(gradeIds[j]);
				for (int i = 0; i < subjectIds.length; i++) {
					int subjectId = Integer.parseInt(subjectIds[i]);
					UserGradeSubjectRole ugsr = new UserGradeSubjectRole();
					ugsr.setUserId(userId);
					ugsr.setGradeId(gradeId);
					ugsr.setRoleId(roleId);
					ugsr.setSubjectId(subjectId);
					ugsr.setStatus(0);
					ugsr.setLimitScopeId(1);
					ugsr.setCreateTime(new Date(System.currentTimeMillis()));
					ugsr.setUpdateTime(new Date(System.currentTimeMillis()));
					this.simpleDao.createEntity(
							"UserGradeSubjectRole_NS.createUserGradeSubjectRole",
							ugsr);
				}
			}
		}
	}

	public boolean checkLoginName(QueryUserCondition queryUserCondition) {
		if((Integer)simpleDao.getEntity("User_NS.checkLoginName", queryUserCondition) < 1) {
			return true;
		} else {
			return false;
		}
	}
	public List<User> getUsetByList(QueryUserCondition queryUserCondition){
		return simpleDao.getForList("User_NS.getUserByList", queryUserCondition);
		
	}
	
	public PageResult getAllUserList(QueryUserCondition queryUserCondition) {
		return simpleDao.getPageResult("User_NS.getAllUserList", "User_NS.getAllUserListCount", queryUserCondition);
	}
}
