package com.shangde.edu.ass.service;

import java.util.List;
import com.shangde.edu.ass.domain.Reassess;
import com.shangde.edu.ass.condition.QueryReassessCondition;
import com.shangde.edu.cou.domain.Clazz;
import com.shangde.common.service.BaseService;

/**
 * 
 * User: guoqiang.liu
 * Date: 2011-05-23
 */
@SuppressWarnings("unchecked")
public class ReassessImpl extends BaseService implements IReassess{
    public java.lang.Integer addReassess(Reassess reassess) {
return simpleDao.createEntity("Reassess_NS.createReassess",reassess);
    }

    public void delReassessById(int reAssessId){
    	simpleDao.deleteEntity("Reassess_NS.deleteReassessById", reAssessId);
    }

    public void updateReassess(Reassess reassess) {
        simpleDao.updateEntity("Reassess_NS.updateReassess",reassess);
    }

    public Reassess getReassessById(int reAssessId) {
    	  return simpleDao.getEntity("Reassess_NS.getReassessById",reAssessId);
    }

    public List<Reassess> getReassessList(QueryReassessCondition queryReassessCondition) {
        return simpleDao.getForList("Reassess_NS.getReassessList",queryReassessCondition);
    }

	public Reassess getReassessByAssId(int AssId) {
		return simpleDao.getEntity("Reassess_NS.getReassessByAssId", AssId);
	}
}
