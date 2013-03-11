package com.shangde.edu.ad.dto;

import java.math.BigDecimal;
import java.util.Date;

public class CHANETOrderQueryDTO {
	//--------返给成果网的信息--开始----------
	
	// 订单发生时间
	private String orderTime;
	//联盟唯一id ([SID])
	private String Sid="abcd";
	// 订单号
	private String orderId;
	
	//商品类型
	private String productType;
	//商品名称
	private String productName="highso视频";
	//商品数量
	private int productCount=1;
	//商品价格
	private String productPrice; 
	
	

//	合作方支付方式说明
//	货到付款	1
//	在线支付-支付宝	2
//	在线支付-财付通	3
//	在线支付-快钱	4
//	在线支付-其他	5
//	邮局汇款	6
//	银行转帐	7
//	礼券支付	8
//	其他	9
	private String payType;
	
	
	// 合作方订单状态
	//0	用户成功下单（未支付）
	//	1	用户成功支付订单（含所有支付方式）
	//	2	商家发出商品
	//	3	用户收到并签收商品
	//	4	用户收到商品后提出退货
	//	5	用户支付后，但未收到商品时，提出取消订单
	//	6	用户收到商品，并过退换货保障期，订单完成
	//	7	订单商品缺货，无法发货
	//	8	订单商品含预售商品，无法发货

	private String status;
	

	
	
	
	
	
	//--------返给成果网的信息--结束----------
	
	// -------本地数据库的订单信息----------------开始------------------------------------------------

	private Integer id;
	// 交易编号
	private String contractId;

	private Date createTime;

	private BigDecimal contractSumMoney;
    //优惠券金额
	private BigDecimal couponMoney;
	//折后总金
	private BigDecimal contractCutSumMoney;
    //运费
	private BigDecimal freight;
    //订单状态
	private Integer localStatus;
	//支付类型
	private int  payTypez;
	
	//亿起发下级网站信息
	private String wi;

	private String src;

	private String cid;
	
	private String price;
	
	private String pname;
	// -------本地数据库的订单信息----------------结束------------------------------------------------
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the contractId
	 */
	public String getContractId() {
		return contractId;
	}

	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the contractSumMoney
	 */
	public BigDecimal getContractSumMoney() {
		return contractSumMoney;
	}

	/**
	 * @param contractSumMoney the contractSumMoney to set
	 */
	public void setContractSumMoney(BigDecimal contractSumMoney) {
		this.contractSumMoney = contractSumMoney;
	}

	/**
	 * @return the couponMoney
	 */
	public BigDecimal getCouponMoney() {
		return couponMoney;
	}

	/**
	 * @param couponMoney the couponMoney to set
	 */
	public void setCouponMoney(BigDecimal couponMoney) {
		this.couponMoney = couponMoney;
	}

	/**
	 * @return the contractCutSumMoney
	 */
	public BigDecimal getContractCutSumMoney() {
		return contractCutSumMoney;
	}

	/**
	 * @param contractCutSumMoney the contractCutSumMoney to set
	 */
	public void setContractCutSumMoney(BigDecimal contractCutSumMoney) {
		this.contractCutSumMoney = contractCutSumMoney;
	}

	/**
	 * @return the freight
	 */
	public BigDecimal getFreight() {
		return freight;
	}

	/**
	 * @param freight the freight to set
	 */
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	/**
	 * @return the localStatus
	 */
	public Integer getLocalStatus() {
		return localStatus;
	}

	/**
	 * @param localStatus the localStatus to set
	 */
	public void setLocalStatus(Integer localStatus) {
		this.localStatus = localStatus;
	}

	/**
	 * @return the wi
	 */
	public String getWi() {
		return wi;
	}

	/**
	 * @param wi the wi to set
	 */
	public void setWi(String wi) {
		this.wi = wi;
	}

	/**
	 * @return the src
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * @param src the src to set
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * @return the cid
	 */
	public String getCid() {
		return cid;
	}

	/**
	 * @param cid the cid to set
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}

	/**
	 * @return the orderTime
	 */
	public String getOrderTime() {
		return orderTime;
	}

	/**
	 * @param orderTime the orderTime to set
	 */
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * @return the sid
	 */
	public String getSid() {
		return Sid;
	}

	/**
	 * @param sid the sid to set
	 */
	public void setSid(String sid) {
		Sid = sid;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productCount
	 */
	public int getProductCount() {
		return productCount;
	}

	/**
	 * @param productCount the productCount to set
	 */
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	/**
	 * @return the productPrice
	 */
	public String getProductPrice() {
		return productPrice;
	}

	/**
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * @return the payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * @param payType the payType to set
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the payTypez
	 */
	public int getPayTypez() {
		return payTypez;
	}

	/**
	 * @param payTypez the payTypez to set
	 */
	public void setPayTypez(int payTypez) {
		this.payTypez = payTypez;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}

	/**
	 * @param pname the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}

	
	
	

}
