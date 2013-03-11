package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QueryModelCondition;
import com.shangde.edu.sys.domain.Model;


/**
 * @author wangzheng
 * 
 * Date: 2012-06-25
 */
public interface IModel {
	
	
	/**
	 * 创建模块
	 * @param model
	 * @return id
	 */
	public int createModel(Model model);
	
	/**
	 * 得到模块集合
	 */
	public PageResult getModelList(QueryModelCondition queryModelCondition);
	
	/**
	 * 删除模块
	 */
	public int deleteModel(int modelId);
	
	/**
	 * 根据模块ID得到当前模块
	 */
	public Model getModelByID(int modelId);
	
	/**
	 * 根据商品ID编辑商品
	 * @param model
	 * @return
	 */
	public int editModel(Model model);
	
	/**
	 * 获得所有模板
	 * @return
	 */
	public List<Model> getAllModelList();
	
	/**
	 * 获取子模块信息
	 */
	public List<Model> getModelByMids(String mIds);
}
