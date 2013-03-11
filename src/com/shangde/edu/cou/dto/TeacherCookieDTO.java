package com.shangde.edu.cou.dto;

import java.io.Serializable;

/**
 * 教师cookies的DTO
 * 获取的对象用于存入Cookie
 * @author chenshuai
 *
 */
public class TeacherCookieDTO implements Serializable{
	/**
	 * 教师Id
	 */
	private int tcId;
	/**
	 * 教师姓名
	 */
    private String name;
    
    /**
     * 教师教育
     */
    private String education;
   
    /**
     * 教师头像
     */
    private String picPath;

	public int getTcId() {
		return tcId;
	}

	public void setTcId(int tcId) {
		this.tcId = tcId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

}
