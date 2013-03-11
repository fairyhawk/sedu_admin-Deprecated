package com.shangde.edu.cus.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.domain.TotolsScore;
import com.shangde.edu.cus.condition.QueryTotolsScoreCondition;


public interface ITotolsScore {
    
    public java.lang.Integer addTotolsScore(TotolsScore totolsScore);

    public void delTotolsScoreById(int tsId);

    public void updateTotolsScore(TotolsScore totolsScore);

    public TotolsScore getTotolsScoreById(int tsId);

    public List<TotolsScore> getTotolsScoreList(QueryTotolsScoreCondition queryTotolsScoreCondition);
    
    public TotolsScore getTotolsScore(int cusId);
    
    public PageResult getTotolsScorePageList(QueryTotolsScoreCondition queryTotolsScoreCondition);
    
    public void delTotolsScoreByCusId(int cusId);
    
    /**
     * Yangning 2011/12/06 后台批量删除用户时级联删除TotolScore
     * @param cusIds
     * @return
     * Author:Yangning
     * CreateDate:2011-12-6
     */
    public Integer delBathTotolsScoreByCusIds(String cusIds);
}