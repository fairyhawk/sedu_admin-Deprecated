package com.shangde.edu.kb.domain;

import java.io.Serializable;

public class Section implements Serializable{
	//节id
    private int sId;
    //索引
    private String sIndex;
    //节名称
    private String sName;
    //章名称
    private int chId;
    //学科名称
    private int cId;
    //专业名称
    private int pId;
    //版本
    private int sVersion;
    //包含知识单元数
    private int sKSubject;
    //创建时间
    private java.util.Date sCreatetime;
    //修改时间
    private java.util.Date sUpdatetime;
    //节说明
    private String sNote;
        
    public int getSId(){
        return sId;
    }

    public void setSId(int sId){
        this.sId = sId; 
    }
        
    public String getSIndex(){
        return sIndex;
    }

    public void setSIndex(String sIndex){
        this.sIndex = sIndex; 
    }
        
    public String getSName(){
        return sName;
    }

    public void setSName(String sName){
        this.sName = sName; 
    }
        
    public int getChId(){
        return chId;
    }

    public void setChId(int chId){
        this.chId = chId; 
    }
        
    public int getCId(){
        return cId;
    }

    public void setCId(int cId){
        this.cId = cId; 
    }
        
    public int getPId(){
        return pId;
    }

    public void setPId(int pId){
        this.pId = pId; 
    }
        
    public int getSVersion(){
        return sVersion;
    }

    public void setSVersion(int sVersion){
        this.sVersion = sVersion; 
    }
        
    public int getSKSubject(){
        return sKSubject;
    }

    public void setSKSubject(int sKSubject){
        this.sKSubject = sKSubject; 
    }
        
    public java.util.Date getSCreatetime(){
        return sCreatetime;
    }

    public void setSCreatetime(java.util.Date sCreatetime){
        this.sCreatetime = sCreatetime; 
    }
        
    public java.util.Date getSUpdatetime(){
        return sUpdatetime;
    }

    public void setSUpdatetime(java.util.Date sUpdatetime){
        this.sUpdatetime = sUpdatetime; 
    }

	public String getSNote() {
		return sNote;
	}

	public void setSNote(String note) {
		sNote = note;
	}
}
