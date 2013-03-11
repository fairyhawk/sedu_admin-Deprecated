package com.shangde.edu.tk.condition;

import com.shangde.common.vo.PageQuery;

public class QueryTaskCondition  extends PageQuery{
    private int taskId;
    
    private String searchKey;
        
    public int getTaskId(){
        return taskId;
    }

    public void setTaskId(int taskId){
        this.taskId = taskId;
    }

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
}