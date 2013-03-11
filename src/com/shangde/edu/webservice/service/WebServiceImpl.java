package com.shangde.edu.webservice.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessResourceFailureException;

import com.shangde.common.service.BaseServiceManyDb;

/**
 * 
 * @author Yangning
 * @Date:2011-11-07
 * @desc:webService接口实现类，主要为四个操作,统计表记录数量，根据beginId,EndId得到列表，查询beginId,endId数值
 */
public class WebServiceImpl extends BaseServiceManyDb implements IWebService {
	
	private static final Logger logger = Logger.getLogger(WebServiceImpl.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object> getObjectList(String tableName, Integer beginId,Integer endId) {
		List<Object> list = null;
		boolean flag = true;
		Map map = new HashMap<String, Integer>();
        map.put("beginId", beginId);
        map.put("endId", endId);
        map.put("tableName", tableName);
        try{
        	list = simpleDaoRead.getForList("webservice.getObjectList",map);
        }catch(Exception e){
        	flag = false;
        	logger.error("WebServiceImpl.getObjectList \n\r " + e.getMessage());
        	throw new DataAccessResourceFailureException("getObjectList");
        }finally{
        	//记录调用日志
  			logger.info("WebServiceImplInvoke-- Method:getObjectList paramterbeginId:" + 
  			beginId + "\tparamterendId:" + endId + "\tisSuccess:" + flag +"\treturRecord:"+ ((list == null) ? 0 : list.size()) 
  			+ "\tinvokeTime:"+ formateString(new Date(),"yyyy-MM-dd hh:mm:ss"));
        }
		return list;
	}
	
	public Integer getObjectCount(String tableName) {
		Integer result = 0;
		boolean flag = true;
		try{
			result = simpleDaoRead.getEntity("webservice.getObjectCount", tableName);
		}catch(Exception e){
			flag = false;
			logger.error("WebServiceImpl.getObjectCount \n\r " , e);
			throw new DataAccessResourceFailureException("getObjectCount");
		}finally{
			logger.info("WebServiceImplInvoke-- Method: getObjectCount \tisSuccess:" + flag +"\treturResult:"+ result + "\tinvokeTime:"+ formateString(new Date(),"yyyy-MM-dd hh:mm:ss"));
		}
		return result;
	}
	
	private String formateString(Date date,String fromat){
		String formateStr = null;
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat(fromat);
			formateStr = sdf.format(date);
		}
		return formateStr;
	}

	public List<Object> getObjectList(Map paramMap) {
		List<Object> list = null;
		boolean flag = true;
        try{
        	list = simpleDaoRead.getForList("webservice.getObjectListMaped",paramMap);
        }catch(Exception e){
        	flag = false;
        	logger.error("WebServiceImpl.getObjectList \n\r " + e.getMessage());
        	throw new DataAccessResourceFailureException("getObjectList");
        }finally{
        	//记录调用日志
  			logger.info("WebServiceImplInvoke-- Method:getObjectList paramterbeginId:" + 
  			paramMap.get("beginId") + "\tparamterendId:" + paramMap.get("endId") + "\tisSuccess:" + flag +"\treturRecord:"+ ((list == null) ? 0 : list.size()) 
  			+ "\tinvokeTime:"+ formateString(new Date(),"yyyy-MM-dd hh:mm:ss"));
        }
		return list;
	}
	
	public Integer getObjectMaxId(Map paramMap) {
		int result = 0;
		boolean flag = true;
        try{
        	result = simpleDaoRead.getEntity("webservice.getObjectMaxId",paramMap);
        }catch(Exception e){
        	flag = false;
        	logger.error("WebServiceImpl.getObjectList \n\r " + e.getMessage());
        	throw new DataAccessResourceFailureException("getObjectList");
        }finally{
        	//记录调用日志
        	logger.info("WebServiceImplInvoke-- Method:getObjectMaxId " +  "\tisSuccess:" + flag + "\tinvokeTime:"+ formateString(new Date(),"yyyy-MM-dd hh:mm:ss"));
        }
		return result;
	}
	
	public Integer getObjectMinId(Map paramMap) {
		int result = 0;
		boolean flag = true;
        try{
        	result = simpleDaoRead.getEntity("webservice.getObjectMinId",paramMap);
        }catch(Exception e){
        	flag = false;
        	logger.error("WebServiceImpl.getObjectList \n\r " + e.getMessage());
        	throw new DataAccessResourceFailureException("getObjectList");
        }finally{
        	//记录调用日志
  			logger.info("WebServiceImplInvoke-- Method:getObjectMinId " +  "\tisSuccess:" + flag + "\tinvokeTime:"+ formateString(new Date(),"yyyy-MM-dd hh:mm:ss"));
        }
		return result;
	}

}
