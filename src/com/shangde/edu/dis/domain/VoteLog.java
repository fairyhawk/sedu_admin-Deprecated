package com.shangde.edu.dis.domain;

import java.io.Serializable;

public class VoteLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5378373736003881885L;
	private int id;
	private int voteId;
	private String voteDetailId;
	private int cusId;

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

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getVoteDetailId() {
		return voteDetailId;
	}

	public void setVoteDetailId(String voteDetailId) {
		this.voteDetailId = voteDetailId;
	}
}
