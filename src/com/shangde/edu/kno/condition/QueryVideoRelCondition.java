package com.shangde.edu.kno.condition;

import java.io.Serializable;

public class QueryVideoRelCondition implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int vrId;
    private int ksnId;
        
    public int getKsnId() {
		return ksnId;
	}

	public void setKsnId(int ksnId) {
		this.ksnId = ksnId;
	}

	public int getVrId(){
        return vrId;
    }

    public void setVrId(int vrId){
        this.vrId = vrId;
    }
}