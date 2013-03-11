package com.shangde.edu.stu.condition;


public class QueryVideoStatisticsCondition {
    
	private int start;
	private int end;
    private int vStatId;
  
	private int cusId;
    private int courseId;
    private String viewCode;
    private String dateStr;
    private int subjectId;

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getVStatId() {
        return vStatId;
    }

    public void setVStatId(int vStatId) {
        this.vStatId = vStatId;
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

    public String getViewCode() {
        return viewCode;
    }

    public void setViewCode(String viewCode) {
        this.viewCode = viewCode;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
    
    public int getStart() {
  		return start;
  	}

  	public void setStart(int start) {
  		this.start = start;
  	}

  	public int getEnd() {
  		return end;
  	}

  	public void setEnd(int end) {
  		this.end = end;
  	}
  	
  	public int getvStatId() {
		return vStatId;
	}

	public void setvStatId(int vStatId) {
		this.vStatId = vStatId;
	}
}