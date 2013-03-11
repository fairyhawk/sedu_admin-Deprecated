package com.shangde.edu.feed.condition;

import com.shangde.common.vo.PageQuery;

/**
 * 微学习，ad广告查询接口
 * 
 * @author Libg
 * 
 */
public class QueryAdCondition extends PageQuery implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

}