package com.shangde.edu.res.service;

import java.util.List;
import com.shangde.edu.res.domain.Booksgroup;
import com.shangde.edu.res.condition.QueryBooksgroupCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;


@SuppressWarnings("unchecked")
public class BooksgroupImpl extends BaseService implements IBooksgroup{
    public java.lang.Integer addBooksgroup(Booksgroup booksgroup) {
return simpleDao.createEntity("Booksgroup_NS.createBooksgroup",booksgroup);
    }

    public void delBooksgroupById(int bgId){
        simpleDao.deleteEntity("Booksgroup_NS.deleteBooksgroupById",bgId);
    }

    public void updateBooksgroup(Booksgroup booksgroup) {
        simpleDao.updateEntity("Booksgroup_NS.updateBooksgroup",booksgroup);
    }

    public Booksgroup getBooksgroupById(int bgId) {
        return simpleDao.getEntity("Booksgroup_NS.getBooksgroupById",bgId);
    }

    public PageResult getBooksgroupList(QueryBooksgroupCondition queryBooksgroupCondition) {
        return simpleDao.getPageResult("Booksgroup_NS.getBooksgroupList", "Booksgroup_NS.getBooksgroupListCount", queryBooksgroupCondition);
    	
    }

	public List<Booksgroup> getBooksgroupByList(
			QueryBooksgroupCondition queryBooksgroupCondition) {
		
		return simpleDao.getForList("Booksgroup_NS.getBooksgroupByList", queryBooksgroupCondition);
	}
    
}
