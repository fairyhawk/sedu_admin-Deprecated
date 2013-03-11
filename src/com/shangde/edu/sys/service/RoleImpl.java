package com.shangde.edu.sys.service;

import java.util.List;
import java.util.Set;
import java.util.Date;

import bsh.StringUtil;

import com.mysql.jdbc.StringUtils;
import com.shangde.common.service.BaseService;
import com.shangde.edu.sys.condition.QueryGradeCondition;
import com.shangde.edu.sys.condition.QueryRoleCondition;
import com.shangde.edu.sys.condition.QuerySubjectCondition;
import com.shangde.edu.sys.domain.Grade;
import com.shangde.edu.sys.domain.Role;
import com.shangde.edu.sys.domain.RoleFunction;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.UserGradeSubjectRole;

/**
 * Role对象操作实现类
 *
 * @author guoqiang.liu
 */
@SuppressWarnings("unchecked")
public class RoleImpl extends BaseService implements IRole {
	public java.lang.Integer addRole(Role role) {
		return simpleDao.createEntity("Role_NS.createRole", role);
	}

	public void delRoleById(int roleId) {
		simpleDao.deleteEntity("RoleFunction_NS.deleteRoleFunctionByRoleId", roleId);
		delUserGradeSubjectRoleByRole(roleId);
		simpleDao.deleteEntity("Role_NS.deleteRoleById", roleId);
	}

	public void updateRole(Role role) {
		simpleDao.updateEntity("Role_NS.updateRole", role);
	}

	public Role getRoleById(int roleId) {
		return simpleDao.getEntity("Role_NS.getRoleById", roleId);
	}

	public List<Role> getRoleList(QueryRoleCondition queryRoleCondition) {
		return simpleDao.getForList("Role_NS.getRoleList", queryRoleCondition);
	}

	public List<Role> getScopeRoleList() {
		QueryRoleCondition queryRoleCondition = new QueryRoleCondition();
		queryRoleCondition.setRoleApplyScopeId(Role.ROLE_APPLY_SCOPE_ALL);
		return getRoleList(queryRoleCondition);
	}

	public void updateRoleFunction(int roleId, Set<Integer> functionIdList) {
		simpleDao.deleteEntity("RoleFunction_NS.deleteRoleFunctionByRoleId", roleId);
		if (functionIdList != null) {
			for (int functionId : functionIdList) {
				RoleFunction roleFunction = new RoleFunction();
				roleFunction.setCreateTime(new Date(System.currentTimeMillis()));
				roleFunction.setFunctionId(functionId);
				roleFunction.setRoleId(roleId);
				roleFunction.setStatus(RoleFunction.ROLE_FUNCTION_STATUS_DEFAULT);
				simpleDao.createEntity("RoleFunction_NS.createRoleFunction", roleFunction);
			}
		}
	}

	public Role getRoleInfo(int userId, int roleId) {
		// TODO Auto-generated method stub
		//定义角色
		Role ttrole = this.getRoleById(roleId);
		//得到角色对应的专业，根据userId和roleId
		QuerySubjectCondition qsc = new QuerySubjectCondition();
		qsc.setRoleId(ttrole.getRoleId());
		qsc.setUserId(userId);
		List<Subject> subjectList = this.simpleDao.getForList("Subject_NS.getSubjectListByRoleId", qsc);
		//得到学科对应的入学年份，根据专业
		for(Subject ss:subjectList){
			QueryGradeCondition qgc = new QueryGradeCondition();
			qgc.setSubjectId(ss.getSubjectId());
			qgc.setUserId(userId);
			qgc.setRoleId(ttrole.getRoleId());
			List<Grade> gradeList = this.simpleDao.getForList("Grade_NS.getGradeListBySubjectId",qgc);
			ss.setGradeList(gradeList);
		}
		ttrole.setSubjectList(subjectList);
		return ttrole;
	}

	public void updateRefRole( String userId,String roleId,String gradeIds) {
		// TODO Auto-generated method stub
		//清空所有专业下选项
		this.delRoleRef(userId, roleId);
		//设置选中项
		this.addRoleRef(userId, roleId, gradeIds);
	}

	public void addRoleRef(String userId,String roleId,String gradeIds){
		if(userId == null  ||  "".equals(userId.trim())  ||
		   roleId == null ||  "".equals(roleId.trim()) ) {
			return;
		}
        UserGradeSubjectRole ugsr = new UserGradeSubjectRole();
        ugsr.setRoleId(new Integer(roleId));
        ugsr.setUserId(new Integer(userId));
        ugsr.setSubjectId(new Integer(1));
        ugsr.setGradeId(new Integer(1));
        ugsr.setCreateTime(new Date());
        ugsr.setStatus(0);
        ugsr.setUpdateTime(new Date());
        //部门1或者个人2
        ugsr.setLimitScopeId(1);
        this.simpleDao.createEntity("UserGradeSubjectRole_NS.createUserGradeSubjectRole", ugsr);
    
    
	}
	public void delRoleRef(String userId,String roleId){
		java.util.Map keyMap = new java.util.HashMap();
		keyMap.put("roleId",roleId);
		keyMap.put("userId",userId);
		List<UserGradeSubjectRole> ugsrList = this.simpleDao.getForList("UserGradeSubjectRole_NS.getUserGradeSubjectRoleByUserIdRoleId", keyMap);
		for(UserGradeSubjectRole ugsr:ugsrList){
			java.util.Map keyMap1 = new java.util.HashMap();
			keyMap1.put("roleId",ugsr.getRoleId());
			keyMap1.put("userId",ugsr.getUserId());
			keyMap1.put("gradeId", ugsr.getGradeId());
			keyMap1.put("subjectId", ugsr.getSubjectId());
			this.simpleDao.deleteEntity("UserGradeSubjectRole_NS.deleteUserGradeSubjectRoleById", keyMap1);
		}
	}
	
	public void delUserGradeSubjectRoleByRole(int roledId) {
		simpleDao.deleteEntity("UserGradeSubjectRole_NS.deleteUserGradeSubjectRoleByRole", roledId);
	}
}

