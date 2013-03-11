package com.shangde.edu.dis.domain;

import java.io.Serializable;

public class Vote implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3725491313538070427L;
	private int id;
	private int voteType;// 投票类型
	private int topicId;// 话题ID
	private String referenceResults;// 参考结果
	private int status;// 状态 1=显示投票结果,0=不显示

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVoteType() {
		return voteType;
	}

	public void setVoteType(int voteType) {
		this.voteType = voteType;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getReferenceResults() {
		return referenceResults;
	}

	public void setReferenceResults(String referenceResults) {
		this.referenceResults = referenceResults;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
