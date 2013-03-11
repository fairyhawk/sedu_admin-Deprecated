package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.domain.VisitHistory;
import com.shangde.edu.sys.condition.QueryVisitHistoryCondition;
public interface IVisitHistory {
    public java.lang.Integer addVisitHistory(VisitHistory visitHistory);
    public void delVisitHistoryById(int id);
    public void updateVisitHistory(VisitHistory visitHistory);
    public VisitHistory getVisitHistoryById(int id);
    public List<VisitHistory> getVisitHistoryList(QueryVisitHistoryCondition queryVisitHistoryCondition);
    public PageResult getVisitHistoryByList(QueryVisitHistoryCondition queryVisitHistoryCondition);
}