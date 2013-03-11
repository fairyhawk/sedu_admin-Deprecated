package com.shangde.edu.cus.condition;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import com.shangde.common.vo.PageQuery;
/**
 * 发短信运用的Query
 * @author Liming
 *	Date：2011-9-28
 */
public class QueryNoteContentCondition extends PageQuery{
	private int noteId;
	private String noteContent;
	private int status;
	private String noteDespatcher;
	private String noteAddTime;
	//为了多田间查询用到
	private String endTime;
	private int cellId;
	private String sellPhone;
	private int sellStatus;
	private  String keyword;
	//~~~~~~~~~~
	private String word;
	private int searchId;
	
	
	private  int key=0;
	public String getWord() {
		return word;
	}
	public void setWord(String word) throws UnsupportedEncodingException {
		this.word =URLDecoder.decode(word,"UTF-8").replace(" ", "");
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
	public String getNoteAddTime() {
		return noteAddTime;
	}
	public void setNoteAddTime(String noteAddTime) {
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
	public String getNoteDespatcher() {
		return noteDespatcher;
	}
	public void setNoteDespatcher(String noteDespatcher) {
		this.noteDespatcher = noteDespatcher;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getSearchId() {
		return searchId;
	}
	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
}
