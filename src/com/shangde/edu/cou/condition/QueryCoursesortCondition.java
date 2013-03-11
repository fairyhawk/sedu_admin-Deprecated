package com.shangde.edu.cou.condition;

import com.shangde.common.vo.PageQuery;

/**
 * 查询课程分类条件
 * @author chenshuai
 *
 */
public class QueryCoursesortCondition extends PageQuery{
	/**
	 * 课程分类ID
	 */
    private int coursesortId;
    
    /**
     * 父ID
     */
    private int pId = -1;
    
    /**
     * 查询关键字
     */
    private String searchKey;
    
    /**
     * 查询type
     */
    private String searchType;
    /**
     * 专业id
     */
    private int subjectId;
    
    private int status;
    
    public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getPId() {
		return pId;
	}

	public void setPId(int id) {
		pId = id;
	}

	public int getCoursesortId(){
        return coursesortId;
    }

    public void setCoursesortId(int coursesortId){
        this.coursesortId = coursesortId;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
}