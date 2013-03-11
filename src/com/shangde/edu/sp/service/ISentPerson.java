package com.shangde.edu.sp.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.crm.condition.QueryUsersCondition;
import com.shangde.edu.sp.domain.SentPerson;
import com.shangde.edu.sp.condition.QuerySentPersonCondition;


public interface ISentPerson {
	
    
    public Integer addSentPerson(SentPerson sentPerson);
   
    public void delSentPersonById(int sentPersonId);

    public void updateSentPerson(SentPerson sentPerson);

    public SentPerson getSentPersonById(int sentPersonId);

    public List<SentPerson> getSentPersonByList(QuerySentPersonCondition querySentPersonCondition);
    
    
    /**
     *   范昕
     * 查询全部SentPerson列表 且做分页处理
     * @param QuerySentPersonCondition 查询对象
     * @return 分页对象
     */
    public PageResult getSentPersonBackPaperByCondition(QuerySentPersonCondition querySentPersonCondition);
    
    /**
     *   范昕
     * 查询搜索全部SentPerson列表 且做分页处理
     * @param QuerySentPersonCondition 查询对象
     * @return 分页对象
     */    
    public PageResult searchSentPersonListByParamCondition(QuerySentPersonCondition querySentPersonCondition);
    
}
