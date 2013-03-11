package com.shangde.edu.sys.condition;

import com.shangde.common.vo.PageQuery;

public class QueryModulesCondition extends PageQuery{
	
	private String name;
	
	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
