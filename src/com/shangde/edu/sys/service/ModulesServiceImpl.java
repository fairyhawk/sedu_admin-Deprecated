package com.shangde.edu.sys.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

import cn.highso.core.utils.DateUtil;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QueryModulesCondition;
import com.shangde.edu.sys.domain.Model;
import com.shangde.edu.sys.domain.Modules;

public class ModulesServiceImpl extends BaseService implements IModules{

	private Logger logger = Logger.getLogger(ModulesServiceImpl.class);
	
	/**
	 * ==================================获得模板列表=================================
	 */
	@Override
	public PageResult modulesList(QueryModulesCondition queryModulesCondition){
		return simpleDao.getPageResult("Modules_NS.getModulesList", "Modules_NS.getModulesCount", queryModulesCondition);
	}
	
	/**
	 * ===================================创建模板===================================
	 */
	@Override
	public void createModules(Modules modules,String baseUrl) {
		
		/**
		 * 生成模板文件
		 */
		String content = createTempletFile(modules,baseUrl+ File.separator + modules.getUrl());
		
		/**
		 * 保存模板信息
		 */
		modules.setCreateTime(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		modules.setContent(content);
		this.simpleDao.createEntity("Modules_NS.createModules", modules);
	}
	
	/**
	 * ===============================获得模板信息=====================================
	 */
	@Override
	public Modules getModulesById(String id) {
		
		Modules modules = new Modules();
		/**
		 * 获得模板信息
		 */
		List<Modules> modulesList = this.simpleDao.getForList("Modules_NS.getModulesByIds", id);
		modules = modulesList.get(0);
		
		/**
		 * 获取子模块信息
		 */
		List<Model> modelList = this.simpleDao.getForList("Model_NS.getModelByMids", modules.getmIds());
		
		/**
		 * 处理模块顺序
		 */
		List<Model> orderList = new ArrayList<Model>();
		modules.setOrderMIds(modules.getmIds().split(","));
		for(String str : modules.getOrderMIds()){
			for(Model m : modelList){
				if(str.equals(String.valueOf(m.getId()))){
					orderList.add(m);
					break;
				}
			}
		}
		modules.setModelList(orderList);
		
		return modules;
	}
	
	/**
	 * =========================================修改模板===================================
	 */
	public void modifyModules(Modules modules,String baseUrl){
		
		/**
		 * 删除之前模板文件
		 */
		List<Modules> modulesList = this.simpleDao.getForList("Modules_NS.getModulesByIds", String.valueOf(modules.getId()));
		deleteDir(new File(baseUrl + File.separator + modulesList.get(0).getUrl()));
		
		/**
		 * 创建新的模板文件
		 */
		String content = createTempletFile(modules,baseUrl + File.separator + modulesList.get(0).getUrl());
		modules.setContent(content);
		
		try {
			this.simpleDao.update("Modules_NS.updateModules", modules);
		} catch (Exception e) {
			logger.error("更新模板异常：" + e.getMessage());
		}
		
	}

	/**
	 * =================================删除模板===============================
	 */
	@Override
	public void delModules(String ids,String baseUrl) {
		
		/**
		 * 删除模板文件
		 */
		List<Modules> modules = this.simpleDao.getForList("Modules_NS.getModulesByIds", ids);
		
		for (Modules m : modules) {
			deleteDir(new File(baseUrl + File.separator + m.getUrl()));
		}
		
		/**
		 * 删除数据库保存信息
		 */
		this.simpleDao.delete("Modules_NS.deleteModules", ids);
	}

	/**
	 * ========================生成模板==============================
	 */
	public String createTempletFile(Modules modules,String baseUrl){
		
		File file = new File(baseUrl);
		file.mkdir();
		/**
		 * 模板名称
		 */
		String modulesName = modules.getUrl() + ".jsp";
		
		/**
		 * 获取模块信息
		 */
		List<Model> modelList = simpleDao.getForList("Model_NS.getModelByMids", modules.getmIds());
		
		/**
		 * 处理模块顺序
		 */
		Map<Integer, Model> orderResult = new LinkedHashMap<Integer, Model>();
		for(String str : modules.getOrderMIds()){
			
			for(Model m : modelList){
				if(str.equals(String.valueOf(m.getId()))){
					orderResult.put(m.getId(), m);
					break;
				}
			}
		}
		
		/**
		 * 生成模板文件
		 */
		StringBuffer modulesContent = new StringBuffer();
		List<String> subModulesList = new ArrayList<String>();
		Iterator<Entry<Integer, Model>> entrys = orderResult.entrySet().iterator();
		while(entrys.hasNext()){
			Entry<Integer, Model> entry = entrys.next();
			/**
			 * 把模块生成jsp页面
			 */
			String currUrl = baseUrl + File.separator + entry.getValue().getUrl() + ".jsp";
			subModulesList.add(currUrl);
			createFile(currUrl,entry.getValue().getContent());
		}
		
		/**
		 * 生成主模板文件
		 */
		/*modulesContent.append("<%@ page language=\"java\" contentType=\"text/html;\"%>\r\n");
		modulesContent.append("<%@ include file=\"/include/header.inc\" %>\r\n");
		for (String url : subModulesList) {
			Pattern pattern = Pattern.compile("(^.*)(static.*$)");
			Matcher matcher = pattern.matcher(url);
			while(matcher.find()){
				url = matcher.group(2).replaceAll("\\\\", "/");
				modulesContent.append("<tiles:insertTemplate template=\"/" + url + "\"></tiles:insertTemplate>\r\n");
			}
			
		}
		createFile(baseUrl + File.separator + modulesName, modulesContent.toString());*/
		
		//return modulesContent.toString();
		return "";
	}
	
	/**
	 * =============================创建文件===================================
	 */
	public void createFile(String url,String content){
		
		File file = new File(url);
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"));
			buff.write(content);
			buff.close();
			
			logger.debug("生成模板文件成功！路径为：" + url);
		} catch (Exception e) {
			logger.error("生成模版文件异常：" + e.getMessage());
		}
	}
	
	/**
	 * =================================删除模板文件===================================
	 */
	public boolean deleteDir(File file){
		
		if (file.isDirectory()) {
			String[] children = file.list();
			//递归删除目录中的子目录下            
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(file, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return file.delete();
		
	}

}
