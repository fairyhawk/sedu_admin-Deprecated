package com.shangde.edu.cms.domain;

import java.io.Serializable;

public class Article implements Serializable{
    private int articleId;
    private int columnId;
    private String title;
    private String meta;
    private String articleDesc;
    private String content;
    private String picture;
    private String articleUrl;
    private int sequence;
    private java.util.Date createDate;
    private String author;
    private String titleFont;
    private String titleSize;
    private String titleColor;
    private int clickTimes;
    private Long aticleType;
    private int functionType;//文章功能分类  1：报考信息 2：考试辅导 3：模考点评 4：热点解析 5：考试动态
    public int getFunctionType() {
		return functionType;
	}

	public void setFunctionType(int functionType) {
		this.functionType = functionType;
	}

	/**
     * 总页数，静态化所用属性，数据库中无对应字段
     */
    private int totalPage;
        
    public int getArticleId(){
        return articleId;
    }

    public void setArticleId(int articleId){
        this.articleId = articleId; 
    }
        
    public int getColumnId(){
        return columnId;
    }

    public void setColumnId(int columnId){
    	this.columnId = columnId;     		
    }
    public void setColumnId(String columnId) {
        try {
			this.columnId = Integer.parseInt(columnId);
		} catch (NumberFormatException e) {
			this.columnId = 0;
		}
    }
        
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title; 
    }
        
    public String getMeta(){
        return meta;
    }

    public void setMeta(String meta){
        this.meta = meta; 
    }
        
    public String getArticleDesc(){
        return articleDesc;
    }

    public void setArticleDesc(String articleDesc){
        this.articleDesc = articleDesc; 
    }
        
    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content; 
    }
        
    public String getPicture(){
        return picture;
    }

    public void setPicture(String picture){
        this.picture = picture; 
    }
        
    public String getArticleUrl(){
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl){
        this.articleUrl = articleUrl; 
    }
        
    public int getSequence(){
        return sequence;
    }

    public void setSequence(int sequence){
        this.sequence = sequence; 
    }
        
    public java.util.Date getCreateDate(){
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate){
        this.createDate = createDate; 
    }
        
    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author; 
    }
        
    public String getTitleFont(){
        return titleFont;
    }

    public void setTitleFont(String titleFont){
        this.titleFont = titleFont; 
    }
        
    public String getTitleSize(){
        return titleSize;
    }

    public void setTitleSize(String titleSize){
        this.titleSize = titleSize; 
    }
        
    public String getTitleColor(){
        return titleColor;
    }

    public void setTitleColor(String titleColor){
        this.titleColor = titleColor; 
    }
        
    public int getClickTimes(){
        return clickTimes;
    }

    public void setClickTimes(int clickTimes){
        this.clickTimes = clickTimes; 
    }
        
    public Long getAticleType(){
        return aticleType;
    }

    public void setAticleType(Long aticleType){
        this.aticleType = aticleType; 
    }

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
