package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QueryModelCondition;
import com.shangde.edu.sys.domain.Model;

public class ModelServiceImpl extends BaseService implements IModel{

	
	/**
	 * 创建模块
	 * @author wangzheng
	 */
	public int createModel(Model model) {
		// TODO Auto-generated method stub
		return simpleDao.createEntity("Model_NS.createModel", model);
	}

	public PageResult getModelList(QueryModelCondition queryModelCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("Model_NS.getModelList", "Model_NS.getModelCount", queryModelCondition);
	}
	
	public int deleteModel(int modelId){
		
		return simpleDao.delete("Model_NS.deleteModelById", modelId);
	}

	public Model getModelByID(int modelId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Model_NS.getModelById", modelId);
	}

	public int editModel(Model model) {
		// TODO Auto-generated method stub
		return simpleDao.update("Model_NS.updateModel", model);
	}

	@Override
	public List<Model> getAllModelList() {
		return simpleDao.getForList("Model_NS.getAllModelList");
	}
	
	public List<Model> getModelByMids(String mIds){
		return simpleDao.getForList("Model_NS.getModelByMids", mIds);
	}

}
