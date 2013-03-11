package com.shangde.edu.sys.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.StringUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QueryModelCondition;
import com.shangde.edu.sys.domain.Model;
import com.shangde.edu.sys.service.IModel;


/**
 * 模块管理
 * 
 * @author wangzheng
 */
public class BackModelAction extends CommonAction {
	

	
	private IModel modelService;
	
	private Model model;
	
	private int modelId;
	
	private QueryModelCondition queryModelCondition;
	
	private String pageUrlParms;
	
	
	/**
	 * 创建模块
	 * @return
	 */
	public String createModel(){
		String returnStr="";
		String userName = getLoginedUser().getUserName();
		if(model!=null && model.getModel_name()!=null && !model.getModel_name().equals("") ){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date());
			model.setCreate_time(date);
			model.setAuthor(userName);
			int createModel = modelService.createModel(model);
			if(createModel!=0){
				returnStr="createModel";
			}else {
				returnStr="error";
			}
		}
		return returnStr;
	}
	
	public String toCreateModel(){
		
		return "toCreateModel";
	}
	
	
	/**
	 * 跳转编辑模块
	 * @return
	 */
	public String toEditModel(){
		String returnStr="";
		
		model = modelService.getModelByID(modelId);
		if(model!=null){
			returnStr="toUpdateModel";
		}else {
			returnStr="error";
		}
		return returnStr;
	}
	
	
	/**
	 * 编辑模块
	 * @return
	 */
	public String editModel(){
		String returnStr="";
		int editModel = modelService.editModel(model);
		if(editModel!=0){
			returnStr="updateModel";
		}else {
			returnStr="error";
		}
		
		return returnStr;
	}
	
	
	/**
	 * 得到模块列表
	 * @return
	 */
	public String getModelList() {
		
		try {
			String modelName = queryModelCondition.getModel_name();
			if (!StringUtil.isNullString(modelName)) {
				modelName = new String(modelName.getBytes("ISO-8859-1"), "UTF-8");
				queryModelCondition.setModel_name(modelName);
			}
			PageResult result = this.modelService.getModelList(queryModelCondition);
			this.setPage(result);
			pageUrlParms = this.getUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(10);
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "modelList";
	}
	
	
	/**
	 * 删除模块
	 * @return
	 */
	public String deleteModelByID(){
		String returnStr="";
		int deleteModel = modelService.deleteModel(modelId);
		if(deleteModel!=0){
			returnStr="deleteModel";
		}else {
			returnStr="error";
		}
		return returnStr;
	}
	
	public QueryModelCondition getQueryModelCondition() {
		return queryModelCondition;
	}

	public void setQueryModelCondition(QueryModelCondition queryModelCondition) {
		this.queryModelCondition = queryModelCondition;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public IModel getModelService() {
		return modelService;
	}

	public void setModelService(IModel modelService) {
		this.modelService = modelService;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public String getPageUrlParms() {
		return pageUrlParms;
	}

	public void setPageUrlParms(String pageUrlParms) {
		this.pageUrlParms = pageUrlParms;
	}
	
	
	
}
