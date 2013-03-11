package com.shangde.edu.exam.service;

import com.shangde.common.service.BaseService;
import com.shangde.edu.exam.domain.Options;


public class OptionsImpl extends BaseService implements IOptions{
	/**
     * 添加选项
     * @param options 添加选项
     * @return id
     */
    public java.lang.Integer addOptions(Options options) {
    	return simpleDao.createEntity("Options_NS.createOptions",options);
    }
    
    /**
     * 删除选项
     * @param optId 选项ID
     */
    public void delOptionsById(int optId){
        simpleDao.deleteEntity("Options_NS.deleteOptionsById",optId);
    }
    
    /**
     * 更新选项
     * @param options 要更新的选项
     */
    public void updateOptions(Options options) {
        simpleDao.updateEntity("Options_NS.updateOptions",options);
    }

}
