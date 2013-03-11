package com.shangde.edu.sys.service;

import com.shangde.common.service.BaseService;
import com.shangde.edu.sys.condition.QueryNoticeCondition;
import com.shangde.edu.sys.domain.Notice;

public class NoticeImpl extends BaseService implements INotice {
    /**
     * 增加公告
     */
    public Integer addNotice(QueryNoticeCondition noticeCondition) {
        return simpleDao.createEntity("Notice_NS.createNotice",noticeCondition);
    }
    /**
     * 删除公告
     */
    public void delNoticeById(QueryNoticeCondition noticeCondition){
        simpleDao.deleteEntity("Notice_NS.deleteNoticeById",noticeCondition);
    }
    /**
     * 更新公告
     */
    public void updateNotice(QueryNoticeCondition noticeCondition) {
        simpleDao.updateEntity("Notice_NS.updateNotice",noticeCondition);
    }
    /**
     * 根据ID获得公告
     */
    public Notice getNoticeById(QueryNoticeCondition noticeCondition) {
        return simpleDao.getEntity("Notice_NS.getNoticeById",noticeCondition);
    }
    /**
     * 根据项目ID获得最新的公告
     */
    public Notice getNoticeBySubject(QueryNoticeCondition noticeCondition) {
        return    simpleDao.getEntity("Notice_NS.getNoticeBySubject",noticeCondition);
    }

}
