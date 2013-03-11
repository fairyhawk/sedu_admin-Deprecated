package com.shangde.edu.feed.domain;

import java.io.Serializable;

/**
 * 模板（邮件内容模板实体）
 * 
 */
public class Template implements Serializable {

	private int id;// id值
	private String name;// 名称
	private String fileName;// 文件名称
	private String content;// 内容
	private String remark;// 备注
	private java.util.Date pubdate;// 发布时间
	private java.util.Date modified;// 修改时间

	/**
	 * 获取id值
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置id值
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获取内容值
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容值
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取名称
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取文件名称
	 * 
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 设置文件名称
	 * 
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 获取备注
	 * 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置模板备注
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取发布时间
	 * 
	 * @return
	 */
	public java.util.Date getPubdate() {
		return pubdate;
	}

	/**
	 * 设置发布时间
	 * 
	 * @param pubdate
	 */
	public void setPubdate(java.util.Date pubdate) {
		this.pubdate = pubdate;
	}

	/**
	 * 获取修改时间
	 * 
	 * @return
	 */
	public java.util.Date getModified() {
		return modified;
	}

	/**
	 * 设置修改时间
	 * 
	 * @param modified
	 */
	public void setModified(java.util.Date modified) {
		this.modified = modified;
	}
}
