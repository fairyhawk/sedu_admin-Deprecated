package com.shangde.edu.kno.domain;

import java.io.Serializable;

public class Sys implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ksId;
    /**
     * 专业Id
     */
    private int subjectId;
    /**
     * 专业名称
     */
    private String subjectName;
    /**
     * 版本时间
     */
    private String versionTime;
    /**
     * 关键字
     */
    private String keyWord;
    /**
     * 内容
     */
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
    /**
     * 返工原因
     */
    private String reason;
    /**
     * 返工人
     */
    private String reviewer;
    /**
     * 作者
     */
    private String author; 
    
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getKsId(){
        return ksId;
    }

    public void setKsId(int ksId){
        this.ksId = ksId; 
    }
        
    public int getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId; 
    }
        
    public String getSubjectName(){
        return subjectName;
    }

    public void setSubjectName(String subjectName){
        this.subjectName = subjectName; 
    }
        
        
    public String getVersionTime() {
		return versionTime;
	}

	public void setVersionTime(String versionTime) {
		this.versionTime = versionTime;
	}

	public String getKeyWord(){
        return keyWord;
    }

    public void setKeyWord(String keyWord){
        this.keyWord = keyWord; 
    }
        
    public String getContent(){
        return content;
    }

    public void setContent(String content){
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
}
