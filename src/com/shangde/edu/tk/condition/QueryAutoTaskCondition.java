package com.shangde.edu.tk.condition;

import com.shangde.common.vo.PageQuery;



/**
 * <br>
 * <b>功能：</b><br>
 * <b>作者：</b>李志强 Kobe.Lee<br>
 * <b>日期：</b> 2012.06.05 <br>
 * 
 * @return
 */
public class QueryAutoTaskCondition extends PageQuery {
	private String nowDate;

	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

}