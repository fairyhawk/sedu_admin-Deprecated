package com.shangde.edu.sys.domain;

import java.io.Serializable;

public class Grade implements Serializable{
	
	public static final int GRADE_DEFAULT_STATUS = 0;
	public static final int GRADE_FREEZE_STATUS = 1;
	public static final int GRADE_DELETE_STATUS = 2;
	/**入学年份id*/
    private int gradeId;
    /**入学年份*/
    private String gradeName;
    /**状态*/
    private int status;
    /**创建时间*/
    private java.util.Date createTime;
    /**修改时间*/
    private java.util.Date updateTime;
        
    public int getGradeId(){
        return gradeId;
    }

    public void setGradeId(int gradeId){
        this.gradeId = gradeId; 
    }
        
    public String getGradeName(){
        return gradeName;
    }

    public void setGradeName(String gradeName){
        this.gradeName = gradeName; 
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
