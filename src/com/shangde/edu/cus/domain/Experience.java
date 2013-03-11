package com.shangde.edu.cus.domain;

import java.io.Serializable;

public class Experience implements Serializable{
	/**
	 * 当前经验值
	 */
	private int expValue;
	/**
	 * 下一级的经验值
	 */
    private int nextExpValue;
	/**
	 * 当前级别名称　
	 */
    private String  expName;
	/**
	 * 当前等级
	 */
    private int expLevel;
    
    
	public int getExpValue() {
		return expValue;
	}
	public void setExpValue(int expValue) {
		this.expValue = expValue;
	}
	public int getNextExpValue() {
		return nextExpValue;
	}
	public void setNextExpValue(int nextExpValue) {
		this.nextExpValue = nextExpValue;
	}
	public String getExpName() {
		return expName;
	}
	public void setExpName(String expName) {
		this.expName = expName;
	}
	public int getExpLevel() {
		return expLevel;
	}
	public void setExpLevel(int expLevel) {
		this.expLevel = expLevel;
	}
    
    
        
  
}
