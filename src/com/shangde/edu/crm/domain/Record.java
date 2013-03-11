package com.shangde.edu.crm.domain;

import java.io.Serializable;

public class Record implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	/**
	 * 销售机会id
	 */
    private int crmChanceId;
    /**
     * 沟通状态
     * 1空号2通话中3再联系4测试
     * 5正常接通6未接通7假号码8已购买
     */
    private int commStatus;
    /**
     * 学习原因
     */
    private String whyLearm;
    /**
     * 关注点
     */
    private String concerns;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 销售坐席id
     */
    private int userId;
    /**
     * 乐语咨询id
     */
    private String leyuId;
    
    private java.util.Date createTime;
    /**
     * 未成单原因
     */
    private String reasons;
    
    
        
	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getCrmChanceId(){
        return crmChanceId;
    }

    public void setCrmChanceId(int crmChanceId){
        this.crmChanceId = crmChanceId; 
    }
        
    public int getCommStatus(){
        return commStatus;
    }

    public void setCommStatus(int commStatus){
        this.commStatus = commStatus; 
    }
        
    public String getWhyLearm(){
        return whyLearm;
    }

    public void setWhyLearm(String whyLearm){
        this.whyLearm = whyLearm; 
    }
        
    public String getConcerns(){
        return concerns;
    }

    public void setConcerns(String concerns){
        this.concerns = concerns; 
    }
        
    public String getRemarks(){
        return remarks;
    }

    public void setRemarks(String remarks){
        this.remarks = remarks; 
    }
        
    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId; 
    }

	public String getLeyuId() {
		return leyuId;
	}

	public void setLeyuId(String leyuId) {
		this.leyuId = leyuId;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
}
