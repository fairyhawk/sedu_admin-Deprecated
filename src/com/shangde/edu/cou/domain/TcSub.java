package com.shangde.edu.cou.domain;

import java.io.Serializable;

public class TcSub implements Serializable{
    private int subjectId;
    private int tcId;
    private int id;
        

        
    public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getTcId(){
        return tcId;
    }

    public void setTcId(int tcId){
        this.tcId = tcId; 
    }
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
}
