package com.shangde.edu.res.domain;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class Status  extends PageQuery{
	/** 开始时间 */
	private Date beginDate;
	/** 结束时间 */
	private Date endDate;
	/** 当天 */
	private String now;
	/** 当周 */
	private String week;
	/** 当月 */
	private String month;
	
	private int Id;
	
	private Date data;
	
	private int noteNo;
	private int noteLoad;
	
	private int biji;
	
	private int jiangyi;
	
	private int pingjia;
	
	private int status;

	
	private int ceshi;
	
	private int qiehuan;
	
	private int zice;
	
	private int tiwen;
	
	
	
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	

	public int getNoteNo() {
		return noteNo;
	}

	public void setNoteNo(int noteNo) {
		this.noteNo = noteNo;
	}

	public int getNoteLoad() {
		return noteLoad;
	}

	public void setNoteLoad(int noteLoad) {
		this.noteLoad = noteLoad;
	}

	public int getBiji() {
		return biji;
	}

	public void setBiji(int biji) {
		this.biji = biji;
	}

	public int getJiangyi() {
		return jiangyi;
	}

	public void setJiangyi(int jiangyi) {
		this.jiangyi = jiangyi;
	}

	public int getPingjia() {
		return pingjia;
	}

	public void setPingjia(int pingjia) {
		this.pingjia = pingjia;
	}

	public int getCeshi() {
		return ceshi;
	}

	public void setCeshi(int ceshi) {
		this.ceshi = ceshi;
	}

	

	public int getQiehuan() {
		return qiehuan;
	}

	public void setQiehuan(int qiehuan) {
		this.qiehuan = qiehuan;
	}

	public int getZice() {
		return zice;
	}

	public void setZice(int zice) {
		this.zice = zice;
	}

	public int getTiwen() {
		return tiwen;
	}

	public void setTiwen(int tiwen) {
		this.tiwen = tiwen;
	}

	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	
	
	
}






















