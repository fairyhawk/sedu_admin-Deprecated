package com.shangde.edu.sys.domain;

import java.io.Serializable;

public class UserGradeSubjectRole implements Serializable{
    private int roleId;
    private int userId;
    private int gradeId;
    private int subjectId;
    private int limitScopeId;
    private int status;
    private java.util.Date createTime;
    private java.util.Date updateTime;
        
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
        
    public int getLimitScopeId(){
        return limitScopeId;
    }

    public void setLimitScopeId(int limitScopeId){
        this.limitScopeId = limitScopeId; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public java.util.Date getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime){
        this.updateTime = updateTime; 
    }
}
