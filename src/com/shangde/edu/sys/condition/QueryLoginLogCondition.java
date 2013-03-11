package com.shangde.edu.sys.condition;

import com.shangde.common.vo.PageQuery;

public class QueryLoginLogCondition extends PageQuery{
	private int userId;			//外键_登录人ID
	private String userName;	//登录人

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
