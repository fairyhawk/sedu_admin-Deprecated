package com.shangde.edu.cus.dto;

import java.io.Serializable;

public class CustomerFreezeStatusDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer cusId;
	private Integer freezeStatus;

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public Integer getFreezeStatus() {
		return freezeStatus;
	}

	public void setFreezeStatus(Integer freezeStatus) {
		this.freezeStatus = freezeStatus;
	}

}
