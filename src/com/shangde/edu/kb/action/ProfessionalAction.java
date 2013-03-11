package com.shangde.edu.kb.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.kb.condition.QueryProfessionalCondition;
import com.shangde.edu.kb.domain.Knowledge;
import com.shangde.edu.kb.domain.Professional;
import com.shangde.edu.kb.service.IProfessional;
/**
 * 专业管理action
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class ProfessionalAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(ProfessionalAction.class);
	/**
	 * 声名专业服务
	 */
	private IProfessional professionalService;
	/**
	 * 查询条件
	 */
	private QueryProfessionalCondition queryProfessionalCondition;
	/**
	 * List
	 */
	private List<Professional> pflList=new ArrayList<Professional>();
	/**
	 * 实体对象
	 */
	private Professional professional;
	/**
	 * 获得专业列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getProfessionalList(){
		try{
			pflList=this.professionalService.getProfessionalByList(queryProfessionalCondition);
		} catch (Exception e) {
			logger.error("ProfessionalAction.getProfessionalList", e);
			return ERROR;
		}
		return "professionalList";
	}
	/**
	 * 打开专业添加页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toProfessionalAdd(){
		try{
		} catch (Exception e) {
			logger.error("ProfessionalAction.toProfessionalAdd", e);
			return ERROR;
		}
		return "professionalAdd";
	}
	/**
	 * 获得专业列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String professionalAdd(){
		try{
			Date date=new Date();
			String pIndex="";
			professional.setPCreatetime(date);
			Professional pf=this.professionalService.getProfessionalLast();
			if(pf!=null){
			pIndex=pf.getPIndex();
			Knowledge knowledge=new Knowledge();
			pIndex=knowledge.getIndex(pIndex);
			}else{
			pIndex="P01";
			}
			professional.setPIndex(pIndex);
			this.professionalService.addProfessional(professional);
		} catch (Exception e) {
			logger.error("ProfessionalAction.professionalAdd", e);
			return ERROR;
		}
		return "professional_Add";
	}
	
	/**
	 * 删除专业
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String deleteProfessional(){
		try{
			if(professional.getPId()!=0){
				this.professionalService.delProfessionalById(professional.getPId());
			}
		} catch (Exception e) {
			logger.error("ProfessionalAction.deleteProfessional", e);
			return ERROR;
		}
		return "professional_Add";
	}
	/**
	 * 打开修改专业页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toEditProfessional(){
		try{
			if(professional.getPId()!=0){
				professional=this.professionalService.getProfessionalById(professional.getPId());
			}
		} catch (Exception e) {
			logger.error("ProfessionalAction.toEditProfessional", e);
			return ERROR;
		}
		return "professionalEdit";
	}
	/**
	 * 专业修改
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String professionalEdit(){
		try{
			Date date=new Date();
			if(professional!=null){
				professional.setPCreatetime(date);
			}
			this.professionalService.updateProfessional(professional);
		} catch (Exception e) {
			logger.error("ProfessionalAction.professionalEdit", e);
			return ERROR;
		}
		return "professional_Add";
	}
	

	public IProfessional getProfessionalService() {
		return professionalService;
	}

	public void setProfessionalService(IProfessional professionalService) {
		this.professionalService = professionalService;
	}

	public QueryProfessionalCondition getQueryProfessionalCondition() {
		if(queryProfessionalCondition==null){
			queryProfessionalCondition=new QueryProfessionalCondition();
		}
		return queryProfessionalCondition;
	}

	public void setQueryProfessionalCondition(
			QueryProfessionalCondition queryProfessionalCondition) {
		this.queryProfessionalCondition = queryProfessionalCondition;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

	public List<Professional> getPflList() {
		return pflList;
	}

	public void setPflList(List<Professional> pflList) {
		this.pflList = pflList;
	}


}
