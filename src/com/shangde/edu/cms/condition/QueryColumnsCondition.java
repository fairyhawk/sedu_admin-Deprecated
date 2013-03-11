package com.shangde.edu.cms.condition;

import com.shangde.common.vo.PageQuery;

public class QueryColumnsCondition extends PageQuery{
    private int columnId;
    private int parentId;
    private int subjectId;
    private String keyWord;
    private String meta;
    public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getColumnId(){
        return columnId;
    }

    public void setColumnId(int columnId){
        this.columnId = columnId;
    }

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
}