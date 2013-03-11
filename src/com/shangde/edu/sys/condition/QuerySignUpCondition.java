package com.shangde.edu.sys.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class QuerySignUpCondition extends PageQuery {
	private Integer education;//学历
	private Integer age;//年龄
	private String phoneNumber;//电话号码
	private Integer subjectId;//项目ID
	private Date createBeginDate;//创建开始时间
	private Date createEndTime;//创建结束时间
	
	public Date getCreateBeginDate() {
		return createBeginDate;
	}
	public void setCreateBeginDate(Date createBeginDate) {
		this.createBeginDate = createBeginDate;
	}
	public Date getCreateEndTime() {
		return createEndTime;
	}
	public void setCreateEndTime(Date createEndTime) {
		this.createEndTime = createEndTime;
	}
	public Integer getSubjectId() {
		return subjectId==null?0:subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		if(subjectId==null){
			subjectId=0;
		}
		this.subjectId = subjectId;
	}
	public Integer getEducation() {
		return education;
	}
	public void setEducation(Integer education) {
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
}
