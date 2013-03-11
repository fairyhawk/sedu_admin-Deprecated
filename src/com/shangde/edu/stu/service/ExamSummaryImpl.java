package com.shangde.edu.stu.service;

import java.util.Date;
import java.util.List;


import com.shangde.edu.stu.domain.ExamSummary;
import com.shangde.edu.stu.domain.Exapa;
import com.shangde.edu.stu.domain.ExapaRe;
import com.shangde.edu.stu.condition.QueryExamSummaryCondition;
import com.shangde.edu.stu.condition.QueryExapaReCondition;
import com.shangde.common.service.BaseService;


@SuppressWarnings("unchecked")
public class ExamSummaryImpl extends BaseService implements IExamSummary {
	
	public Integer addExamSummary(ExamSummary examSummary) {
		return simpleDao.createEntity("ExamSummary_NS.createExamSummary",
				examSummary);
	}

	public void delExamSummaryById(int eSumId) {
		simpleDao.deleteEntity("ExamSummary_NS.deleteExamSummaryById", eSumId);
	}

	public void updateExamSummary(ExamSummary examSummary) {
		simpleDao.updateEntity("ExamSummary_NS.updateExamSummary", examSummary);
	}

	public ExamSummary getExamSummaryById() {
		return null ;
	}

	public List<ExamSummary> getExamSummaryList(
			QueryExamSummaryCondition queryExamSummaryCondition) {
		return simpleDao.getForList("ExamSummary_NS.getExamSummaryList",
				queryExamSummaryCondition);
	}
	
	
    /**
     * 通过条件获取试卷记录集合
     * @param queryExampaperRecordCondition 试卷记录查询条件
     * @return 试卷查询集合
     * @author fanxin
     * @time   2011.05.24
     * @version 1.0
     */
    public List<ExapaRe> getExampaperRecordList(QueryExapaReCondition queryExapaReCondition) {
    	
        return simpleDao.getForList("ExamSummary_NS.getExampaperRecordList",queryExapaReCondition);
    }
    
    /**
     * 通过试卷Id 获取试卷
     * @return 试卷
     * @author fanxin
     * @time   2011.05.24
     * @version 1.0
     */
    public Exapa getExampaperById(int exampaperId){
    	
    	return simpleDao.getEntity("ExamSummary_NS.getExampaperById",exampaperId);
    	
    }
    
}
