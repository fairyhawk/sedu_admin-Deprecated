package com.shangde.edu.stu.service;

import java.util.Date;
import java.util.List;

import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.stu.domain.CourseSummary;
import com.shangde.edu.stu.condition.QueryCourseSummaryCondition;

public interface ICourseSummary {
    /**
     * 
     * @param courseSummary 
     * @return id
     */
    public Integer addCourseSummary(CourseSummary courseSummary);

    /**
     * 
     */
    public void delCourseSummaryById(int cSumId);

    /**
     * 
     * @param courseSummary 
     */
    public void updateCourseSummary(CourseSummary courseSummary);

    /**
     *
     * @return 
     */
    public CourseSummary getCourseSummaryById();

    /**
    *
    * @return 
    */
   public List<CourseSummary> getCourseSummaryListBySummarydate(QueryCourseSummaryCondition queryCourseSummaryCondition);
   
    /**
     * 
     * @param 
     * @return 
     */
    public  CourseSummary getCourseSummaryLimt(QueryCourseSummaryCondition queryCourseSummaryCondition);
    
    /**
     * 
     * @param 
     * @return 
     */  
    public  List<CourseSummary> getCourseSummaryLimtContent(QueryCourseSummaryCondition queryCourseSummaryCondition);
    
    
    /**
     * 查询知识点
     * @param queryKpointCondition 查询条件
     * @return   
     * fanxin---2011-05-27
     * 
     */
    public List<Kpoint> getKpointListBypointIdList(List<String> list);
}