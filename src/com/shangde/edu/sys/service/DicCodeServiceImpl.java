package com.shangde.edu.sys.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.shangde.common.service.BaseService;
import com.shangde.common.util.DicCodeSingleton;
import com.shangde.edu.sys.domain.DicCode;

public class DicCodeServiceImpl extends BaseService implements DicCodeService {
	private static   Map<String, LinkedHashMap<String, String>> all = new HashMap<String, LinkedHashMap<String, String>>();
	/**
	 * 查询出字典实体
	 * @param dicCode
	 * @return
	 */
	public List<DicCode> getDicCodeList(String dicType,String dicCodeStr){
		if(!dicType.equals("")){
			DicCodeSingleton.getInstance().setDicType(dicType);
		}
		if(!dicCodeStr.equals("")){
			DicCodeSingleton.getInstance().setDicCode(dicCodeStr);
		}else{
			DicCodeSingleton.getInstance().setDicCode("");
		}
		return simpleDao.getForList("DicCode_NS.getDic", DicCodeSingleton.getInstance());
	}
	 public void getFields() {
	        List<DicCode> list = simpleDao.getForList("DicCode_NS.getDicList","");
	       
	        LinkedHashMap<String, String> part = null;
	        for (DicCode dicCode : list) {
	            String key = dicCode.getDicType();
	            if (all.containsKey(key)) {
	                // 如果包含这个field，就加入它的值
	                part = all.get(key);
	                part.put(dicCode.getDicCode(), dicCode.getDicContent());
	            } else {
	                part = new LinkedHashMap<String, String>();
	                part.put(dicCode.getDicCode(), dicCode.getDicContent());
	                // 没有这个fiel，则新加入这个filed
	                all.put(key, part);
	            }
	           
	        }
	      /*  part = new LinkedHashMap<String, String>();
	        logger.info("开始读取系统默认配置");
	        for (Map.Entry<String, LinkedHashMap<String, String>> entry : all.entrySet()) {
	            String key = entry.getKey();
	            HashMap<String, String> value = entry.getValue();
	            // 为了eval('(${applicationScope.fields.sex})')这个单引号使用,替换所有的'，为\'
	            String val = JackJson.fromObjectToJson(value).replaceAll("\\'", "\\\\'");
	            logger.info(key+":" +val);
	            part.put(key, val);
	        }
	        logger.info("结束读取系统默认配置");*/
	        
	     
	    }
	 public Map<String,String> getDicByType(String type){
		 Map<String, String> map = all.get(type);
		 return map ;
	 }
	 /**
		 * 根据字典类型以及字典code查询字典内容
		 * @param dictype
		 * @param dicCode
		 * @return
		 */
	public Map<String,String> getDicContent(String dictype, List<String> list) {
		Map<String,String> map=new HashMap<String,String>();
		Iterator<String> iter=list.iterator();
		while(iter.hasNext()){
			String dicTypeCode=iter.next();
			map.put(dicTypeCode, all.get(dictype).get(dicTypeCode));
		}
		return map;
	}
	 
}
