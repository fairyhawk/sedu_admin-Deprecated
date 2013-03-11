package com.shangde.edu.finance.domain;

import java.io.Serializable;
import java.util.Date;

import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.dto.SimpleCustomerDTO;

public class CashRecordDTO implements Serializable{
	 /** 注册用户id   */
	private int cusId;
	/** 流水id   */
	private int id;
	 /** 流水类型   */
	private int type; //1前台购买　2后台赠送  3后台修复
	 /** 流水金额   */
    private Object price; 
    /** 流水说明   */
    private String crInfo;
    /** 创建时间   */
    private java.util.Date createTime;
    /** 订单号   */
    private String contractId;
	 /** 课程id   */
	private int courseId;
	 /** 打包id   */
    private int packId;
    /** 订单id   */
    private int ctId;
    /** 流水状态 */
    private int status;  //0无效 1有效
    
    private String packName;
    
    private int payType;  //支付类型
    
    private int subjectId;
    
    private Course course;
    
	 private Customer customer;  
	    
	    private String subjectName; //项目名称
	    
	    
	    private int shopStatus;//商品状态
	    
	    private String cusName;//学员姓名
	    
	    private String email;//学员email
	    
	    private String shopType;//商品类型
	    
	    private Date refundTime;
	    
	    private java.lang.Object cashRecordPrice;
	    
	    private java.lang.Object couponMoney;
	    
	    private Date stopTime;
	    private Date validityTime;
	    private java.lang.Object reliefPrice;
	    private Date delayTime;
	    private Date shopPayTime;
	    private String shopPayType;
    /**
     * @王超
     * 后台管理员取消订单标识1
     * 用户取消订单标识0
     */
    private int cancelFrom;
    
    private float payPrice;

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Object getPrice() {
		return price;
	}

	public void setPrice(Object price) {
		this.price = price;
	}

	public String getCrInfo() {
		return crInfo;
	}

	public void setCrInfo(String crInfo) {
		this.crInfo = crInfo;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getPackId() {
		return packId;
	}

	public void setPackId(int packId) {
		this.packId = packId;
	}

	public int getCtId() {
		return ctId;
	}

	public void setCtId(int ctId) {
		this.ctId = ctId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getShopStatus() {
		return shopStatus;
	}

	public void setShopStatus(int shopStatus) {
		this.shopStatus = shopStatus;
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

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public java.lang.Object getCashRecordPrice() {
		return cashRecordPrice;
	}

	public void setCashRecordPrice(java.lang.Object cashRecordPrice) {
		this.cashRecordPrice = cashRecordPrice;
	}

	public java.lang.Object getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(java.lang.Object couponMoney) {
		this.couponMoney = couponMoney;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public Date getValidityTime() {
		return validityTime;
	}

	public void setValidityTime(Date validityTime) {
		this.validityTime = validityTime;
	}

	public java.lang.Object getReliefPrice() {
		return reliefPrice;
	}

	public void setReliefPrice(java.lang.Object reliefPrice) {
		this.reliefPrice = reliefPrice;
	}

	public Date getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(Date delayTime) {
		this.delayTime = delayTime;
	}

	public Date getShopPayTime() {
		return shopPayTime;
	}

	public void setShopPayTime(Date shopPayTime) {
		this.shopPayTime = shopPayTime;
	}

	public String getShopPayType() {
		return shopPayType;
	}

	public void setShopPayType(String shopPayType) {
		this.shopPayType = shopPayType;
	}

	public int getCancelFrom() {
		return cancelFrom;
	}

	public void setCancelFrom(int cancelFrom) {
		this.cancelFrom = cancelFrom;
	}

	public float getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(float payPrice) {
		this.payPrice = payPrice;
	}


}
