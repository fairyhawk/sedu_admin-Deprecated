package com.shangde.edu.kno.domain;

import java.io.Serializable;

public class SysNode implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 知识树节点Id
	 */
    private int ksnId;
    /**
     * 知识树id
     */
    private int ksId;
    /**
     * 排序id
     */
    private int sortId;
    /**
     * 父节点id
     */
    private int parentId;
    /**
     * 知识树节点名称
     */
    private String nodeName;
    /**
     * 知识树节点规范命名id
     */
    private String nodeId;
    /**
     * 知识树节点所在层级
     */
    private int level;
    /**
     * 创建时间
     */
    private java.util.Date createTime;
    /**
     * 作者
     */
    private String author;
    /**
     * 修改时间
     */
    private java.util.Date lastEditTime;
        
    public int getKsnId(){
        return ksnId;
    }

    public void setKsnId(int ksnId){
        this.ksnId = ksnId; 
    }
        
    public int getKsId(){
        return ksId;
    }

    public void setKsId(int ksId){
        this.ksId = ksId; 
    }
        
    public int getSortId(){
        return sortId;
    }

    public void setSortId(int sortId){
        this.sortId = sortId; 
    }
        
    public int getParentId(){
        return parentId;
    }

    public void setParentId(int parentId){
        this.parentId = parentId; 
    }
        
    public String getNodeName(){
        return nodeName;
    }

    public void setNodeName(String nodeName){
        this.nodeName = nodeName; 
    }
        
    public String getNodeId(){
        return nodeId;
    }

    public void setNodeId(String nodeId){
        this.nodeId = nodeId; 
    }
        
    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author; 
    }

	public java.util.Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(java.util.Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
}
