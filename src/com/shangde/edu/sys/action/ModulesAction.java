package com.shangde.edu.sys.action;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.highso.core.orm.Page;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.sys.condition.QueryModulesCondition;
import com.shangde.edu.sys.domain.Model;
import com.shangde.edu.sys.domain.Modules;
import com.shangde.edu.sys.service.IModel;
import com.shangde.edu.sys.service.IModules;
import com.shangde.edu.sys.service.ModelServiceImpl;

/**
 * 模块管理 modules
 * @author 陈晶学
 * @version 1.0
 */
@SuppressWarnings("all")
public class ModulesAction extends CommonAction {
	
	private IModel modelService;
	private IModules modulesService;
	private QueryModulesCondition queryModulesCondition;
	private Modules modules;
	private List<Model> modelList;
	private String pageUrlParms;
	private String ids;
	private static String BASEURL = "/static/usercenter/container/";
	
	private Logger logger = Logger.getLogger(ModulesAction.class);
	/**
	 * 模板管理列表
	 * @return
	 */
	public String list(){
		
		this.getQueryModulesCondition().setPageSize(20);
		this.setPage(modulesService.modulesList(queryModulesCondition));
		setPageUrlParms();
		return "modulesList";
	}
	
	/**
	 * to 创建模版
	 * @return
	 */
	public String toAdd(){
		
		/**
		 * 获得模块列表
		 */
		try {
			modelList = modelService.getAllModelList();
		} catch (Exception e) {
			logger.error("获取子模块异常：" + e.getMessage());
		}
		

		return "toAdd";
	}
	
	/**
	 * 创建并生成模版
	 * @return
	 */
	public String doAdd(){
		
		String[] mIdsParas = getServletRequest().getParameterValues("mIds");
		StringBuffer mIds = new StringBuffer();
		for(String str : mIdsParas){
			mIds.append(str+",");
		}
		modules.setOrderMIds(mIdsParas);
		modules.setmIds(mIds.substring(0, mIds.length()-1));
		
		setSavePath(BASEURL);
		modulesService.createModules(modules,getRealSavePathForWeb());
		return "add";
	}
	
	/**
	 * to 修改模版
	 */
	public String toModify(){
		/**
		 * 获得当前模板信息
		 */
		modelList = modelService.getAllModelList();
		modules = modulesService.getModulesById(String.valueOf(modules.getId()));
		return "toModify";
	}
	
	/**
	 * 提交修改
	 */
	public String doModify(){
		
		String[] mIdsParas = getServletRequest().getParameterValues("mIds");
		StringBuffer mIds = new StringBuffer();
		for(String str : mIdsParas){
			mIds.append(str+",");
		}
		modules.setOrderMIds(mIdsParas);
		modules.setmIds(mIds.substring(0, mIds.length()-1));
		setSavePath(BASEURL);
		modulesService.modifyModules(modules,getRealSavePathForWeb());
		
		return "modify";
	}
	
	/**
	 * 删除模板
	 */
	public void del(){
		PrintWriter out = null;
		try {
			out = this.getServletResponse().getWriter();
			setSavePath(BASEURL);
			modulesService.delModules(ids,getRealSavePathForWeb());
			out.write("1");
		} catch (Exception e) {
			out.write("0");
			logger.error("删除模板异常：" + e.getMessage());
		}finally{
			out.flush();
			out.close();
		}
	}

	public List<Model> getModelList() {
		return modelList;
	}

	public void setModelList(List<Model> modelList) {
		this.modelList = modelList;
	}

	public IModel getModelService() {
		return modelService;
	}

	public void setModelService(IModel modelService) {
		this.modelService = modelService;
	}

	public Modules getModules() {
		return modules;
	}

	public void setModules(Modules modules) {
		this.modules = modules;
	}

	public IModules getModulesService() {
		return modulesService;
	}

	public void setModulesService(IModules modulesService) {
		this.modulesService = modulesService;
	}

	public QueryModulesCondition getQueryModulesCondition() {
		if(queryModulesCondition == null){
			queryModulesCondition = new QueryModulesCondition();
		}
		return queryModulesCondition;
	}

	public void setQueryModulesCondition(QueryModulesCondition queryModulesCondition) {
		this.queryModulesCondition = queryModulesCondition;
	}

	public String getPageUrlParms() {
		return pageUrlParms;
	}

	public void setPageUrlParms(String pageUrlParms) {
		this.pageUrlParms = pageUrlParms;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
