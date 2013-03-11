package com.shangde.edu.sys.domain;

import java.io.Serializable;

public class ClassT implements Serializable{
	/**课程Id*/
    private int classId;
    /**组Id*/
    private int groupId;
    /**入学年份Id*/
    private int gradeId;
    /**课程名称*/
    private String className;
    /**修改时间*/
    private java.util.Date updateTime;
    /**创建时间*/
    private java.util.Date createTime;
    /**状态id*/
    private int status;
        
    public int getClassId(){
        return classId;
    }

    public void setClassId(int classId){
        this.classId = classId; 
    }
        
    public int getGroupId(){
        return groupId;
    }

    public void setGroupId(int groupId){
        this.groupId = groupId; 
    }
        
    public int getGradeId(){
        return gradeId;
    }

    public void setGradeId(int gradeId){
        this.gradeId = gradeId; 
    }
        
    public String getClassName(){
        return className;
    }

    public void setClassName(String className){
        this.className = className; 
    }
        
    public java.util.Date getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime){
        this.updateTime = updateTime; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }
}
