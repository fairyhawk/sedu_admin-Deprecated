package com.shangde.edu.kno.domain;

import java.io.Serializable;
import java.util.List;

public class PreItem implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int preId;
    private String preName;
    private String content;
    private String createTime;
    private String author;
    private int status;
    private String lastEditTime;
    private List<PreNode> preNodeList;
    //是否已和知识树关联
    private int relCount;
    public int getRelCount() {
		return relCount;
	}

	public void setRelCount(int relCount) {
		this.relCount = relCount;
	}

	public List<PreNode> getPreNodeList() {
		return preNodeList;
	}

	public void setPreNodeList(List<PreNode> preNodeList) {
		this.preNodeList = preNodeList;
	}

	public int getPreId(){
        return preId;
    }

    public void setPreId(int preId){
        this.preId = preId; 
    }
        
    public String getPreName(){
        return preName;
    }

    public void setPreName(String preName){
        this.preName = preName; 
    }
        
    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content; 
    }
        
    public String getCreateTime(){
        return createTime;
    }

    public void setCreateTime(String createTime){
        this.createTime = createTime; 
    }
        
    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author; 
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(String lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
}
