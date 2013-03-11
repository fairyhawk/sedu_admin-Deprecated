package com.shangde.edu.stu.service;

import java.util.Date;
import java.util.List;

import com.shangde.edu.stu.domain.ExamSummary;
import com.shangde.edu.stu.domain.Exapa;
import com.shangde.edu.stu.domain.ExapaRe;
import com.shangde.edu.stu.condition.QueryExamSummaryCondition;
import com.shangde.edu.stu.condition.QueryExapaReCondition;

public interface IExamSummary {
    /**
     * 
     * @param 
     * @return id
     */
    public Integer addExamSummary(ExamSummary examSummary);

    /**
     * 
     */
    public void delExamSummaryById(int eSumId);

    /**
     * 
     * @param examSummary 
     */
    public void updateExamSummary(ExamSummary examSummary);

    /**
     * 
     * @return 
     */
    public ExamSummary getExamSummaryById();

    /**
     * 
     * @param 
     * @return 
     */
    public List<ExamSummary> getExamSummaryList(QueryExamSummaryCondition queryExamSummaryCondition);
    
    
    /**
     * 通过条件获取试卷记录集合
     * @param queryExampaperRecordCondition 试卷记录查询条件
     * @return 试卷查询集合
     * @author fanxin
     * @time   2011.05.24
     * @version 1.0
     */
    public List<ExapaRe> getExampaperRecordList(QueryExapaReCondition queryExapaReCondition);
    
    /**
     * 通过试卷Id 获取试卷
     * @return 试卷
     * @author fanxin
     * @time   2011.05.24
     * @version 1.0
     */
    public Exapa getExampaperById(int exampaperId); 
    
}