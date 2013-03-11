package com.shangde.common.util;

public class Result<E> {

	private boolean jumpType;
	private String returnMessage;
	private E entity;

	private String jumpUrl;

	
	public Result(){
		
	}

	public Result(boolean jumpType, String returnMessage, String jumpUrl,
			E entity) {
		this.jumpType = jumpType;
		this.returnMessage = returnMessage;
		this.jumpUrl = jumpUrl;
		this.entity = entity;
	}

	public boolean getJumpType() {
		return jumpType;
	}

	public void setJumpType(boolean jumpType) {
		this.jumpType = jumpType;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}
	
	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}
	
}