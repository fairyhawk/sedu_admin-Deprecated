package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.sys.condition.QuerySimpleDicCondition;
import com.shangde.edu.sys.domain.SimpleDic;

/**
 * SimpleDic对象操作实现类
 * @author guoqiang.liu
 */
@SuppressWarnings("unchecked")
public class SimpleDicImpl extends BaseService implements ISimpleDic{
    public void addSimpleDic(SimpleDic simpleDic) {
        simpleDao.createEntity("SimpleDic_NS.createSimpleDic",simpleDic);
    }

    public void delSimpleDicById(int dicId,String tableName,String columnName){
        java.util.Map keyMap = new java.util.HashMap();
        keyMap.put("dicId",dicId);
        keyMap.put("tableName",tableName);
        keyMap.put("columnName",columnName);
        simpleDao.deleteEntity("SimpleDic_NS.deleteSimpleDicById",keyMap);
    }

    public void updateSimpleDic(SimpleDic simpleDic) {
        simpleDao.updateEntity("SimpleDic_NS.updateSimpleDic",simpleDic);
    }

    public SimpleDic getSimpleDicById(int dicId,String tableName,String columnName) {
        java.util.Map keyMap = new java.util.HashMap();
        keyMap.put("dicId",dicId);
        keyMap.put("tableName",tableName);
        keyMap.put("columnName",columnName);
        return simpleDao.getEntity("SimpleDic_NS.getSimpleDicById",keyMap);
    }

    public List<SimpleDic> getSimpleDicList(QuerySimpleDicCondition querySimpleDicCondition) {
        return simpleDao.getForList("SimpleDic_NS.getSimpleDicList",querySimpleDicCondition);
    }
}
