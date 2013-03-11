package com.shangde.edu.cms.domain;

import java.io.Serializable;

public class Columns implements Serializable{
    private int columnId;
    private int parentId;
    private String columnName;
    private String explanation;
    private String catalog;
    private String meta;
    private int indexTemplateId;
    private int articleTemplateId;
    private int sequence;
    private int isFinally;
    private String linkUrl;
    private String path;
    private String columnType;
    private int subjectId;
    private int staticType;
    private String keyWord;
        
    public int getColumnId(){
        return columnId;
    }

    public void setColumnId(int columnId){
        this.columnId = columnId; 
    }
        
    public int getParentId(){
        return parentId;
    }

    public void setParentId(int parentId){
        this.parentId = parentId; 
    }
        
    public String getColumnName(){
        return columnName;
    }

    public void setColumnName(String columnName){
        this.columnName = columnName; 
    }
        
    public String getExplanation(){
        return explanation;
    }

    public void setExplanation(String explanation){
        this.explanation = explanation; 
    }
        
    public String getCatalog(){
        return catalog;
    }

    public void setCatalog(String catalog){
        this.catalog = catalog; 
    }
        
    public String getMeta(){
        return meta;
    }

    public void setMeta(String meta){
        this.meta = meta; 
    }
        
    public int getIndexTemplateId(){
        return indexTemplateId;
    }

    public void setIndexTemplateId(int indexTemplateId){
        this.indexTemplateId = indexTemplateId; 
    }
        
    public int getArticleTemplateId(){
        return articleTemplateId;
    }

    public void setArticleTemplateId(int articleTemplateId){
        this.articleTemplateId = articleTemplateId; 
    }
        
    public int getSequence(){
        return sequence;
    }

    public void setSequence(int sequence){
        this.sequence = sequence; 
    }
        
    public int getIsFinally(){
        return isFinally;
    }

    public void setIsFinally(int isFinally){
        this.isFinally = isFinally; 
    }
        
    public String getLinkUrl(){
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl){
        this.linkUrl = linkUrl; 
    }
        
    public String getPath(){
        return path;
    }

    public void setPath(String path){
        this.path = path; 
    }
        
    public String getColumnType(){
        return columnType;
    }

    public void setColumnType(String columnType){
        this.columnType = columnType; 
    }

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getStaticType() {
		return staticType;
	}

	public void setStaticType(int staticType) {
		this.staticType = staticType;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
}
