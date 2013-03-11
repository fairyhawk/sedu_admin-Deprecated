package com.shangde.edu.count.condition;

import com.shangde.common.vo.PageQuery;

public class QueryContractCountCondition extends PageQuery {
	
	private String startCountTime; //开始时间
	
	private String endCountTime; //结束时间
	
	private int payType;
	
	private int subjectId;
	
	private String contractFromUrl; //订单来源域名
	
	private String cusFromUrl;	//学员注册域名
	
	private String webFrom;
	
	private String webAgnet;

	public String getWebFrom() {
		return webFrom;
	}

	public void setWebFrom(String webFrom) {
		this.webFrom = webFrom;
	}

	public String getWebAgnet() {
		return webAgnet;
	}

	public void setWebAgnet(String webAgnet) {
		this.webAgnet = webAgnet;
	}

	public String getContractFromUrl() {
		return contractFromUrl;
	}

	public void setContractFromUrl(String contractFromUrl) {
		this.contractFromUrl = contractFromUrl;
	}

	public String getCusFromUrl() {
		return cusFromUrl;
	}

	public void setCusFromUrl(String cusFromUrl) {
		this.cusFromUrl = cusFromUrl;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getStartCountTime() {
		return startCountTime;
	}

	public void setStartCountTime(String startCountTime) {
		this.startCountTime = startCountTime;
	}

	public String getEndCountTime() {
		return endCountTime;
	}

	public void setEndCountTime(String endCountTime) {
		this.endCountTime = endCountTime;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

}
