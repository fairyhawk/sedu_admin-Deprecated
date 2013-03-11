package com.shangde.edu.cou.dto;

import java.io.Serializable;

/**
 * 键值类
 * @author cxs
 *
 */
public class KeyValueDTO implements Serializable, Comparable{
	/**
	 * 父ID
	 */
	private int pId;
	
	/**
	 * ID
	 */
	private int id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 视频地址
	 */
	private String vedioUrl;
	
	/**
	 * 排序值
	 */
	private int sortNum;
	
	/**
	 * 是否能试听(0：不能, 1:能)
	 */
	private int isAudition;
	
	
	public int getSortNum() {
		return sortNum;
	}
	public void setSortNum(int sorNum) {
		this.sortNum = sorNum;
	}
	
	public int compareTo(Object arg0) {
		KeyValueDTO kpointtemp = (KeyValueDTO)arg0;
		return new Integer(sortNum).compareTo(new Integer(kpointtemp.getSortNum()));
	}
	public int getPId() {
		return pId;
	}
	public void setPId(int id) {
		pId = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVedioUrl() {
		return vedioUrl;
	}
	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}
	public int getIsAudition() {
		return isAudition;
	}
	public void setIsAudition(int isAudition) {
		this.isAudition = isAudition;
	}
}
