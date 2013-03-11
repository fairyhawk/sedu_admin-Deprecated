package com.shangde.edu.exam.domain;

import java.io.Serializable;

public class QstKb implements Serializable{
	/**
	 * 表id
	 */
    private int id;
    /**
     * 知识库id
     */
    private int kId;
    /**
     * 试题id
     */
    private int qstId;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getKId(){
        return kId;
    }

    public void setKId(int kId){
        this.kId = kId; 
    }
        
    public int getQstId(){
        return qstId;
    }

    public void setQstId(int qstId){
        this.qstId = qstId; 
    }
}
