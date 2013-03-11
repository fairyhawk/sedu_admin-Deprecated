package com.shangde.edu.card.domain;

public class CardMainModel extends CardMain {
	private static final long serialVersionUID = 1L;
	private String cardUserStatusContent;//后台操作状态
	public String getCardUserStatusContent() {
		return cardUserStatusContent;
	}
	public void setCardUserStatusContent(String cardUserStatusContent) {
		this.cardUserStatusContent = cardUserStatusContent;
	}
}
