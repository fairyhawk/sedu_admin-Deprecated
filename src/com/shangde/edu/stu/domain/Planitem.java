package com.shangde.edu.stu.domain;

import java.io.Serializable;

public class Planitem implements Serializable{
    private int pitemId;
    private String itemtitle;
    private java.util.Date itemdate;
    private int planId;
    private int cusId;
        
    public int getPitemId(){
        return pitemId;
    }

    public void setPitemId(int pitemId){
        this.pitemId = pitemId; 
    }
        
    public String getItemtitle(){
        return itemtitle;
    }

    public void setItemtitle(String itemtitle){
        this.itemtitle = itemtitle; 
    }
        
    public java.util.Date getItemdate(){
        return itemdate;
    }

    public void setItemdate(java.util.Date itemdate){
        this.itemdate = itemdate; 
    }
        
    public int getPlanId(){
        return planId;
    }

    public void setPlanId(int planId){
        this.planId = planId; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
}
