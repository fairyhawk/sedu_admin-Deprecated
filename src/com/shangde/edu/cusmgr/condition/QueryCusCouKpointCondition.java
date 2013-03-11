package com.shangde.edu.cusmgr.condition;

public class QueryCusCouKpointCondition {
    private int id;
    
    private int cusId;
    
    private int courseId;
    
    private int pointId;
    
    /**
     * 判断是试听还是正式听课
     */
    private String type;
    
    /**
     * 学习视频数量
     */
    private int studyNum;
    
    private int subjectId;
    
    private int status;
        
    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	public int getStudyNum() {
		return studyNum;
	}

	public void setStudyNum(int studyNum) {
		this.studyNum = studyNum;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
}