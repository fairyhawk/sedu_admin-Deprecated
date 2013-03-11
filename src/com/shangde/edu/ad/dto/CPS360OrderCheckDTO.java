package com.shangde.edu.ad.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 360CPS对账查询结果。
 * 
 * @author ZHENG QIANG
 */
public class CPS360OrderCheckDTO {

	// -------返回给360的订单信息----------------开始------------------------------------------------
	// 订单号
	private String orderId;

	// 下单时间
	private String orderTime;

	// 订单最后更新时间
	private String orderUpdtime;

	// 服务费用，如运送费等,单位:元
	private String serverPrice;

	// 订单应付总额 = 订单总价(不含服务费，运费等) - 优惠券金额,单位:元
	private String totalPrice;

	// 用户使用优惠卡或积分抵充商品的金额 单位:元
	private String coupon;

	// 总佣金,按照用户实际应支付的商品价格计算,即扣除优惠券和运费部分,单位：元
	private String totalComm;

	// 佣金明细。 其中分成金额 = 分成比例 * 商品单价 * 数量
	private String commission;

	// -------返回给360的订单信息----------------结束------------------------------------------------

	// -------本地数据库的订单信息----------------开始------------------------------------------------

	private Integer id;

	private String contractId;

	private Date createTime;

	private BigDecimal contractSumMoney;

	private BigDecimal couponMoney;

	private BigDecimal contractCutSumMoney;

	private BigDecimal freight;

	private Integer localStatus;

	// -------本地数据库的订单信息----------------结束------------------------------------------------

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderUpdtime() {
		return orderUpdtime;
	}

	public void setOrderUpdtime(String orderUpdtime) {
		this.orderUpdtime = orderUpdtime;
	}

	public String getServerPrice() {
		return serverPrice;
	}

	public void setServerPrice(String serverPrice) {
		this.serverPrice = serverPrice;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public String getTotalComm() {
		return totalComm;
	}

	public void setTotalComm(String totalComm) {
		this.totalComm = totalComm;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getContractSumMoney() {
		return contractSumMoney;
	}

	public void setContractSumMoney(BigDecimal contractSumMoney) {
		this.contractSumMoney = contractSumMoney;
	}

	public BigDecimal getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(BigDecimal couponMoney) {
		this.couponMoney = couponMoney;
	}

	public BigDecimal getContractCutSumMoney() {
		return contractCutSumMoney;
	}

	public void setContractCutSumMoney(BigDecimal contractCutSumMoney) {
		this.contractCutSumMoney = contractCutSumMoney;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public Integer getLocalStatus() {
		return localStatus;
	}

	public void setLocalStatus(Integer localStatus) {
		this.localStatus = localStatus;
	}

}
