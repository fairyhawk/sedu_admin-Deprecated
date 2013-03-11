package com.shangde.edu.finance.condition;

import com.shangde.common.vo.PageQuery;

public class QueryChildContractCondition extends PageQuery{
	private int id;
    private String contractId;
    private String childContractId;
    private int cusId;
    private java.util.Date createTime;
    private java.util.Date payTime;
    private int payType;
    private int status;
    private java.math.BigDecimal money;
    private int ctId;
    
    private int allcount;//所有的子订单数量
    private int paycount;//支付成功的数量
    private java.util.Date startPayTime;
    private java.util.Date endPayTime;
    private java.util.Date startCreateTime;
    private java.util.Date endCreateTime;
    private String email;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the contractId
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return the childContractId
	 */
	public String getChildContractId() {
		return childContractId;
	}
	/**
	 * @param childContractId the childContractId to set
	 */
	public void setChildContractId(String childContractId) {
		this.childContractId = childContractId;
	}
	/**
	 * @return the cusId
	 */
	public int getCusId() {
		return cusId;
	}
	/**
	 * @param cusId the cusId to set
	 */
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	/**
	 * @return the createTime
	 */
	public java.util.Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the payTime
	 */
	public java.util.Date getPayTime() {
		return payTime;
	}
	/**
	 * @param payTime the payTime to set
	 */
	public void setPayTime(java.util.Date payTime) {
		this.payTime = payTime;
	}
	/**
	 * @return the payType
	 */
	public int getPayType() {
		return payType;
	}
	/**
	 * @param payType the payType to set
	 */
	public void setPayType(int payType) {
		this.payType = payType;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the money
	 */
	public java.math.BigDecimal getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(java.math.BigDecimal money) {
		this.money = money;
	}
    public int getCtId() {
        return ctId;
    }
    public void setCtId(int ctId) {
        this.ctId = ctId;
    }
    public int getAllcount() {
        return allcount;
    }
    public void setAllcount(int allcount) {
        this.allcount = allcount;
    }
    public int getPaycount() {
        return paycount;
    }
    public void setPaycount(int paycount) {
        this.paycount = paycount;
    }
    public java.util.Date getStartPayTime() {
        return startPayTime;
    }
    public void setStartPayTime(java.util.Date startPayTime) {
        this.startPayTime = startPayTime;
    }
    public java.util.Date getEndPayTime() {
        return endPayTime;
    }
    public void setEndPayTime(java.util.Date endPayTime) {
        this.endPayTime = endPayTime;
    }
    public java.util.Date getStartCreateTime() {
        return startCreateTime;
    }
    public void setStartCreateTime(java.util.Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }
    public java.util.Date getEndCreateTime() {
        return endCreateTime;
    }
    public void setEndCreateTime(java.util.Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    
}