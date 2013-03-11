package com.shangde.edu.cms.condition;

import com.shangde.common.vo.PageResult;

public class QueryCommentCondition extends PageResult{
    private int cmtId;
    private int sourceId;
    private int cfId;
    private int checkState = -1;
    private String cmtInfo;
    /**
     * 查询状态  1本周 2 本月 3 三个月
     */
    private int searchStatus;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 是否置顶
     */
    private int isTop;    
    /**
     * 留言创建开始时间
     */
    private String startTime;
    
    /**
     * 留言创建结束时间
     */
    private String endTime;
    /**
     * 建议人名称
     */
    private String visitorName;
    /**
     * 专业id
     */
    private int subjectId;
    
    
    
        
    
    
    public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public int getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(int searchStatus) {
		this.searchStatus = searchStatus;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}


	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public int getCfId() {
		return cfId;
	}

	public void setCfId(int cfId) {
		this.cfId = cfId;
	}

	public int getCmtId(){
        return cmtId;
    }

    public void setCmtId(int cmtId){
        this.cmtId = cmtId;
    }

	public String getCmtInfo() {
		return cmtInfo;
	}

	public void setCmtInfo(String cmtInfo) {
		this.cmtInfo = cmtInfo;
	}

	public int getCheckState() {
		return checkState;
	}

	public void setCheckState(int checkState) {
		this.checkState = checkState;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
}