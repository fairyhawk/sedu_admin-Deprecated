package com.shangde.edu.exam.condition;

import java.io.Serializable;

public class QueryQstmiddleCondition implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int eqId;
    private int epId;
    private int qstType;
    private int qstId;
    
    public int getEqId(){
        return eqId;
    }

    public void setEqId(int eqId){
        this.eqId = eqId;
    }

	public int getEpId() {
		return epId;
	}

	public void setEpId(int epId) {
		this.epId = epId;
	}

	public int getQstType() {
		return qstType;
	}

	public void setQstType(int qstType) {
		this.qstType = qstType;
	}

	public int getQstId() {
		return qstId;
	}

	public void setQstId(int qstId) {
		this.qstId = qstId;
	}
}