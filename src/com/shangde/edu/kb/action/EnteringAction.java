package com.shangde.edu.kb.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.edu.kb.condition.QueryKnowledgeCondition;
import com.shangde.edu.kb.condition.QueryProfessionalCondition;
import com.shangde.edu.kb.condition.QueryStudyCourseCondition;
import com.shangde.edu.kb.domain.Knowledge;
import com.shangde.edu.kb.domain.Professional;
import com.shangde.edu.kb.domain.StudyCourse;
import com.shangde.edu.kb.service.IKnowledge;
import com.shangde.edu.kb.service.IProfessional;
import com.shangde.edu.kb.service.IStudyCourse;

public class EnteringAction {
	
	private static final Logger logger = Logger.getLogger(EnteringAction.class);
	
	private IKnowledge knowledgeService; // 知识库服务对象

	private QueryKnowledgeCondition queryKnowledgeCondition; // 知识库查询条件

	private List<Knowledge> kList = new ArrayList<Knowledge>(); // 知识库集合

	private IStudyCourse studyCourseService; // 课程服务对象

	private Knowledge knowledge;

	private IProfessional professionalService; // 专业服务对象

	private QueryProfessionalCondition queryProfessionalCondition; // 专业查询条件

	private List<Professional> pList = new ArrayList<Professional>(); // 专业集合

	private List<Knowledge> knowledgeList = new ArrayList<Knowledge>(); // 知识点列表

	private List<StudyCourse> scList = new ArrayList<StudyCourse>(); // 课程列表

	private QueryStudyCourseCondition queryStudyCourseCondition;

	private int pId; // 专业id

	private String index; // 查询索引

	/**
	 * 加载录入管理，专业下拉列表 包含章节数
	 * 
	 * @return
	 */
	public String getProfessionalList() {

		try {
			scList = this.studyCourseService
					.getStudyCourseList(queryStudyCourseCondition); // 得到全部课程信息

			pList = this.professionalService
					.getProfessionalByList(queryProfessionalCondition); // 得到全部专业信息

			this.getQueryKnowledgeCondition().setPlId(this.pId);
			this.kList = this.knowledgeService
					.getKnowledgeListByPidOrIndex(this
							.getQueryKnowledgeCondition()); // 得到全部课程数据

			this.knowledgeSum(); // 科目中包含的章节数 方法
			if (this.index != null) {
				if (this.index.length() != 0) {
					return "professionalList";
				}
			}
		} catch (Exception e) {
			logger.error("EnteringAction.getProfessionalList", e);
			return "error";
		}
		return "professionalList";
	}

	/**
	 * 根据专业，关键字查找课程
	 * 
	 * @return
	 */
	public String getKnowledgeListByPidOrIndex() {

		try {
			this.getQueryKnowledgeCondition().setPlId(this.pId);

			if (this.index != null && this.index.length() != 0
					&& this.index.length() != 1) {
				this.getQueryKnowledgeCondition().setKIndex(this.index); // 添加查询索引
			}

			this.kList = this.knowledgeService
					.getKnowledgeListByPidOrIndex(this.queryKnowledgeCondition); // 根据专业查询课程

			this.scList = this.studyCourseService
					.getStudyCourseList(queryStudyCourseCondition); // 得到全部课程信息
				this.knowledgeSum();

			// 专业，关键索引获取数据
			pList = this.professionalService
					.getProfessionalByList(this.queryProfessionalCondition);
			if (this.index != null) {
				if (this.index.length() != 0) {
					return "professionalList";
				}
			}
		} catch (RuntimeException e) {
			logger.error("EnteringAction.getKnowledgeListByPidOrIndex", e);
			return "error";
		}
		return "professionalList";
	}

	/**
	 * 修改操作准备
	 * 
	 * @return
	 */
	public String toUpdateKnowledge() {

		try {
			if (this.knowledge != null) {
				this.knowledge = knowledgeService
						.getKnowledgeById(this.knowledge.getKId()); // 根据id获取更新对象
				this.pList = this.professionalService
						.getProfessionalByList(this.queryProfessionalCondition); // 获取专业列表

				this.scList = this.studyCourseService
						.getStudyCourseList(queryStudyCourseCondition); // 得到全部课程信息

				if (this.pId == 0) {
					this.pId = this.knowledge.getPlId(); // 判断是否为首次加载，首次加载将列对象专业设为默认值
				}
				for (int i = 0; i < pList.size(); i++) { // 处理对象年份
					if (this.pId == this.pList.get(i).getPId()) {
						this.knowledge.setKVersion(this.pList.get(i)
								.getPVersion());
					}
				}
				for (int i = 0; i < scList.size(); i++) {
					if (this.scList.get(i).getCId() == this.knowledge.getScId()) {
						this.knowledge.setKName(scList.get(i).getCName());
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("EnteringAction.toUpdateKnowledge", e);
			return "error";
		}
		return "toUpdateKnowledge";
	}

	/**
	 * 更新操作
	 * 
	 * @return
	 */
	public String updateKnowledge() {

		try {
			if (this.pId != 0) {
				this.knowledge.setPlId(this.pId);
				if (this.knowledge != null) {
					this.knowledgeService.updateKnowledge(this.knowledge); // 更新操作
				}
			}
			this.pId = 0;
			this.getProfessionalList(); // 更新完成加载数据
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("EnteringAction.updateKnowledge", e);
			return "error";
		}
		return "auditList";
	}

	/**
	 * 根据kid执行发布操作
	 * 
	 * @return
	 */
	public String publishKnowledge() {

		try {
			if (this.knowledge.getKId() != 0) {
				this.knowledgeService.publishKnowledge(this.knowledge); // 发布操作
			}
			this.getProfessionalList();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("EnteringAction.publishKnowledge", e);
			return "error";
		}
		return "auditList";
	}

	/**
	 * 根据kid删除操作
	 * 
	 * @return
	 */
	public String deleteKnowledge() {

		try {
			if (this.knowledge.getKId() != 0) {

				this.knowledgeService.delKnowledgeById(this.knowledge.getKId());
			}
			getProfessionalList();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("EnteringAction.deleteKnowledge", e);
			return "error";
		}
		return "professionalList";
	}

	/**
	 * 处理科目包含的 章数，节数...
	 * @throws ParseException 
	 */
	public void knowledgeSum(){

		for (int i = 0; i < kList.size(); i++) { // 循环得到章节知识点中包含的专业
			int sum = 0;
			if (this.knowledgeList.size() == 0
					&& this.kList.get(i).getScId() != 0) {
				this.knowledgeList.add(kList.get(i));
			}
			for (int a = 0; a < knowledgeList.size(); a++) {
				if (this.knowledgeList.get(a).getScId() != kList.get(i)
						.getScId()) {
					sum++;
				}
			}
			if (sum == knowledgeList.size() && sum != 0) {
				this.knowledgeList.add(kList.get(i));
			}
		}
		for (int i = 0; i < this.knowledgeList.size(); i++) {
			int zSum = 0;
			int jSum = 0;
			int zsSum = 0;
			int zsSum1 = 0;
			int zsSum2 = 0;
			int zsSum3 = 0;

			for (int a = 0; a < this.kList.size(); a++) { // 根据科目id
				// 索引长度判断为科目的章，或节
				if (kList.get(a).getKIndex().trim().length() == 9
						&& this.knowledgeList.get(i).getScId() == this.kList
								.get(a).getScId()) {
					zSum++;

				} else if (this.kList.get(a).getKIndex().trim().length() == 12
						&& this.knowledgeList.get(i).getScId() == this.kList
								.get(a).getScId()) {
					jSum++;

				} else if (this.kList.get(a).getKIndex().trim().length() == 15
						&& this.knowledgeList.get(i).getScId() == this.kList
								.get(a).getScId()) {
					zsSum++;

				} else if (this.kList.get(a).getKIndex().trim().length() == 18
						&& this.knowledgeList.get(i).getScId() == this.kList
								.get(a).getScId()) {
					zsSum1++;

				} else if (this.kList.get(a).getKIndex().trim().length() == 21
						&& this.knowledgeList.get(i).getScId() == this.kList
								.get(a).getScId()) {
					zsSum2++;

				} else if (this.kList.get(a).getKIndex().trim().length() == 24
						&& this.knowledgeList.get(i).getScId() == this.kList
								.get(a).getScId()) {
					zsSum3++;
				}
			}
			this.knowledgeList.get(i).setZSum(zSum); // 添加 科目包含的 章数 节数。。。
			this.knowledgeList.get(i).setJSum(jSum);
			this.knowledgeList.get(i).setZsSum(zsSum);
			this.knowledgeList.get(i).setZsSum1(zsSum1);
			this.knowledgeList.get(i).setZsSum2(zsSum2);
			this.knowledgeList.get(i).setZsSum3(zsSum3);

		}
		for (int i = 0; i < knowledgeList.size(); i++) {
			for (int a = 0; a < scList.size(); a++) {
				if (knowledgeList.get(i).getScId() == scList.get(a).getCId()) {
					this.knowledgeList.get(i)
							.setKName(scList.get(a).getCName());
					this.knowledgeList.get(i).setKCreateTime(scList.get(a).getCCreatetime());
				}
			}
		}
	}

	public IProfessional getProfessionalService() {
		return professionalService;
	}

	public void setProfessionalService(IProfessional professionalService) {
		this.professionalService = professionalService;
	}

	public QueryProfessionalCondition getQueryProfessionalCondition() {
		return queryProfessionalCondition;
	}

	public void setQueryProfessionalCondition(
			QueryProfessionalCondition queryProfessionalCondition) {
		this.queryProfessionalCondition = queryProfessionalCondition;
	}

	public List<Professional> getPList() {
		return pList;
	}

	public void setPList(List<Professional> list) {
		pList = list;
	}

	public IKnowledge getKnowledgeService() {
		return knowledgeService;
	}

	public void setKnowledgeService(IKnowledge knowledgeService) {
		this.knowledgeService = knowledgeService;
	}

	public QueryKnowledgeCondition getQueryKnowledgeCondition() {

		if (queryKnowledgeCondition == null) {
			queryKnowledgeCondition = new QueryKnowledgeCondition();
		}
		return queryKnowledgeCondition;
	}

	public void setQueryKnowledgeCondition(
			QueryKnowledgeCondition queryKnowledgeCondition) {
		this.queryKnowledgeCondition = queryKnowledgeCondition;
	}

	public List<Knowledge> getKList() {
		return kList;
	}

	public void setKList(List<Knowledge> list) {
		kList = list;
	}

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}

	public int getPId() {
		return pId;
	}

	public void setPId(int id) {
		pId = id;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public List<Knowledge> getKnowledgeList() {
		return knowledgeList;
	}

	public void setKnowledgeList(List<Knowledge> knowledgeList) {
		this.knowledgeList = knowledgeList;
	}

	public IStudyCourse getStudyCourseService() {
		return studyCourseService;
	}

	public void setStudyCourseService(IStudyCourse studyCourseService) {
		this.studyCourseService = studyCourseService;
	}

	public List<StudyCourse> getScList() {
		return scList;
	}

	public void setScList(List<StudyCourse> scList) {
		this.scList = scList;
	}

	public QueryStudyCourseCondition getQueryStudyCourseCondition() {
		return queryStudyCourseCondition;
	}

	public void setQueryStudyCourseCondition(
			QueryStudyCourseCondition queryStudyCourseCondition) {
		this.queryStudyCourseCondition = queryStudyCourseCondition;
	}
}
