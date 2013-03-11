package com.shangde.edu.sys.domain;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 公告按项目区分
 * 
 * @author liuqinggang
 */
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;
    private int noticeId;
    private int subjectId;
    private Timestamp updateTime;
    private String content;

  

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
