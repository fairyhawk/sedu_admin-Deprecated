package com.shangde.edu.kb.domain;

import java.io.Serializable;

public class Professional implements Serializable{
	//专业id
    private int pId;
    //索引
    private String pIndex;
    //专业名称
    private String pName;
    //学科数量
    private int pCSubject;
    //版本
    private int pVersion;
    //创建时间
    private java.util.Date pCreatetime;
    //修改时间
    private java.util.Date pUpdatetime;
    //专业说明
    private String pNote;
        
    public int getPId(){
        return pId;
    }

    public void setPId(int pId){
        this.pId = pId; 
    }
        
    public String getPIndex(){
        return pIndex;
    }

    public void setPIndex(String pIndex){
        this.pIndex = pIndex; 
    }
        
    public String getPName(){
        return pName;
    }

    public void setPName(String pName){
        this.pName = pName; 
    }
        
    public int getPCSubject(){
        return pCSubject;
    }

    public void setPCSubject(int pCSubject){
        this.pCSubject = pCSubject; 
    }
        
    public int getPVersion(){
        return pVersion;
    }

    public void setPVersion(int pVersion){
        this.pVersion = pVersion; 
    }
        
    public java.util.Date getPCreatetime(){
        return pCreatetime;
    }

    public void setPCreatetime(java.util.Date pCreatetime){
        this.pCreatetime = pCreatetime; 
    }
        
    public java.util.Date getPUpdatetime(){
        return pUpdatetime;
    }

    public void setPUpdatetime(java.util.Date pUpdatetime){
        this.pUpdatetime = pUpdatetime; 
    }

	public String getPNote() {
		return pNote;
	}

	public void setPNote(String note) {
		pNote = note;
	}
}
