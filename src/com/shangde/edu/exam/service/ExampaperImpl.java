package com.shangde.edu.exam.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.exam.condition.QueryExampaperCondition;
import com.shangde.edu.exam.domain.Exampaper;

/**
 * IExampaper的实现类
 * 对试卷的相关操作
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public class ExampaperImpl extends BaseServiceManyDb implements IExampaper{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 添加试卷Exampaper
     * @param exampaper
     * @return id
     */
    public java.lang.Integer addExampaper(Exampaper exampaper) {
    	return simpleDao.createEntity("Exampaper_NS.createExampaper",exampaper);
    }
    
    
    /**
     * 更新试卷Exampaper
     * @param exampaper 要更新的试卷
     */
    public void updateExampaper(Exampaper exampaper) {
        simpleDao.updateEntity("Exampaper_NS.updateExampaper",exampaper);
    }

    /**
     * 根据ID获取试卷
     * @param epId 试卷ID
     * @return 试卷
     */
    public Exampaper getExampaperById(int epId) {
        return simpleDao.getEntity("Exampaper_NS.getExampaperById",epId);
    }
    
    /**
     * 根据ID获取试卷信息进行修改
     * @param epId
     * @return
     */
    public Exampaper getExampaperToUpdate(int epId){
        return simpleDao.getEntity("Exampaper_NS.getExampaperToUpdate",epId);
    }
   
    
    /**
     * 列出试卷列表前台
     * @param queryExampaperCondition
     * @return 分页查询试卷结果
     */
    public PageResult listExamPaperByCondition(QueryExampaperCondition queryExampaperCondition){
    	return simpleDao.getPageResult("Exampaper_NS.listExamPaper", "Exampaper_NS.listExamPaperCount", queryExampaperCondition);
    }
    
    /**
     * 列出试卷列表后台
     * @param queryExampaperCondition
     * @return 分页查询试卷结果
     */
    public PageResult listExamPaperByConditionexam(QueryExampaperCondition queryExampaperCondition){
    	return simpleDaoRead.getPageResult("Exampaper_NS.listExamPaperByConditionexam", "Exampaper_NS.listExamPaperCountByConditionexam", queryExampaperCondition);
    }

	
	 public List<Exampaper> getExampaperByKpointList(int kpoint)
	    {
	    	
	    	return simpleDao.getForList("Exampaper_NS.getExampaperKpoint", kpoint);
	    }
	 
	    
	  /**
	   * 前台getExampaperPaperResult
	   */
	    
	    public Exampaper getExampaperPaperResult(int epId) {
	        return simpleDao.getEntity("Exampaper_NS.getExampaperPaperResult",epId);
	    }
	    
	    public Exampaper getExampaperToAddQst(int epId) {
	    	return simpleDao.getEntity("Exampaper_NS.getExampaperToAddQst", epId);
	    }


	    public void addCusExampaperToTable(){
	    	Map<String, Object> param = new HashMap<String, Object>();
	        param.put("icount", 0);
			simpleDao.getEntity("Exampaper_NS.addCusExampaperToTable",param);
		}
}			

