package com.shangde.edu.finance.service;

import java.util.List;
import com.shangde.edu.finance.domain.Cod;
import com.shangde.edu.finance.condition.QueryCodCondition;
import com.shangde.common.service.BaseService;


@SuppressWarnings("unchecked")
public class CodImpl extends BaseService implements ICod{
    public java.lang.Integer addCod(Cod cod) {
return simpleDao.createEntity("Cod_NS.createCod",cod);
    }

    public void delCodById(int codId){
        simpleDao.deleteEntity("Cod_NS.deleteCodById",codId);
    }

    public void updateCod(Cod cod) {
        simpleDao.updateEntity("Cod_NS.updateCod",cod);
    }

    public Cod getCodById(int codId) {
        return simpleDao.getEntity("Cod_NS.getCodById",codId);
    }

    public List<Cod> getCodList(QueryCodCondition queryCodCondition) {
        return simpleDao.getForList("Cod_NS.getCodList",queryCodCondition);
    }
}
