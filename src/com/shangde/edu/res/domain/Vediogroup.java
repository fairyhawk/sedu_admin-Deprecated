package com.shangde.edu.res.domain;

import java.io.Serializable;

public class Vediogroup implements Serializable{
	/** 视频组主键id   */
    private int vgId;
    /** 视频组名称   */
    private String vgName;
    /** 视频组类型   */
    private String vgType;
    /** 创建时间   */
    private java.util.Date vgTime;
    /** 备注   */
    private String vgNfo;
        
    public int getVgId(){
        return vgId;
    }

    public void setVgId(int vgId){
        this.vgId = vgId; 
    }
        
    public String getVgName(){
        return vgName;
    }

    public void setVgName(String vgName){
        this.vgName = vgName; 
    }
        
    public String getVgType(){
        return vgType;
    }

    public void setVgType(String vgType){
        this.vgType = vgType; 
    }
        
    public java.util.Date getVgTime(){
        return vgTime;
    }

    public void setVgTime(java.util.Date vgTime){
        this.vgTime = vgTime; 
    }
        
    public String getVgNfo(){
        return vgNfo;
    }

    public void setVgNfo(String vgNfo){
        this.vgNfo = vgNfo; 
    }
}
