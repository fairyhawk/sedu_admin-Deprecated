package com.shangde.edu.kb.service;

import java.util.List;
import com.shangde.edu.kb.domain.KnowledgeUnit;
import com.shangde.edu.kb.condition.QueryKnowledgeUnitCondition;
import com.shangde.common.service.BaseService;

/**
 * KnowledgeUnit操作对象实现
 * User: guoqiang.liu
 * Date: 2010-12-27
 */
@SuppressWarnings("unchecked")
public class KnowledgeUnitImpl extends BaseService implements IKnowledgeUnit{
    public java.lang.Integer addKnowledgeUnit(KnowledgeUnit knowledgeUnit) {
return simpleDao.createEntity("KnowledgeUnit_NS.createKnowledgeUnit",knowledgeUnit);
    }

    public void delKnowledgeUnitById(int kId){
        simpleDao.deleteEntity("KnowledgeUnit_NS.deleteKnowledgeUnitById",kId);
    }

    public void updateKnowledgeUnit(KnowledgeUnit knowledgeUnit) {
        simpleDao.updateEntity("KnowledgeUnit_NS.updateKnowledgeUnit",knowledgeUnit);
    }

    public KnowledgeUnit getKnowledgeUnitById(int kId) {
        return simpleDao.getEntity("KnowledgeUnit_NS.getKnowledgeUnitById",kId);
    }

    public List<KnowledgeUnit> getKnowledgeUnitList(QueryKnowledgeUnitCondition queryKnowledgeUnitCondition) {
        return simpleDao.getForList("KnowledgeUnit_NS.getKnowledgeUnitList",queryKnowledgeUnitCondition);
    }
}
