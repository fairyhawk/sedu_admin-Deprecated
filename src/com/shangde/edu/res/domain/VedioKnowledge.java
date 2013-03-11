package com.shangde.edu.res.domain;

import java.io.Serializable;

public class VedioKnowledge implements  Serializable{
	/** 视频知识点ID**/
	private Integer knowledgePointId;
	/** 视频ID**/
	private Integer voId;
	/** 视频知识点**/
	private String knowledgePoint;
	/** 时间**/
	private String timePoint;
	public Integer getKnowledgePointId() {
		return knowledgePointId;
	}
	public void setKnowledgePointId(Integer knowledgePointId) {
		this.knowledgePointId = knowledgePointId;
	}
	public Integer getVoId() {
		return voId;
	}
	public void setVoId(Integer voId) {
		this.voId = voId;
	}
	public String getKnowledgePoint() {
		return knowledgePoint;
	}
	public void setKnowledgePoint(String knowledgePoint) {
		this.knowledgePoint = knowledgePoint;
	}
	public String getTimePoint() {
		return timePoint;
	}
	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}
}
