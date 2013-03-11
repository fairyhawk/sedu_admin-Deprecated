package com.shangde.edu.kno.condition;

import java.io.Serializable;

public class QuerySysNodeCondition implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ksnId;
    /**
     * 用于重新排序的最小id
     */
    private int minId;
    private int ksId;
 
    private int maxId;
    private int parentId;
    private int sortId;

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	public int getMinId() {
		return minId;
	}

	public void setMinId(int minId) {
		this.minId = minId;
	}

	public int getMaxId() {
		return maxId;
	}

	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getKsnId(){
        return ksnId;
    }

    public void setKsnId(int ksnId){
        this.ksnId = ksnId;
    }

	public int getKsId() {
		return ksId;
	}

	public void setKsId(int ksId) {
		this.ksId = ksId;
	}
    
}