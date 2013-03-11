package com.shangde.edu.exam.domain;

import java.io.Serializable;

/**
 * 试卷评论记录
 * @author chenshuai
 *
 */
public class Reviews implements Serializable{
	/**
	 * 试卷ID
	 */
    private int epId;
    /**
     * 试卷评论ID
     */
    private int rvId;
    /**
     * 评论内容
     */
    private String rvInfo;
    /**
     * 评论区间
     */
    private int evaType;
        
    public int getEpId(){
        return epId;
    }

    public void setEpId(int epId){
        this.epId = epId; 
    }
        
    public int getRvId(){
        return rvId;
    }

    public void setRvId(int rvId){
        this.rvId = rvId; 
    }
        
    public String getRvInfo(){
        return rvInfo;
    }

    public void setRvInfo(String rvInfo){
        this.rvInfo = rvInfo; 
    }
        
    public int getEvaType(){
        return evaType;
    }

    public void setEvaType(int evaType){
        this.evaType = evaType; 
    }
}
