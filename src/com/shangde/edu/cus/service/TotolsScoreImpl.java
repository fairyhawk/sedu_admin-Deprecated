package com.shangde.edu.cus.service;

import java.util.List;
import com.shangde.edu.cus.domain.TotolsScore;
import com.shangde.edu.cus.condition.QueryTotolsScoreCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;


@SuppressWarnings("unchecked")
public class TotolsScoreImpl extends BaseService implements ITotolsScore{
    public java.lang.Integer addTotolsScore(TotolsScore totolsScore) {
return simpleDao.createEntity("TotolsScore_NS.createTotolsScore",totolsScore);
    }

    public void delTotolsScoreById(int tsId){
        simpleDao.deleteEntity("TotolsScore_NS.deleteTotolsScoreById",tsId);
    }

    public void updateTotolsScore(TotolsScore totolsScore) {
        simpleDao.updateEntity("TotolsScore_NS.updateTotolsScore",totolsScore);
    }

    public TotolsScore getTotolsScoreById(int tsId) {
        return simpleDao.getEntity("TotolsScore_NS.getTotolsScoreById",tsId);
    }

    public List<TotolsScore> getTotolsScoreList(QueryTotolsScoreCondition queryTotolsScoreCondition) {
        return simpleDao.getForList("TotolsScore_NS.getTotolsScoreList",queryTotolsScoreCondition);
    }

	public TotolsScore getTotolsScore(int cusId) {
		return simpleDao.getEntity("TotolsScore_NS.getTotolsScore", cusId);
	}

	public PageResult getTotolsScorePageList(QueryTotolsScoreCondition queryTotolsScoreCondition) {
		return simpleDao.getPageResult("TotolsScore_NS.getTotolsScorePageList", "TotolsScore_NS.getTotolsScoreByCount", queryTotolsScoreCondition);
	}

	public void delTotolsScoreByCusId(int cusId) {
		  simpleDao.deleteEntity("TotolsScore_NS.deleteTotolsScoreByCusId",cusId);
	}
	
	public Integer delBathTotolsScoreByCusIds(String cusIds) {
		 return simpleDao.delete("TotolsScore_NS.deleteTotolsScoreByCusIds",cusIds);
	}
}
