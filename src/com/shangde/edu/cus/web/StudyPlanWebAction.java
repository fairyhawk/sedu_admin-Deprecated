package com.shangde.edu.cus.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.edu.cus.condition.QueryStudyPlanCondition;
import com.shangde.edu.cus.domain.StudyPlan;
import com.shangde.edu.cus.service.IStudyPlan;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */
@SuppressWarnings("serial")
public class StudyPlanWebAction extends CommonAction{
	
	/**
	 * 学习计划服务对象
	 */
	private IStudyPlan studyPlanService;
	
	/**
	 * 学习计划实体
	 */
	private StudyPlan studyPlan = new StudyPlan();
	
	/**
	 * 学习计划查询条件
	 */
	private QueryStudyPlanCondition queryStudyPlanCondition;
	
	/**
	 * 学习计划list
	 */
	private List<StudyPlan> studyPlanList=new ArrayList<StudyPlan>();
	
	/**
	 * id数组
	 */
	private int[] ids;
	
	/**
	 * 添加学习计划，不支持访问，为flex提供
	 * @return String
	 * @thorows Exception
	 */
	public StudyPlan addStudyPlan(String spInfo, Date spDate){
		studyPlan = new StudyPlan();
		studyPlan.setCusId(getLoginUserId());
		studyPlan.setSpInfo(spInfo);
		studyPlan.setSpDate(spDate);
		int spId = studyPlanService.addStudyPlan(studyPlan);
		return studyPlanService.getStudyPlanById(spId);
	}
	
	/**
	 * 根据时间查询当月的学习计划有哪些天，不支持访问，为flex提供
	 * @return
	 */
	public List<Integer> getMonthStudyPlan(String timeStr) {
		List<Integer> list = null;
		try {
			if(timeStr == null) {
				return null;
			}
			
			//准备查询条件
			getQueryStudyPlanCondition().setCusId(getLoginUserId());
			getQueryStudyPlanCondition().setStartTime(timeStr+"-01");
			getQueryStudyPlanCondition().setEndTime(DateUtil.getNextMonthDate(timeStr+"-01"));
			list = studyPlanService.getMonthStudyPlan(getQueryStudyPlanCondition());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据时间查询学习计划，不支持访问，为flext提供
	 * @return
	 */
	public StudyPlan getStudyPlanByDate(String timeStr) {
		if(timeStr == null) {
			return null;
		}
		getQueryStudyPlanCondition().setCusId(getLoginUserId());
		getQueryStudyPlanCondition().setTimeStr(timeStr);
		studyPlan = studyPlanService.getStudyPlanByDate(getQueryStudyPlanCondition());
		return studyPlan;
	}
	
	/**
	 * 修改学习计划，不支持访问，为flext提供
	 * @return String
	 * @thorows Exception
	 */
	public int updateStudyPlan(StudyPlan studyPlan){
		int spId = 0;
		if(studyPlan != null){
			if(studyPlan.getId() > 0){
				studyPlanService.updateStudyPlan(studyPlan);
				spId = studyPlan.getId();
			}else{
				studyPlan.setCusId(getLoginUserId());
				spId = studyPlanService.addStudyPlan(studyPlan);
			}
		}
		return spId;
	}
	
	/**
	 * 根据id删除计划，不支持访问，为flext提供
	 * @thorows Exception
	 */
	public void deleteStudyPlan(int id){
		studyPlanService.delStudyPlanById(id);
	}
	
	public String initStudyPlan() {
		return "initStudyPlan";
	}

	/**
	 * 根据ids删除学员
	 * @return String
	 * @thorows Exception
	 */
	public String deleteStudyPlan(){
		try {
			if(ids != null) {
				for(int i=0; i<ids.length; i++) {
					studyPlanService.delStudyPlanById(ids[i]);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "relist";
	}

	/**
	 * 分页查询
	 * @return String
	 * @thorows Exception
	 */
	public String showStudyPlanList() {
		return "list";
	}
	
	public void sendMessage(String message) throws IOException {
		getServletResponse().setCharacterEncoding("utf-8");
		getServletResponse().getWriter().write(message);
	}

	public StudyPlan getStudyPlan() {
		return studyPlan;
	}

	public void setStudyPlan(StudyPlan studyPlan) {
		this.studyPlan = studyPlan;
	}

	public QueryStudyPlanCondition getQueryStudyPlanCondition() {
		if(queryStudyPlanCondition == null) {
			queryStudyPlanCondition = new QueryStudyPlanCondition();
		}
		return queryStudyPlanCondition;
	}

	public void setQueryStudyPlanCondition(
			QueryStudyPlanCondition queryStudyPlanCondition) {
		this.queryStudyPlanCondition = queryStudyPlanCondition;
	}

	public List<StudyPlan> getStudyPlanList() {
		return studyPlanList;
	}

	public void setStudyPlanList(List<StudyPlan> studyPlanList) {
		this.studyPlanList = studyPlanList;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public void setStudyPlanService(IStudyPlan studyPlanService) {
		this.studyPlanService = studyPlanService;
	}
}
