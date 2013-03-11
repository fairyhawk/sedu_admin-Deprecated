package com.shangde.edu.finance.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 新建优惠券entity
 * @author Administrator
 *
 */

public class Coupontype implements Serializable{
	
	private Integer id;
	
	private String title;  				//优惠卷名称	1
	
	private String cInfo;				//添加新优惠券暂时还没用到；
	
	private String picPath; 			//图片	1
	
	private Date createTime;			//创建时间	1
	
	private Date useTime;        		//使用时间
	
	private Date stopTime;				//结束时间	1
	
	private Date generateTime;			//优惠生成时间
	
	private Float leastPrice; 			 //使用限额	1
	
	private String preferentialPrice;	//折扣金额and优惠金额  1
	
	private String subjectId;         //课程使用范围      1
	
	private Integer cNum;				//设置生成数量   1
	
	private Integer cToscore;       // 积分		1
	
	private Integer cType;            //优惠卷类型  1
	
	private Integer cooperativeId;  	//合作商Id	1
	
	private Integer createSum;      //创建数量  1
	
	private Integer useSum;          //使用数量   
	
	private Integer paySum;          //支付数量   
	
	private Integer state;			//状态	1为正常2为过期3为冻结4为作废
	
	private String company;			// 合作商名称     
	
	private String subjectName;		//课程  
	
	private  String operatingName;  //创建的用户
	
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//在时间显示的时候运用到
	private String stringCreateTime;
	private String stringstopTime;
	//为了显示使用限额
	private String leastPriceString;
	
	public String getLeastPriceString() {
		return leastPriceString;
	}

	public void setLeastPriceString(String leastPriceString) {
		this.leastPriceString = leastPriceString;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCInfo() {
		return cInfo;
	}

	public void setCInfo(String cInfo) {
		this.cInfo = cInfo;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public Date getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}

	public Float getLeastPrice() {
		return leastPrice;
	}

	public void setLeastPrice(Float leastPrice) {
		this.leastPrice = leastPrice;
	}

	public String getPreferentialPrice() {
		return preferentialPrice;
	}

	public void setPreferentialPrice(String preferentialPrice) {
		this.preferentialPrice = preferentialPrice;
	}

	public Integer getCNum() {
		return cNum;
	}

	public void setCNum(Integer cNum) {
		this.cNum = cNum;
	}

	public Integer getCToscore() {
		return cToscore;
	}

	public void setCToscore(Integer cToscore) {
		this.cToscore = cToscore;
	}

	public Integer getCType() {
		return cType;
	}

	public void setCType(Integer cType) {
		this.cType = cType;
	}

	public Integer getCooperativeId() {
		return cooperativeId;
	}
	public Integer getcooperativeId() {
		return cooperativeId;
	}

	public void setCooperativeId(Integer cooperativeId) {
		this.cooperativeId = cooperativeId;
	}
	public void setcooperativeId(Integer cooperativeId) {
		this.cooperativeId = cooperativeId;
	}

	public Integer getCreateSum() {
		return createSum;
	}

	public void setCreateSum(Integer createSum) {
		this.createSum = createSum;
	}

	public Integer getUseSum() {
		return useSum;
	}

	public void setUseSum(Integer useSum) {
		this.useSum = useSum;
	}

	public Integer getPaySum() {
		return paySum;
	}

	public void setPaySum(Integer paySum) {
		this.paySum = paySum;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getcInfo() {
		return cInfo;
	}

	public void setcInfo(String cInfo) {
		this.cInfo = cInfo;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getcNum() {
		return cNum;
	}

	public void setcNum(Integer cNum) {
		this.cNum = cNum;
	}

	public Integer getcToscore() {
		return cToscore;
	}

	public void setcToscore(Integer cToscore) {
		this.cToscore = cToscore;
	}

	public Integer getcType() {
		return cType;
	}

	public void setcType(Integer cType) {
		this.cType = cType;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getStringCreateTime() {
		return stringCreateTime;
	}

	public void setStringCreateTime(String stringCreateTime) {
		this.stringCreateTime = stringCreateTime;
	}

	public String getStringstopTime() {
		return stringstopTime;
	}

	public void setStringstopTime(String stringstopTime) {
		this.stringstopTime = stringstopTime;
	}

	public String getOperatingName() {
		return operatingName;
	}

	public void setOperatingName(String operatingName) {
		this.operatingName = operatingName;
	}


	

	
}