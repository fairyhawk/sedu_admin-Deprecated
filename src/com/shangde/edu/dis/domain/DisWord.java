package com.shangde.edu.dis.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 敏感词
 * @author changfu.dai
 *
 */
public class DisWord implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;						//主键编号、自增
	private String word;					//敏感字
	private Date createTime;				//创建时间
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
