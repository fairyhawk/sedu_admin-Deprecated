package com.shangde.edu.stu.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.Result;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.service.IKpoint;

import com.shangde.edu.stu.condition.QueryCourseSummaryCondition;
import com.shangde.edu.stu.condition.QueryExamSummaryCondition;
import com.shangde.edu.stu.condition.QueryPlanCondition;
import com.shangde.edu.stu.condition.QueryPlanclockCondition;
import com.shangde.edu.stu.domain.CourseSummary;
import com.shangde.edu.stu.domain.ExamSummary;

import com.shangde.edu.stu.domain.Plan;
import com.shangde.edu.stu.domain.Planclock;
import com.shangde.edu.stu.domain.Planitem;
import com.shangde.edu.stu.service.ICourseSummary;
import com.shangde.edu.stu.service.IExamSummary;
import com.shangde.edu.stu.service.IPlan;
import com.shangde.edu.stu.service.IPlanclock;
import com.shangde.edu.stu.service.IPlanitem;
import com.shangde.edu.stu.service.IVideoStatistics;

import com.shangde.edu.stu.condition.QueryExapaReCondition;
import com.shangde.edu.stu.domain.ExapaRe;
import com.shangde.edu.stu.domain.Exapa;


/**  
 *  学习计划 Action
 * @author fanxin
 * @time   2011.05.18
 * @version 1.0
 */
public class PlanWebAction extends CommonAction{
	
	/**
	 *   学习计划
	 */
	private Plan plan;
	
	/**
	 *   学习计划内容
	 */
	private Planitem planitem;
	
	/**
	 *   学习计划内容
	 */
	private Planclock planclock;
	
	
	/**
	 *   学习计划内容的详情数组
	 */
	private String[] strArr;
	
	/**
	 *	今日总结  课程 
	 */
	private CourseSummary courseSummary;
	
	/**
	 *	今日总结  考试 
	 */
	private ExamSummary examSummary;
	
	/**
	 *	试卷
	 */
	private Exapa exapa;
	
	
	private Kpoint kpoint;
	
	
	private IKpoint kpointService;
	
	/**
	 *	今日总结  课程服务 
	 */
	private ICourseSummary courseSummaryService;
	
	
	/**
	 *	今日总结  课程服务 
	 */
	private List<Planclock> planclockListByDay;
	 
	
	private IVideoStatistics   videoStatisticsService;
	
	/**
	 * 计划服务
	 */
	private IPlan planService;
	
	/**
	 *   学习计划内容服务
	 */
	private IPlanitem planitemService;
	
	/**
	 *   学习提醒内容服务
	 */
	private IPlanclock planclockService;
	
	/**
	 *   考试记入服务
	 */
	private IExamSummary examSummaryService;
	
	/**
	 *   今日课程总结条件
	 */
	private QueryCourseSummaryCondition queryCourseSummaryCondition;
	
	/**
	 *   考试记入条件
	 */
	private QueryExapaReCondition queryExapaReCondition;
	
	/**
	 *   今日考试总结条件
	 */
	private QueryExamSummaryCondition queryExamSummaryCondition;
	
	/**
	 *   计划总结条件
	 */
	private QueryPlanCondition queryPlanCondition;
	
	/**
	 *   学习提醒条件
	 */
	private QueryPlanclockCondition queryPlanclockCondition;
	
	
	/**
	 *   知识点 条件
	 */
	private QueryKpointCondition queryKpointCondition;
	
	
	
	public QueryKpointCondition getQueryKpointCondition() {
		if (queryKpointCondition ==null){
			queryKpointCondition = new QueryKpointCondition();
		}
		return queryKpointCondition;
	}

	public void setQueryKpointCondition(QueryKpointCondition queryKpointCondition) {
		this.queryKpointCondition = queryKpointCondition;
	}

	public QueryPlanclockCondition getQueryPlanclockCondition() {
		if (queryPlanclockCondition ==null){
			queryPlanclockCondition = new QueryPlanclockCondition();
		}
		return queryPlanclockCondition;
	}

	public void setQueryPlanclockCondition(
			QueryPlanclockCondition queryPlanclockCondition) {
		this.queryPlanclockCondition = queryPlanclockCondition;
	}

	public QueryPlanCondition getQueryPlanCondition() {
		if (queryPlanCondition ==null){
			queryPlanCondition = new QueryPlanCondition();
		}
		return queryPlanCondition;
	}

	public void setQueryPlanCondition(QueryPlanCondition queryPlanCondition) {
		this.queryPlanCondition = queryPlanCondition;
	}

	public QueryExamSummaryCondition getQueryExamSummaryCondition() {
		if (queryExamSummaryCondition ==null){
			queryExamSummaryCondition = new QueryExamSummaryCondition();
		}
		return queryExamSummaryCondition;
	}

	public void setQueryExamSummaryCondition(
			QueryExamSummaryCondition queryExamSummaryCondition) {
		this.queryExamSummaryCondition = queryExamSummaryCondition;
	}

	public QueryExapaReCondition getQueryExapaReCondition() {
		if (queryExapaReCondition ==null){
			queryExapaReCondition = new QueryExapaReCondition();
		}
		return queryExapaReCondition;
	}
	
	public void setQqueryExapaReCondition(
			QueryExapaReCondition queryExapaReCondition) {
		this.queryExapaReCondition = queryExapaReCondition;
	}

	public QueryCourseSummaryCondition getQueryCourseSummaryCondition() {
		if (queryCourseSummaryCondition ==null){
			queryCourseSummaryCondition = new QueryCourseSummaryCondition();
		}
		return queryCourseSummaryCondition;
	}

	public void setQueryCourseSummaryCondition(
			QueryCourseSummaryCondition queryCourseSummaryCondition) {
		this.queryCourseSummaryCondition = queryCourseSummaryCondition;
	}

	public IPlanitem getPlanitemService() {
		return planitemService;
	}

	public void setPlanitemService(IPlanitem planitemService) {
		this.planitemService = planitemService;
	}
	
	public IExamSummary getExamSummaryService() {
		return examSummaryService;
	}

	public void setExamSummaryService(IExamSummary examSummaryService) {
		this.examSummaryService = examSummaryService;
	}

	
	
	public IVideoStatistics getVideoStatisticsService() {
		return videoStatisticsService;
	}

	public void setVideoStatisticsService(IVideoStatistics videoStatisticsService) {
		this.videoStatisticsService = videoStatisticsService;
	}

	public IPlanclock getPlanclockService() {
		return planclockService;
	}

	public void setPlanclockService(IPlanclock planclockService) {
		this.planclockService = planclockService;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Planitem getPlanitem() {
		return planitem;
	}

	public void setPlanitem(Planitem planitem) {
		this.planitem = planitem;
	}
	
	
    public String[] getStrArr() {
		return strArr;
	}

	public void setStrArr(String[] strArr) {
		this.strArr = strArr;
	}

	/**
	 * @return the exapa
	 */
	public Exapa getExapa() {
		return exapa;
	}

	/**
	 * @param exapa the exapa to set
	 */
	public void setExapa(Exapa exapa) {
		this.exapa = exapa;
	}

	public CourseSummary getCourseSummary() {
		return courseSummary;
	}

	public void setCourseSummary(CourseSummary courseSummary) {
		this.courseSummary = courseSummary;
	}

	public IPlan getPlanService() {
		return planService;
	}

	public void setPlanService(IPlan planService) {
		this.planService = planService;
	}

	
	public ICourseSummary getCourseSummaryService() {
		return courseSummaryService;
	}

	public void setCourseSummaryService(ICourseSummary courseSummaryService) {
		this.courseSummaryService = courseSummaryService;
	}
	
	public ExamSummary getExamSummary() {
		return examSummary;
	}

	public void setExamSummary(ExamSummary examSummary) {
		this.examSummary = examSummary;
	}
	

	public String getCheckDay() {
		return checkDay;
	}

	public void setCheckDay(String checkDay) {
		this.checkDay = checkDay;
	}

	public Planclock getPlanclock() {
		return planclock;
	}

	public void setPlanclock(Planclock planclock) {
		this.planclock = planclock;
	}

	public List<Planclock> getPlanclockListByDay() {
		return planclockListByDay;
	}

	public void setPlanclockListByDay(List<Planclock> planclockListByDay) {
		this.planclockListByDay = planclockListByDay;
	}
	
	public Kpoint getKpoint() {
		return kpoint;
	}

	public void setKpoint(Kpoint kpoint) {
		this.kpoint = kpoint;
	}

	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	/**
     * 跳转到 学习计划 页面
     * @param  
     * @return  String
     * @author fanxin
     * @time   2011.05.18
     * @version 1.0
     */
	public String toAddPlan() {
	
		return "toAddPlan";
	}
	
	
	/**
     * 添加学习计划，通过plan_add.jsp页面提交信息添加
     * @param  plan
     * @return  String
     * @author fanxin
     * @time   2011.05.18
     * @version 1.0
     */
    public String addPlan() {
    	try{
    		//添加学习计划  和 计划内容
    		
    		String time = DateUtil.formatDate(new java.util.Date(),DateUtil.TIME_FMT);
    		Date currTime = DateUtil.toDate(time,DateUtil.TIME_FMT);
    		
    		Date qTcheckDay = plan.getOpendate();
    		// String checkDay = DateUtil.formatDate(qTcheckDay,DateUtil.DATE_FMT);
    	    checkDay = new SimpleDateFormat("yyyy-MM-dd").format(qTcheckDay) ;
    		
    		plan.setCusId(getLoginUserId());
    		plan.setPlantitle(checkDay+"学习计划");
    		plan.setPublish(currTime);
    		plan.setCheckDay(checkDay);
    		
    		planService.addPlan(plan);
    		if( plan.getPlanId()!=0 ){
    			planitem.setPlanId(plan.getPlanId());
    			planitem.setCusId(plan.getCusId());
    			planitem.setItemdate(currTime);
    			planitemService.addPlanitem(planitem);
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		
    		return "planAddFalse";
    	}
    	
    	return "planAddSuccess";
    }
	
	/**
     * 添加学习内容计划，通过plan_add.jsp页面提交信息添加
     * @param  
     * @return  int
     * @author fanxin
     * @time   2011.05.18
     * @version 1.0
     */
    public int addPlanitem(Planitem planitem)
    {   	       
    	return planitemService.addPlanitem(planitem);
    }
	
    
	/**
     * 根据planId取得 学习计划
     * @author fanxin
     * @time   2011.05.19
     * @version 1.0
     * @return
     */
    public String getPlanById()
    {   	       
    	try{
    		plan = planService.getPlanById(plan.getPlanId());
    		if(plan!=null){
    			planitem = planitemService.getPlanitemByPlanId(plan.getPlanId());
    			
    			String str = planitem.getItemtitle();
    			strArr = str.split(",");
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		//return "ERROR";
    	}	
    	 return "getPlan";
    }
	/**
     * 根据Id取得 学习计划
     * @author fanxin
     * @time   2011.05.20
     * @version 1.0
     * @return
     */
    public String getPlanitemById()
    {   	       
    	try{
    		planitem = planitemService.getPlanitemById(planitem.getPitemId());
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		//return "ERROR";
    	}	
    	 return "getPlanitem";
    }
    
    
	/**
     * 根据planId取得 学习计划
     * @author fanxin
     * @time   2011.05.20
     * @version 1.0
     * @return
     */
    public String getPlanitemByPlanId()
    {   	       
    	try{
    		planitem = planitemService.getPlanitemByPlanId(plan.getPlanId());
    		String str = planitem.getItemtitle();
			strArr = str.split(",");	
			if(planitem!=null){
				plan = planService.getPlanById(planitem.getPlanId());
			}
    	}catch(Exception e){
    		e.printStackTrace();
    		//return "ERROR";
    	}	
    	 return "getPlanitemByPlanId";
    }
    
    
	/**
	 * 根据用户修改信息޸更新学习计划内容
	 * @author fanxin
     * @time   2011.05.20
     * @version 1.0
	 * @return String
	 */
	public String updatePlanitem() {
		try{
			Planitem upPlanitem = planitemService.getPlanitemByPlanId(planitem.getPlanId());
			
			upPlanitem.setItemtitle(planitem.getItemtitle());
			
			planitemService.updatePlanitem(upPlanitem);
			
			if(upPlanitem!=null){
				Plan upPlan = planService.getPlanById(upPlanitem.getPlanId());
				//设置跳转日期 checkDay
	    		Date qTcheckDay = upPlan.getOpendate();
	    	    checkDay = new SimpleDateFormat("yyyy-MM-dd").format(qTcheckDay) ;
				plan.setCheckDay(checkDay);
				upPlan.setPicon(plan.getPicon());
				planService.updatePlan(upPlan);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "updatePlanitemSuc";
	}
	
	
	/**
	 * 根据PlanitemId 删除 学习计划内容和 学习计划
	 * @author fanxin
     * @time   2011.05.20
     * @version 1.0
	 * @return String
	 */
	public String deletePlanitemById() {
		try{
			Planitem plitem = planitemService.getPlanitemById(planitem.getPitemId());
			if(plitem != null){
				// 外键关联  先删从表 再删主表
				planitemService.delPlanitemById(plitem.getPitemId());
				
				//取得Plan 设置跳转日期 checkDay
				plan = planService.getPlanById(plitem.getPlanId());
				Date qTcheckDay = plan.getOpendate();
	    	    checkDay = new SimpleDateFormat("yyyy-MM-dd").format(qTcheckDay) ;
				plan.setCheckDay(checkDay);
				
				planService.delPlanById(plitem.getPlanId());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "delPlanScu";
	}
	
	
	/**
	 * 根据Summarydate 查找今天是否看过课程
	 * @author fanxin
     * @time   2011.05.23
     * @version 1.0
	 * @return String
	 */
	public String getWatchCourseListByDate(){
		List<Kpoint> kpointList = null;
		try{ 
			
    		String chackToday = DateUtil.formatDate(courseSummary.getSummarydate());
			String today =chackToday.replace("-", "");//20110508 
			//今天看过的课程的节点  list 中存的是 知识点的Id
			List<String> list = videoStatisticsService.getUserLearnKpointByDate(today, getLoginUserId());
			
			if(list!= null && list.size()>0){
				kpointList = courseSummaryService.getKpointListBypointIdList(list);
				System.out.println("今天看过的课程的节点  kpointList.size="+kpointList.size());
				//根据知识点的Id 获取知识点数据，并插入课程总结 courseSummary表
				if(kpointList!= null && kpointList.size()>0){
					for(int i=0;i<kpointList.size();i++){
						Kpoint kpoint = kpointList.get(i);
						
						CourseSummary courseSummary = new CourseSummary();
			    		//String currdate = DateUtil.formatDate(new java.util.Date());
			    		Date summarydate = DateUtil.toSqlDate(chackToday, "yyyy-MM-dd");
			    		
						courseSummary.setSummarydate(summarydate);
						courseSummary.setCusId(getLoginUserId());
						courseSummary.setPointId(kpoint.getPointId());
						courseSummary.setPointName(kpoint.getName());
						
						courseSummaryService.addCourseSummary(courseSummary);
					}
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			setResult( new Result<String>(false,"failure",null,null));
		}
		
		if(kpointList != null && kpointList.size()>0){
			setResult( new Result<String>(false,"success",null,null));
		}else{
			setResult( new Result<String>(false,"failure",null,null));
		}
		return "json" ;
	}
		
	/**
	 * 
	 * @author fanxin
     * @time   2011.05.23
     * @version 1.0
	 */
	public void InsertNotWatchCourse(){
		try{
			String chackToday = DateUtil. formatDate(courseSummary.getSummarydate());
			
			CourseSummary courseSummary = new CourseSummary();
			Date summarydate = DateUtil.toSqlDate(chackToday, "yyyy-MM-dd");
			
			courseSummary.setSummarydate(summarydate);
			courseSummary.setCusId(getLoginUserId());
			courseSummary.setPointId(-1);
			courseSummary.setPointName("没有学习");
			
			courseSummaryService.addCourseSummary(courseSummary);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @author fanxin
     * @time   2011.05.23
     * @version 1.0
	 */
	public void InsertNotWatchExam(){
		try{
			String chackToday = DateUtil. formatDate(examSummary.getSummarydate());
			
			ExamSummary examSummary = new ExamSummary();
			Date summarydate = DateUtil.toSqlDate(chackToday, "yyyy-MM-dd");
			
			examSummary.setSummarydate(summarydate);
			examSummary.setCusId(getLoginUserId());
			examSummary.setExamId(-1);
			examSummary.setExamname("没有做");
			
			examSummaryService.addExamSummary(examSummary);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 查找课程的今日总结的最后一条
	 * @author fanxin
     * @time   2011.05.23
     * @version 1.0
	 * @return String
	 */
	public String getCourseSummaryLimt() {
		CourseSummary cs = null;
		try{
			getQueryCourseSummaryCondition().setCusId(getLoginUserId());
		    cs = courseSummaryService.getCourseSummaryLimt(getQueryCourseSummaryCondition());
		    
		}catch(Exception e){
			e.printStackTrace();
			setResult( new Result<String>(false,"failure",null,null));
		}
		if(cs != null){
			setResult( new Result<String>(false,"success",null,null));
		}else{
			setResult( new Result<String>(false,"failure",null,null));
		}
		return "json" ;
	}
	
	
	/**
	 * 查找课程的今日总结的计入的 具体内容
	 * @author fanxin
     * @time   2011.05.23
     * @version 1.0
	 * @return String
	 */
	public String getCourseSummaryLimtContent() {
		List<CourseSummary> courseSummaryList = null;
		try{
			QueryCourseSummaryCondition qCourseSummaryCondition = getQueryCourseSummaryCondition();
    		String currdate = DateUtil. formatDate(courseSummary.getSummarydate());
    		Date summarydate = DateUtil.toSqlDate(currdate, "yyyy-MM-dd");
    		
    		qCourseSummaryCondition.setSummarydate(summarydate);
    		qCourseSummaryCondition.setCusId(getLoginUserId());

    		courseSummaryList = courseSummaryService.getCourseSummaryLimtContent(queryCourseSummaryCondition);
		    
    		if(courseSummaryList!=null && courseSummaryList.size()>0){
    			List<String> pointNameList = new ArrayList<String>();
    			for(int i=0;i<courseSummaryList.size();i++){
    				CourseSummary cs = courseSummaryList.get(i);
    				String potName = cs.getPointName();
    				pointNameList.add(potName);
    			}
    			
    			if(pointNameList != null){
    				setResult( new Result<List<String>>(false,"success",null,pointNameList));
    			}else{
    				setResult( new Result<List<String>>(false,"failure",null,null));
    			}
    			
    		}else{
				setResult( new Result<List<String>>(false,"failure",null,null));
			}
    		
		}catch(Exception e){
			e.printStackTrace();
			setResult( new Result<List<String>>(false,"failure",null,null));
		}
		
		return "json" ;
	}
	
	
    /**
     * 通过条件获取试卷记录集合
     * @param queryExampaperRecordCondition 试卷记录查询条件
     * @return 试卷查询集合
     * @author fanxin
     * @time   2011.05.24
     * @version 1.0
     */
    public String getExampaperRecordList(){
    	
    	List<ExapaRe> exampaperRecordList = null;
    	try{
    		/*//String currdate = this.checkDay;
    		String currdate = DateUtil. formatDate(examSummary.getSummarydate());
    		String befordate = DateUtil.addDays2(currdate, -2);
    		Date addtime = DateUtil.toSqlDate(befordate, "yyyy-MM-dd");
    		
    		Date bftime = DateUtil.toSqlDate(DateUtil.addDays2(currdate, 1), "yyyy-MM-dd");
    		
    		getQueryExampaperRecordCondition().setCusId(getLoginUserId());
    		getQueryExampaperRecordCondition().setAddtime(addtime);
    		getQueryExampaperRecordCondition().setBftime(bftime);*/
    		
    		// 当天
    		String currdate = DateUtil. formatDate(examSummary.getSummarydate());
    		Date addtime = DateUtil.toSqlDate(currdate, "yyyy-MM-dd");
    		
    		getQueryExapaReCondition().setAddtime(addtime);
    		getQueryExapaReCondition().setCusId(getLoginUserId());
    		
    		exampaperRecordList = examSummaryService.getExampaperRecordList(queryExapaReCondition);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	if(exampaperRecordList!=null && exampaperRecordList.size()>0){
    		//有计入 -- 将计入插入 ----> 今日考试总结表
    		for(int i =0;i<exampaperRecordList.size();i++){
    			ExapaRe er = exampaperRecordList.get(i);
    			
    			ExamSummary  exSummary = new ExamSummary();
    			exSummary.setCusId(er.getCusId());
    			exSummary.setExamId(er.getEpId());
    			
    			//根据试卷Id 找到试卷，为考试总结设置 试卷名称
    			Exapa examper = examSummaryService.getExampaperById(er.getEpId());
    			if(examper!=null){
    				exSummary.setExamname(examper.getEpName());
    			}
    			
    			exSummary.setSummarydate(er.getAddtime());
    			//插入 ----> 今日考试总结表
    			examSummaryService.addExamSummary(exSummary);
    			
    		}
    		setResult( new Result<String>(false,"success",null,null));
    	}else{
    		//没有计入
    		System.out.println("##################没有计入################");
    		
    		setResult( new Result<String>(false,"failure",null,null));
    	}
    	
    	return "json" ;
    }

	
    /**
     * 通过条件获取试卷记录集合
     * @param queryExampaperRecordCondition 试卷记录查询条件
     * @return 试卷查询集合
     * @author fanxin
     * @time   2011.05.24
     * @version 1.0
     */
    public String getExamSummaryLimtContent(){ 
    	List<ExamSummary> examSummaryList = null;
    	try{
    		QueryExamSummaryCondition qExamsummaryCondition = getQueryExamSummaryCondition();
        	
    		// 当天
    		String currdate = DateUtil. formatDate(examSummary.getSummarydate());
    		Date summarydate = DateUtil.toSqlDate(currdate, "yyyy-MM-dd");
    		
        	/*	最近三天
        	 String currdate = DateUtil. formatDate(examSummary.getSummarydate());
    		 String befordate = DateUtil.addDays2(currdate, -2);
    		 Date summarydate = DateUtil.toSqlDate(befordate, "yyyy-MM-dd");
    		 Date bfdate = DateUtil.toSqlDate(DateUtil.addDays2(currdate, 1), "yyyy-MM-dd");
    		 qExamsummaryCondition.setBfdate(bfdate);
    		*/
    		
        	qExamsummaryCondition.setSummarydate(summarydate);
        	qExamsummaryCondition.setCusId(getLoginUserId());
        	
        	examSummaryList =  examSummaryService.getExamSummaryList(queryExamSummaryCondition);
        	
        	List<String>  epNameList = new ArrayList<String>();
        	if(examSummaryList != null && examSummaryList.size()>0){
        		//取得 Exapa的epName 集合
        		for(int i=0;i<examSummaryList.size();i++){
        			ExamSummary es = examSummaryList.get(i);
        			int epId = es.getExamId();
        			Exapa exapa = null;
        			
        			exapa = examSummaryService.getExampaperById(epId);
        			System.out.println(exapa.getEpName());
        			if(exapa != null){
        				String epName = exapa.getEpName();
        				epNameList.add(epName);
        			}
        		}
        		
        		setResult( new Result<List<String>>(false,"success",null,epNameList));
        	}else{
    			setResult( new Result<List<String>>(false,"failure",null,null));
    		}
        	
    	}catch(Exception e){
    		e.printStackTrace();
    		setResult( new Result<List<String>>(false,"failure",null,null));
    	
    	}
    	
    	return "json" ;
    }
	
    /**
     * 通过id获取试卷记录
     * @param queryExampaperRecordCondition 试卷记录查询条件
     * @return 试卷查询集合
     * @author fanxin
     * @time   2011.05.25
     * @version 1.0
     */
    public String getExampaperById(){
    	Exapa examper = null;
    	try{
    		examper = examSummaryService.getExampaperById(exapa.getEpId());
    		if(examper!=null){
        		setResult( new Result<String>(false,examper.getEpName(),null,null));
        	}else{
        		setResult( new Result<String>(false,"",null,null));
        	}
        	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return "json" ;
   
    }
    
    
    /**
	 * 根据 条件 如：开启时间（添加时间）查找 Plan
	 * @author fanxin
     * @time   2011.05.26
     * @version 1.0
     */
    public String getPlanByDate(){
    	try{
        	QueryPlanCondition queryPlanCondition = getQueryPlanCondition();
        	
        	queryPlanCondition.setCusId(getLoginUserId());
        	//queryPlanCondition.setCourseId(courseId)   课程id
        	// currentDay :前台 日历选择的时间
        	queryPlanCondition.setCalendar(checkDay);
        	 plan = planService.getPlanByDate(queryPlanCondition);
        	 
        	 System.out.println(plan.getPlantitle());
        	 
        	if(plan!=null){
    			planitem = planitemService.getPlanitemByPlanId(plan.getPlanId());
    			
    			String str = planitem.getItemtitle();
    			strArr = str.split(",");
    		}
        	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return "getPlanByDate";
    }
    
    
    /**
	 * 根据 条件 如：开启时间（添加时间）查找 Planclock 集合
	 * @author fanxin
     * @time   2011.05.27
     * @version 1.0
     * @throws ParseException 
     */
	public String getPlanclockListByDate() throws ParseException{
		try{
			String currdate = DateUtil.formatDate(new java.util.Date());
			Date alertdate = DateUtil.toSqlDate(currdate, "yyyy-MM-dd");
			
			QueryPlanclockCondition queryPlanclockCondition = getQueryPlanclockCondition();
			queryPlanclockCondition.setAlertdate(alertdate);
			planclockListByDay = planclockService.getPlanclockListByDate(queryPlanclockCondition);
			
			//System.out.println("---size----"+planclockListByDay.size());
			
			/*if(planclockListByDay!=null && planclockListByDay.size()>0){
				for(int i =0;i<planclockListByDay.size();i++){
					Planclock planclock = planclockListByDay.get(i);
					System.out.println("===="+planclock.getAlertdate());
				}
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "getPlanclockListByDate";
	}

	/**
	 * 按照次日期检索 学习计划、提醒、总结 最终的格式为 2011-05-25,在ibatiS中，使用模糊查询，直接匹配
	 * 
	 * 用以接收从日历中传递过来的参数
	 */
	private String checkDay;
	
	
	/**
	 * plan_list页面用到的monthPlanList
	 */
	
	
	private List<Plan>  monthPlanList;
	
	/**
	 * 批处理所有选择ID
	 */
	private String batchParamsId;
	/**
	 * 当前月 
	*/
	private String currMonth;
	
    
 	/**
	 * @return the batchParamsId
	 */
	public String getBatchParamsId() {
		return batchParamsId;
	}

	/**
	 * @param batchParamsId the batchParamsId to set
	 */
	public void setBatchParamsId(String batchParamsId) {
		this.batchParamsId = batchParamsId;
	}



	/**
	 * @return the monthPlanList
	 */
	public List<Plan> getMonthPlanList() {
		return monthPlanList;
	}

	/**
	 * @param monthPlanList the monthPlanList to set
	 */
	public void setMonthPlanList(List<Plan> monthPlanList) {
		this.monthPlanList = monthPlanList;
	}

	/**
	 * @return the currMonth
	 */
	public String getCurrMonth() {
		return currMonth;
	}

	/**
	 * @param currMonth the currMonth to set
	 */
	public void setCurrMonth(String currMonth) {
		this.currMonth = currMonth;
	}

	public String getPlanList() throws ParseException{
 		try{
 			
 			QueryPlanCondition queryPlanCondition = getQueryPlanCondition();
 			queryPlanCondition.setCusId(getLoginUserId());
 			queryPlanCondition.setPageSize(15);
 			setPage(planService.getPlanPaperByCondition(getQueryPlanCondition()));
			// 需要再设置一次分页
			getPage().setPageSize(15);
			setPageUrlParms();
			
			/**
			 * 取得当月下的 学习计划
			currMonth = DateUtil.formatDate(new java.util.Date()).substring(0, 7);
			QueryPlanCondition queryPlanCondition2 = getQueryPlanCondition();
			queryPlanCondition2.setCalendar(currMonth);
			queryPlanCondition2.setCusId(getLoginUserId());
			monthPlanList = planService.getPlanListByCalendar(queryPlanCondition2);
			
			**/
			
			return "getPlanList";
			
 		}catch(Exception e){
 			logger.error("getPlanList()方法出错了！",e);
			return "error";
 		}
 		
 		
 	}
	
	/** log对象 */
	private Log logger = LogFactory.getLog(getClass());

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}

	// 批处理
	public String batchProcessPlan() {
		try {
			// 对字符串进行拆分，得到所有ID的集合
			String[] batchIds = batchParamsId.split(",");
			// 遍历该集合，通过ID得到 学习计划内容 和 学习计划
			for(int i = 0; i < batchIds.length; i++) {
				
				//取得页面跳转时，需要plan来设置参数（以第一个plan为准）
				if(i==0){
					plan = planService.getPlanById(Integer.parseInt(batchIds[0]));
				}
				Planitem planitemtemp = planitemService.getPlanitemByPlanId(Integer.parseInt(batchIds[i]));
				int planitemId = planitemtemp.getPitemId();
				//删除 学习计划内容 和 学习计划
				planitemService.delPlanitemById(planitemId);
				planService.delPlanById(Integer.parseInt(batchIds[i]));
			}

			return "bathProcessPlan";
			
		} catch (Exception e) {
			logger.error("batchProcessPlan()方法出错了！",e);
			return "error";
		}
	}

	
}