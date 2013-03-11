package com.shangde.edu.sys.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 该类用于实现最新注会报考条件查询
 * @author dks
 *
 */
public class SignUp implements Serializable{
	private Integer id;//主键Id
	private Integer subjectId;//项目Id
	private String education;//学历
	private Integer age;//年龄
	private String phoneNumber;//电话号码
	private Date createDate;//创建时间
	private String subjectName;//项目专业名称
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
