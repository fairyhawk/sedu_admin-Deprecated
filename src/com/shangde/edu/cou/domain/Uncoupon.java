package com.shangde.edu.cou.domain;

import java.io.Serializable;

public class Uncoupon implements Serializable{
	public static int UNCP_STATUS_USABLE = 0;
	public static int UNCP_STATUS_USED = 1;
    private int uncpId;
    private int id;
    private String email;
    private String mobile;
    private String serialNumber;
    private int status;
        
    public int getUncpId(){
        return uncpId;
    }

    public void setUncpId(int uncpId){
        this.uncpId = uncpId; 
    }
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email; 
    }
        
    public String getMobile(){
        return mobile;
    }

    public void setMobile(String mobile){
        this.mobile = mobile; 
    }
        
    public String getSerialNumber(){
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber){
        this.serialNumber = serialNumber; 
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
