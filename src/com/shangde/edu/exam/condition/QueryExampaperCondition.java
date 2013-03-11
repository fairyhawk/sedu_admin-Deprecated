package com.shangde.edu.exam.condition;

import java.io.Serializable;
import java.util.Date;

import com.shangde.common.vo.PageResult;

/**
 * 查询试卷条件
 * @author chenshuai
 *
 */
public class QueryExampaperCondition extends PageResult implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int epId;
    /**
     * 关节点类型
     */
    private int type;
    
    /**
     * 课程或知识点Id
     */
    private int kOrCouId;
    
    /**
     * 专业id
     */
    
    private int subjectId;
    
    /**
     * 课程id
     * 
     */
    private int cid;
    
    /**
     * 测试类型
     */
    private Integer eptype;
    
    /**
     * 考试的测试类型关键字
     */
    private String eptypekeyword;
    
 
    /**
     * 查询条件
     */
    private String searchKey;
    /**
     * 课程id
     * @return
     */
    
    private int courseId;
    
    /**
     * 试题类型
     */
    private int level;
    /**
     * 用户id
     * @return
     */
    
    private int userId;
    
    /**
     * 前台页面排序条件
     */
    private int paixu;
    
    /**
     * 课程id
     */
    private int sortId;
    
    private int tiaojian;
    
    private Date startTime;
    
    private Date endTime;
    private String epState;
    
    /**
     * 录试卷作者
     */
    private String author;
    
    /**
     * 用户id
     */
    private int cusId;
    
    /**
     * 排序语句
     */
    private String sql;
    
    public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public int getKOrCouId() {
		return kOrCouId;
	}

	public void setKOrCouId(int orCouId) {
		kOrCouId = orCouId;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getEpId(){
        return epId;
    }

    public void setEpId(int epId){
        this.epId = epId;
    }



	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getEptype() {
		return eptype;
	}

	public void setEptype(Integer eptype) {
		this.eptype = eptype;
	}

	public String getEptypekeyword() {
		return eptypekeyword;
	}

	public void setEptypekeyword(String eptypekeyword) {
		this.eptypekeyword = eptypekeyword;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPaixu() {
		return paixu;
	}

	public void setPaixu(int paixu) {
		this.paixu = paixu;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	public int getTiaojian() {
		return tiaojian;
	}

	public void setTiaojian(int tiaojian) {
		this.tiaojian = tiaojian;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		if(endTime!=null){
			endTime.setHours(23);
			endTime.setMinutes(59);
			endTime.setSeconds(59);
		}
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getEpState() {
		return epState;
	}

	public void setEpState(String epState) {
		if("".equals(epState)){
			this.epState=epState="-1";
		}else{
			this.epState = epState;
		}
		
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}