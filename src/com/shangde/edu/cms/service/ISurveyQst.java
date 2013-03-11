package com.shangde.edu.cms.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QuerySurveyQstCondition;
import com.shangde.edu.cms.domain.SurveyQst;

/**
 * SurveyQst管理接口
 * User: guoqiang.liu
 * Date: 2010-10-14
 */
public interface ISurveyQst {
    /**
     * 添加SurveyQst
     * @param surveyQst 要添加的SurveyQst
     * @return id
     */
    public java.lang.Integer addSurveyQst(SurveyQst surveyQst);

    /**
     * 根据id删除一个SurveyQst
     * @param sqId 要删除的id
     */
    public void delSurveyQstById(int sqId);

    /**
     * 修改SurveyQst
     * @param surveyQst 要修改的SurveyQst
     */
    public void updateSurveyQst(SurveyQst surveyQst);

    /**
     * 根据id获取单个SurveyQst对象
     * @param sqId 要查询的id
     * @return 年级
     */
    public SurveyQst getSurveyQstById(int sqId);

    /**
     * 根据条件获取SurveyQst列表
     * @param querySurveyQstCondition 查询条件
     * @return 查询结果
     */
    public List<SurveyQst> getSurveyQstList(QuerySurveyQstCondition querySurveyQstCondition);

    /**
     * 分页查询问卷
     * @param querySurveyQstCondition
     * @return
     */
	public PageResult getSurveyListByCondition(
			PageQuery querySurveyQstCondition);

	/**
	 * 生成问卷调查页
	 * @param realPath
	 * @param surveyQst
	 */
	public void genericSurvey(String realPath, QuerySurveyQstCondition querySurveyQstCondition) throws Exception;

	/**
	 * 静态化标签处理用
	 * @param querySurveyQstCondition
	 * @return
	 */
	public List<SurveyQst> getSurveyQstListForTag(
			QuerySurveyQstCondition querySurveyQstCondition);
}