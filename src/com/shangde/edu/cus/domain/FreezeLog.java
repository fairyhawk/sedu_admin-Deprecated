package com.shangde.edu.cus.domain;

import java.io.Serializable;
import java.util.Date;

/** 冻结、解冻操作日志 */
public class FreezeLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 编号 */
	private Integer id;
	/** 用户编号 */
	private Integer cusId;
	/** 操作类型： 0：冻结；1：解冻 */
	private Integer operationType;
	/** 操作日期 */
	private Date operationDate;
	/** 操作者编号。如果是系统自动冻结的用户，则操作者编号为0 */
	private Integer operator;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public Integer getOperationType() {
		return operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

}
