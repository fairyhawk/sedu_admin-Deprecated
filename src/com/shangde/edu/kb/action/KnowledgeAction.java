package com.shangde.edu.kb.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.kb.condition.QueryKnowledgeCondition;
import com.shangde.edu.kb.condition.QueryProfessionalCondition;
import com.shangde.edu.kb.domain.Knowledge;
import com.shangde.edu.kb.domain.Professional;
import com.shangde.edu.kb.domain.StudyCourse;
import com.shangde.edu.kb.service.IKnowledge;
import com.shangde.edu.kb.service.IProfessional;
import com.shangde.edu.kb.service.IStudyCourse;
/**
 * 知识结构树action
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class KnowledgeAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(KnowledgeAction.class);
	
	/**
	 * 声名知识结构服务
	 */
	private IKnowledge knowledgeService;
	private IProfessional professionalService;
	/**
	 * 声名学科服务
	 */
	private IStudyCourse studyCourseService;
	private StudyCourse studycourse;
	/**
	 * 查询条件
	 */
	private QueryKnowledgeCondition queryKnowledgeCondition;
	/**
	 * List
	 */
	private List<Knowledge> knowledgeList=new ArrayList<Knowledge>();
	private List<Knowledge> newKList=new ArrayList<Knowledge>();;
	private List<Professional> professionalList=new ArrayList<Professional>();
	/**
	 * 实体对象
	 */
	private Knowledge knowledge;
	private String index;
	private int kId; 
	private int spId; //专业id
	private int fpId; //课程id
	
	
	/**
	 * 查询出知识结构树
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getKnowledgeTree(){
		try{
			//联动菜单用
			this.professionalList = professionalService.getProfessionalList(new QueryProfessionalCondition());
			if(knowledge!=null){
				//查找这个科目下的所有结点
				this.getQueryKnowledgeCondition().setScId(knowledge.getScId());
				knowledgeList=this.knowledgeService.getKnowledgeListByScId(getQueryKnowledgeCondition());
				//查出科目这条记录
				studycourse=this.studyCourseService.getStudyCourseById(knowledge.getScId());
				//根据科目索引，查找这个科目的所有下级
				if(index!=null&&!"".equals(index)){
					this.getQueryKnowledgeCondition().setUpIndex(index);
					knowledge=this.knowledgeService.getKnowledgeByIndex(index);
				}
				else{
					if(studycourse.getCIndex()!=null&&!"".equals(studycourse.getCIndex())){
					this.getQueryKnowledgeCondition().setUpIndex(studycourse.getCIndex());
					}
				}
				newKList=this.knowledgeService.getKnowledgeListByScId(this.getQueryKnowledgeCondition());
			}
		} catch (Exception e) {
			logger.error("KnowledgeAction.getKnowledgeTree", e);
			return ERROR;
		}
		return "knowledgetree";
	}
	
	
	/**
	 * 查询出知识结构树Json
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getKnowledgeTreeJson(){
		try{
			//联动菜单用
			this.professionalList = professionalService.getProfessionalList(new QueryProfessionalCondition());
			if(knowledge!=null){
				//查找这个科目下的所有结点
				this.getQueryKnowledgeCondition().setScId(knowledge.getScId());
				knowledgeList=this.knowledgeService.getKnowledgeListByScId(getQueryKnowledgeCondition());
				//查出科目这条记录
				studycourse=this.studyCourseService.getStudyCourseById(knowledge.getScId());
				//根据科目索引，查找这个科目的所有下级
				if(index!=null&&!"".equals(index)){
					this.getQueryKnowledgeCondition().setUpIndex(index);
					knowledge=this.knowledgeService.getKnowledgeByIndex(index);
				}
				else{
					if(studycourse.getCIndex()!=null&&!"".equals(studycourse.getCIndex())){
					this.getQueryKnowledgeCondition().setUpIndex(studycourse.getCIndex());
					}
				}
				newKList=this.knowledgeService.getKnowledgeListByScId(this.getQueryKnowledgeCondition());
				
				JSONArray jstl = JSONArray.fromObject(knowledgeList);
				this.setResult(new Result(true,jstl.toString(),studycourse.getCName(),null));
			
			}
		} catch (Exception e) {
			logger.error("KnowledgeAction.getKnowledgeTreeJson", e);
			return ERROR;
		}
		return "Json";
	}
	
	
	
    //添加结构树的节点
	public String addKnowledgeTree(){
		String kResult="";
		try{
			Date date=new Date();
			String kIndex="";
			this.getQueryKnowledgeCondition().setScId(knowledge.getScId());
			this.getQueryKnowledgeCondition().setUpIndex(knowledge.getUpIndex());
			Knowledge kl=this.knowledgeService.getKnowledgeLast(queryKnowledgeCondition);
			if(kl!=null){
				kIndex=kl.getKIndex();
				kIndex=knowledge.getIndex(kIndex);
			}else{
				
				kIndex=knowledge.getUpIndex()+"_01";
			}
			knowledge.setKCreateTime(date);
			knowledge.setKIndex(kIndex);
			this.knowledgeService.addKnowledge(knowledge);
			if(index!=null&&!"".equals(index)){
				kResult="addknowledgetree";
			}else{
				kResult="addknowledgetreenew";
			}
			
		} catch (Exception e) {
			logger.error("KnowledgeAction.addKnowledgeTree", e);
			return ERROR;
		}
		return kResult;
	}

	//打开添加子级页面
	public String toAddNextKnowledge(){
		try{
			if(knowledge.getKId()!=0){
				knowledge=this.knowledgeService.getKnowledgeById(knowledge.getKId());
			}
		}catch (Exception e) {
			logger.error("KnowledgeAction.toAddNextKnowledge", e);
			return ERROR;
		}
		return "toaddnextknowledge";
	}
	//添加子级
	public String addNextKnowledge(){
		try{
			Date date=new Date();
			String kIndex="";
			this.getQueryKnowledgeCondition().setScId(knowledge.getScId());
			this.getQueryKnowledgeCondition().setUpIndex(knowledge.getUpIndex());
			Knowledge kl=this.knowledgeService.getKnowledgeLast(queryKnowledgeCondition);
			if(kl!=null){
				kIndex=kl.getKIndex();
				kIndex=knowledge.getIndex(kIndex);
			}else{
				
				kIndex=knowledge.getUpIndex()+"_01";
			}
			knowledge.setKCreateTime(date);
			knowledge.setKIndex(kIndex);
			this.knowledgeService.addKnowledge(knowledge);
			
			
		}catch (Exception e) {
			logger.error("KnowledgeAction.addNextKnowledge", e);
			return ERROR;
		}
		return "addknowledgetree";
	}
	
	//打开修改页面
	public String toEditKnowledge(){
		try{
			if(knowledge.getKId()!=0){
				knowledge=this.knowledgeService.getKnowledgeById(knowledge.getKId());
			}
			
		}catch (Exception e) {
			logger.error("KnowledgeAction.toEditKnowledge", e);
			return ERROR;
		}
		return "editknowledge";
	}
	//修改
	public String editKnowledge(){
		try{
			Date date=new Date();
			if(knowledge!=null){
				knowledge.setKCreateTime(date);
				this.knowledgeService.updateKnowledge(knowledge);
			}
			
		}catch (Exception e) {
			logger.error("KnowledgeAction.editKnowledge", e);
			return ERROR;
		}
		return "addknowledgetree";
	}
	
	public String deleteKnowledge(){
		try {
			this.getQueryKnowledgeCondition().setKPId(this.kId);
			this.knowledgeList=this.knowledgeService.getKnowledgeList(this.getQueryKnowledgeCondition());
			
			if(knowledgeList.size()>0){
				JSONArray jslist = JSONArray.fromObject(knowledgeList);
				this.setResult(new Result<Object>(true, jslist.toString(), null, null));
				return "Json";
			}else {
				this.knowledgeService.delKnowledgeById(this.kId);
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("KnowledgeAction.deleteKnowledge", e);
			return ERROR;
		}
		return "Json";
	}
	
	public IKnowledge getKnowledgeService() {
		return knowledgeService;
	}



	public void setKnowledgeService(IKnowledge knowledgeService) {
		this.knowledgeService = knowledgeService;
	}



	public QueryKnowledgeCondition getQueryKnowledgeCondition() {
		if(queryKnowledgeCondition==null){
			queryKnowledgeCondition=new QueryKnowledgeCondition();
		}
		return queryKnowledgeCondition;
	}



	public void setQueryKnowledgeCondition(
			QueryKnowledgeCondition queryKnowledgeCondition) {
		this.queryKnowledgeCondition = queryKnowledgeCondition;
	}



	public List<Knowledge> getKnowledgeList() {
		return knowledgeList;
	}



	public void setKnowledgeList(List<Knowledge> knowledgeList) {
		this.knowledgeList = knowledgeList;
	}



	public Knowledge getKnowledge() {
		return knowledge;
	}



	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}



	public IProfessional getProfessionalService() {
		return professionalService;
	}



	public void setProfessionalService(IProfessional professionalService) {
		this.professionalService = professionalService;
	}



	public List<Professional> getProfessionalList() {
		return professionalList;
	}



	public void setProfessionalList(List<Professional> professionalList) {
		this.professionalList = professionalList;
	}



	public IStudyCourse getStudyCourseService() {
		return studyCourseService;
	}



	public void setStudyCourseService(IStudyCourse studyCourseService) {
		this.studyCourseService = studyCourseService;
	}



	public StudyCourse getStudycourse() {
		return studycourse;
	}



	public void setStudycourse(StudyCourse studycourse) {
		this.studycourse = studycourse;
	}



	public List<Knowledge> getNewKList() {
		return newKList;
	}



	public void setNewKList(List<Knowledge> newKList) {
		this.newKList = newKList;
	}



	public String getIndex() {
		return index;
	}



	public void setIndex(String index) {
		this.index = index;
	}
	public int getKId() {
		return kId;
	}
	public void setKId(int id) {
		kId = id;
	}


	public int getSpId() {
		return spId;
	}


	public void setSpId(int spId) {
		this.spId = spId;
	}


	public int getFpId() {
		return fpId;
	}


	public void setFpId(int fpId) {
		this.fpId = fpId;
	}
	


}
