package com.shangde.edu.kno.condition;

import java.io.Serializable;

public class QueryResolveCondition implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int resId;
        
    public int getResId(){
        return resId;
    }

    public void setResId(int resId){
        this.resId = resId;
    }
}