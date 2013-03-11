package com.shangde.edu.test.condition;

public class QuerySubjectCondition {
    private int subjectId;
    
    private int roleId;
    
	private int userId;
        
    public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId;
    }

}