package com.shangde.edu.cus.domain;

import java.io.Serializable;

import org.apache.struts2.json.annotations.JSON;

@SuppressWarnings("serial")
public class CustomerDTO implements Serializable{
	private int todayRegistNb=0; //当天数据
	private int todayRegistWb=0;
	private int todayRegistNumber;
	private int todayContractNumber;
	private int todayPayContractNumber;
	
	private int weekRegistNb=0; //一周数据
	private int weekRegistWb=0;
	private int weekRegistNumber;
	private int weekContractNumber;
	private int weekPayContractNumber;
	
	private int monthRegistNb=0;  //当月数据
	private int monthRegistWb=0;
	private int monthRegistNumber;
	private int monthContractNumber;
	private int monthPayContractNumber;
	
	private int threeMonthRegistNb=0; //三个月数据
	private int threeMonthRegistWb=0;
	private int threeMonthRegistNumber;
	private int threeMonthContractNumber;
	private int threeMonthPayContractNumber;
	
	private int justRegistNb=0;
	private int justRegistWb=0;
	private int justRegistNumber;
	private int justContractNumber;
	private int justPayContractNumber;
	
	private int kjRegist;
	private int rlRegist;
	private int sfRegist;
	private int zkRegist;
	private int zqRegist;
	private int gkRegist;
	private int gwyRegist;
	private int jzRegist;
	
	private int kjContract;
	private int rlContract;
	private int sfContract;
	private int zkContract;
	private int zqContract;
	private int gkContract;
	private int gwyContract;
	private int jzContract;
	
	private int kjPayContract;
	private int rlPayContract;
	private int sfPayContract;
	private int zkPayContract;
	private int zqPayContract;
	private int gkPayContract;
	private int gwyPayContract;
	/**
	 * 一级建造师
	 */
	private int jzPayContract;
	
	
	private int qbRegist;
	private int qbRegistNb;
	private int qbRegistWb;
	
	private int qbContract;
	private int qbPayContract;
	private int wyzxContract;
	private int wyzxPayContract;
	
	public int getTodayRegistNumber() {
		return todayRegistNumber;
	}
	public void setTodayRegistNumber(int todayRegistNumber) {
		this.todayRegistNumber = todayRegistNumber;
	}
	public int getTodayContractNumber() {
		return todayContractNumber;
	}
	public void setTodayContractNumber(int todayContractNumber) {
		this.todayContractNumber = todayContractNumber;
	}
	public int getTodayPayContractNumber() {
		return todayPayContractNumber;
	}
	public void setTodayPayContractNumber(int todayPayContractNumber) {
		this.todayPayContractNumber = todayPayContractNumber;
	}
	public int getWeekRegistNumber() {
		return weekRegistNumber;
	}
	public void setWeekRegistNumber(int weekRegistNumber) {
		this.weekRegistNumber = weekRegistNumber;
	}
	public int getWeekContractNumber() {
		return weekContractNumber;
	}
	public void setWeekContractNumber(int weekContractNumber) {
		this.weekContractNumber = weekContractNumber;
	}
	public int getWeekPayContractNumber() {
		return weekPayContractNumber;
	}
	public void setWeekPayContractNumber(int weekPayContractNumber) {
		this.weekPayContractNumber = weekPayContractNumber;
	}
	public int getMonthRegistNumber() {
		return monthRegistNumber;
	}
	public void setMonthRegistNumber(int monthRegistNumber) {
		this.monthRegistNumber = monthRegistNumber;
	}
	public int getMonthContractNumber() {
		return monthContractNumber;
	}
	public void setMonthContractNumber(int monthContractNumber) {
		this.monthContractNumber = monthContractNumber;
	}
	public int getMonthPayContractNumber() {
		return monthPayContractNumber;
	}
	public void setMonthPayContractNumber(int monthPayContractNumber) {
		this.monthPayContractNumber = monthPayContractNumber;
	}
	public int getThreeMonthRegistNumber() {
		return threeMonthRegistNumber;
	}
	public void setThreeMonthRegistNumber(int threeMonthRegistNumber) {
		this.threeMonthRegistNumber = threeMonthRegistNumber;
	}
	public int getThreeMonthContractNumber() {
		return threeMonthContractNumber;
	}
	public void setThreeMonthContractNumber(int threeMonthContractNumber) {
		this.threeMonthContractNumber = threeMonthContractNumber;
	}
	public int getThreeMonthPayContractNumber() {
		return threeMonthPayContractNumber;
	}
	public void setThreeMonthPayContractNumber(int threeMonthPayContractNumber) {
		this.threeMonthPayContractNumber = threeMonthPayContractNumber;
	}
	public int getJustRegistNumber() {
		return justRegistNumber;
	}
	public void setJustRegistNumber(int justRegistNumber) {
		this.justRegistNumber = justRegistNumber;
	}
	public int getJustContractNumber() {
		return justContractNumber;
	}
	public void setJustContractNumber(int justContractNumber) {
		this.justContractNumber = justContractNumber;
	}
	public int getJustPayContractNumber() {
		return justPayContractNumber;
	}
	public void setJustPayContractNumber(int justPayContractNumber) {
		this.justPayContractNumber = justPayContractNumber;
	}
	public int getTodayRegistNb() {
		return todayRegistNb;
	}
	public void setTodayRegistNb(int todayRegistNb) {
		this.todayRegistNb = todayRegistNb;
	}
	public int getTodayRegistWb() {
		return todayRegistWb;
	}
	public void setTodayRegistWb(int todayRegistWb) {
		this.todayRegistWb = todayRegistWb;
	}
	public int getWeekRegistNb() {
		return weekRegistNb;
	}
	public void setWeekRegistNb(int weekRegistNb) {
		this.weekRegistNb = weekRegistNb;
	}
	public int getWeekRegistWb() {
		return weekRegistWb;
	}
	public void setWeekRegistWb(int weekRegistWb) {
		this.weekRegistWb = weekRegistWb;
	}
	public int getMonthRegistNb() {
		return monthRegistNb;
	}
	public void setMonthRegistNb(int monthRegistNb) {
		this.monthRegistNb = monthRegistNb;
	}
	public int getMonthRegistWb() {
		return monthRegistWb;
	}
	public void setMonthRegistWb(int monthRegistWb) {
		this.monthRegistWb = monthRegistWb;
	}
	public int getThreeMonthRegistNb() {
		return threeMonthRegistNb;
	}
	public void setThreeMonthRegistNb(int threeMonthRegistNb) {
		this.threeMonthRegistNb = threeMonthRegistNb;
	}
	public int getThreeMonthRegistWb() {
		return threeMonthRegistWb;
	}
	public void setThreeMonthRegistWb(int threeMonthRegistWb) {
		this.threeMonthRegistWb = threeMonthRegistWb;
	}
	public int getJustRegistNb() {
		return justRegistNb;
	}
	public void setJustRegistNb(int justRegistNb) {
		this.justRegistNb = justRegistNb;
	}
	public int getJustRegistWb() {
		return justRegistWb;
	}
	public void setJustRegistWb(int justRegistWb) {
		this.justRegistWb = justRegistWb;
	}
	public int getKjRegist() {
		return kjRegist;
	}
	public void setKjRegist(int kjRegist) {
		this.kjRegist = kjRegist;
	}
	public int getRlRegist() {
		return rlRegist;
	}
	public void setRlRegist(int rlRegist) {
		this.rlRegist = rlRegist;
	}
	public int getSfRegist() {
		return sfRegist;
	}
	public void setSfRegist(int sfRegist) {
		this.sfRegist = sfRegist;
	}
	public int getZkRegist() {
		return zkRegist;
	}
	public void setZkRegist(int zkRegist) {
		this.zkRegist = zkRegist;
	}
	public int getZqRegist() {
		return zqRegist;
	}
	public void setZqRegist(int zqRegist) {
		this.zqRegist = zqRegist;
	}
	public int getKjContract() {
		return kjContract;
	}
	public void setKjContract(int kjContract) {
		this.kjContract = kjContract;
	}
	public int getRlContract() {
		return rlContract;
	}
	public void setRlContract(int rlContract) {
		this.rlContract = rlContract;
	}
	public int getSfContract() {
		return sfContract;
	}
	public void setSfContract(int sfContract) {
		this.sfContract = sfContract;
	}
	public int getZkContract() {
		return zkContract;
	}
	public void setZkContract(int zkContract) {
		this.zkContract = zkContract;
	}
	public int getZqContract() {
		return zqContract;
	}
	public void setZqContract(int zqContract) {
		this.zqContract = zqContract;
	}
	public int getKjPayContract() {
		return kjPayContract;
	}
	public void setKjPayContract(int kjPayContract) {
		this.kjPayContract = kjPayContract;
	}
	public int getRlPayContract() {
		return rlPayContract;
	}
	public void setRlPayContract(int rlPayContract) {
		this.rlPayContract = rlPayContract;
	}
	public int getSfPayContract() {
		return sfPayContract;
	}
	public void setSfPayContract(int sfPayContract) {
		this.sfPayContract = sfPayContract;
	}
	public int getZkPayContract() {
		return zkPayContract;
	}
	public void setZkPayContract(int zkPayContract) {
		this.zkPayContract = zkPayContract;
	}
	public int getZqPayContract() {
		return zqPayContract;
	}
	public void setZqPayContract(int zqPayContract) {
		this.zqPayContract = zqPayContract;
	}
	public int getQbRegist() {
		return qbRegist;
	}
	public void setQbRegist(int qbRegist) {
		this.qbRegist = qbRegist;
	}
	public int getQbRegistNb() {
		return qbRegistNb;
	}
	public void setQbRegistNb(int qbRegistNb) {
		this.qbRegistNb = qbRegistNb;
	}
	public int getQbRegistWb() {
		return qbRegistWb;
	}
	public void setQbRegistWb(int qbRegistWb) {
		this.qbRegistWb = qbRegistWb;
	}
	public int getQbContract() {
		return qbContract;
	}
	public void setQbContract(int qbContract) {
		this.qbContract = qbContract;
	}
	public int getQbPayContract() {
		return qbPayContract;
	}
	public void setQbPayContract(int qbPayContract) {
		this.qbPayContract = qbPayContract;
	}
	public int getJzRegist() {
		return jzRegist;
	}
	public void setJzRegist(int jzRegist) {
		this.jzRegist = jzRegist;
	}
	public int getJzContract() {
		return jzContract;
	}
	public void setJzContract(int jzContract) {
		this.jzContract = jzContract;
	}
	public int getJzPayContract() {
		return jzPayContract;
	}
	public void setJzPayContract(int jzPayContract) {
		this.jzPayContract = jzPayContract;
	}
	public int getWyzxContract() {
		return wyzxContract;
	}
	public void setWyzxContract(int wyzxContract) {
		this.wyzxContract = wyzxContract;
	}
	public int getWyzxPayContract() {
		return wyzxPayContract;
	}
	public void setWyzxPayContract(int wyzxPayContract) {
		this.wyzxPayContract = wyzxPayContract;
	}
	public int getGkRegist() {
		return gkRegist;
	}
	public void setGkRegist(int gkRegist) {
		this.gkRegist = gkRegist;
	}
	public int getGwyRegist() {
		return gwyRegist;
	}
	public void setGwyRegist(int gwyRegist) {
		this.gwyRegist = gwyRegist;
	}
	public int getGkContract() {
		return gkContract;
	}
	public void setGkContract(int gkContract) {
		this.gkContract = gkContract;
	}
	public int getGwyContract() {
		return gwyContract;
	}
	public void setGwyContract(int gwyContract) {
		this.gwyContract = gwyContract;
	}
	public int getGkPayContract() {
		return gkPayContract;
	}
	public void setGkPayContract(int gkPayContract) {
		this.gkPayContract = gkPayContract;
	}
	public int getGwyPayContract() {
		return gwyPayContract;
	}
	public void setGwyPayContract(int gwyPayContract) {
		this.gwyPayContract = gwyPayContract;
	}
	
	
	
}
