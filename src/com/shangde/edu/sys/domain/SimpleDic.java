package com.shangde.edu.sys.domain;

import java.io.Serializable;

public class SimpleDic implements Serializable{
	/**字典id*/
    private int dicId;
    /**表名*/
    private String tableName;
    /**字段名*/
    private String columnName;
    /**字段名称*/
    private String dicName;
    /**状态*/
    private int status;
    /**创建时间*/
    private java.util.Date createTime;
    /**修改时间*/
    private java.util.Date updateTime;
        
    public int getDicId(){
        return dicId;
    }

    public void setDicId(int dicId){
        this.dicId = dicId; 
    }
        
    public String getTableName(){
        return tableName;
    }

    public void setTableName(String tableName){
        this.tableName = tableName; 
    }
        
    public String getColumnName(){
        return columnName;
    }

    public void setColumnName(String columnName){
        this.columnName = columnName; 
    }
        
    public String getDicName(){
        return dicName;
    }

    public void setDicName(String dicName){
        this.dicName = dicName; 
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
