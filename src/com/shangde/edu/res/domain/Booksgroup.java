package com.shangde.edu.res.domain;

import java.io.Serializable;

public class Booksgroup implements Serializable{
	/** 书籍组主键id   */
	private int bgId;
	/** 书籍组名称   */
	private String bgName;
	/** 书籍组类型   */
	private String bgType;
	/** 创建时间   */
	private java.util.Date createTime;
	/** 备注   */
	private String bgInfo;
        
    public int getBgId(){
        return bgId;
    }

    public void setBgId(int bgId){
        this.bgId = bgId; 
    }
        
    public String getBgName(){
        return bgName;
    }

    public void setBgName(String bgName){
        this.bgName = bgName; 
    }
        
    public String getBgType(){
        return bgType;
    }

    public void setBgType(String bgType){
        this.bgType = bgType; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public String getBgInfo(){
        return bgInfo;
    }

    public void setBgInfo(String bgInfo){
        this.bgInfo = bgInfo; 
    }
}
