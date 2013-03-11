package com.shangde.edu.cou.service;

import java.util.List;
import com.shangde.edu.cou.domain.Gmrecord;
import com.shangde.edu.cou.condition.QueryGmrecordCondition;
import com.shangde.common.service.BaseService;

/**
 * Gmrecord购买记录服务类
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
@SuppressWarnings("unchecked")
public class GmrecordImpl extends BaseService implements IGmrecord{
    public java.lang.Integer addGmrecord(Gmrecord gmrecord) {
return simpleDao.createEntity("Gmrecord_NS.createGmrecord",gmrecord);
    }

    public void delGmrecordById(int id){
        simpleDao.deleteEntity("Gmrecord_NS.deleteGmrecordById",id);
    }

    public void updateGmrecord(Gmrecord gmrecord) {
        simpleDao.updateEntity("Gmrecord_NS.updateGmrecord",gmrecord);
    }

    public Gmrecord getGmrecordById(int id) {
        return simpleDao.getEntity("Gmrecord_NS.getGmrecordById",id);
    }

    public List<Gmrecord> getGmrecordList(QueryGmrecordCondition queryGmrecordCondition) {
        return simpleDao.getForList("Gmrecord_NS.getGmrecordList",queryGmrecordCondition);
    }

	public Gmrecord getLastGMRecord(int userId) {
		return simpleDao.getEntity("Gmrecord_NS.getLastGMRecord", userId);
	}
}
