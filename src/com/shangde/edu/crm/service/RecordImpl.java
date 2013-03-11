package com.shangde.edu.crm.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.crm.condition.QueryRecordCondition;
import com.shangde.edu.crm.domain.Record;
import com.shangde.edu.crm.dto.RecordDTO;


/**
 *Record 接口管理
 *User:guoqiang.liu
 *Date:2011-11-03 
 */
@SuppressWarnings("unchecked")
public class RecordImpl extends BaseService implements IRecord{
    public java.lang.Integer addRecord(Record record) {
return simpleDao.createEntity("Record_NS.createRecord",record);
    }

    public void delRecordById(int id){
        simpleDao.deleteEntity("Record_NS.deleteRecordById",id);
    }

    public void updateRecord(Record record) {
        simpleDao.updateEntity("Record_NS.updateRecord",record);
    }

    public Record getRecordById(int id) {
        return simpleDao.getEntity("Record_NS.getRecordById",id);
    }

    public List<Record> getRecordList(QueryRecordCondition queryRecordCondition) {
        return simpleDao.getForList("Record_NS.getRecordList",queryRecordCondition);
    }
    
    /**
     * 通过销售机会id查询销售指派人记录
     * @param crmChanceId
     * @return
     */
    public List<RecordDTO> getRecordDTOListByChanceId(int crmChanceId){
    	return simpleDao.getForList("Record_NS.getRecordDTOListByChanceId", crmChanceId);
    }
    
    /**
     * 通过销售机会id查询沟通记录
     * @param crmChanceId
     * @return
     */
    public List<RecordDTO> getRecordListByChanceId(int crmChanceId){
    	return simpleDao.getForList("Record_NS.getRecordListByChanceId", crmChanceId);
    }
}
