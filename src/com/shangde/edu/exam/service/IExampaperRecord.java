package com.shangde.edu.exam.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.exam.condition.QueryExampaperRecordCondition;
import com.shangde.edu.exam.dto.ExamAnalysisDTO;


/**
 * IExampaperRecordӿ
 * 试卷记录接口
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public interface IExampaperRecord {
    
    /**
     * 获取考试分析
     * @param queryExampaperRecordCondition
     * @return 考试分析集合
     */
    public List<ExamAnalysisDTO> getExampaperAnalysisDTO(QueryExampaperRecordCondition queryExampaperRecordCondition);

    
    /**
     * 按用户Id 及 课程ID获取正确试题数
     * @param queryExampaperRecordCondition
     * @return 正确题数
     */
    public int getRightQstNumByCourseId(QueryExampaperRecordCondition queryExampaperRecordCondition);

    /**
     * 按用户Id 及 课程ID获取试题数
     * @param 试卷记录查询条件 queryExampaperRecordCondition
     * @return 总题数
     */
    public int getQstNumByCourseId(QueryExampaperRecordCondition queryExampaperRecordCondition);

    /**
 	  * 删除试卷记录
 	  */
    public void delERecordByCusId(int cusId);
    

    /**
 	  * 成绩查询
 	  */
    public PageResult GetExampaperRecordAll(QueryExampaperRecordCondition queryExampaperRecordCondition);
	
    /**
	 * Yangning 2011/12/6 后台批量删除用户时批量删除考试信息 
	 * @param cusIds
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-6
	 */
    public Integer delBathERecordByCusIds(String cusIds);
}