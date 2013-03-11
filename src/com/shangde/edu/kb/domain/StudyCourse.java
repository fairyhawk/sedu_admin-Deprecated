package com.shangde.edu.kb.domain;

import java.io.Serializable;

public class StudyCourse implements Serializable{
	//学科id
    private int cId;
    //索引
    private String cIndex;
    //学科名称
    private String cName;
    //专业名称
    private int pId;
    //版本
    private int cVersion;
    //包含章数
    private int cCtSubject;
    //创建时间
    private java.util.Date cCreatetime;
    //修改时间
    private java.util.Date cUpdatetime;
    //学科说明
    private String cNote;
    //专业名称
    private String pName;
    //状态
    private int cStatus;
    
    public int getCId(){
        return cId;
    }

    public void setCId(int cId){
        this.cId = cId; 
    }
        
    public String getCIndex(){
        return cIndex;
    }

    public void setCIndex(String cIndex){
        this.cIndex = cIndex; 
    }
        
    public String getCName(){
        return cName;
    }

    public void setCName(String cName){
        this.cName = cName; 
    }
        
    public int getPId(){
        return pId;
    }

    public void setPId(int pId){
        this.pId = pId; 
    }
        
    public int getCVersion(){
        return cVersion;
    }

    public void setCVersion(int cVersion){
        this.cVersion = cVersion; 
    }
        
    public int getCCtSubject(){
        return cCtSubject;
    }

    public void setCCtSubject(int cCtSubject){
        this.cCtSubject = cCtSubject; 
    }
        
    public java.util.Date getCCreatetime(){
        return cCreatetime;
    }

    public void setCCreatetime(java.util.Date cCreatetime){
        this.cCreatetime = cCreatetime; 
    }
        
    public java.util.Date getCUpdatetime(){
        return cUpdatetime;
    }

    public void setCUpdatetime(java.util.Date cUpdatetime){
        this.cUpdatetime = cUpdatetime; 
    }

	public String getCNote() {
		return cNote;
	}

	public void setCNote(String note) {
		cNote = note;
	}

	public String getPName() {
		return pName;
	}

	public void setPName(String name) {
		pName = name;
	}

	public int getCStatus() {
		return cStatus;
	}

	public void setCStatus(int status) {
		cStatus = status;
	}
}
