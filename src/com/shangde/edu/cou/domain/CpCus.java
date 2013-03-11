package com.shangde.edu.cou.domain;

import java.io.Serializable;

public class CpCus implements Serializable{
	public static final int CPCUS_STATUS_USABLE = 0;
	public static final int CPCUS_STAUTS_USED = 1;
    private int cusId;
    private int id;
    private int status;
    private String serialNumber;
    private Coupon coupon;
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
}
