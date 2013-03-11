package com.shangde.edu.cms.condition;

import com.shangde.common.vo.PageQuery;

public class QueryArticleCondition extends PageQuery{
    private int articleId;
    private int columnId;
    private String title;
    public QueryArticleCondition(){}
    public QueryArticleCondition(int columnId){
    	this.columnId=columnId;
    }   
    public int getColumnId() {
		return columnId;
	}
    
	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	public int getArticleId(){
        return articleId;
    }

    public void setArticleId(int articleId){
        this.articleId = articleId;
    }
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}