package com.shangde.edu.purse.domain;

import java.io.Serializable;

public class CardCode implements Serializable{
    private int id;
    private String code;
    private String password;
    private int cardType;
    private int status;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code; 
    }
        
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password; 
    }
        
    public int getCardType(){
        return cardType;
    }

    public void setCardType(int cardType){
        this.cardType = cardType; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }
}
