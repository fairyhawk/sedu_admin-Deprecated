package com.shangde.edu.sys.condition;

public class QueryUserGradeSubjectRoleCondition {
    private int roleId;
    private int userId;
    private int gradeId;
    private int subjectId;
        
    public int getRoleId(){
        return roleId;
    }

    public void setRoleId(int roleId){
        this.roleId = roleId;
    }
    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }
    public int getGradeId(){
        return gradeId;
    }

    public void setGradeId(int gradeId){
        this.gradeId = gradeId;
    }
    public int getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId;
    }
}