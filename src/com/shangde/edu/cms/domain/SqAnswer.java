package com.shangde.edu.cms.domain;

import java.io.Serializable;

public class SqAnswer implements Serializable{
    private int asId;
    private int sqId;
    private String asContent;
    private String email;
        
    public int getAsId(){
        return asId;
    }

    public void setAsId(int asId){
        this.asId = asId; 
    }
        
    public int getSqId(){
        return sqId;
    }

    public void setSqId(int sqId){
        this.sqId = sqId; 
    }
        
    public String getAsContent(){
        return asContent;
    }

    public void setAsContent(String asContent){
        this.asContent = asContent; 
    }
        
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email; 
    }
}
