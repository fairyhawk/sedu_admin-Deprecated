package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.sys.condition.QueryRoleFunctionCondition;
import com.shangde.edu.sys.domain.RoleFunction;

/**
 * RoleFunction对象操作实现类
 * @author guoqiang.liu
 */
@SuppressWarnings("unchecked")
public class RoleFunctionImpl extends BaseService implements IRoleFunction{
    public void addRoleFunction(RoleFunction roleFunction) {
        simpleDao.createEntity("RoleFunction_NS.createRoleFunction",roleFunction);
    }

    public void delRoleFunctionById(int roleId,int functionId){
        java.util.Map keyMap = new java.util.HashMap();
        keyMap.put("roleId",roleId);
        keyMap.put("functionId",functionId);
        simpleDao.deleteEntity("RoleFunction_NS.deleteRoleFunctionById",keyMap);
    }

    public void updateRoleFunction(RoleFunction roleFunction) {
        simpleDao.updateEntity("RoleFunction_NS.updateRoleFunction",roleFunction);
    }

    public RoleFunction getRoleFunctionById(int roleId,int functionId) {
        java.util.Map keyMap = new java.util.HashMap();
        keyMap.put("roleId",roleId);
        keyMap.put("functionId",functionId);
        return simpleDao.getEntity("RoleFunction_NS.getRoleFunctionById",keyMap);
    }

    public List<RoleFunction> getRoleFunctionList(QueryRoleFunctionCondition queryRoleFunctionCondition) {
        return simpleDao.getForList("RoleFunction_NS.getRoleFunctionList",queryRoleFunctionCondition);
    }
}
