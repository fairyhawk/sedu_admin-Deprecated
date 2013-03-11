package com.shangde.edu.exam.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.exam.condition.QueryExampaperRecordCondition;
import com.shangde.edu.exam.dto.ExamAnalysisDTO;

/**
 * ExampaperRecord试卷记录
 * IExampaperRecord实现类
 * 主要为试卷记录的操作类
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public class ExampaperRecordImpl extends BaseService implements IExampaperRecord{
    
    /**
     * 获取考试分析
     * @param queryExampaperRecordCondition
     * @return 考试分析集合
     */
    public List<ExamAnalysisDTO> getExampaperAnalysisDTO(QueryExampaperRecordCondition queryExampaperRecordCondition){
    	return simpleDao.getForList("ExampaperRecord_NS.getExampaperAnalysisDTO", queryExampaperRecordCondition);
    }
    
    /**
     * 按用户Id 及 课程ID获取正确试题数
     * @param queryExampaperRecordCondition
     * @return 正确题数
     */
    public int getRightQstNumByCourseId(QueryExampaperRecordCondition queryExampaperRecordCondition){
    	return simpleDao.getEntity("ExampaperRecord_NS.getRightQstNumByCourseId", queryExampaperRecordCondition);
    }
    
    /**
     * 按用户Id 及 课程ID获取试题数
     * @param 试卷记录查询条件 queryExampaperRecordCondition
     * @return 总题数
     */
    public int getQstNumByCourseId(QueryExampaperRecordCondition queryExampaperRecordCondition){
    	return simpleDao.getEntity("ExampaperRecord_NS.getQstNumByCourseId", queryExampaperRecordCondition);
    }

	 public void delERecordByCusId(int cusId){
		 simpleDao.deleteEntity("ExampaperRecord_NS.deleteERecordById", cusId);
	 }
	 
	 /**
	  * 成绩查询
	  */
	 
	 public PageResult GetExampaperRecordAll(QueryExampaperRecordCondition queryExampaperRecordCondition){
		 
		 return simpleDao.getPageResult("ExampaperRecord_NS.getExampaperRecordAll", "ExampaperRecord_NS.getExampaperRecordAllCount", queryExampaperRecordCondition);
	 }
	 
	 public Integer delBathERecordByCusIds(String cusIds){
		return simpleDao.delete("ExampaperRecord_NS.deleteERecordByIds", cusIds);
	 }
}
