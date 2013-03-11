package com.shangde.edu.res.service;

import java.util.List;
import com.shangde.edu.res.domain.Vediogroup;
import com.shangde.edu.res.condition.QueryVediogroupCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;


@SuppressWarnings("unchecked")
public class VediogroupImpl extends BaseService implements IVediogroup{
    public java.lang.Integer addVediogroup(Vediogroup vediogroup) {
return simpleDao.createEntity("Vediogroup_NS.createVediogroup",vediogroup);
    }

    public void delVediogroupById(int vgId){
        simpleDao.deleteEntity("Vediogroup_NS.deleteVediogroupById",vgId);
    }

    public void updateVediogroup(Vediogroup vediogroup) {
        simpleDao.updateEntity("Vediogroup_NS.updateVediogroup",vediogroup);
    }

    public Vediogroup getVediogroupById(int vgId) {
        return simpleDao.getEntity("Vediogroup_NS.getVediogroupById",vgId);
    }

    public PageResult getVediogroupList(QueryVediogroupCondition queryVediogroupCondition) {
        return simpleDao.getPageResult("Vediogroup_NS.getVediogroupList", "Vediogroup_NS.getVediogroupListCount", queryVediogroupCondition);
    }
    public List<Vediogroup> getVediogroupByList(QueryVediogroupCondition queryVediogroupCondition){
    	
    	return simpleDao.getForList("Vediogroup_NS.getVediogroupByList", queryVediogroupCondition);
    	
    }


}
