package com.shangde.edu.dis.service;

import java.util.List;
import com.shangde.edu.dis.domain.DisArea;
import com.shangde.edu.dis.condition.QueryDisAreaCondition;
import com.shangde.common.service.BaseService;

/**
 * DisArea
 * User: guoqiang.liu
 * Date: 2011-05-14
 */
@SuppressWarnings("unchecked")
public class DisAreaImpl extends BaseService implements IDisArea{
    
    public Integer addDisArea(DisArea disArea) {
            return simpleDao.createEntity("DisArea_NS.createDisArea",disArea);
    }

    public void delDisAreaById(){
        
    }

    public void updateDisArea(DisArea disArea) {
        simpleDao.updateEntity("DisArea_NS.updateDisArea",disArea);
    }

    public DisArea getDisAreaById() {
        return new DisArea();
    }

    public List<DisArea> getDisAreaList(QueryDisAreaCondition queryDisAreaCondition) {
        return simpleDao.getForList("DisArea_NS.getDisAreaList",queryDisAreaCondition);
    }
}
