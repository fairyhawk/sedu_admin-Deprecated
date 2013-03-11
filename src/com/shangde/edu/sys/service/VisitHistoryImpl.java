package com.shangde.edu.sys.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shangde.edu.sys.domain.VisitHistory;
import com.shangde.edu.sys.condition.QueryVisitHistoryCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;

@SuppressWarnings("unchecked")
public class VisitHistoryImpl extends BaseService implements IVisitHistory{
    
	public java.lang.Integer addVisitHistory(VisitHistory visitHistory) {
	
		return simpleDao.createEntity("VisitHistory_NS.createVisitHistory",visitHistory);
    }

    public void delVisitHistoryById(int id){
        simpleDao.deleteEntity("VisitHistory_NS.deleteVisitHistoryById",id);
    }

    public void updateVisitHistory(VisitHistory visitHistory) {
        simpleDao.updateEntity("VisitHistory_NS.updateVisitHistory",visitHistory);
    }

    public VisitHistory getVisitHistoryById(int id) {
        return simpleDao.getEntity("VisitHistory_NS.getVisitHistoryById",id);
    }

    public List<VisitHistory> getVisitHistoryList(QueryVisitHistoryCondition queryVisitHistoryCondition) {
        return simpleDao.getForList("VisitHistory_NS.getVisitHistoryList",queryVisitHistoryCondition);
    }

	public PageResult getVisitHistoryByList(QueryVisitHistoryCondition queryVisitHistoryCondition) {
		return simpleDao.getPageResult("VisitHistory_NS.getVisitHistoryByList","VisitHistory_NS.getVisitHistoryByCount",queryVisitHistoryCondition);
	}
    
}
