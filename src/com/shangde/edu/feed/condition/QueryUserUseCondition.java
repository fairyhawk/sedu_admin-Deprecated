package com.shangde.edu.feed.condition;

import com.shangde.common.vo.PageQuery;

/**
 * 微学习，用户使用行为记录查询接口
 * 
 * @author Libg
 * 
 */
public class QueryUserUseCondition extends PageQuery implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 分类[1=注册点击,2=注册用户,3=登录点击,4=登录成功]
	 */
	private Integer type;

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 来源id
	 */
	private Integer fromId;
	
	public Integer getFromId() {
		return fromId;
	}

	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * pubDate
	 * 当前时间（）用来查询微学习用户统计
	 */
	private String  pubDate;

}