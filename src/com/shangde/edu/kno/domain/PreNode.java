package com.shangde.edu.kno.domain;

import java.io.Serializable;

public class PreNode implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int preNodeId;
    private String preNodeName;
    private int preId;
    private int sortId;
    private int relCount;
        
    public int getRelCount() {
		return relCount;
	}

	public void setRelCount(int relCount) {
		this.relCount = relCount;
	}

	public int getPreNodeId(){
        return preNodeId;
    }

    public void setPreNodeId(int preNodeId){
        this.preNodeId = preNodeId; 
    }
        
    public String getPreNodeName(){
        return preNodeName;
    }

    public void setPreNodeName(String preNodeName){
        this.preNodeName = preNodeName; 
    }
        
        
    public int getSortId(){
        return sortId;
    }

    public void setSortId(int sortId){
        this.sortId = sortId; 
    }

	public int getPreId() {
		return preId;
	}

	public void setPreId(int preId) {
		this.preId = preId;
	}
}
