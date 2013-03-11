package com.shangde.edu.sys.condition;

import com.shangde.common.vo.PageQuery;

public class QueryRoleCondition extends PageQuery{
    /**角色id*/
    private int roleId;
    /**角色类别id*/
    private int roleTypeId;
    /**角色使用范围id*/
    private int roleApplyScopeId;
    
    private int userId;
    
    private int gradeId;
    
    private int subjectId;
        
    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getRoleId(){
        return roleId;
    }

    public void setRoleId(int roleId){
        this.roleId = roleId;
    }

    public int getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(int roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    public int getRoleApplyScopeId() {
        return roleApplyScopeId;
    }

    public void setRoleApplyScopeId(int roleApplyScopeId) {
        this.roleApplyScopeId = roleApplyScopeId;
    }
}