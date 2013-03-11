package com.shangde.edu.finance.domain;

import java.io.Serializable;

public class ChildContract implements Serializable{
	
	//生成子订单金额
	public static final float GEN_MONEY_AMOUNT = 0.02f;
	
    private int id;
    private String contractId;
    private String childContractId;
    private int cusId;
    private java.util.Date createTime;
    private java.util.Date payTime;
    private int payType;
    private int status;
    private java.math.BigDecimal money;
    private int ctId;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public String getContractId(){
        return contractId;
    }

    public void setContractId(String contractId){
        this.contractId = contractId; 
    }
        
    public String getChildContractId(){
        return childContractId;
    }

    public void setChildContractId(String childContractId){
        this.childContractId = childContractId; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public java.util.Date getPayTime(){
        return payTime;
    }

    public void setPayTime(java.util.Date payTime){
        this.payTime = payTime; 
    }
        
    public int getPayType(){
        return payType;
    }

    public void setPayType(int payType){
        this.payType = payType; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }
        
    public java.math.BigDecimal getMoney(){
        return money;
    }

    public void setMoney(java.math.BigDecimal money){
        this.money = money; 
    }

	public int getCtId() {
		return ctId;
	}

	public void setCtId(int ctId) {
		this.ctId = ctId;
	}
    
}
