package com.shangde.edu.exam.service;

import java.io.Serializable;
import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.exam.condition.QueryExampaperCondition;
import com.shangde.edu.exam.domain.Exampaper;

/**
 * Exampaper 试卷接口
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public interface IExampaper extends Serializable {
	
	/**
	 * 用户试题排名统计
	 */
	public void addCusExampaperToTable();
    /**
     * 添加试卷Exampaper
     * @param exampaper
     * @return id
     */
    public java.lang.Integer addExampaper(Exampaper exampaper);

    /**
     * 更新试卷Exampaper
     * @param exampaper 要更新的试卷
     */
    public void updateExampaper(Exampaper exampaper);

    /**
     * 根据ID获取试卷
     * @param epId 试卷ID
     * @return 试卷
     */
    public Exampaper getExampaperById(int epId);
    
    /**
     * 根据ID获取试卷的信息转向添加试题页
     * @param epId
     * @return
     */
    public Exampaper getExampaperToAddQst(int epId);
    
    /**
     * 根据ID获取试卷信息进行修改
     * @param epId
     * @return
     */
    public Exampaper getExampaperToUpdate(int epId);
    
    /**
     * 根据kpoint获取试卷
     */
    public List<Exampaper> getExampaperByKpointList(int kpoint);
    
    /**
     * 列出试卷列表后台
     * @param queryExampaperCondition
     * @return 分页查询试卷结果
     */
    public PageResult listExamPaperByConditionexam(QueryExampaperCondition queryExampaperCondition);
       
   
    /**
	   * 前台getExampaperPaperResult
	   */
    public Exampaper getExampaperPaperResult(int epId);

}