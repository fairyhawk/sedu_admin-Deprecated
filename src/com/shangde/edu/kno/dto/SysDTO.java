package com.shangde.edu.kno.dto;

import java.io.Serializable;

public class SysDTO implements Serializable{
	private int ksId;
    private int subjectId;
    private String subjectName;
    private java.util.Date versionTime;
    private String keyWord;
    private String content;    
    /**
     * 知识树状态
     * 1：未发布
     * 2：待审核
     * 3：发布
     * 4：冻结
     * 5：返工
     */
    private int status;
    private String reason;
    private String reviewer;
    private String author;  
    private int counts;
    
    /**
     * 知识点ID
     */
    private int ksnId;
    
    /**
     * 知识点名称
     */
    private String nodeName;
    
    /**
     * 节点ID
     */
    private String nodeId;
	public int getKsId() {
		return ksId;
	}
	public void setKsId(int ksId) {
		this.ksId = ksId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public java.util.Date getVersionTime() {
		return versionTime;
	}
	public void setVersionTime(java.util.Date versionTime) {
		this.versionTime = versionTime;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public int getKsnId() {
		return ksnId;
	}
	public void setKsnId(int ksnId) {
		this.ksnId = ksnId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
}
