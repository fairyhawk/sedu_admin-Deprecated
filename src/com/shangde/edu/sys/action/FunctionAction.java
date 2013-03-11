package com.shangde.edu.sys.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.sys.condition.QueryFunctionCondition;
import com.shangde.edu.sys.domain.Function;
import com.shangde.edu.sys.dto.KeyValueDTO;
import com.shangde.edu.sys.service.IFunction;

/**
 * 用户组管理action类
 * @author guoqiang.liu
 * @version 1.0
 */
public class FunctionAction extends CommonAction{
	
	private static final Logger logger = Logger.getLogger(FunctionAction.class);
	
	private static final long serialVersionUID = 7206401215912138624L;
	private IFunction functionService;
	private List<Function> functionList = new ArrayList<Function>();
	private QueryFunctionCondition queryFunctionCondition = new QueryFunctionCondition();
	private Function function;
	private List<Integer> ids;
	private Function parentFunction;

	public String toAddFunction() {
		//一级组
		functionList = functionService.getUsableFunctionList();
		return "toAddFunction";
	}

	/**
	 * 获取子权限列表
	 * @return
	 * @throws Exception
	 * @author chenshuai
	 */
	public String getChildFunctionById() {
		try{
			functionList = functionService.getChildFunctionById(function.getFunctionId());
			
			List<KeyValueDTO> myList = new ArrayList<KeyValueDTO>();
			
			KeyValueDTO keyvalue = null;

			for(int i=0; i < functionList.size(); i++) {
				keyvalue = new KeyValueDTO();
				Function tempFunction = functionList.get(i); 
				
				keyvalue.setKey(tempFunction.getFunctionId());
				keyvalue.setValue(tempFunction.getFunctionName());
				myList.add(keyvalue);
			}
			
			this.setResult(new Result<List<KeyValueDTO>>(true, "", null, myList));
		}catch(Exception e){
			logger.error("FunctionAction.getChildFunctionById", e);
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 添加权限
	 * @return
	 */
	public String addFunction() {
		try{
			function.setStatus(1);
			function.setCreateTime(new Date());
			function.setFunctionApplyScopeId(2);
			functionService.addFunction(function);
		}catch(Exception e){
			logger.error("FunctionAction.addFunction", e);
			return ERROR;
		}
		return "reshow";
	}
	
	/**
	 * 添加权限
	 * @return
	 */
	public String showFunctionList() {
		try{
			functionList = functionService.getUsableFunctionList();
		}catch(Exception e){
			logger.error("FunctionAction.showFunctionList", e);
			return ERROR;
		}
		return "showFunctionList";
	}
	
	/**
	 * 初始化修改权限
	 * @return
	 */
	public String toUpdateFunction() {
		try{
			function = functionService.getFunctionById(function.getFunctionId());
			functionList = functionService.getUsableFunctionList();
		}catch(Exception e){
			logger.error("FunctionAction.toUpdateFunction", e);
			return ERROR;
		}
		return "toUpdateFunction";
	}
	
	/**
	 * 修改权限
	 * @return
	 */
	public String updateFunction() {
		try{
			functionService.updateFunction(function);
		}catch(Exception e){
			logger.error("FunctionAction.updateFunction", e);
			return ERROR;
		}
		return "reshow";
	}
	
	/**
	 * 删除权限及子权限
	 * @return
	 */
	public String delFunctions() {
		functionService.delFunctions(ids);
		return "reshow";
	}

	public IFunction getFunctionService() {
		return functionService;
	}

	public void setFunctionService(IFunction functionService) {
		this.functionService = functionService;
	}

	public List<Function> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}

	public QueryFunctionCondition getQueryFunctionCondition() {
		if(queryFunctionCondition == null) {
			queryFunctionCondition = new QueryFunctionCondition();
		}
		return queryFunctionCondition;
	}

	public void setQueryFunctionCondition(
			QueryFunctionCondition queryFunctionCondition) {
		this.queryFunctionCondition = queryFunctionCondition;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public Function getParentFunction() {
		return parentFunction;
	}

	public void setParentFunction(Function parentFunction) {
		this.parentFunction = parentFunction;
	}
}
