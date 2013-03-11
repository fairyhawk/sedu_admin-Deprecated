package com.shangde.edu.feed.condition;

import com.shangde.common.vo.PageQuery;

/**
 * 微学习，ad广告查询接口
 * 
 * @author Libg
 * 
 */
public class QueryStatCommonCondition extends PageQuery implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;

	/**
	 * 类型[1=注册点击,2=注册成功,3=登录点击,4=登录成功]
	 */
	private Integer type;

	/**
	 * 来源id
	 */
	private Integer fromId;

	/**
	 * 广告id
	 */
	private Integer adId;

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

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
	 * @return the fromId
	 */
	public Integer getFromId() {
		return fromId;
	}

	/**
	 * @param fromId
	 *            the fromId to set
	 */
	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}

	/**
	 * @return the adId
	 */
	public Integer getAdId() {
		return adId;
	}

	/**
	 * @param adId
	 *            the adId to set
	 */
	public void setAdId(Integer adId) {
		this.adId = adId;
	}

}