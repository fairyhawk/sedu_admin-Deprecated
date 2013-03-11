package com.shangde.edu.kb.condition;

import com.shangde.common.vo.PageQuery;


public class QueryKnowledgeCondition extends PageQuery{
	private int kId;
	private int scId;
	private int plId;
	private String kIndex;
	private String upIndex;
	private int kPId;

	public int getKId() {
		return kId;
	}

	public void setKId(int kId) {
		this.kId = kId;
	}

	public int getPlId() {
		return plId;
	}

	public void setPlId(int plId) {
		this.plId = plId;
	}

	public String getKIndex() {
		return kIndex;
	}

	public void setKIndex(String index) {
		kIndex = index;
	}

	public int getScId() {
		return scId;
	}

	public void setScId(int scId) {
		this.scId = scId;
	}

	public String getUpIndex() {
		return upIndex;
	}

	public void setUpIndex(String upIndex) {
		this.upIndex = upIndex;
	}

	public int getKPId() {
		return kPId;
	}

	public void setKPId(int id) {
		kPId = id;
	}
}