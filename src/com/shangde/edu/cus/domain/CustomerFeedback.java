package com.shangde.edu.cus.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Yangning
 * @createDate:2011-11-09
 * @desc:用户反馈实体类
 */
public class CustomerFeedback implements Serializable{

	private static final long serialVersionUID = 7900573039099321717L;
	
	private int id;
	
	private String email;
	
	private Integer qq;
	
	private String mobile;
	
	private Integer province;
	
	private Integer city;
	/**解决时间**/
	private Date soltime; 
	
	/**--项目专业Id--**/
	private Integer subject_id;
	
	/**--用户反馈描述信息--**/
	private String description;
	
	/**运营商id**/
	private Integer sp;
	/**网络带宽**/
	private Integer bandwidth;
	
	/**专业名称**/
	private String subjectname;
	
	/**省份名**/
	private String provincename;
	
	/**城市名**/
	private String cityname;
	
	/**反馈信息状态 0未解决 1已解决**/
	private Integer status;
	/**解决部门**/
	private String soldept;
	/**解决方式**/
	private String soltype;
	
	private Date createtime;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSoldept() {
		return soldept;
	}

	public void setSoldept(String soldept) {
		this.soldept = soldept;
	}

	public String getSoltype() {
		return soltype;
	}

	public void setSoltype(String soltype) {
		this.soltype = soltype;
	}
	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getQq() {
		return qq;
	}

	public void setQq(Integer qq) {
		this.qq = qq;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(Integer subject_id) {
		this.subject_id = subject_id;
	}


	public Integer getSp() {
		return sp;
	}

	public void setSp(Integer sp) {
		this.sp = sp;
	}

	public Integer getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(Integer bandwidth) {
		this.bandwidth = bandwidth;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getSoltime() {
		return soltime;
	}

	public void setSoltime(Date soltime) {
		this.soltime = soltime;
	}
}
