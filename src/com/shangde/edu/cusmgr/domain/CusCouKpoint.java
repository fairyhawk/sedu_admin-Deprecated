package com.shangde.edu.cusmgr.domain;

import java.io.Serializable;
import java.util.Date;

import com.shangde.edu.cou.domain.Kpoint;

@SuppressWarnings("serial")
public class CusCouKpoint implements Serializable{
	
	public static final int CUSCOUKPOINT_DEFAULT_STATUS = 0;
	public static final int CUSCOUKPOINT_FREEZE_STATUS = 1;
	public static final int CUSCOUKPOINT_DELETE_STATUS = 2;
	
    private int courseId;
    private int cusId;
    private int pointId;
    private int rdState;
    private int id;
    private Date lastTime;
    
    private Kpoint kpoint;
        
    public int getCourseId(){
        return courseId;
    }

    public void setCourseId(int courseId){
        this.courseId = courseId; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public int getPointId(){
        return pointId;
    }

    public void setPointId(int pointId){
        this.pointId = pointId; 
    }
        
    public int getRdState(){
        return rdState;
    }

    public void setRdState(int rdState){
        this.rdState = rdState; 
    }
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }

	public Kpoint getKpoint() {
		return kpoint;
	}

	public void setKpoint(Kpoint kpoint) {
		this.kpoint = kpoint;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
}
