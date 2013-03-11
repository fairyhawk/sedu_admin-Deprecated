package com.shangde.edu.dis.domain;

import java.io.Serializable;

public class UserDis implements Serializable{
    private int id;
    private int disId;
    private int cusId;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getDisId(){
        return disId;
    }

    public void setDisId(int disId){
        this.disId = disId; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
}
