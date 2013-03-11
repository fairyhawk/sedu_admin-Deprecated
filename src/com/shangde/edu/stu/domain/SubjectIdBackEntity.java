package com.shangde.edu.stu.domain;

import java.io.Serializable;

/**
 * 根据项目ID查询所返回的要发送的信息；
 * @author liming
 *
 */
public class SubjectIdBackEntity implements Serializable {
	private int cusId;	
	private String cusName;  //昵称
	private String cusMobile; //手机号码
	private String cusEmail;	//EMAIL
	private String pack_id;		//包名
	private String subjectName; 	// 项目名字
	private String  nonceDate;		//当前时间
	private String ccontent;		//提醒的内容；
	private String   sendDate;				//发送的时间进行拼接，分时间点and时间段；
	
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public String getNonceDate() {
		return nonceDate;
	}
	public void setNonceDate(String nonceDate) {
		this.nonceDate = nonceDate;
	}
	public String getPack_id() {
		return pack_id;
	}
	public void setPack_id(String pack_id) {
		this.pack_id = pack_id;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusMobile() {
		return cusMobile;
	}
	public void setCusMobile(String cusMobile) {
		this.cusMobile = cusMobile;
	}
	public String getCusEmail() {
		return cusEmail;
	}
	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

}
