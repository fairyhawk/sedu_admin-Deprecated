package com.shangde.edu.cou.dto;

import java.io.Serializable;

/**
 * 教师cookies的DTO
 * 获取的对象用于存入Cookie
 * @author chenshuai
 *
 */
public class TeacherDTO implements Serializable{
	 private int tcId;
	    private String name;
	    private String education;
	    private String career;
	    private int isStar;
	    private String picPath;
	    
	    //~~~~~
	    private String courseName;

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

		public String getCareer() {
			return career;
		}

		public void setCareer(String career) {
			this.career = career;
		}

		public int getIsStar() {
			return isStar;
		}

		public void setIsStar(int isStar) {
			this.isStar = isStar;
		}

		public String getPicPath() {
			return picPath;
		}

		public void setPicPath(String picPath) {
			this.picPath = picPath;
		}

		public String getCourseName() {
			return courseName;
		}

		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}


}
