package com.shangde.edu.cus.domain;

import java.io.Serializable;

public class LoginRecord implements Serializable{
    private int cusId;
    private String loginIp;
    private java.util.Date loginTime;
    private String address;
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public String getLoginIp(){
        return loginIp;
    }

    public void setLoginIp(String loginIp){
        this.loginIp = loginIp; 
    }
        
    public java.util.Date getLoginTime(){
        return loginTime;
    }

    public void setLoginTime(java.util.Date loginTime){
        this.loginTime = loginTime; 
    }
        
    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address; 
    }
}
