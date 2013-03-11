package com.shangde.common.service;

import com.shangde.common.dao.ISimpleDao;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * 所有service对象的抽象父类，主要定义service层通用的方法，如dao注入、cache工具类注入等功能。注入的对象使用protected范围，以方便子类的调用。
 * User: guoqiang.liu
 * Date: 2008-11-29
 * Time: 16:20:34
 */
public abstract class BaseService{
    /**
     * 统一dao对象
     */
    protected ISimpleDao simpleDao;

    /**
     * 日志输出对象
     */
    protected Log logger = LogFactory.getLog(getClass());


    /**
     * 配置服务对象
     */
    protected ConfigService configurator;

    public ISimpleDao getSimpleDao() {
        return simpleDao;
    }

    public void setSimpleDao(ISimpleDao simpleDao) {
        this.simpleDao = simpleDao;
    }

    public Log getLogger() {
        return logger;
    }

    public void setLogger(Log logger) {
        this.logger = logger;
    }


    public ConfigService getConfigurator() {
        return configurator;
    }

    public void setConfigurator(ConfigService configurator) {
        this.configurator = configurator;
    }
    
   
}
