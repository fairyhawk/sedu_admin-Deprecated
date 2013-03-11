package com.shangde.edu.res.domain;

import java.io.Serializable;

public class Picture implements Serializable{
	public static final int PIC_MAIN_PIC = 1;
	public static final int PIC_OTHER_PIC = 0;
	/** 图片主键id   */
	private int picId;
	/** 书籍外键id   */
	private int bkId;
	/** 视频外键id   */
	private int voId;
	/** 图片名称   */
	private String picName;
	/** 图片大小   */
	private String picSize;
	/** 图片类型   */
	private String picType;
	/** 是否为首页   */
	private int isIndex;
	/** 图片路径   */
	private String picUrl;
	/** 上传时间   */
	private java.util.Date creatTime;
	/** 图片描述   */
    private String picContent;
    /** 备注   */
    private String picInfo;
        
    public int getPicId(){
        return picId;
    }

    public void setPicId(int picId){
        this.picId = picId; 
    }
        
    public int getBkId(){
        return bkId;
    }

    public void setBkId(int bkId){
        this.bkId = bkId; 
    }
        
    public int getVoId(){
        return voId;
    }

    public void setVoId(int voId){
        this.voId = voId; 
    }
        
    public String getPicName(){
        return picName;
    }

    public void setPicName(String picName){
        this.picName = picName; 
    }
        
    public String getPicSize(){
        return picSize;
    }

    public void setPicSize(String picSize){
        this.picSize = picSize; 
    }
        
    public String getPicType(){
        return picType;
    }

    public void setPicType(String picType){
        this.picType = picType; 
    }
        
  
        
    public String getPicUrl(){
        return picUrl;
    }

    public void setPicUrl(String picUrl){
        this.picUrl = picUrl; 
    }
        
    public java.util.Date getCreatTime(){
        return creatTime;
    }

    public void setCreatTime(java.util.Date creatTime){
        this.creatTime = creatTime; 
    }
        
    public String getPicContent(){
        return picContent;
    }

    public void setPicContent(String picContent){
        this.picContent = picContent; 
    }
        
    public String getPicInfo(){
        return picInfo;
    }

    public void setPicInfo(String picInfo){
        this.picInfo = picInfo; 
    }

	public int getIsIndex() {
		return isIndex;
	}

	public void setIsIndex(int isIndex) {
		this.isIndex = isIndex;
	}
}
