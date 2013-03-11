package com.shangde.edu.cou.dto;

import java.io.Serializable;

/**
 * 知识点叶子节点的DTO
 * @author chenshuai
 *
 */
public class LeafKpointUrlDTO implements Serializable,Comparable<Object>{
	private int id;
	private int sortNum;
	private String vedioUrl;
	private String voSize;
	
	public String getVoSize() {
		return voSize;
	}
	public void setVoSize(String voSize) {
		this.voSize = voSize;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSortNum() {
		return sortNum;
	}
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
	public String getVedioUrl() {
		return vedioUrl;
	}
	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}

	public int compareTo(Object arg0) {
		LeafKpointUrlDTO kpointtemp = (LeafKpointUrlDTO)arg0;
		return new Integer(sortNum).compareTo(new Integer(kpointtemp.getSortNum()));
	}
}
