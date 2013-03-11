package com.shangde.edu.sys.service;

import com.shangde.edu.sys.condition.QueryNoticeCondition;
import com.shangde.edu.sys.domain.Notice;

public interface INotice {
    
    public Notice getNoticeBySubject( QueryNoticeCondition noticeCondition);
    
    public Integer addNotice(QueryNoticeCondition noticeCondition) ;

    public void delNoticeById(QueryNoticeCondition noticeCondition);

    public void updateNotice(QueryNoticeCondition noticeCondition);
   
    public Notice getNoticeById(QueryNoticeCondition noticeCondition);
    
}
