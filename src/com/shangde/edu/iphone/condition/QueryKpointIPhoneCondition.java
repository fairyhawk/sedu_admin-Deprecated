package com.shangde.edu.iphone.condition;

import java.util.List;

import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;

/**
 * ֪ 查询知识点条件
 * @author chenshuai
 *
 */
public class QueryKpointIPhoneCondition extends PageQuery{
	/**
	 * 知识点ID
	 */
    private int pointId;
    
    /**
     * 查询的关键字
     */
    private String searchKey;
    
    /**
     * 课程ID
     */
    private int courseId;
    
    /**
     * 父ID
     */
    private int pid;
    
    /**
     * 叶子
     */
    private int leaf;
    /**
     * 专业ID
     * 
     */
    private int subjectId;
    /**
     * 客户ID
     */
    private int cusId;
    /**
     * 已观看知识点ID list
     *评价中心查询未评价知识点时用
     */
    private List<String> watchKids;
    /**
     * 已评价知识点ID list
     * 评价中心查询未评价知识点时用
     */
    private List<Integer> assKids;
    
    public List<String> getWatchKids() {
		return watchKids;
	}

	public void setWatchKids(List<String> watchKids) {
		this.watchKids = watchKids;
	}

	public List<Integer> getAssKids() {
		return assKids;
	}

	public void setAssKids(List<Integer> assKids) {
		this.assKids = assKids;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getLeaf() {
		return leaf;
	}

	public void setLeaf(int leaf) {
		this.leaf = leaf;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getPointId(){
        return pointId;
    }

    public void setPointId(int pointId){
        this.pointId = pointId;
    }

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
}