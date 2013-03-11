package com.shangde.edu.cou.condition;

public class QueryUncouponCondition {
    private int uncpId;
    private int id;
    private String email;
        
    public int getUncpId(){
        return uncpId;
    }

    public void setUncpId(int uncpId){
        this.uncpId = uncpId;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}