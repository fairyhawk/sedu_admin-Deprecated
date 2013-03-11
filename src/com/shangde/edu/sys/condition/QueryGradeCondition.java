package com.shangde.edu.sys.condition;

public class QueryGradeCondition {
    private int gradeId;
        
    private int userId;
    
    private int subjectId;
    
    private int roleId;
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

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getGradeId(){
        return gradeId;
    }

    public void setGradeId(int gradeId){
        this.gradeId = gradeId;
    }
}