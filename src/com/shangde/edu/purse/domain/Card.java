package com.shangde.edu.purse.domain;

import java.io.Serializable;

public class Card implements Serializable{
    private int id;
    private String cardName;
    private int cardType;
    private java.util.Date endTime;
    private String operator;
    private String remark;
    private int status;
    private java.math.BigDecimal money;
    private int cardSum;
    private String cardUrl;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public String getCardName(){
        return cardName;
    }

    public void setCardName(String cardName){
        this.cardName = cardName; 
    }
        
    public int getCardType(){
        return cardType;
    }

    public void setCardType(int cardType){
        this.cardType = cardType; 
    }
        
    public java.util.Date getEndTime(){
        return endTime;
    }

    public void setEndTime(java.util.Date endTime){
        this.endTime = endTime; 
    }
        
    public String getOperator(){
        return operator;
    }

    public void setOperator(String operator){
        this.operator = operator; 
    }
        
    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark; 
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
        
    public int getCardSum(){
        return cardSum;
    }

    public void setCardSum(int cardSum){
        this.cardSum = cardSum; 
    }
        
    public String getCardUrl(){
        return cardUrl;
    }

    public void setCardUrl(String cardUrl){
        this.cardUrl = cardUrl; 
    }
}
