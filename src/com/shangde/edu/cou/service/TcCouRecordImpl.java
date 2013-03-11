package com.shangde.edu.cou.service;

import java.util.List;
import com.shangde.edu.cou.domain.TcCouRecord;
import com.shangde.edu.cou.condition.QueryTcCouRecordCondition;
import com.shangde.common.service.BaseService;


@SuppressWarnings("unchecked")
public class TcCouRecordImpl extends BaseService implements ITcCouRecord{
    public java.lang.Integer addTcCouRecord(TcCouRecord tcCouRecord) {
return simpleDao.createEntity("TcCouRecord_NS.createTcCouRecord",tcCouRecord);
    }

    public void delTcCouRecordById(int id){
        simpleDao.deleteEntity("TcCouRecord_NS.deleteTcCouRecordById",id);
    }

    public void updateTcCouRecord(TcCouRecord tcCouRecord) {
        simpleDao.updateEntity("TcCouRecord_NS.updateTcCouRecord",tcCouRecord);
    }

    public TcCouRecord getTcCouRecordById(int id) {
        return simpleDao.getEntity("TcCouRecord_NS.getTcCouRecordById",id);
    }

    public List<TcCouRecord> getTcCouRecordList(QueryTcCouRecordCondition queryTcCouRecordCondition) {
        return simpleDao.getForList("TcCouRecord_NS.getTcCouRecordList",queryTcCouRecordCondition);
    }
}
