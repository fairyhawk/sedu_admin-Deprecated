package com.shangde.edu.tk.domain;

import java.io.Serializable;

import com.shangde.edu.tk.dto.NomalTaskDTO;

public class TaskCus implements Serializable{
    private int id;
    private int taskId;
    private int cusId;
    private int isComplete;
    private int isExpired;
    private java.util.Date acceptTime;
    private java.util.Date completeTime;
    private int  isReceived;
    
    private NomalTaskDTO task;
        
    public int getIsReceived() {
		return isReceived;
	}

	public void setIsReceived(int isReceived) {
		this.isReceived = isReceived;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getTaskId(){
        return taskId;
    }

    public void setTaskId(int taskId){
        this.taskId = taskId; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public int getIsComplete(){
        return isComplete;
    }

    public void setIsComplete(int isComplete){
        this.isComplete = isComplete; 
    }
        
    public int getIsExpired(){
        return isExpired;
    }

    public void setIsExpired(int isExpired){
        this.isExpired = isExpired; 
    }
        
    public java.util.Date getAcceptTime(){
        return acceptTime;
    }

    public void setAcceptTime(java.util.Date acceptTime){
        this.acceptTime = acceptTime; 
    }
        
    public java.util.Date getCompleteTime(){
        return completeTime;
    }

    public void setCompleteTime(java.util.Date completeTime){
        this.completeTime = completeTime; 
    }

	public NomalTaskDTO getTask() {
		return task;
	}

	public void setTask(NomalTaskDTO task) {
		this.task = task;
	}

	public boolean equals(Object obj) {
		TaskCus t = (TaskCus)obj;
		return new Integer(this.getId()).equals(new Integer(t.getId()));
	}

	public int hashCode() {
		return this.getId() ;
	}
	
	

}
