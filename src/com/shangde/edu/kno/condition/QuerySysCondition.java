package com.shangde.edu.kno.condition;

import java.io.Serializable;

import com.shangde.common.vo.PageResult;

public class QuerySysCondition extends PageResult implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ksId;
    private int subjectId;
    private int status;
    public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getKsId(){
        return ksId;
    }

    public void setKsId(int ksId){
        this.ksId = ksId;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}