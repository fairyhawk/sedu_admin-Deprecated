package com.shangde.edu.dis.domain;

import java.io.Serializable;

public class VoteDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3449801607100603023L;
	private int id;
	private int voteId;
	private int voteOrder;
	private String voteOption;
	private int voteCount;

	/**
	 * 扩展属性，百分比（存储通过算法计算出来的 *%）
	 */
	private String percentage = "0";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVoteId() {
		return voteId;
	}

	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}

	public int getVoteOrder() {
		return voteOrder;
	}

	public void setVoteOrder(int voteOrder) {
		this.voteOrder = voteOrder;
	}

	public String getVoteOption() {
		return voteOption;
	}

	public void setVoteOption(String voteOption) {
		this.voteOption = voteOption;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
}
