package com.shangde.edu.exam.domain;

import java.io.Serializable;

/**
 * 试卷试题图片
 * @author chenshuai
 *
 */
public class QstPic implements Serializable{
	/**
	 * 试题ID
	 */
    private int qstId;
	/**
	 * 试题ID
	 */
    private int picId;
    /**
     * 图片名称
     */
    private String picName;
    /**
     * 图片大小
     */
    private java.lang.Object picSize;
    /**
     * 格式
     */
    private String picType;
    /**
     * 路径
     */
    private String picPath;
    /**
     * 创建时间
     */
    private java.util.Date creatTime;
        
    public int getQstId(){
        return qstId;
    }

    public void setQstId(int qstId){
        this.qstId = qstId; 
    }
        
    public int getPicId(){
        return picId;
    }

    public void setPicId(int picId){
        this.picId = picId; 
    }
        
    public String getPicName(){
        return picName;
    }

    public void setPicName(String picName){
        this.picName = picName; 
    }
        
    public java.lang.Object getPicSize(){
        return picSize;
    }

    public void setPicSize(java.lang.Object picSize){
        this.picSize = picSize; 
    }
        
    public String getPicType(){
        return picType;
    }

    public void setPicType(String picType){
        this.picType = picType; 
    }
        
    public String getPicPath(){
        return picPath;
    }

    public void setPicPath(String picPath){
        this.picPath = picPath; 
    }
        
    public java.util.Date getCreatTime(){
        return creatTime;
    }

    public void setCreatTime(java.util.Date creatTime){
        this.creatTime = creatTime; 
    }
}
