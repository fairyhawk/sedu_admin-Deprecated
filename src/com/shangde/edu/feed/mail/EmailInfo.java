package com.shangde.edu.feed.mail;

import java.io.Serializable;


/**
 * 邮件简单实体信息
 * 
 * @author Basil
 *
 */
public class EmailInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 邮件地址
	private String addr;
	// 抄送
	private String cc;
	// 密送
	private String bcc;
	// 邮件主题
	private String subject;
	// 邮件内容
	private String content;
	// 邮件标识(任务编号)
	private String mark;

	/**
	 * @return the mark
	 */
	public String getMark() {
		return mark;
	}

	/**
	 * @param mark the mark to set
	 */
	public void setMark(String mark) {
		this.mark = mark;
	}

	// 无附件
	public String[] getAddress() {
		if (this.addr.length() > 0) {
			addr = addr.trim();
			addr.replaceAll("；", ",");
			addr.replaceAll(" ", ",");
			addr.replaceAll(",", ",");
			addr.replaceAll("，", ",");
			addr.replaceAll("|", ",");
			
			return addr.split(",");
		}
		
		return null;
	}

	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the cc
	 */
	public String getCc() {
		return cc;
	}

	/**
	 * @param cc the cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}

	/**
	 * @return the bcc
	 */
	public String getBcc() {
		return bcc;
	}

	/**
	 * @param bcc the bcc to set
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
