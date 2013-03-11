package com.shangde.edu.stu.domain;

import java.io.Serializable;


/**
 * 试卷
 * @author chenshuai
 *
 */
public class Exapa implements Serializable{

    private int epId;

    private String epName;
   

	public int getEpId(){
        return epId;
    }

    public void setEpId(int epId){
        this.epId = epId; 
    }
        
   
    public String getEpName(){
        return epName;
    }

    public void setEpName(String epName){
        this.epName = epName; 
    }


	
}
