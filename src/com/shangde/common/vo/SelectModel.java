package com.shangde.common.vo;

/**
 * select组件使用
 * 
 * @author Lee
 * 
 */
public class SelectModel {

	/**
	 * 显示的文本
	 */
	private String text;
	/**
	 * 值
	 */
	private String value;

	public SelectModel() {

	}

	public SelectModel(String value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
