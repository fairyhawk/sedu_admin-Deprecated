package com.shangde.edu.stu.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class QueryPlanCondition extends PageQuery{

	private int planId;
	private int cusId;
	private int courseId;
	/**
	 * 学习计划的提醒日期
	 */
	private java.util.Date opendate;
	/**
	 * 该字段已废弃
	 */
	private java.util.Date enddate;
	private int pstatus;
	private java.util.Date publish;
	private java.util.Date handledate;
	private String picon;
	private String content;
	private String cityname;
	private String citypinyin;
	private String plantitle;
	
	
	private Date stapublish;
	private Date endpublish;
	private Date stahandledate;
	private Date endhandledate;
	
	
	/**
	 * 匹配查询日期
	 */
	private String calendar;
	
    private String cusName;
    private String email;

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public java.util.Date getOpendate() {
		return opendate;
	}

	public void setOpendate(java.util.Date opendate) {
		this.opendate = opendate;
	}

	public java.util.Date getEnddate() {
		return enddate;
	}

	public void setEnddate(java.util.Date enddate) {
		this.enddate = enddate;
	}

	public int getPstatus() {
		return pstatus;
	}

	public void setPstatus(int pstatus) {
		this.pstatus = pstatus;
	}

	public java.util.Date getPublish() {
		return publish;
	}

	public void setPublish(java.util.Date publish) {
		this.publish = publish;
	}

	public java.util.Date getHandledate() {
		return handledate;
	}

	public void setHandledate(java.util.Date handledate) {
		this.handledate = handledate;
	}

	public String getPicon() {
		return picon;
	}

	public void setPicon(String picon) {
		this.picon = picon;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getCitypinyin() {
		return citypinyin;
	}

	public void setCitypinyin(String citypinyin) {
		this.citypinyin = citypinyin;
	}

	public String getPlantitle() {
		return plantitle;
	}

	public void setPlantitle(String plantitle) {
		this.plantitle = plantitle;
	}

	public String getCalendar() {
		return calendar;
	}

	public void setCalendar(String calendar) {
		this.calendar = calendar;
	}


	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getStapublish() {
		return stapublish;
	}

	public void setStapublish(Date stapublish) {
		this.stapublish = stapublish;
	}

	public Date getEndpublish() {
		return endpublish;
	}

	public void setEndpublish(Date endpublish) {
		this.endpublish = endpublish;
	}

	public Date getStahandledate() {
		return stahandledate;
	}

	public void setStahandledate(Date stahandledate) {
		this.stahandledate = stahandledate;
	}

	public Date getEndhandledate() {
		return endhandledate;
	}

	public void setEndhandledate(Date endhandledate) {
		this.endhandledate = endhandledate;
	}
	
	
}