package com.shangde.edu.feed.dto;

import java.io.Serializable;

/**
 * 应用统计,DTO
 * 
 * @author Libg
 * 
 */
public class AppStatDTO implements Serializable {

	private String name;// 名称
	private Integer clickNum;// 点击数
	private Integer useUserNum;// 使用人数

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the clickNum
	 */
	public Integer getClickNum() {
		return clickNum;
	}

	/**
	 * @param clickNum
	 *            the clickNum to set
	 */
	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	/**
	 * @return the useUserNum
	 */
	public Integer getUseUserNum() {
		return useUserNum;
	}

	/**
	 * @param useUserNum
	 *            the useUserNum to set
	 */
	public void setUseUserNum(Integer useUserNum) {
		this.useUserNum = useUserNum;
	}

}
