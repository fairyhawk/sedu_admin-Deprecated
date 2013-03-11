package com.shangde.edu.stu.domain;

import java.io.Serializable;

public class VideoStatistics implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int vStatId;
    private int cusId;
    private int courseId;
    private String viewCode;

    public int getVStatId() {
        return vStatId;
    }

    public void setVStatId(int statId) {
        vStatId = statId;
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

}
