package com.shangde.edu.ad.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 360CPS订单查询结果。
 * 
 * @author ZHENG QIANG
 */
public class CPS360OrderQueryDTO {

	// -------返回给360的订单信息----------------开始------------------------------------------------

	// 合作网站编号，360分配
	private String bid;

	// 360用户ID
	private String qid;

	// 360业务编号
	private String qihooId;

	// 扩展字段，必须回传跳转接口中传递过来的值
	private String ext;

	// 订单号
	private String orderId;

	// 下单时间
	private String orderTime;

	// 订单最后更新时间
	private String orderUpdtime;

	// 总佣金,按照用户实际应支付的商品价格计算,即扣除优惠券和运费部分,单位：元
	private String totalComm;

	// 佣金明细。 其中分成金额 = 分成比例 * 商品单价 * 数量
	private String commission;

	// 订单商品的详细信息
	// 说明：多个商品用“|”隔开，单个商品之间用“,”逗号隔开
	// 商品URL地址需要预先进行URL编码
	// 商品分类名称显示从顶级分类到商品当前分类的所有分类名称，用"_"分隔
	// 商品名称和商品分类中若有","和"|"需过滤
	private String pInfo;

	// 服务费用，如运送费等,单位:元
	private String serverPrice;

	// 订单应付总额 = 订单总价(不含服务费，运费等) - 优惠券金额,单位:元
	private String totalPrice;

	// 用户使用优惠卡或积分抵充商品的金额 单位:元
	private String coupon;

	// 合作方订单状态
	// 新订单:1;
	// 已确认(尚未发货和支付):2;
	// 已发货:3;
	// 已支付:4;
	// 已完成:5;
	// 已作废(已取消):6
	private String status;

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

	private String wi;

	private String src;

	private String cid;

	// -------本地数据库的订单信息----------------结束------------------------------------------------

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getQihooId() {
		return qihooId;
	}

	public void setQihooId(String qihooId) {
		this.qihooId = qihooId;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

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

	public String getPInfo() {
		return pInfo;
	}

	public void setPInfo(String info) {
		pInfo = info;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getWi() {
		return wi;
	}

	public void setWi(String wi) {
		this.wi = wi;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

}
