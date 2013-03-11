package com.shangde.edu.res.domain;

import java.io.Serializable;

public class RelateKnowledge implements  Serializable {
	/**主键ID**/
	private Integer relateKnowLedgeId;
	/**视频ID**/
	private Integer voId;
	/**知识点ID**/
	private Integer knowledgePointId;
	/**关联知识点**/
	private String relateKnowledgePoint;
	/**重要程度**/
	private String importantLevel;
	public Integer getRelateKnowLedgeId() {
		return relateKnowLedgeId;
	}
	public void setRelateKnowLedgeId(Integer relateKnowLedgeId) {
		this.relateKnowLedgeId = relateKnowLedgeId;
	}
	public Integer getVoId() {
		return voId;
	}
	public void setVoId(Integer voId) {
		this.voId = voId;
	}
	public Integer getKnowledgePointId() {
		return knowledgePointId;
	}
	public void setKnowledgePointId(Integer knowledgePointId) {
		this.knowledgePointId = knowledgePointId;
	}
	public String getRelateKnowledgePoint() {
		return relateKnowledgePoint;
	}
	public void setRelateKnowledgePoint(String relateKnowledgePoint) {
		this.relateKnowledgePoint = relateKnowledgePoint;
	}
	public String getImportantLevel() {
		return importantLevel;
	}
	public void setImportantLevel(String importantLevel) {
		this.importantLevel = importantLevel;
	}
}
