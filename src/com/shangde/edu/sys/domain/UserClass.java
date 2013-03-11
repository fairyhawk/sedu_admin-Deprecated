package com.shangde.edu.sys.domain;

import java.io.Serializable;

public class UserClass implements Serializable{
	/**用户id*/
    private int userId;
    /**班级id*/
    private int classId;
    /**状态*/
    private int status;
    /**创建时间*/
    private java.util.Date createTime;
    /**修改时间*/
    private java.util.Date updateTime;
        
    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId; 
    }
        
    public int getClassId(){
        return classId;
    }

    public void setClassId(int classId){
        this.classId = classId; 
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
