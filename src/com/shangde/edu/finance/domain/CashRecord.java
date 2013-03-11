package com.shangde.edu.finance.domain;

import java.io.Serializable;
import java.util.Date;

import com.shangde.edu.cus.domain.Customer;

public class CashRecord implements Serializable{
	 /** 注册用户id   */
	private int cusId;
	/** 流水id   */
	private int id;
	 /** 流水类型   */
	private int type; //1前台购买　2后台赠送  3后台修复
	 /** 原始金额   */
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
    private int status;  //流水的支付状态 0 未支付，  1 已支付，  2 已取消 ，3:退费

   private int crSubjectId;
   
    private Customer customer;
    

    //流水金额
    private Object cashRecordPrice;
    //减免价格
    private Object reliefPrice;
    //优惠金额
    private Object couponMoney;
    //商品状态
    private int shopStatus; // 商品状态 0:未激活/1:已激活/2:已延期/3:已关闭
    //支付类型
    private int shopPayType;   //0:赠送（内部开通）/1:网上支付（支付宝）/2:货到付款/3:网银在线/4:快钱/5:代理商开通
    //支付时间
    private java.util.Date shopPayTime; 
    //退款时间
    private java.util.Date refundTime;
    //到期时间
    private java.util.Date validityTime;
    //停止服务时间
    private java.util.Date stopTime;
    //延期时间
    private Date delayTime;
    //商品类型
    private int shopType;   //1 代表课程，2 代表书籍 
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
	public int getCrSubjectId() {
		return crSubjectId;
	}
	public void setCrSubjectId(int crSubjectId) {
		this.crSubjectId = crSubjectId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Object getCashRecordPrice() {
		return cashRecordPrice;
	}
	public void setCashRecordPrice(Object cashRecordPrice) {
		this.cashRecordPrice = cashRecordPrice;
	}
	public Object getReliefPrice() {
		return reliefPrice;
	}
	public void setReliefPrice(Object reliefPrice) {
		this.reliefPrice = reliefPrice;
	}
	public Object getCouponMoney() {
		return couponMoney;
	}
	public void setCouponMoney(Object couponMoney) {
		this.couponMoney = couponMoney;
	}
	public int getShopStatus() {
		return shopStatus;
	}
	public void setShopStatus(int shopStatus) {
		this.shopStatus = shopStatus;
	}
	public int getShopPayType() {
		return shopPayType;
	}
	public void setShopPayType(int shopPayType) {
		this.shopPayType = shopPayType;
	}
	public java.util.Date getShopPayTime() {
		return shopPayTime;
	}
	public void setShopPayTime(java.util.Date shopPayTime) {
		this.shopPayTime = shopPayTime;
	}
	public java.util.Date getRefundTime() {
		return refundTime;
	}
	public void setRefundTime(java.util.Date refundTime) {
		this.refundTime = refundTime;
	}
	public java.util.Date getValidityTime() {
		return validityTime;
	}
	public void setValidityTime(java.util.Date validityTime) {
		this.validityTime = validityTime;
	}
	public java.util.Date getStopTime() {
		return stopTime;
	}
	public void setStopTime(java.util.Date stopTime) {
		this.stopTime = stopTime;
	}
	public Date getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(Date delayTime) {
		this.delayTime = delayTime;
	}
	public int getShopType() {
		return shopType;
	}
	public void setShopType(int shopType) {
		this.shopType = shopType;
	}
	
}
