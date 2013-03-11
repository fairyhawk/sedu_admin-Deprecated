package com.shangde.edu.card.domain;

import java.io.Serializable;

public class CardCourseSell implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;//主键ID
	private Integer cardMainId;//卡ID
	private Integer sellId;//商品ID
	public Integer getId() {
		return id;
	}
	public Integer getCardMainId() {
		return cardMainId;
	}
	public void setCardMainId(Integer cardMainId) {
		this.cardMainId = cardMainId;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSellId() {
		return sellId;
	}
	public void setSellId(Integer sellId) {
		this.sellId = sellId;
	}
	
}
