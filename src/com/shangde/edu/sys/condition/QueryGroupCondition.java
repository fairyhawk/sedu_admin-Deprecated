package com.shangde.edu.sys.condition;

import java.io.Serializable;

public class QueryGroupCondition implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int groupId;
    private int thirdGroupId;
        
    public int getGroupId(){
        return groupId;
    }

    public void setGroupId(int groupId){
        this.groupId = groupId;
    }

    public int getThirdGroupId() {
        return thirdGroupId;
    }

    public void setThirdGroupId(int thirdGroupId) {
        this.thirdGroupId = thirdGroupId;
    }
}