package com.shangde.edu.kno.service;

import java.util.List;
import com.shangde.edu.kno.domain.Resolve;
import com.shangde.edu.kno.condition.QueryResolveCondition;
import com.shangde.common.service.BaseService;


@SuppressWarnings("unchecked")
public class ResolveImpl extends BaseService implements IResolve{
    public java.lang.Integer addResolve(Resolve resolve) {
return simpleDao.createEntity("Resolve_NS.createResolve",resolve);
    }

    public void delResolveById(int resId){
        simpleDao.deleteEntity("Resolve_NS.deleteResolveById",resId);
    }

    public void updateResolve(Resolve resolve) {
        simpleDao.updateEntity("Resolve_NS.updateResolve",resolve);
    }

    public Resolve getResolveById(int resId) {
        return simpleDao.getEntity("Resolve_NS.getResolveById",resId);
    }

    public List<Resolve> getResolveList(QueryResolveCondition queryResolveCondition) {
        return simpleDao.getForList("Resolve_NS.getResolveList",queryResolveCondition);
    }
    
    /**
     * 根据ksnId获取resolve对象列表
     * @param ksnId
     * @return
     */
    public List<Resolve> getResolveListByKsnId(int ksnId){
    	return simpleDao.getForList("Resolve_NS.getResolveListByKsnId", ksnId);
    }
    
    /**
     * 查找首选解析的节点
     * @param ksnId
     * @return
     */
    public Resolve getResolveFirstByKsnId(int ksnId){
    	return simpleDao.getEntity("Resolve_NS.getResolveFirstByKsnId", ksnId);
    }
}
