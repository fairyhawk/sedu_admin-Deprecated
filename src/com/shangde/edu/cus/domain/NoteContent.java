package com.shangde.edu.cus.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *  发信息内容entity
 * @author liming
 * Date：2011-9-28
 *
 */
public class NoteContent implements Serializable{
	private int noteId;
	private String noteContent;
	private int status;
	private String noteDespatcher;
	private Date noteAddTime;
	private int userId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
}
