package com.shangde.edu.kno.dto;

import java.io.Serializable;

public class SysNodeDTO implements Serializable{
	private String nodeId;
	private String nodeName;
	private String preNodeName;
	private int ksnId;
	private int counts;
	private int sortId;
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getPreNodeName() {
		return preNodeName;
	}
	public void setPreNodeName(String preNodeName) {
		this.preNodeName = preNodeName;
	}
	public int getKsnId() {
		return ksnId;
	}
	public void setKsnId(int ksnId) {
		this.ksnId = ksnId;
	}
	

}
