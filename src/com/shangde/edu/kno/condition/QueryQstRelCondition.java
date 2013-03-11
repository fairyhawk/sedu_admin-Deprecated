package com.shangde.edu.kno.condition;

import java.io.Serializable;

public class QueryQstRelCondition implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int qrId;
    private int ksnId;
    private int limitNumber;
	
	
    public int getKsnId() {
		return ksnId;
	}

	public void setKsnId(int ksnId) {
		this.ksnId = ksnId;
	}

	public int getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(int limitNumber) {
		this.limitNumber = limitNumber;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getQrId(){
        return qrId;
    }

    public void setQrId(int qrId){
        this.qrId = qrId;
    }
}