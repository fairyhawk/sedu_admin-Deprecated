package com.shangde.edu.tk.dto;

import java.io.Serializable;


/**
 * <br>
 * <b>功能：</b><br>
 * <b>作者：</b>李志强 Kobe.Lee<br>
 * <b>日期：</b> 2012.06.05 <br>
 * 
 * @return
 */
public class AutoTaskDTO implements Serializable{	
	private int cusId;
	private int subjectId;
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
}
