package com.shangde.edu.cms.domain;

import java.io.Serializable;

public class Template implements Serializable{
    private int tmpId;
    private String tmpName;
    private String tmpType;
    private String tmpContent;
    private int isUse;
    private String tmpContent_bak ;
        
    public String getTmpContent_bak() {
		return tmpContent_bak;
	}

	public void setTmpContent_bak(String tmpContent_bak) {
		this.tmpContent_bak = tmpContent_bak;
	}

	public int getTmpId(){
        return tmpId;
    }

    public void setTmpId(int tmpId){
        this.tmpId = tmpId; 
    }
    public void setTmpId(String tmpId1){
    	int tmpId=Integer.parseInt(tmpId1);
        this.tmpId = tmpId; 
    }    
    public String getTmpName(){
        return tmpName;
    }

    public void setTmpName(String tmpName){
        this.tmpName = tmpName; 
    }
        
    public String getTmpType(){
        return tmpType;
    }

    public void setTmpType(String tmpType){
        this.tmpType = tmpType; 
    }
        
    public String getTmpContent(){
        return tmpContent;
    }

    public void setTmpContent(String tmpContent){
        this.tmpContent = tmpContent; 
    }
        
    public int getIsUse(){
        return isUse;
    }

    public void setIsUse(int isUse){
        this.isUse = isUse; 
    }
}
