package com.shangde.edu.sys.condition;

import com.shangde.common.vo.PageQuery;

public class QueryModelCondition extends PageQuery{
	
	private int id;
	
	private String model_name;
	
	private String create_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	

}
