package com.shangde.edu.sys.dto;

import java.io.Serializable;

/**
 * 键值类
 * @author cxs
 *
 */
public class KeyValueDTO implements Serializable{
	private int key;
	private String value;
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
