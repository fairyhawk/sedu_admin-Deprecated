package com.shangde.edu.sp.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sp.condition.QuerySentPersonCondition;
import com.shangde.edu.sp.domain.SentPerson;




public class SentPersonImpl extends BaseService  implements ISentPerson {

	public Integer addSentPerson(SentPerson sentPerson) {
		return simpleDao.createEntity("SentPerson_NS.createSentPerson", sentPerson);
	
	}

	public void delSentPersonById(int sentPersonId) {
		simpleDao.deleteEntity("SentPerson_NS.deleteSentPersonById", sentPersonId);

	}

	public List<SentPerson> getSentPersonByList(
			QuerySentPersonCondition querySentPersonCondition) {
		return simpleDao.getForList("SentPerson_NS.getBackSentPersonListAll", querySentPersonCondition);
	}

	public SentPerson getSentPersonById(int sentPersonId) {
		 return simpleDao.getEntity("SentPerson_NS.getSentPersonById",sentPersonId);
	}

	public void updateSentPerson(SentPerson sentPerson) {
		 simpleDao.updateEntity("SentPerson_NS.updateSentPerson", sentPerson);
	}
	
    /**
     *   范昕
     * 查询全部SentPerson列表 且做分页处理
     * @param QuerySentPersonCondition 查询对象
     * @return 分页对象
     */
    public PageResult getSentPersonBackPaperByCondition(QuerySentPersonCondition querySentPersonCondition){
    	
    	return simpleDao.getPageResult("SentPerson_NS.getBackSentPersonList", "SentPerson_NS.getBcakSentPersonListCount", querySentPersonCondition);
    }

	public PageResult searchSentPersonListByParamCondition(
			QuerySentPersonCondition querySentPersonCondition) {
		return simpleDao.getPageResult("SentPerson_NS.searchSentPersonByParam", "SentPerson_NS.searchSentPersonByParamCount", querySentPersonCondition);
	}
    

 
}
