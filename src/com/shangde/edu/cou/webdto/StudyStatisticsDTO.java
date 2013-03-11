package com.shangde.edu.cou.webdto;

public class StudyStatisticsDTO {
	/**
	 * 学员总数
	 */
	private int totalUserNum;
	
	/**
	 * 多一节的数量
	 */
	private int thanOneNum;
	
	/**
	 * 多两节的数量
	 */
	private int thanTwoNum;
	
	/**
	 * 大于三节的人数
	 */
	private int thanThreeNum;
	
	/**
	 * 大于三节的人数
	 */
	private int userSelfNum;

	public int getTotalUserNum() {
		return totalUserNum;
	}

	public void setTotalUserNum(int totalUserNum) {
		this.totalUserNum = totalUserNum;
	}

	public int getThanOneNum() {
		return thanOneNum;
	}

	public void setThanOneNum(int thanOneNum) {
		this.thanOneNum = thanOneNum;
	}

	public int getThanTwoNum() {
		return thanTwoNum;
	}

	public void setThanTwoNum(int thanTwoNum) {
		this.thanTwoNum = thanTwoNum;
	}

	public int getThanThreeNum() {
		return thanThreeNum;
	}

	public void setThanThreeNum(int thanThreeNum) {
		this.thanThreeNum = thanThreeNum;
	}

	public int getUserSelfNum() {
		return userSelfNum;
	}

	public void setUserSelfNum(int userSelfNum) {
		this.userSelfNum = userSelfNum;
	}
}
