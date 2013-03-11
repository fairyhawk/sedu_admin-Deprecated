package com.shangde.edu.dis.domain;

/**
 * 
 * @author Libg
 * 
 * @category 标签
 */
public class Tags implements java.io.Serializable {

	private int id;
	private String name;// 标签名称
	private int count;// 个数，有程序统计实现
	private int status;// 状态

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
