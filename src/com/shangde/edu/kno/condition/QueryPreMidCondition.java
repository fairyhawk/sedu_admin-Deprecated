package com.shangde.edu.kno.condition;

import java.io.Serializable;

public class QueryPreMidCondition implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}