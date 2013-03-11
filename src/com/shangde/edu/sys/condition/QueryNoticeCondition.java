package com.shangde.edu.sys.condition;

import java.sql.Timestamp;

import com.shangde.common.vo.PageQuery;
/**
 * 公告按项目区分
 * 
 * @author liuqinggang
 */
public class QueryNoticeCondition extends PageQuery {
 
    private int notice_id;
    private int subjectId;
    private Timestamp updateTime;
    private String content;

    public int getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(int notice_id) {
        this.notice_id = notice_id;
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
