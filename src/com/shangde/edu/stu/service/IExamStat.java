package com.shangde.edu.stu.service;

import java.util.List;
import com.shangde.edu.stu.domain.ExamStat;
import com.shangde.edu.stu.condition.QueryExamStatCondition;


public interface IExamStat {
    /**
     * 
     * @param 
     * @return 
     */
    public Integer addExamStat(ExamStat examStat);

    /**
     * 
     */
    public void delExamStatById();

    /**
     * 
     * @param examStat 
     */
    public void updateExamStat(ExamStat examStat);

    /**
     * 
     * @return 
     */
    public ExamStat getExamStatById();

    /**
     * 
     * @param 
     * @return 
     */
    public List<ExamStat> getExamStatList(QueryExamStatCondition queryExamStatCondition);
}