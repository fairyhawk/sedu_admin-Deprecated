package com.shangde.edu.kb.domain;

import java.io.Serializable;

public class Chapter implements Serializable{
	//章　id
    private int chId;
    //索引
    private String chIndex;
    //学科名称
    private int cId;
    //章名称
    private String chName;
    //专业名称
    private int pId;
    //版本
    private int chVersion;
    //包含节数
    private int chStSubject;
    //创建时间
    private java.util.Date chCreatetime;
    //修改时间
    private java.util.Date chUpdatetime;
    //章说明
    private String chNote;
        
    public int getChId(){
        return chId;
    }

    public void setChId(int chId){
        this.chId = chId; 
    }
        
    public String getChIndex(){
        return chIndex;
    }

    public void setChIndex(String chIndex){
        this.chIndex = chIndex; 
    }
        
    public int getCId(){
        return cId;
    }

    public void setCId(int cId){
        this.cId = cId; 
    }
        
    public String getChName(){
        return chName;
    }

    public void setChName(String chName){
        this.chName = chName; 
    }
        
    public int getPId(){
        return pId;
    }

    public void setPId(int pId){
        this.pId = pId; 
    }
        
    public int getChVersion(){
        return chVersion;
    }

    public void setChVersion(int chVersion){
        this.chVersion = chVersion; 
    }
        
    public int getChStSubject(){
        return chStSubject;
    }

    public void setChStSubject(int chStSubject){
        this.chStSubject = chStSubject; 
    }
        
    public java.util.Date getChCreatetime(){
        return chCreatetime;
    }

    public void setChCreatetime(java.util.Date chCreatetime){
        this.chCreatetime = chCreatetime; 
    }
        
    public java.util.Date getChUpdatetime(){
        return chUpdatetime;
    }

    public void setChUpdatetime(java.util.Date chUpdatetime){
        this.chUpdatetime = chUpdatetime; 
    }

	public String getChNote() {
		return chNote;
	}

	public void setChNote(String chNote) {
		this.chNote = chNote;
	}
}
