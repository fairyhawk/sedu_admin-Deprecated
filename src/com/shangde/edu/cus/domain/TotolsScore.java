package com.shangde.edu.cus.domain;

import java.io.Serializable;

public class TotolsScore implements Serializable{
	/**
	 * 积分ID
	 */
	private int tsId;
	/**
	 * 用户ID
	 */
    private int cusId;
	/**
	 * 兑换积分　
	 */
    private int tsCurrent;
	/**
	 * 成长积分（经验值）
	 */
    private int tsAction;
    
    /**
     * 用户信息
     */
    private Customer customer;
        
    public int getTsId(){
        return tsId;
    }

    public void setTsId(int tsId){
        this.tsId = tsId; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public int getTsCurrent(){
        return tsCurrent;
    }

    public void setTsCurrent(int tsCurrent){
        this.tsCurrent = tsCurrent; 
    }
        
    public int getTsAction(){
        return tsAction;
    }

    public void setTsAction(int tsAction){
        this.tsAction = tsAction; 
    }

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
