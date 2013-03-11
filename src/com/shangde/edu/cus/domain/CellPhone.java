package com.shangde.edu.cus.domain;

import java.io.Serializable;
import java.util.Date;
/**
 *  手机号and邮箱entity
 * @author liming
 * Date：2011-9-28
 *
 */
public class CellPhone implements Serializable{
	private int cellId;
	private String sellPhone;
	private int sellStatus;
	private int noteContentId;
	private String cellEmail;
	private Date sellAddTime;
	public int getCellId() {
		return cellId;
	}
	public void setCellId(int cellId) {
		this.cellId = cellId;
	}
	public String getSellPhone() {
		return sellPhone;
	}
	public void setSellPhone(String sellPhone) {
		this.sellPhone = sellPhone;
	}
	public int getSellStatus() {
		return sellStatus;
	}
	public void setSellStatus(int sellStatus) {
		this.sellStatus = sellStatus;
	}
	public int getNoteContentId() {
		return noteContentId;
	}
	public void setNoteContentId(int noteContentId) {
		this.noteContentId = noteContentId;
	}
	public String getCellEmail() {
		return cellEmail;
	}
	public void setCellEmail(String cellEmail) {
		this.cellEmail = cellEmail;
	}
	public Date getSellAddTime() {
		return sellAddTime;
	}
	public void setSellAddTime(Date sellAddTime) {
		this.sellAddTime = sellAddTime;
	}

}
