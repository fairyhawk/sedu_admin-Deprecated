package com.shangde.edu.kno.dto;

import java.io.Serializable;
import java.util.List;

import com.shangde.edu.exam.domain.Options;

public class QstRelDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 试题ID
	 */
    private int qstId;
	/**
	 * 选项列表
	 */
	private List<Options> options;
	/**
     * 试题难度
     */
    private int level;
	/**
     * 试题内容
     */
    private String qstContent;
    /**
     * 正确选项
     */
    private String isAsr;
    /**
     * 试题类型
     */
    private int qstType;
    /**
     * 解析
     */
    private String wrongJude;
	public int getQstId() {
		return qstId;
	}
	public void setQstId(int qstId) {
		this.qstId = qstId;
	}
	public List<Options> getOptions() {
		return options;
	}
	public void setOptions(List<Options> options) {
		this.options = options;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getQstContent() {
		return qstContent;
	}
	public void setQstContent(String qstContent) {
		this.qstContent = qstContent;
	}
	public String getIsAsr() {
		return isAsr;
	}
	public void setIsAsr(String isAsr) {
		this.isAsr = isAsr;
	}
	public int getQstType() {
		return qstType;
	}
	public void setQstType(int qstType) {
		this.qstType = qstType;
	}
	public String getWrongJude() {
		return wrongJude;
	}
	public void setWrongJude(String wrongJude) {
		this.wrongJude = wrongJude;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
