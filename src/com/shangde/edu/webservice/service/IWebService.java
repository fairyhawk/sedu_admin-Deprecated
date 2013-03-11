package com.shangde.edu.webservice.service;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author Yangning
 * @date:2011-11-07
 * @desc:WebService接口，用于官网平台获取数据
 */
public interface IWebService {
	
    public Integer getObjectCount(String tableName);
    
    public List<Object> getObjectList(String tableName ,Integer beginId,Integer endId);
    
    public List<Object> getObjectList(Map paramMap);
    
    public Integer getObjectMaxId(Map paramMap);
    
    public Integer getObjectMinId(Map paramMap);
}
