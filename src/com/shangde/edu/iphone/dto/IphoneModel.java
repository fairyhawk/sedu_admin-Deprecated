package com.shangde.edu.iphone.dto;

import java.io.Serializable;
import java.sql.Date;

public class IphoneModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4624657017484684031L;
	
	private int courseNum;
	
	private String teachers;
	
	private String imageURL;
	
	private String videoName;
	
	private int playTimes;
	
	private String radioURL;
	
	private int videoId;
	
	private String videoURL;
	
	private Date updateDate;
	
	private Date playedDate;
	
	
	
	public int getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public int getPlayTimes() {
		return playTimes;
	}

	public void setPlayTimes(int playTimes) {
		this.playTimes = playTimes;
	}

	public String getRadioURL() {
		return radioURL;
	}

	public void setRadioURL(String radioURL) {
		this.radioURL = radioURL;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getPlayedDate() {
		return playedDate;
	}

	public void setPlayedDate(Date playedDate) {
		this.playedDate = playedDate;
	}

	
	

}
