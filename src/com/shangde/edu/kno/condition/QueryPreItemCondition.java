package com.shangde.edu.kno.condition;

import java.io.Serializable;

import com.shangde.common.vo.PageQuery;

public class QueryPreItemCondition extends PageQuery implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int preId;
        
    public int getPreId(){
        return preId;
    }

    public void setPreId(int preId){
        this.preId = preId;
    }
}