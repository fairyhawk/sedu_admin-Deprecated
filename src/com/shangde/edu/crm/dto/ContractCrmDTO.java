package com.shangde.edu.crm.dto;

import java.io.Serializable;

public class ContractCrmDTO implements Serializable{

    /** 订单号   */
    private String contractId;
    /** 创建时间   */
    private java.util.Date createTime;
    /** 折后总金额  */
    private java.lang.Object contractCutSumMoney = 0;
    /** 支付类型 */
    private int payType; //0 为赠送　1支付宝 　2货到付款 3 网银在线 4 块钱
    /** 状态   */
    private int status; //赠送的包括　2赠送　4修复;	支付宝的包括　0未付　1已付　3退费;　 货到付款的包括 0未激活 1已激活 3已取消 4退费    
    /** 付款时间　  */
    private java.util.Date payTime;
    private String email;
	private String crInfo; 
	
    public String getContractId(){
        return contractId;
    }

    public void setContractId(String contractId){
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
        
    public int getPayType(){
        return payType;
    }

    public void setPayType(int payType){
        this.payType = payType; 
    }

	public java.lang.Object getContractCutSumMoney() {
		return contractCutSumMoney;
	}

	public void setContractCutSumMoney(java.lang.Object contractCutSumMoney) {
		this.contractCutSumMoney = contractCutSumMoney;
	}

	public java.util.Date getPayTime() {
		return payTime;
	}

	public void setPayTime(java.util.Date payTime) {
		this.payTime = payTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	private String shopNames;

	public String getShopNames() {
		return shopNames;
	}

	public void setShopNames(String shopNames) {
		this.shopNames = shopNames;
	}

	public String getCrInfo() {
		return crInfo;
	}

	public void setCrInfo(String crInfo) {
		this.crInfo = crInfo;
	}
	
	
}
