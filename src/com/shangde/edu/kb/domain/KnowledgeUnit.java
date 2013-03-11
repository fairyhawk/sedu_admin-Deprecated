package com.shangde.edu.kb.domain;

import java.io.Serializable;

public class KnowledgeUnit implements Serializable{
	//知识单元id
    private int kId;
   //索引
    private String kIndex;
   //知识单元名称
    private String kName;
    //节名称
    private int sId;
    //章名称
    private int chId;
    //学科名称
    private int cId;
    //专业名称
    private int pId;
    //版本
    private int kVersion;
    //包含知识点数
    private int kKlNumber;
    //创建时间
    private java.util.Date kCreateTime;
    //修改时间
    private java.util.Date kModifyTime;
    //备注
    private String kNote;
    //知识点级别
    private int kType;
    //难度名称
    private int dId;
        
    public int getKId(){
        return kId;
    }

    public void setKId(int kId){
        this.kId = kId; 
    }
        
    public String getKIndex(){
        return kIndex;
    }

    public void setKIndex(String kIndex){
        this.kIndex = kIndex; 
    }
        
    public String getKName(){
        return kName;
    }

    public void setKName(String kName){
        this.kName = kName; 
    }
        
    public int getSId(){
        return sId;
    }

    public void setSId(int sId){
        this.sId = sId; 
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
        
    public int getKVersion(){
        return kVersion;
    }

    public void setKVersion(int kVersion){
        this.kVersion = kVersion; 
    }
        
    public int getKKlNumber(){
        return kKlNumber;
    }

    public void setKKlNumber(int kKlNumber){
        this.kKlNumber = kKlNumber; 
    }
        
    public java.util.Date getKCreateTime(){
        return kCreateTime;
    }

    public void setKCreateTime(java.util.Date kCreateTime){
        this.kCreateTime = kCreateTime; 
    }
        
    public java.util.Date getKModifyTime(){
        return kModifyTime;
    }

    public void setKModifyTime(java.util.Date kModifyTime){
        this.kModifyTime = kModifyTime; 
    }
        
    public String getKNote(){
        return kNote;
    }

    public void setKNote(String kNote){
        this.kNote = kNote; 
    }
        
    public int getKType(){
        return kType;
    }

    public void setKType(int kType){
        this.kType = kType; 
    }

	public int getDId() {
		return dId;
	}

	public void setDId(int id) {
		dId = id;
	}
}
