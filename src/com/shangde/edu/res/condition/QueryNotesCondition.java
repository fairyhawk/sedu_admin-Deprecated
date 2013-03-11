package com.shangde.edu.res.condition;

import com.shangde.common.vo.PageQuery;

public class QueryNotesCondition extends PageQuery {
	private int cusId;
	private int pointId;
    private int noteId;
    private int courseId;
        
    public int getNoteId(){
        return noteId;
    }

    public void setNoteId(int noteId){
        this.noteId = noteId;
    }

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
}