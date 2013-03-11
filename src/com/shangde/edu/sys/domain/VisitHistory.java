package com.shangde.edu.sys.domain;

import java.io.Serializable;

public class VisitHistory implements Serializable{
	public static final int VISITOR =1;
	public static final int BEFORE_USER=2;
	public static final int ADMIN_USER=3;
	/**id*/
    private int id;
    /**用户类型　前台游客　前台用户　后台用户*/
    private int userType;
    /**用户id  游客为空　前台cusId 后台userId */
    private int userId;
    /**访问ip*/
    private String visitIp;
    /**访问的url含参*/
    private String visitUrl;
    /**访问类型　.action .jsp*/
    private String visitType;
    /**访问时间*/
    private java.util.Date visitTime;
    /**访问的功能名称*/
    private String visitName;
    /**访问描述*/
    private String visitInfo;
    /**url参数*/
    private String visitParms;
    /** namespace  */
    private String nameSpace;
    /** action名字  */ 
    private String actionName;
    
    
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getUserType(){
        return userType;
    }

    public void setUserType(int userType){
        this.userType = userType; 
    }
        
    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId; 
    }
        
    public String getVisitIp(){
        return visitIp;
    }

    public void setVisitIp(String visitIp){
        this.visitIp = visitIp; 
    }
        
    public String getVisitUrl(){
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl){
        this.visitUrl = visitUrl; 
    }
        
    public String getVisitType(){
        return visitType;
    }

    public void setVisitType(String visitType){
        this.visitType = visitType; 
    }
        
    public java.util.Date getVisitTime(){
        return visitTime;
    }

    public void setVisitTime(java.util.Date visitTime){
        this.visitTime = visitTime; 
    }
        
    public String getVisitName(){
        return visitName;
    }

    public void setVisitName(String visitName){
        this.visitName = visitName; 
    }
        
    public String getVisitInfo(){
        return visitInfo;
    }

    public void setVisitInfo(String visitInfo){
        this.visitInfo = visitInfo; 
    }
        
    public String getVisitParms(){
        return visitParms;
    }

    public void setVisitParms(String visitParms){
        this.visitParms = visitParms; 
    }

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
}
