package com.shangde.edu.cus.condition;

public class QueryAreaCondition {
    private int id;
    
    private int parentId;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
}