package com.shangde.edu.finance.domain;

import java.io.Serializable;

public class Account implements Serializable{
	 /** 注册用户id   */
	private int cusId;
	 /** 账户id   */
    private int id;
    /** 金额  */
    private java.lang.Object totolPrice;
        
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
        
    public java.lang.Object getTotolPrice(){
        return totolPrice;
    }

    public void setTotolPrice(java.lang.Object totolPrice){
        this.totolPrice = totolPrice; 
    }
}
