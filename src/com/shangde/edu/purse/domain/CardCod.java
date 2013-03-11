package com.shangde.edu.purse.domain;

import java.io.Serializable;

public class CardCod implements Serializable{
    private int id;
    private String email;
    private String cusName;
    private String mobile;
    private String cardId;
    private java.math.BigDecimal freightMoney;
    private int contractId;
    private java.util.Date createTime;
    private int status;
    private String operator;
    private String address;
    private String postcode;
    private String tel;
    private java.util.Date sendTime;
    private int sendConfirm;
        
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
        
    public String getCusName(){
        return cusName;
    }

    public void setCusName(String cusName){
        this.cusName = cusName; 
    }
        
    public String getMobile(){
        return mobile;
    }

    public void setMobile(String mobile){
        this.mobile = mobile; 
    }
        
    public String getCardId(){
        return cardId;
    }

    public void setCardId(String cardId){
        this.cardId = cardId; 
    }
        
    public java.math.BigDecimal getFreightMoney(){
        return freightMoney;
    }

    public void setFreightMoney(java.math.BigDecimal freightMoney){
        this.freightMoney = freightMoney; 
    }
        
    public int getContractId(){
        return contractId;
    }

    public void setContractId(int contractId){
        this.contractId = contractId; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }
        
    public String getOperator(){
        return operator;
    }

    public void setOperator(String operator){
        this.operator = operator; 
    }
        
    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address; 
    }
        
    public String getPostcode(){
        return postcode;
    }

    public void setPostcode(String postcode){
        this.postcode = postcode; 
    }
        
    public String getTel(){
        return tel;
    }

    public void setTel(String tel){
        this.tel = tel; 
    }
        
    public java.util.Date getSendTime(){
        return sendTime;
    }

    public void setSendTime(java.util.Date sendTime){
        this.sendTime = sendTime; 
    }
        
    public int getSendConfirm(){
        return sendConfirm;
    }

    public void setSendConfirm(int sendConfirm){
        this.sendConfirm = sendConfirm; 
    }
}
