package com.shangde.edu.cms.domain;

import java.io.Serializable;

import org.apache.struts2.json.annotations.JSON;

public class Commentfrom implements Serializable{
    private int cfId;
    private String cfName;
    private int cfState;
    private String cfInfo;
    private java.util.Date createTime;
    private java.util.Date updateTime;
        
    public int getCfId(){
        return cfId;
    }

    public void setCfId(int cfId){
        this.cfId = cfId; 
    }
        
    public String getCfName(){
        return cfName;
    }

    public void setCfName(String cfName){
        this.cfName = cfName; 
    }
        
    public int getCfState(){
        return cfState;
    }

    public void setCfState(int cfState){
        this.cfState = cfState; 
    }
        
    public String getCfInfo(){
        return cfInfo;
    }

    public void setCfInfo(String cfInfo){
        this.cfInfo = cfInfo; 
    }

	@JSON(format="yyyy-MM-dd")
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }

	@JSON(format="yyyy-MM-dd")
    public java.util.Date getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime){
        this.updateTime = updateTime; 
    }
}
