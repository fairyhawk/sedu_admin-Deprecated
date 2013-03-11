package com.shangde.edu.cou.condition;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.shangde.common.vo.PageQuery;

public class QueryTeacherCondition  extends PageQuery{
    private Integer tcId;
    private String name;
    private Integer isStar;
    private String courseName;
    private Integer sortId;
    private Integer subjectId;
    
	public Integer getSortId() {
		return sortId;
	}
	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}
	public Integer getTcId() {
		return tcId;
	}
	public void setTcId(Integer tcId) {
		this.tcId = tcId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		try {
			this.name =URLDecoder.decode(name,"UTF-8").replace(" ", "");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Integer getIsStar() {
		return isStar;
	}
	public void setIsStar(Integer isStar) {
		this.isStar = isStar;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
}