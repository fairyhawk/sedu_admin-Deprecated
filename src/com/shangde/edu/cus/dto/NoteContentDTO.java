package com.shangde.edu.cus.dto;

import java.io.Serializable;
import java.util.Date;
/**
 *  发信息内容and手机号DTO
 * @author liming
 * Date：2011-9-28
 *
 */
public class NoteContentDTO implements Serializable {
	private int noteId;
	private String noteContent;
	private int status;
	private String noteDespatcher;
	private Date noteAddTime;
	private int cellId;
	private String sellPhone;
	private int sellStatus;
	private int noteContentId;
	private Date sellAddTime;
	private int sellId;
	private String sellName;
	
	
	private String allAndsingleStatus;
	private String fanhui;
	private int slll;
	private int zhuangtai;
	private String discern;
	private int singleCount;
	private int singleRepeatCount;
	public int getSingleRepeatCount() {
		return singleRepeatCount;
	}
	public void setSingleRepeatCount(int singleRepeatCount) {
		this.singleRepeatCount = singleRepeatCount;
	}
	public int getSingleCount() {
		return singleCount;
	}
	public void setSingleCount(int singleCount) {
		this.singleCount = singleCount;
	}
	public String getDiscern() {
		return discern;
	}
	public void setDiscern(String discern) {
		this.discern = discern;
	}
	public int getZhuangtai() {
		return zhuangtai;
	}
	public void setZhuangtai(int zhuangtai) {
		this.zhuangtai = zhuangtai;
	}
	public int getSlll() {
		return slll;
	}
	public void setSlll(int slll) {
		this.slll = slll;
	}
	public String getAllAndsingleStatus() {
		return allAndsingleStatus;
	}
	public void setAllAndsingleStatus(String allAndsingleStatus) {
		this.allAndsingleStatus = allAndsingleStatus;
	}
	public String getFanhui() {
		return fanhui;
	}
	public void setFanhui(String fanhui) {
		this.fanhui = fanhui;
	}
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public String getNoteContent() {
		return noteContent;
	}
	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getNoteDespatcher() {
		return noteDespatcher;
	}
	public void setNoteDespatcher(String noteDespatcher) {
		this.noteDespatcher = noteDespatcher;
	}
	public Date getNoteAddTime() {
		return noteAddTime;
	}
	public void setNoteAddTime(Date noteAddTime) {
		this.noteAddTime = noteAddTime;
	}
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
	public Date getSellAddTime() {
		return sellAddTime;
	}
	public void setSellAddTime(Date sellAddTime) {
		this.sellAddTime = sellAddTime;
	}
	public int getSellId() {
		return sellId;
	}
	public void setSellId(int sellId) {
		this.sellId = sellId;
	}
	public String getSellName() {
		return sellName;
	}
	public void setSellName(String sellName) {
		this.sellName = sellName;
	}
}
