package com.shangde.edu.cms.service;

import java.util.List;
import com.shangde.edu.cms.domain.SqAnswer;
import com.shangde.edu.cms.condition.QuerySqAnswerCondition;
import com.shangde.common.service.BaseService;


/**
 * SqAnswer实现
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
@SuppressWarnings("unchecked")
public class SqAnswerImpl extends BaseService implements ISqAnswer{
    public java.lang.Integer addSqAnswer(SqAnswer sqAnswer) {
return simpleDao.createEntity("SqAnswer_NS.createSqAnswer",sqAnswer);
    }

    public void delSqAnswerById(int asId){
        simpleDao.deleteEntity("SqAnswer_NS.deleteSqAnswerById",asId);
    }

    public void updateSqAnswer(SqAnswer sqAnswer) {
        simpleDao.updateEntity("SqAnswer_NS.updateSqAnswer",sqAnswer);
    }

    public SqAnswer getSqAnswerById(int asId) {
        return simpleDao.getEntity("SqAnswer_NS.getSqAnswerById",asId);
    }

    public List<SqAnswer> getSqAnswerList(QuerySqAnswerCondition querySqAnswerCondition) {
        return simpleDao.getForList("SqAnswer_NS.getSqAnswerList",querySqAnswerCondition);
    }
}
