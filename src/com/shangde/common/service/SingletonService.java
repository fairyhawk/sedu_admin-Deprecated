package com.shangde.common.service;

import com.shangde.common.exception.CommException;

public class SingletonService extends BaseService{
    public synchronized long getTableKey(String tableName) throws CommException{
        Object tableKeyObj = simpleDao.getEntity("public_sql.getTableKey",tableName);
        if(tableKeyObj == null || new Long("0").equals(tableKeyObj)){
            simpleDao.createEntity("public_sql.addTableKey",tableName);
            tableKeyObj = simpleDao.getEntity("public_sql.getTableKey",tableName);
        }
        simpleDao.updateEntity("public_sql.updateTableKey",tableName);
        return Long.parseLong(tableKeyObj.toString());
    }
}
