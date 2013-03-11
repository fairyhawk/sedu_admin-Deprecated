package com.shangde.edu.finance.domain;

import java.io.Serializable;

import com.shangde.edu.sys.domain.User;

public class Cod implements Serializable{
	/**备注*/
    private String codRemark;
    /**说明内容*/
    private String codContent;
    /**审核人的id*/
    private int codAuditId;
    /**审核时间*/
    private java.util.Date codTime;
    /**货到付款的状态*/
    private int codStatus;
    /**订单id*/
    private int codCtId;
    /**主键id*/
    private int codId;
    /**后台用户*/
    private User user;
        
    public String getCodRemark(){
        return codRemark;
    }

    public void setCodRemark(String codRemark){
        this.codRemark = codRemark; 
    }
        
    public String getCodContent(){
        return codContent;
    }

    public void setCodContent(String codContent){
        this.codContent = codContent; 
    }
        
    public int getCodAuditId(){
        return codAuditId;
    }

    public void setCodAuditId(int codAuditId){
        this.codAuditId = codAuditId; 
    }
        
    public java.util.Date getCodTime(){
        return codTime;
    }

    public void setCodTime(java.util.Date codTime){
        this.codTime = codTime; 
    }
        
    public int getCodStatus(){
        return codStatus;
    }

    public void setCodStatus(int codStatus){
        this.codStatus = codStatus; 
    }
        
    public int getCodCtId(){
        return codCtId;
    }

    public void setCodCtId(int codCtId){
        this.codCtId = codCtId; 
    }
        
    public int getCodId(){
        return codId;
    }

    public void setCodId(int codId){
        this.codId = codId; 
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
