package com.shangde.edu.kb.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.kb.condition.QueryChapterCondition;
import com.shangde.edu.kb.condition.QueryKnowledgeCondition;
import com.shangde.edu.kb.condition.QueryProfessionalCondition;
import com.shangde.edu.kb.condition.QueryStudyCourseCondition;
import com.shangde.edu.kb.domain.Knowledge;
import com.shangde.edu.kb.domain.Professional;
import com.shangde.edu.kb.domain.StudyCourse;
import com.shangde.edu.kb.service.IKnowledge;
import com.shangde.edu.kb.service.IProfessional;
import com.shangde.edu.kb.service.IStudyCourse;

/**
 * 专业管理action
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class StudyCourseAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(StudyCourseAction.class);
	/**
	 * 声名专业服务
	 */
	private IProfessional professionalService;
	
	private IKnowledge knowledgeService;
	/**
	 * 查询条件
	 */
	private QueryStudyCourseCondition queryStudyCourseCondition;
	private QueryProfessionalCondition queryProfessionalCondition;
	private QueryKnowledgeCondition queryKnowledgeCondition;
	/**
	 * 实体对象
	 */
	private Professional professional;
	private StudyCourse studycourse;
	/**
	 * List
	 */
	private List<StudyCourse> scList = new ArrayList<StudyCourse>();
	
	private List<Professional> pList = new ArrayList<Professional>();

	/**
	 * 声名学科服务
	 */
	private IStudyCourse studyCourseService;
	/**
	 * 声明专业id
	 */
	private int pId;

	/**
	 * 声明课程ID
	 */
	private int cId;
	/**
	 * 声明专业索引
	 */
	private String pIndex;
	/**
	 * 声明年份
	 */
	private int pVersion;

	/**
	 * 获得学科列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getStudyCourseList() {
		try {
			scList = this.studyCourseService.getStudyCourseByList(this
					.getQueryStudyCourseCondition());
			this.pList = this.professionalService.getProfessionalList(this.queryProfessionalCondition);
			
			for(int i=0;i<scList.size();i++){
				for(int a=0;a<pList.size();a++){
					if(scList.get(i).getPId()==pList.get(a).getPId()){
						scList.get(i).setPName(pList.get(a).getPName());
					}
				}
			}
		} catch (Exception e) {
			logger.error("StudyCourseAction.getStudyCourseList", e);
			return ERROR;
		}
		return "studyCourseList";
	}

	/**
	 * 打开学科添加页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toStudyCourseAdd() {
		try {
			pList = this.professionalService
					.getProfessionalByList(queryProfessionalCondition); // 得到全部专业信息

			if (this.pId != 0) {
				for (int i = 0; i < pList.size(); i++) {
					if (pList.get(i).getPId() == this.pId) {
						this.pIndex = this.pList.get(i).getPIndex();
						this.pVersion = this.pList.get(i).getPVersion();
					}
				}
			}

		} catch (Exception e) {
			logger.error("StudyCourseAction.toStudyCourseAdd", e);
			return ERROR;
		}
		return "studyCourseAdd";
	}

	/**
	 * 添加学科
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String studyCourseAdd() {
		try {
			String cIndex="";
			Date date = new Date();
			studycourse.setCCreatetime(date);
			// 处理索引编号，专业编号+下划线+学科编号＝学科编号
			StudyCourse sc=this.studyCourseService.getStudyCourseLast(pId);
			if(sc!=null){
				cIndex=sc.getCIndex();
				Knowledge knowledge=new Knowledge();
				cIndex=knowledge.getIndex(cIndex.trim());
			}else{
				cIndex=pIndex+"_01";
			}
			studycourse.setCIndex(cIndex);
			if (this.pId != 0) {
				studycourse.setPId(this.pId);
			}
			if(this.pVersion!=0){
				studycourse.setCVersion(this.pVersion);
			}
			this.studyCourseService.addStudyCourse(studycourse);
		} catch (Exception e) {
			logger.error("StudyCourseAction.studyCourseAdd", e);
			return ERROR;
		}
		return "studyCourse_Add";
	}

	/**
	 * 删除学科
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String deleteStudyCourse() {
		List<Knowledge> kList = new ArrayList<Knowledge>();
		try {
			if (this.cId!=0) {
				this.getQueryKnowledgeCondition().setScId(this.cId);
				kList = this.knowledgeService.getKnowledgeListByScId(this.queryKnowledgeCondition);
				if(kList.size()!=0){
					JSONArray jslist = JSONArray.fromObject(kList);
					this.setResult(new Result<Object>(true, jslist.toString(), null, null));
					return "json";
				}else {
					this.studyCourseService
					.delStudyCourseById(this.cId);
				}
			}
		} catch (Exception e) {
			logger.error("StudyCourseAction.deleteStudyCourse", e);
			return ERROR;
		}
		return "json";
	}

	/**
	 * 打开修改学科页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toEditStudyCourse() {
		try {
			pList = this.professionalService
					.getProfessionalByList(queryProfessionalCondition); // 得到全部专业信息
			this.studycourse = this.studyCourseService
					.getStudyCourseById(this.studycourse.getCId());
			if (this.pId != 0) {
				for (int i = 0; i < pList.size(); i++) {
					if (pList.get(i).getPId() == this.pId) {
						this.pIndex = this.studycourse.getCIndex();
						this.pIndex = this.pIndex.substring(this.pIndex
								.lastIndexOf("_") + 1, this.pIndex.length());
						this.studycourse.setCIndex(this.pList.get(i)
								.getPIndex());
						this.pVersion = this.pList.get(i).getPVersion();
					}
				}
			}
			if (this.pId == 0) {
				this.pId = this.studycourse.getPId();
				for (int i = 0; i < pList.size(); i++) {
					if (pList.get(i).getPId() == this.studycourse.getPId()) {
						this.pIndex = this.studycourse.getCIndex();
						this.pIndex = this.pIndex.substring(this.pIndex
								.lastIndexOf("_") + 1, this.pIndex.length());
						this.studycourse.setCIndex(this.pList.get(i)
								.getPIndex());
						this.pVersion = this.pList.get(i).getPVersion();
					}
				}
			}
		} catch (Exception e) {
			logger.error("StudyCourseAction.toEditStudyCourse", e);
			return ERROR;
		}
		return "studycourseEdit";
	}

	/**
	 * 专业修改
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String studyCourseEdit() {
		try {
			String newIndex = "";
			Date date = new Date();
			// 处理索引编号，专业编号+下划线+学科编号＝学科编号
			if (this.pId != 0) {
				this.studycourse.setPId(this.pId);
			}
			if (this.pIndex != "") {
				newIndex = this.studycourse.getCIndex() + "_" + this.pIndex;
			}
			if(this.pVersion!=0){
				this.studycourse.setCVersion(this.pVersion);
			}
			this.studycourse.setCIndex(newIndex);
			this.studycourse.setCUpdatetime(date);
			this.studycourse.setCCreatetime(this.studycourse.getCCreatetime());
			this.studyCourseService.updateStudyCourse(studycourse);
		} catch (Exception e) {
			logger.error("StudyCourseAction.studyCourseEdit", e);
			return ERROR;
		}
		return "studyCourse_Add";
	}
	/**
	 * 根据专业获取课程
	 * 
	 * @return String
	 */
	public String getStudyCourseListByPid() {

		try {
			this.getQueryStudyCourseCondition().setPId(this.pId);
			scList = this.studyCourseService.getStudyCourseByList(this.getQueryStudyCourseCondition());
			JSONArray jslist = JSONArray.fromObject(scList);
			this.setResult(new Result<Object>(true, jslist.toString(), null, null));
			return "json";
		} catch (RuntimeException e) {
			logger.error("StudyCourseAction.getStudyCourseListByPid", e);
			return ERROR;
		}
	}

	public IProfessional getProfessionalService() {
		return professionalService;
	}

	public void setProfessionalService(IProfessional professionalService) {
		this.professionalService = professionalService;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

	public StudyCourse getStudycourse() {
		return studycourse;
	}

	public void setStudycourse(StudyCourse studycourse) {
		this.studycourse = studycourse;
	}

	public QueryStudyCourseCondition getQueryStudyCourseCondition() {
		if (queryStudyCourseCondition == null) {
			queryStudyCourseCondition = new QueryStudyCourseCondition();
		}
		return queryStudyCourseCondition;
	}

	public void setQueryStudyCourseCondition(
			QueryStudyCourseCondition queryStudyCourseCondition) {
		this.queryStudyCourseCondition = queryStudyCourseCondition;
	}

	public List<StudyCourse> getScList() {
		return scList;
	}

	public void setScList(List<StudyCourse> scList) {
		this.scList = scList;
	}

	public IStudyCourse getStudyCourseService() {
		return studyCourseService;
	}

	public void setStudyCourseService(IStudyCourse studyCourseService) {
		this.studyCourseService = studyCourseService;
	}

	public QueryProfessionalCondition getQueryProfessionalCondition() {
		if (this.queryProfessionalCondition == null) {
			this.queryProfessionalCondition = new QueryProfessionalCondition();
		}
		return queryProfessionalCondition;
	}

	public void setQueryProfessionalCondition(
			QueryProfessionalCondition queryProfessionalCondition) {
		this.queryProfessionalCondition = queryProfessionalCondition;
	}

	public String getPIndex() {
		return pIndex;
	}

	public void setPIndex(String index) {
		pIndex = index;
	}

	public int getPVersion() {
		return pVersion;
	}

	public void setPVersion(int version) {
		pVersion = version;
	}

	public List<Professional> getPList() {
		return pList;
	}

	public void setPList(List<Professional> list) {
		pList = list;
	}

	public int getPId() {
		return pId;
	}

	public void setPId(int id) {
		pId = id;
	}

	public int getCId() {
		return cId;
	}

	public void setCId(int id) {
		cId = id;
	}

	public IKnowledge getKnowledgeService() {
		return knowledgeService;
	}

	public void setKnowledgeService(IKnowledge knowledgeService) {
		this.knowledgeService = knowledgeService;
	}

	public QueryKnowledgeCondition getQueryKnowledgeCondition() {
		if(this.queryKnowledgeCondition==null){
			this.queryKnowledgeCondition = new QueryKnowledgeCondition();
		}
		return queryKnowledgeCondition;
	}

	public void setQueryKnowledgeCondition(
			QueryKnowledgeCondition queryKnowledgeCondition) {
		this.queryKnowledgeCondition = queryKnowledgeCondition;
	}
}
