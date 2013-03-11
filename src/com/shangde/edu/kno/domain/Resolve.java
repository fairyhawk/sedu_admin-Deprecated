package com.shangde.edu.kno.domain;

import java.io.Serializable;

public class Resolve implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int resId;
    /**
     * 解析内容
     */
    private String resContent;
    /**
     * 关键字
     */
    private String resKeyword;
    /**
     * 作者
     */
    private String resPerson;
    /**
     * 创建时间
     */
    private java.util.Date createTime;
    /**
     * 修改时间
     */
    private java.util.Date lastEditTime;
    /**
     * 0首位解析1不是首位解析
     */
    private int isFirst;
    /**
     * 知识树节点id
     */
    private int ksnId;
    /**
     * 节点规范命名id
     */
    private String nodeId;
        
    public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public int getResId(){
        return resId;
    }

    public void setResId(int resId){
        this.resId = resId; 
    }
        
    public String getResContent(){
        return resContent;
    }

    public void setResContent(String resContent){
        this.resContent = resContent; 
    }
        
    public String getResKeyword(){
        return resKeyword;
    }

    public void setResKeyword(String resKeyword){
        this.resKeyword = resKeyword; 
    }
        
    public String getResPerson(){
        return resPerson;
    }

    public void setResPerson(String resPerson){
        this.resPerson = resPerson; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public java.util.Date getLastEditTime(){
        return lastEditTime;
    }

    public void setLastEditTime(java.util.Date lastEditTime){
        this.lastEditTime = lastEditTime; 
    }
        
  
        
    public int getKsnId(){
        return ksnId;
    }

    public void setKsnId(int ksnId){
        this.ksnId = ksnId; 
    }

	public int getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(int isFirst) {
		this.isFirst = isFirst;
	}
}
