package com.shangde.edu.cms.service;

import java.io.File;
import java.util.List;
import com.shangde.edu.cms.domain.SurveyQst;
import com.shangde.edu.cms.condition.QuerySurveyQstCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.util.FileUtils;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;


/**
 * SurveyQst实现类
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
@SuppressWarnings("unchecked")
public class SurveyQstImpl extends BaseService implements ISurveyQst{
	
	private ITemplate templateService;
	
    public java.lang.Integer addSurveyQst(SurveyQst surveyQst) {
return simpleDao.createEntity("SurveyQst_NS.createSurveyQst",surveyQst);
    }

    public void delSurveyQstById(int sqId){
        simpleDao.deleteEntity("SurveyQst_NS.deleteSurveyQstById",sqId);
    }

    public void updateSurveyQst(SurveyQst surveyQst) {
        simpleDao.updateEntity("SurveyQst_NS.updateSurveyQst",surveyQst);
    }

    public SurveyQst getSurveyQstById(int sqId) {
        return simpleDao.getEntity("SurveyQst_NS.getSurveyQstById",sqId);
    }

    public List<SurveyQst> getSurveyQstList(QuerySurveyQstCondition querySurveyQstCondition) {
        return simpleDao.getForList("SurveyQst_NS.getSurveyQstList",querySurveyQstCondition);
    }

	public PageResult getSurveyListByCondition(PageQuery querySurveyQstCondition) {
		return simpleDao.getPageResult("SurveyQst_NS.getSurveyListByCondition", "SurveyQst_NS.getSurveyCountByCondition", querySurveyQstCondition);
	}

	public void genericSurvey(String realPath, QuerySurveyQstCondition querySurveyQstCondition) throws Exception{
		FileUtils.clearFile(realPath);
		FileUtils.createFile(realPath);
		templateService.processTag(realPath + File.separator + "survey_qst.shtml", templateService.getTemplateById(14).getTmpContent(), querySurveyQstCondition);
	}

	public void setTemplateService(ITemplate templateService) {
		this.templateService = templateService;
	}

	public List<SurveyQst> getSurveyQstListForTag(
			QuerySurveyQstCondition querySurveyQstCondition) {
		return simpleDao.getForList("SurveyQst_NS.getSurveyQstListForTag",querySurveyQstCondition);
	}
}
