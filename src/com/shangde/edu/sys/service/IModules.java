package com.shangde.edu.sys.service;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QueryModulesCondition;
import com.shangde.edu.sys.domain.Modules;

public interface IModules {
	
	/**
	 * 创建模板
	 */
	void createModules(Modules modules,String baseUrl);
	
	/**
	 * 删除模板
	 */
	void delModules(String ids,String baseUrl);
	
	/**
	 * 获得模板列表
	 */
	PageResult modulesList(QueryModulesCondition queryModulesCondition);
	
	/**
	 * 根据ID获得模板信息
	 */
	Modules getModulesById(String id);
	
	/**
	 * 修改模板
	 */
	void modifyModules(Modules modules,String baseUrl);
	
}
