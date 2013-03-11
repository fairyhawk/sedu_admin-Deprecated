package com.shangde.edu.sys.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class StatisticsWebFromDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 点击数 */
	private Integer clickCount;
	/** WebFrom */
	private String webFrom;
	/** 注册数量 */
	private Integer registerCount;
	/** 点击注册率 */
	private String registerRate;
	/** 订单数量 */
	private Integer contractCount;
	/** 订单总金额 */
	private BigDecimal contractMoneySum;
	/** 订单总金额（格式化） */
	private String contractMoneySumStr;
	/** 有效订单数量（已付款订单数量） */
	private Integer payedContractCount;
	/** 有效订单总金额（已付款订单总金额） */
	private BigDecimal payedContractMoneySum;
	/** 有效订单总金额（已付款订单总金额）（格式化） */
	private String payedContractMoneySumStr;

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public String getWebFrom() {
		return webFrom;
	}

	public void setWebFrom(String webFrom) {
		this.webFrom = webFrom;
	}

	public Integer getRegisterCount() {
		return registerCount;
	}

	public void setRegisterCount(Integer registerCount) {
		this.registerCount = registerCount;
	}

	public Integer getContractCount() {
		return contractCount;
	}

	public void setContractCount(Integer contractCount) {
		this.contractCount = contractCount;
	}

	public BigDecimal getContractMoneySum() {
		return contractMoneySum;
	}

	public void setContractMoneySum(BigDecimal contractMoneySum) {
		this.contractMoneySum = contractMoneySum;
	}

	public Integer getPayedContractCount() {
		return payedContractCount;
	}

	public void setPayedContractCount(Integer payedContractCount) {
		this.payedContractCount = payedContractCount;
	}

	public BigDecimal getPayedContractMoneySum() {
		return payedContractMoneySum;
	}

	public void setPayedContractMoneySum(BigDecimal payedContractMoneySum) {
		this.payedContractMoneySum = payedContractMoneySum;
	}

	public String getRegisterRate() {
		if (clickCount == 0) {
			registerRate = "无效"; // 除数为0时，显示无效
		} else {
			double rate = (double) registerCount / (double) clickCount;
			rate *= 100;
			DecimalFormat df = new DecimalFormat("###0.00");
			registerRate = df.format(rate) + "%"; // 格式化，保留两位小数，并添加百分号
		}
		return registerRate;
	}

	public void setRegisterRate(String registerRate) {
		this.registerRate = registerRate;
	}

	public String getContractMoneySumStr() {
		DecimalFormat df = new DecimalFormat("###0.00");
		if (contractMoneySum == null) {
			return "0.00";
		}
		if (contractMoneySum != null) {
			contractMoneySumStr = df.format(contractMoneySum);
		}
		return contractMoneySumStr;
	}

	public void setContractMoneySumStr(String contractMoneySumStr) {
		this.contractMoneySumStr = contractMoneySumStr;
	}

	public String getPayedContractMoneySumStr() {
		DecimalFormat df = new DecimalFormat("###0.00");
		if (payedContractMoneySum == null) {
			return "0.00";
		}
		if (payedContractMoneySum != null) {
			payedContractMoneySumStr = df.format(payedContractMoneySum);
		}
		return payedContractMoneySumStr;
	}

	public void setPayedContractMoneySumStr(String payedContractMoneySumStr) {
		this.payedContractMoneySumStr = payedContractMoneySumStr;
	}

}
