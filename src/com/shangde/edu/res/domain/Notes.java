package com.shangde.edu.res.domain;

import java.io.Serializable;

import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cus.domain.Customer;

public class Notes implements Serializable{
	private int cusId;
    private int pointId;
    private int noteId;
    private String noteInfo;
    private String noteHTML;
    private java.util.Date updateTime;
    private Kpoint kpoint;
    private Vedio vedio;
    private int number;
    private Customer customer;
    private String recordTime;
    private int flexIndex;
    private Notes nextNotes;
        
    public int getPointId(){
        return pointId;
    }

    public void setPointId(int pointId){
        this.pointId = pointId; 
    }
        
    public int getNoteId(){
        return noteId;
    }

    public void setNoteId(int noteId){
        this.noteId = noteId; 
    }
        
    public String getNoteInfo(){
        return noteInfo;
    }

    public void setNoteInfo(String noteInfo){
        this.noteInfo = noteInfo; 
    }
        
    public java.util.Date getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime){
        this.updateTime = updateTime; 
    }

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public Kpoint getKpoint() {
		return kpoint;
	}

	public void setKpoint(Kpoint kpoint) {
		this.kpoint = kpoint;
	}

	public Vedio getVedio() {
		return vedio;
	}

	public void setVedio(Vedio vedio) {
		this.vedio = vedio;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getNoteHTML() {
		return noteHTML;
	}

	public void setNoteHTML(String noteHTML) {
		this.noteHTML = noteHTML;
	}

	public int getFlexIndex() {
		return flexIndex;
	}

	public void setFlexIndex(int flexIndex) {
		this.flexIndex = flexIndex;
	}

	public Notes getNextNotes() {
		return nextNotes;
	}

	public void setNextNotes(Notes nextNotes) {
		this.nextNotes = nextNotes;
	}
}
