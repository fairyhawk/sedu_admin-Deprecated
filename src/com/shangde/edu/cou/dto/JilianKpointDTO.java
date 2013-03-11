package com.shangde.edu.cou.dto;

import java.io.Serializable;

public class JilianKpointDTO implements Serializable {
	/**
	 * 前台在线模考级联
	 */
	private static final long serialVersionUID = 1L;
	private  Integer pointId;//课程id
	private  String name;//课程名字
	public Integer getPointId() {
		return pointId;
	}
	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
