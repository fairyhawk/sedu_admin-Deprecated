package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.sys.condition.QueryFunctionCondition;
import com.shangde.edu.sys.domain.Function;

/**
 * Function对象操作实现类
 * @author guoqiang.liu
 */
@SuppressWarnings("unchecked")
public class FunctionImpl extends BaseService implements IFunction{
    public java.lang.Integer addFunction(Function function) {
        return simpleDao.createEntity("Function_NS.createFunction",function);
    }

    public void delFunctionById(int functionId){
        simpleDao.deleteEntity("Function_NS.deleteFunctionById",functionId);
    }

    public void updateFunction(Function function) {
        simpleDao.updateEntity("Function_NS.updateFunction",function);
    }

    public Function getFunctionById(int functionId) {
        return simpleDao.getEntity("Function_NS.getFunctionById",functionId);
    }

    public List<Function> getFunctionList(QueryFunctionCondition queryFunctionCondition) {
        return simpleDao.getForList("Function_NS.getFunctionList",queryFunctionCondition);
    }

    public List<Function> getFirstLevelFunctionList() {
        QueryFunctionCondition queryFunctionCondition = new QueryFunctionCondition();
        queryFunctionCondition.setParentFunctionId(1);
        return getFunctionList(queryFunctionCondition);
    }

	public List<Function> getUsableFunctionList() {
        QueryFunctionCondition queryFunctionCondition = new QueryFunctionCondition();
        queryFunctionCondition.setStatus(1);
        return getFunctionList(queryFunctionCondition);
	}

	public List<Function> getFirFunction(
			QueryFunctionCondition queryFunctionCondition) {
		return simpleDao.getForList("Function_NS.getFirFunction",queryFunctionCondition);
	}

	public List<Function> getChildFunctionById(int functionId) {
		return simpleDao.getForList("Function_NS.getChildFunctionById", functionId);
	}

	public void delFunctions(List<Integer> list) {
		if(list != null) {
			for(int i=0; i<list.size(); i++) {
				delFunction(list.get(i));
			}
		}
	}
	
	public void delFunction(int id) {
		simpleDao.deleteEntity("RoleFunction_NS.deleteRoleFunctionByFunctionId",id);
		List<Function> list = getChildFunctionById(id);
		if(list != null) {
			for(int i=0; i<list.size(); i++) {
				delFunction(list.get(i).getFunctionId());
			}
		}
		delFunctionById(id);
	}
}
