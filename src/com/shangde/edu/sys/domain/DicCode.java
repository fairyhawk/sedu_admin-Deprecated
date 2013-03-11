package com.shangde.edu.sys.domain;

import java.io.Serializable;

public class DicCode implements Serializable {

	private static final long serialVersionUID = 1L;
	private String dicType;//字典类型
	private String dicCode;//字典编码
	private String dicContent;//字典内容
	private Integer dicSorts;//排序编号
	public String getDicType() {
		return dicType;
	}
	public void setDicType(String dicType) {
		this.dicType = dicType;
	}
	public String getDicCode() {
		return dicCode;
	}
	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}
	public String getDicContent() {
		return dicContent;
	}
	public void setDicContent(String dicContent) {
		this.dicContent = dicContent;
	}
	public Integer getDicSorts() {
		return dicSorts;
	}
	public void setDicSorts(Integer dicSorts) {
		this.dicSorts = dicSorts;
	}
}
