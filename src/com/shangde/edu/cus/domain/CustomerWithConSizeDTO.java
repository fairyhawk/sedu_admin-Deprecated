package com.shangde.edu.cus.domain;

import java.io.Serializable;
import java.util.Date;

import com.shangde.edu.crm.domain.Sellrecord;
import com.shangde.edu.sys.domain.Subject;

public class CustomerWithConSizeDTO extends Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int contractCount;
	private int contractStatus;
	private Subject cusSubject;
	private Date createtime;
	private Date paytime;
	private Sellrecord sellrecord;
	private int payStatus;
	private int cancelStatus;
	private Boolean isProtocal;

	public int getContractCount() {
		return contractCount;
	}

	public void setContractCount(int contractCount) {
		this.contractCount = contractCount;
	}

	public Subject getCusSubject() {
		return cusSubject;
	}

	public void setCusSubject(Subject cusSubject) {
		this.cusSubject = cusSubject;
	}

	public int getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(int contractStatus) {
		this.contractStatus = contractStatus;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getPaytime() {
		return paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}

	public Sellrecord getSellrecord() {
		return sellrecord;
	}

	public void setSellrecord(Sellrecord sellrecord) {
		this.sellrecord = sellrecord;
	}


	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	public int getCancelStatus() {
		return cancelStatus;
	}

	public void setCancelStatus(int cancelStatus) {
		this.cancelStatus = cancelStatus;
	}

	public Boolean getIsProtocal() {
		return isProtocal;
	}

	public void setIsProtocal(Boolean isProtocal) {
		this.isProtocal = isProtocal;
	}

}
