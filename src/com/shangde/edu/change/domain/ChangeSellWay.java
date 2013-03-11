package com.shangde.edu.change.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 更换课程
 * @author 王郑
 *
 */
public class ChangeSellWay implements Serializable{
	/**关闭课程标记属性值**/
	public static final int CLOSE_CASH_RECORD_SHOPTYPE = 3;
	
	private int id;  //id
	private int cusId; //用户id
	private int cashId;	//流水ID
	private int packId; //原课程ID
	private int newPackId; //更换后课程ID
	private String userName; //操作人id
	private int type;	//记录类型  1 为换课  0为审核
	private String contractId; //订单id
	private Date updateTime; //操作时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public int getCashId() {
		return cashId;
	}
	public void setCashId(int cashId) {
		this.cashId = cashId;
	}
	public int getPackId() {
		return packId;
	}
	public void setPackId(int packId) {
		this.packId = packId;
	}
	public int getNewPackId() {
		return newPackId;
	}
	public void setNewPackId(int newPackId) {
		this.newPackId = newPackId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
