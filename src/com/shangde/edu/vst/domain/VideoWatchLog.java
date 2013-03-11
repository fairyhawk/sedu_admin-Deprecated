package com.shangde.edu.vst.domain;

import java.math.BigDecimal;

public class VideoWatchLog {
	
	/*1、项目名称
	2、	登录的有效购买用户数
	3、	登录看视频的有效购买用户数
	4、	看收费视频的总时长
	5、	截至到目前有效购买用户的总数
	6、	无购买课程登录的用户总数
	7、	无购买课程登录看试听课程的用户总数
	8、	登录看试听课程的总时长
	
	10、平均观看收费视频的时长
	13、	平均观看试听课程的时长
	9、	观看收费视频的比例
	12、观看试听课程的比例
	*/
	
	private String subjectName;//项目名称
	private int buylogincount;// 登录的有效购买用户数
	private int watchusercount;// 登录观看视频的有效购买用户数
	private int buywatchtime;// 观看收费视频总时长
	private int buyallcount;// 截止到目前所有的购买用户数
	
	private int nobuylogincount;// 无购买课程登录的用户总数
	private int shitingusercount;// 无购买课程登录看试听课程的用户总数
	private int shitingwatchtime;// 登录看试听课程的总时长
	
	private BigDecimal ave_buywatchtime;// 平均观看收费视频的时长
	private BigDecimal ave_shitingwatchtime;// 平均观看试听课程的时长
	private BigDecimal scale_buy;// 观看收费视频的比例
	private BigDecimal scale_shiting;// 观看试听课程的比例
	
	private int subjectId;
	private int logincount;// 登录的所有有用户
	
	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getLogincount() {
		return logincount;
	}

	public void setLogincount(int logincount) {
		this.logincount = logincount;
	}

	public int getBuylogincount() {
		return buylogincount;
	}

	public void setBuylogincount(int buylogincount) {
		this.buylogincount = buylogincount;
	}

	public int getWatchusercount() {
		return watchusercount;
	}

	public void setWatchusercount(int watchusercount) {
		this.watchusercount = watchusercount;
	}

	public int getNobuylogincount() {
		return nobuylogincount;
	}

	public void setNobuylogincount(int nobuylogincount) {
		this.nobuylogincount = nobuylogincount;
	}

	public int getBuyallcount() {
		return buyallcount;
	}

	public void setBuyallcount(int buyallcount) {
		this.buyallcount = buyallcount;
	}

	public int getShitingusercount() {
		return shitingusercount;
	}

	public void setShitingusercount(int shitingusercount) {
		this.shitingusercount = shitingusercount;
	}

	public int getShitingwatchtime() {
		return shitingwatchtime;
	}

	public void setShitingwatchtime(int shitingwatchtime) {
		this.shitingwatchtime = shitingwatchtime;
	}

	public int getBuywatchtime() {
		return buywatchtime;
	}

	public void setBuywatchtime(int buywatchtime) {
		this.buywatchtime = buywatchtime;
	}

	public BigDecimal getAve_buywatchtime() {
		return ave_buywatchtime;
	}

	public void setAve_buywatchtime(BigDecimal ave_buywatchtime) {
		this.ave_buywatchtime = ave_buywatchtime;
	}

	public BigDecimal getAve_shitingwatchtime() {
		return ave_shitingwatchtime;
	}

	public void setAve_shitingwatchtime(BigDecimal ave_shitingwatchtime) {
		this.ave_shitingwatchtime = ave_shitingwatchtime;
	}

	public BigDecimal getScale_buy() {
		return scale_buy;
	}

	public void setScale_buy(BigDecimal scale_buy) {
		this.scale_buy = scale_buy;
	}

	public BigDecimal getScale_shiting() {
		return scale_shiting;
	}

	public void setScale_shiting(BigDecimal scale_shiting) {
		this.scale_shiting = scale_shiting;
	}

}
