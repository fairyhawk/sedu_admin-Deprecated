package com.shangde.edu.cms.domain;

import java.io.Serializable;

public class ArticleDTO implements Serializable {

	private Article pre;
	
	private Article current;
	
	private Article after;

	public Article getPre() {
		return pre;
	}

	public void setPre(Article pre) {
		this.pre = pre;
	}

	public Article getCurrent() {
		return current;
	}

	public void setCurrent(Article current) {
		this.current = current;
	}

	public Article getAfter() {
		return after;
	}

	public void setAfter(Article after) {
		this.after = after;
	}
	
}
