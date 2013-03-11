package com.shangde.edu.count.dto;

import java.io.Serializable;

public class SellWayCountDTO implements Serializable{
	

	private int zsSum;  //订单赠送数量总和
	
	private int zfbSum; //支付宝总和
	
	private int hdfkSum; //货到付款总和
	
	private int wyzxSum; //网银在线总和
	
	private int kqSum; //块钱总和

	private int yfZsSum; //已付赠送总和
	
	private int yfZfbSum; //已付款支付宝总数
	
	private int yfHdfkSum; //已付货到付款总数
	
	private int yfWyzxSum; //已付款网银在线数量
	
	private int yfKqSum; //已付款块钱数量
	
	private int tfSum; //退费总数
	
	private int contractSum; //订单总数
	
	private int yfContractSum; //已付订单总数
	
	private String startCountTime; //开始时间
	
	private String endCountTime; //结束时间
	
	private String subjectName;
	
	private int sellPriceSum;
	
	private String sellName;
	
	private int TF;
	
	private String convertContract;
	private int convertSum;
	private String YFconverrContract;
	
	private String contractFromUrl; //订单来源域名
	
	private String cusFromUrl;	//学员注册域名
	
	private String zjConvertSum;
	private String zjYfConvertSum;
	
	
	/**
	 * 订单总数
	 */
	private int subjectContractSum;
	
	
	/**
	 *已付订单总数
	 */
	private int yfSubjectContractSum;
	
	private int zjSum;  //订单总计  订单总数
	private int zjHdfkSum; //订单总计 货到付款总数
	private int zjZfbSum; //订单总计 支付宝总数
	private int zjZsSum;
	private int zjKqSum;
	private int zjWyzxSum;
	private int zjSellPriceSum;
	private int zjWfSum;
	private int zjWfKqSum;
	private int zjWfZfbSum;
	private int zjWfHdfkSum;
	private int zjWfWyzxSum;
	public int getZsSum() {
		return zsSum;
	}
	public void setZsSum(int zsSum) {
		this.zsSum = zsSum;
	}
	public int getZfbSum() {
		return zfbSum;
	}
	public void setZfbSum(int zfbSum) {
		this.zfbSum = zfbSum;
	}
	public int getHdfkSum() {
		return hdfkSum;
	}
	public void setHdfkSum(int hdfkSum) {
		this.hdfkSum = hdfkSum;
	}
	public int getWyzxSum() {
		return wyzxSum;
	}
	public void setWyzxSum(int wyzxSum) {
		this.wyzxSum = wyzxSum;
	}
	public int getKqSum() {
		return kqSum;
	}
	public void setKqSum(int kqSum) {
		this.kqSum = kqSum;
	}
	public int getYfZsSum() {
		return yfZsSum;
	}
	public void setYfZsSum(int yfZsSum) {
		this.yfZsSum = yfZsSum;
	}
	public int getYfZfbSum() {
		return yfZfbSum;
	}
	public void setYfZfbSum(int yfZfbSum) {
		this.yfZfbSum = yfZfbSum;
	}
	public int getYfHdfkSum() {
		return yfHdfkSum;
	}
	public void setYfHdfkSum(int yfHdfkSum) {
		this.yfHdfkSum = yfHdfkSum;
	}
	public int getYfWyzxSum() {
		return yfWyzxSum;
	}
	public void setYfWyzxSum(int yfWyzxSum) {
		this.yfWyzxSum = yfWyzxSum;
	}
	public int getYfKqSum() {
		return yfKqSum;
	}
	public void setYfKqSum(int yfKqSum) {
		this.yfKqSum = yfKqSum;
	}
	public int getTfSum() {
		return tfSum;
	}
	public void setTfSum(int tfSum) {
		this.tfSum = tfSum;
	}
	public int getContractSum() {
		return contractSum;
	}
	public void setContractSum(int contractSum) {
		this.contractSum = contractSum;
	}
	public int getYfContractSum() {
		return yfContractSum;
	}
	public void setYfContractSum(int yfContractSum) {
		this.yfContractSum = yfContractSum;
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
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getSellPriceSum() {
		return sellPriceSum;
	}
	public void setSellPriceSum(int sellPriceSum) {
		this.sellPriceSum = sellPriceSum;
	}
	public int getTF() {
		return TF;
	}
	public void setTF(int tf) {
		TF = tf;
	}
	public String getConvertContract() {
		return convertContract;
	}
	public void setConvertContract(String convertContract) {
		this.convertContract = convertContract;
	}
	public int getConvertSum() {
		return convertSum;
	}
	public void setConvertSum(int convertSum) {
		this.convertSum = convertSum;
	}
	public String getYFconverrContract() {
		return YFconverrContract;
	}
	public void setYFconverrContract(String fconverrContract) {
		YFconverrContract = fconverrContract;
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
	public int getSubjectContractSum() {
		return subjectContractSum;
	}
	public void setSubjectContractSum(int subjectContractSum) {
		this.subjectContractSum = subjectContractSum;
	}
	public int getYfSubjectContractSum() {
		return yfSubjectContractSum;
	}
	public void setYfSubjectContractSum(int yfSubjectContractSum) {
		this.yfSubjectContractSum = yfSubjectContractSum;
	}
	public int getZjSum() {
		return zjSum;
	}
	public void setZjSum(int zjSum) {
		this.zjSum = zjSum;
	}
	public int getZjHdfkSum() {
		return zjHdfkSum;
	}
	public void setZjHdfkSum(int zjHdfkSum) {
		this.zjHdfkSum = zjHdfkSum;
	}
	public int getZjZfbSum() {
		return zjZfbSum;
	}
	public void setZjZfbSum(int zjZfbSum) {
		this.zjZfbSum = zjZfbSum;
	}
	public int getZjZsSum() {
		return zjZsSum;
	}
	public void setZjZsSum(int zjZsSum) {
		this.zjZsSum = zjZsSum;
	}
	public int getZjKqSum() {
		return zjKqSum;
	}
	public void setZjKqSum(int zjKqSum) {
		this.zjKqSum = zjKqSum;
	}
	public int getZjWyzxSum() {
		return zjWyzxSum;
	}
	public void setZjWyzxSum(int zjWyzxSum) {
		this.zjWyzxSum = zjWyzxSum;
	}
	public int getZjSellPriceSum() {
		return zjSellPriceSum;
	}
	public void setZjSellPriceSum(int zjSellPriceSum) {
		this.zjSellPriceSum = zjSellPriceSum;
	}
	public int getZjWfSum() {
		return zjWfSum;
	}
	public void setZjWfSum(int zjWfSum) {
		this.zjWfSum = zjWfSum;
	}
	public int getZjWfKqSum() {
		return zjWfKqSum;
	}
	public void setZjWfKqSum(int zjWfKqSum) {
		this.zjWfKqSum = zjWfKqSum;
	}
	public int getZjWfZfbSum() {
		return zjWfZfbSum;
	}
	public void setZjWfZfbSum(int zjWfZfbSum) {
		this.zjWfZfbSum = zjWfZfbSum;
	}
	public int getZjWfHdfkSum() {
		return zjWfHdfkSum;
	}
	public void setZjWfHdfkSum(int zjWfHdfkSum) {
		this.zjWfHdfkSum = zjWfHdfkSum;
	}
	public int getZjWfWyzxSum() {
		return zjWfWyzxSum;
	}
	public void setZjWfWyzxSum(int zjWfWyzxSum) {
		this.zjWfWyzxSum = zjWfWyzxSum;
	}
	public String getSellName() {
		return sellName;
	}
	public void setSellName(String sellName) {
		this.sellName = sellName;
	}
	public String getZjConvertSum() {
		return zjConvertSum;
	}
	public void setZjConvertSum(String zjConvertSum) {
		this.zjConvertSum = zjConvertSum;
	}
	public String getZjYfConvertSum() {
		return zjYfConvertSum;
	}
	public void setZjYfConvertSum(String zjYfConvertSum) {
		this.zjYfConvertSum = zjYfConvertSum;
	}
}
