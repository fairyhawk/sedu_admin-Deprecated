package com.shangde.edu.cus.service;

import java.util.List;
import com.shangde.edu.cus.domain.Area;
import com.shangde.edu.cus.condition.QueryAreaCondition;
import com.shangde.common.service.BaseService;


@SuppressWarnings("unchecked")
public class AreaImpl extends BaseService implements IArea{
    public java.lang.Integer addArea(Area area) {
return simpleDao.createEntity("Area_NS.createArea",area);
    }

    public void delAreaById(int id){
        simpleDao.deleteEntity("Area_NS.deleteAreaById",id);
    }

    public void updateArea(Area area) {
        simpleDao.updateEntity("Area_NS.updateArea",area);
    }

    public Area getAreaById(int id) {
        return simpleDao.getEntity("Area_NS.getAreaById",id);
    }

    public List<Area> getAreaList(QueryAreaCondition queryAreaCondition) {
        return simpleDao.getForList("Area_NS.getAreaList",queryAreaCondition);
    }
}
