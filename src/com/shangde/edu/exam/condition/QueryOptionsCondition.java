package com.shangde.edu.exam.condition;

import java.io.Serializable;

/**
 * 查询选项条件
 * @author chenshuai
 *
 */
public class QueryOptionsCondition implements Serializable{
    /**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 选项id
	 */
	private int optId;
        
    public int getOptId(){
        return optId;
    }

    public void setOptId(int optId){
        this.optId = optId;
    }
}