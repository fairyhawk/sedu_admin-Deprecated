package com.shangde.edu.ass.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.PreventInfusion;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.ass.condition.QueryAssessCondition;
import com.shangde.edu.ass.condition.QueryReassessCondition;
import com.shangde.edu.ass.domain.AssKpointStarInfo;
import com.shangde.edu.ass.domain.Assess;
import com.shangde.edu.ass.domain.Reassess;
import com.shangde.edu.ass.dto.AssessDto;
import com.shangde.edu.ass.service.IAssess;
import com.shangde.edu.ass.service.IReassess;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cou.service.ITeacher;
import com.shangde.edu.cus.domain.TotolsScore;
import com.shangde.edu.cus.service.ITotolsScore;
import com.shangde.edu.dis.service.IDisWord;
import com.shangde.edu.dis.web.interceptor.KeyWordFilter;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.res.domain.Vedio;
import com.shangde.edu.res.service.IVedio;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;

public class AssWebAction extends CommonAction {


	private static final long serialVersionUID = 1L;

	private IAssess assessService;	//评分服务

	private IReassess reassessService;
	
	private ICashRecord cashRecordService;
	
	private ITeacher teacherService;
	
	private List<Assess> top10list;//好评top10
	
	private Map<String, Integer> kpointCount;
	
	private List<Kpoint> unAssKpointList;//未评价课程
	
	private List<Assess> assList;//已评价课程
	
	private List<String> watchKids;//当前用户选定的专业下，购买并观看过的知识点IDS
	
	private List<Integer> assKids;//已评价知识点IDS
	
	private List<Subject> subList;//右侧显示的已购买专业列表
	
	private List<Subject> unBuySubList;//右侧显示未购买专业列表
	
	private List<CashRecord> cashList;
	
	private Subject subject;//保存默认专业
	
	private Subject currSub;//保存当前专业
	
	private QueryAssessCondition queryAssessCondition=new QueryAssessCondition();  //评论查询条件
	
	private QueryReassessCondition queryReassessCondition;
	
	private QueryCashRecordCondition queryCashRecordCondition; 
	
	private QueryKpointCondition queryKpointCondition;
	
	private IVedio vedioService;
	
	
	private AssKpointStarInfo starInfo;//处理页面各星级知识点统计
	
	private boolean isShow;//用于判断是否显示未评价、已评价课程
	
	private int status=0;//用户状态。0：未购买；1：购买未观看；2：观看未评价；3：已评价

	private IKpoint kpointService;	//知识点服务

	private ISubject subjectService;	//专业服务

	private List<AssessDto> assPageList;	//分页集合

	private AssessDto assessDto;	//评论Dto

	private Kpoint kpoint;		//知识点对象

	private float sum = 0f;		//评分，平均分
	
	private String sumString; //评分 平均分

	private int assessSum = 0;		//知识点评分总人数

	private TotolsScore totolsScore;	//用户经验值 积分对象

	private ITotolsScore totolsScoreService;	//经验值 积分服务

	private String kpId;	//知识点ID

	private int sellId; //售卖方式ID
	
	private int couId;
	
	private static int loginId;
	private int leavel1=0; //打分级别人数
	private int leavel2=0;
	private int leavel3=0;
	private int leavel4=0;
	private int leavel5=0;
	
	private int perLvl1=0; //打分级别百分比
	private int perLvl2=0;
	private int perLvl3=0;
	private int perLvl4=0;
	private int perLvl5=0;
	private int perSum=0; //总分百分比
	int subId;	//专业ID
	private String voSize=""; //视频长度
	private Teacher teacher; //老师
	
	private Assess assess=new Assess();	//评分对象
	
	private IDisWord disWordService;
	
	private Reassess reassess; //回复
	private List<Reassess> reAssList;
	
	private String imgName;
	
	private String assContext;

	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	/**
	 * 跳转评论页面数据加载
	 * 
	 * @return
	 */
	public String toAddAssess() {
		try {
			if (this.assess != null&&this.assess.getKpointId()!=0) { // 点击链接进入此方法，判断评论对象是否为空
				if (this.assess.getSubjectId() != 0) { 				// 判断当前评论节点的 专业id 是否为空
					this.subject = this.subjectService.getSubjectById(this.assess.getSubjectId()); // 得到当前知识点的专业
					switch (this.subject.getSubjectId()) {
						case 1:
							imgName="_rl";
							break;
						case 2:
							imgName="_xl";
							break;
						case 3:
							imgName="_kjz";
							break;
						case 5:
							imgName="_sf";
							break;
						case 6:
							imgName="_mini";
							break;
						case 7:
							imgName="_cpa";
							break;
						case 8:
							imgName="_zq";
							break;
						case 9:
							imgName="_jz";
							break;
						case 10:
							imgName="_gk";
							break;
						case 11:
							imgName="_gwy";
							break;
					default:
						break;
					}
				}
				if (this.assess.getKpointId() != 0) {		 // 判断当前点击知识点是否为0
					this.kpoint = this.kpointService.getKpointById(this.assess.getKpointId()); 				// 得到当前知识点
					
					Vedio vedio = this.vedioService.getVedioByPointid(this.assess.getKpointId());  //得到当前节点  视频信息
					this.teacher = this.teacherService.getTeacherById(vedio.getTcId());  //得到当前知识点  主讲老师
					this.voSize = vedio.getVoSize(); //得到当前视频长度
					this.assess.setKpoint(this.kpoint); 	// 向评论添加知识点对象
				}
			}
			this.getQueryAssessCondition().setKpointId(this.assess.getKpointId()); // 添加知识点ID查询条件
			this.getQueryAssessCondition().setSubId(this.assess.getSubjectId());
			this.getQueryAssessCondition().setStatus(-2);
			assList = assessService.getAllAssessList(this.getQueryAssessCondition()); // 得到当前评论集合
			
			assessNum();	// 调用计算当前知识点评论分数

			//分页开始
			this.getQueryAssessCondition().setPageSize(10);  //添加每页显示数量 
			this.getQueryAssessCondition().setStatus(-2);
			PageResult pageAssessList = this.assessService.getPageAssessList(this.getQueryAssessCondition());
			List<AssessDto> asList=pageAssessList.getPageResult();
			pageAssessList.setPageResult(getReAssessList(asList));
			setPage(pageAssessList);
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(10);
			} 
			getPage().getPageResult();
			
			// 分页结束
			
			assPageList = this.getPage().getPageResult();
			for (int i = 0; i < assPageList.size(); i++) {		//遍历当前分页集合
				totolsScore = totolsScoreService.getTotolsScore(assPageList.get(i).getCusId());//根据customerId  得到当前学员的 积分 经验值
				if(assPageList.get(i).getCusName()!=null && assPageList.get(i).getCusName().length()>10){  //如果用户昵称长度大于5处理
					assPageList.get(i).setCusName(assPageList.get(i).getCusName().substring(0,10)+"...");  //循环遍历处理用户名字过长
					
				}
				if (totolsScore != null) {		//添加当前用户的经验值，积分
					assPageList.get(i).setTsAction(totolsScore.getTsAction());
					assPageList.get(i).setTsCurrent(totolsScore.getTsCurrent());
					assPageList.get(i).setTsId(totolsScore.getTsId());
				}
				assPageList.get(i).setPerNumber((assPageList.get(i).getAssLeavel()*100)/5);  //得到当前用户评分数占总数的百分比
				getExperienceLevel(assPageList.get(i).getTsAction(), i);	//调用计算用户级别方法
			}
			getLevelPercent();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "toAddAssess";	//返回评分页面
	}
	/**
	 * 得到评论后台回复
	 */
	private List<AssessDto> getReAssessList(List<AssessDto> asList){
		
		try {
			for (int i = 0; i < asList.size(); i++) {
				this.getQueryReassessCondition().setAssId(asList.get(i).getId());
				this.reAssList =this.reassessService.getReassessList(this.getQueryReassessCondition());
				if(this.reAssList.size()!=0 ){
					for(int a=0;a<this.reAssList.size();a++){
						if(asList.get(i).getId()==this.reAssList.get(a).getAssId()){
							asList.get(i).setReList(this.reAssList);
						}
					}
				}
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return asList;
	}

	/**
	 * 得到每一级评分人数 别占所有人的百分比
	 * 
	 */
	private void getLevelPercent() {
		this.leavel1=0;  //声明变量   即  1分 到5分  
		this.leavel2=0;
		this.leavel3=0;
		this.leavel4=0;
		this.leavel5=0;
		this.getQueryAssessCondition().setSubId(this.assess.getSubjectId()); // 添加专业ID查询条件
		this.getQueryAssessCondition().setKpointId(this.assess.getKpointId()); // 添加知识点ID查询条件
		this.getQueryAssessCondition().setCourseId(this.assess.getCourseId()); // 添加课程ID查询条件
		this.getQueryAssessCondition().setStatus(-2);
		assList = this.assessService.getAllAssessList(this.getQueryAssessCondition());  //得到当前知识点集合
		if(assList.size()!=0){
		for(int i=0;i<assList.size();i++){	//遍历集合
			if(assList.get(i).getAssLeavel()==1){	//评分数为1分  lvl1 自增
				this.leavel1++;
			}else if (assList.get(i).getAssLeavel()==2) {
				this.leavel2++;
			}else if (assList.get(i).getAssLeavel()==3) {
				this.leavel3++;
			}else if (assList.get(i).getAssLeavel()==4) {
				this.leavel4++;
			}else if (assList.get(i).getAssLeavel()==5) {
				this.leavel5++;
			}
		}
		
			this.perLvl1=(leavel1*100)/(assList.size());  //得到当前级别人数 占总人数的百分比  即 （评分数*100）/总人数
			this.perLvl2=(leavel2*100)/(assList.size());
			this.perLvl3=(leavel3*100)/(assList.size());
			this.perLvl4=(leavel4*100)/(assList.size());
			this.perLvl5=(leavel5*100)/(assList.size());
			float sum = Float.valueOf(sumString);		//格式化当前节点平均分
			this.perSum = (int)(sum*100)/5;				//计算当前节点平均分占总分的百分比
		}
	}

	/**
	 * 验证当前用户  对当前知识点 是否已经评论过 无刷新
	 * @return
	 */
	public String toAddAssessCheck() {
		try {
			this.getQueryAssessCondition().setCusId(this.getLoginUserId());	 //附加查询条件  得到当前登录用户
			if(kpId!=null&&!kpId.equals("")){
				this.getQueryAssessCondition().setKpointId(Integer.valueOf(kpId)); //添加当前知识点 查询条件
			}
			if(subId!=0){
				this.getQueryAssessCondition().setSubId(subId);
			}
			assList = this.assessService.getAssessByCusId(this.getQueryAssessCondition());  //执行查询
			this.getQueryCashRecordCondition().setUserId(this.getLoginUserId());
			this.getQueryCashRecordCondition().setStatus(1);
			this.getQueryCashRecordCondition().setCourseId(this.couId);
			cashList =this.cashRecordService.getCashRecordByList(this.getQueryCashRecordCondition());
			Course course = new Course();
			course.setGmNum(0);
			if(assList.size()!=0){			//判断返回结果集 是否为空
				this.assess = assList.get(0);		 //取第一条记录
			}
			if(cashList.size()==0){
				
				course.setGmNum(1);
				this.assess=new Assess();
				this.assess.setCourse(course);
			}
			JSONArray jsAss = JSONArray.fromObject(this.assess);  //json处理
			this.setResult(new Result<Object>(true, jsAss.toString(), null,		//添加setresult
					null));
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "json"; //返回json
	}

	/**
	 * 用户经验值 级别 称号计算验证
	 * 
	 * @param tScore
	 * @param i
	 */
	private void getExperienceLevel(int tScore, int i) {

		if (tScore < 60) {				//tscore 为当前评论人的级别 根据经验值计算
			assPageList.get(i).setExpLevel("1");
			assPageList.get(i).setExpName("新手上路");
		}
		if (tScore >= 60 && tScore < 75) {
			assPageList.get(i).setExpLevel("2");
			assPageList.get(i).setExpName("手艺学徒");
		}
		if (tScore >= 75 && tScore < 100) {
			assPageList.get(i).setExpLevel("3");
			assPageList.get(i).setExpName("初级学员");
		}
		if (tScore >= 100 && tScore < 150) {
			assPageList.get(i).setExpLevel("4");
			assPageList.get(i).setExpName("初级学员");
		}
		if (tScore >= 150 && tScore < 230) {
			assPageList.get(i).setExpLevel("5");
			assPageList.get(i).setExpName("中级学员");
		}
		if (tScore >= 230 && tScore < 400) {
			assPageList.get(i).setExpLevel("6");
			assPageList.get(i).setExpName("中级学员");
		}
		if (tScore >= 400 && tScore < 800) {
			assPageList.get(i).setExpLevel("7");
			assPageList.get(i).setExpName("高级学员");
		}
		if (tScore >= 800 && tScore < 1500) {
			assPageList.get(i).setExpLevel("8");
			assPageList.get(i).setExpName("高级学员");
		}
		if (tScore >= 1500 && tScore < 3500) {
			assPageList.get(i).setExpLevel("9");
			assPageList.get(i).setExpName("特级学员");
		}
		if (tScore >= 3500 && tScore < 7000) {
			assPageList.get(i).setExpLevel("10");
			assPageList.get(i).setExpName("明星学员");
		}
		if (tScore >= 7000 && tScore < 15000) {
			assPageList.get(i).setExpLevel("11");
			assPageList.get(i).setExpName("超级偶像");
		}
		if (tScore >= 15000 && tScore < 25000) {

			assPageList.get(i).setExpLevel("12");
			assPageList.get(i).setExpName("资深专家");
		}
		if (tScore >= 25000) {
			assPageList.get(i).setExpLevel("13");
			assPageList.get(i).setExpName("百科全书");
		}
	}

	/**
	 * 计算当前节点平均分
	 */
	private void assessNum() {
		if (assList.size() != 0) {
			for (int i = 0; i < assList.size(); i++) { // 遍历添加评价分数
				sum += assList.get(i).getAssLeavel();  //或得当前节点所有人评分集合 即 sum值
			}
			this.sum = sum / (float) assList.size(); // 处理评价平均分操作
			sumString = sum + "";			//将sum 转换成string 类型
			DecimalFormat format = new DecimalFormat("0.0"); // 格式化 取评价分数 两位
			sumString = format.format(this.sum);  //格式化评分（平均分）
			this.assessSum = this.assessService // 得到当前节点的总评论人
					.getAssessCount(this.getQueryAssessCondition());
		}
	}

	/**
	 * 用户评价 添加操作
	 * 
	 * @return
	 */
	
	/**
	 * 百晟提供的接口，过滤词
	 */
	public String getKeyWordFilter(String str){
		KeyWordFilter kf = KeyWordFilter.getInstance(disWordService);
		return kf.doFilter(str);
	}
	/**
	 * 添加评论操作
	 * 
	 */
	public String addAssess() {  //添加评论操作
		//评论状态默认为0  即未审核  1为已审核  2新稿  3删除
		try {
			if (this.assess != null) { 		//判断评论对象是否为空
				this.assess.setAssTime(new Date());	//添加评论时间 即当前时间
				this.assess.setVerifyTime(new Date());
				this.assess.setStatus(0);
				if(assContext!="" && assContext!=null){
					this.assess.setAssContext(getKeyWordFilter(PreventInfusion.xssEncode(assContext)));
				}
				this.assess.setAssTitle(getKeyWordFilter(this.assess.getAssTitle()));
				this.assess.setCusId(getLoginUserId());	//添加评论人 即当前登录用户
				kpId = this.assess.getKpointId()+"";
				subId = this.assess.getSubjectId();
				this.couId = this.assess.getCourseId();
				toAddAssessCheck();
				
				this.assessService.addAssess(this.assess);  //执行添加操作
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "AddAssess";
	}

	public Assess getAssess() {
		if(assess==null){
			assess=new Assess();
		}
		return assess;
	}

	public void setAssess(Assess assess) {
		this.assess = assess;
	}

	public IAssess getAssessService() {
		return assessService;
	}

	public void setAssessService(IAssess assessService) {
		this.assessService = assessService;
	}

	public List<Assess> getTop10list() {
		return top10list;
	}

	public void setTop10list(List<Assess> top10list) {
		this.top10list = top10list;
	}

	public List<Assess> getAssList() {
		return assList;
	}

	public void setAssList(List<Assess> assList) {
		this.assList = assList;
	}

	/**
	 * 初始化信息，去往评价中心
	 * 
	 * @author zhangjuqiang
	 * @return
	 */

	public String toAssess(){
		long start=System.currentTimeMillis();
		System.out.println("开始执行时间："+start);
		initSubject();		
		//首次进入
		if(assess.getSubjectId()==0){
			//subject不为null,肯定是注册未购买或购买专业包括注册时选定的专业，这时默认显示注册时专业
			if(subject!=null){
				currSub=subject;
				queryAssessCondition.setSubId(subject.getSubjectId());
			}else{//第二种情况，购买但不包括注册时选定的专业，默认第一个
				queryAssessCondition.setSubId(subList.get(0).getSubjectId());
				currSub=assessService.getCurrSubject(subList.get(0).getSubjectId());
			}
		}else{
			currSub=assessService.getCurrSubject(assess.getSubjectId());
			queryAssessCondition.setSubId(assess.getSubjectId());
		}
		
		top10list=assessService.getAssessListByCondition(queryAssessCondition);
		//toMyselfSellId(top10list);
	
	/**
	 * 以下只显示用户自己的信息
	 */
	
		//查找已評價的，已評價只顯示自己的評價信息
		queryAssessCondition.setCusId(loginId);			
		assList=assessService.getAssessListByCondition(queryAssessCondition);

		//已观看的IDS
		watchKids=assessService.getWatchedKpointList(currSub.getSubjectId(), loginId);
		
		//得知此用戶對當前專業是否有過购买、观看、評價,符合才会显示已评价、未评价信息		
		if(subList!=null&&subList.size()>0
				&&watchKids!=null&&watchKids.size()>0){
			isShow=true;
		}else{
			isShow=false;
		}
		/**
		查询未评价课程（知识点）
		*/
		
		//已评价的IDS
		this.assKids=assessService.getAssKpointIdsByCusId(queryAssessCondition);
		if(isShow){
			//查询未评价知识点
			queryAssessCondition.setWatchKids(watchKids);
			queryAssessCondition.setAssKids(this.assKids);
			unAssKpointList=assessService.getKpointListForUnAss(queryAssessCondition);
		}
		

		/*
		 * 初始化顶部提示区
		 */
		
		if(subList==null||subList.size()<1){
			//如果未购买
			status=0;
		}else if(watchKids==null||watchKids.size()<1){
			//如果未观看
			status=1;
		}else if(assList==null||assList.size()<1){
			//如果未评价
			status=2;
		}else{
			//已评价
			status=3;
			int count=0;//知识点数量
			//HttpServletRequest request=ServletActionContext.getRequest();
			queryAssessCondition.setCusId(loginId);
			//所有评价
			queryAssessCondition.setAssLeavel(0);
			count=assessService.getKpointCountByLevel(queryAssessCondition);
			this.getStarInfo().setKcAll(count);
			//一星，数据库记录为1，下同
			queryAssessCondition.setAssLeavel(1);
			count=assessService.getKpointCountByLevel(queryAssessCondition);
			this.getStarInfo().setKc1(count);
			//二星
			queryAssessCondition.setAssLeavel(2);
			count=assessService.getKpointCountByLevel(queryAssessCondition);
			this.getStarInfo().setKc2(count);
			//三星
			queryAssessCondition.setAssLeavel(3);
			count=assessService.getKpointCountByLevel(queryAssessCondition);
			this.getStarInfo().setKc3(count);
			//四星
			queryAssessCondition.setAssLeavel(4);
			count=assessService.getKpointCountByLevel(queryAssessCondition);
			this.getStarInfo().setKc4(count);
			//五星
			queryAssessCondition.setAssLeavel(5);
			count=assessService.getKpointCountByLevel(queryAssessCondition);
			this.getStarInfo().setKc5(count);
		}
		long end=System.currentTimeMillis();
		System.out.println("结束时间："+end+";用时："+(end-start));
		return "toAssess";
	}
	private void initSubject() {
		loginId=getLoginUserId();
		//已购买专业
		subList=assessService.getBuySubjectListByCusId(loginId);
		//取得用户注册的专业
		subject=assessService.getDefaultSubject(loginId);
		//如果已购买，但没包括注册时的专业，那注册的专业就不要显示了
		boolean isExist=false;
		List<Integer> sids=new ArrayList<Integer>();
		if(subList!=null&&subList.size()>0){
			for (int i = 0; i < subList.size(); i++) {
				if(subList.get(i).getSubjectId()==subject.getSubjectId()){
					isExist=true;
					//break;
				}
				sids.add(subList.get(i).getSubjectId());
			}
			if(!isExist)subject=null; 
		}else{
			sids.add(subject.getSubjectId());
		}
		unBuySubList=assessService.getUnBuySubject(sids);
		
	}
	//如果好评列表中的知识点不是用户的评价，将此知识点在别人购买时所属的售卖方式改为用户自己的，若用户没卖售卖方式为0
	private void toMyselfSellId(List<Assess> list){
		int sellId=0;
		this.getQueryAssessCondition().setCusId(loginId);
		for(Assess ass:list){
			if(ass.getCusId()!=loginId){
				this.getQueryAssessCondition().setKpointId(ass.getKpointId());
				sellId=assessService.getSellWayIdsByKpointId(this.getQueryAssessCondition());
				ass.setSellwayId(sellId);
			}
		}
	}
	
	public String toMoreAssess(){
		initSubject();
		this.getQueryAssessCondition().setSubId(currSub.getSubjectId());
		this.getQueryAssessCondition().setCusId(loginId);
		this.getQueryAssessCondition().setPageSize(24);
		this.setPage(assessService.getMoreAssessByCondition(this.getQueryAssessCondition()));
		setPageUrlParms();
		if(getPage()!=null){
			   getPage().setPageSize(24);
		 }
		return "toMoreAssess";
	}
	
	public String toMoreUnAssess(){
		initSubject();
		this.getQueryAssessCondition().setSubId(currSub.getSubjectId());
		this.getQueryAssessCondition().setCusId(loginId);
		this.assKids=assessService.getAssKpointIdsByCusId(this.getQueryAssessCondition());
		this.getQueryAssessCondition().setAssKids(assKids);
		this.watchKids=assessService.getWatchedKpointList(currSub.getSubjectId(), loginId);
		this.getQueryAssessCondition().setWatchKids(watchKids);
		this.getQueryAssessCondition().setPageSize(24);
		this.setPage(assessService.getMoreKpointListForUnAss(this.getQueryAssessCondition()));
		setPageUrlParms();
		if(getPage()!=null){
			   getPage().setPageSize(24);
		 }
		return "toMoreUnAssess";
	}

	public IReassess getReassessService() {
		return reassessService;
	}

	public void setReassessService(IReassess reassessService) {
		this.reassessService = reassessService;
	}

	public List<Subject> getSubList() {
		return subList;
	}

	public void setSubList(List<Subject> subList) {
		this.subList = subList;
	}
//
//	public QueryAssessCondition getAssCondition() {
//		return assCondition;
//	}
//
//	public void setAssCondition(QueryAssessCondition assCondition) {
//		this.assCondition = assCondition;
//	}

	public boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(boolean isShow) {
		this.isShow = isShow;
	}


	public List<Kpoint> getUnAssKpointList() {
		return unAssKpointList;
	}

	public void setUnAssKpointList(List<Kpoint> unAssKpointList) {
		this.unAssKpointList = unAssKpointList;
	}

	
	public Subject getCurrSub() {
		return currSub;
	}

	public void setCurrSub(Subject currSub) {
		this.currSub = currSub;
	}

	


	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Kpoint getKpoint() {
		return kpoint;
	}

	public void setKpoint(Kpoint kpoint) {
		this.kpoint = kpoint;
	}

	public float getSum() {
		return sum;
	}

	public void setSum(float sum) {
		this.sum = sum;
	}

	public int getAssessSum() {
		return assessSum;
	}

	public void setAssessSum(int assessSum) {
		this.assessSum = assessSum;
	}

	public List<AssessDto> getAssPageList() {
		return assPageList;
	}

	public void setAssPageList(List<AssessDto> assPageList) {
		this.assPageList = assPageList;
	}

	public String getSumString() {
		return sumString;
	}

	public void setSumString(String sumString) {
		this.sumString = sumString;
	}

	public AssessDto getAssessDto() {
		if(this.assessDto==null){
			this.assessDto = new AssessDto();
		}
		
		return assessDto;
	}

	public void setAssessDto(AssessDto assessDto) {
		this.assessDto = assessDto;
	}

	public TotolsScore getTotolsScore() {
		return totolsScore;
	}

	public void setTotolsScore(TotolsScore totolsScore) {
		this.totolsScore = totolsScore;
	}

	public ITotolsScore getTotolsScoreService() {
		return totolsScoreService;
	}

	public void setTotolsScoreService(ITotolsScore totolsScoreService) {
		this.totolsScoreService = totolsScoreService;
	}

	public String getKpId() {
		return kpId;
	}

	public void setKpId(String kpId) {
		this.kpId = kpId;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public Map<String, Integer> getKpointCount() {
		return kpointCount;
	}

	public void setKpointCount(Map<String, Integer> kpointCount) {
		this.kpointCount = kpointCount;
	}

	public List<Integer> getAssKids() {
		return assKids;
	}

	public void setAssKids(List<Integer> assKids) {
		this.assKids = assKids;
	}

	public List<String> getWatchKids() {
		return watchKids;
	}

	public void setWatchKids(List<String> watchKids) {
		this.watchKids = watchKids;
	}

	public AssKpointStarInfo getStarInfo() {
		if(this.starInfo==null){
			this.starInfo = new AssKpointStarInfo();
		}
		return starInfo;
	}

	public void setStarInfo(AssKpointStarInfo starInfo) {
		this.starInfo = starInfo;
	}

	public int getLeavel1() {
		return leavel1;
	}

	public void setLeavel1(int leavel1) {
		this.leavel1 = leavel1;
	}

	public int getLeavel2() {
		return leavel2;
	}

	public void setLeavel2(int leavel2) {
		this.leavel2 = leavel2;
	}

	public int getLeavel3() {
		return leavel3;
	}

	public void setLeavel3(int leavel3) {
		this.leavel3 = leavel3;
	}

	public int getLeavel4() {
		return leavel4;
	}

	public void setLeavel4(int leavel4) {
		this.leavel4 = leavel4;
	}

	public int getLeavel5() {
		return leavel5;
	}

	public void setLeavel5(int leavel5) {
		this.leavel5 = leavel5;
	}

	public int getPerLvl1() {
		return perLvl1;
	}

	public void setPerLvl1(int perLvl1) {
		this.perLvl1 = perLvl1;
	}

	public int getPerLvl2() {
		return perLvl2;
	}

	public void setPerLvl2(int perLvl2) {
		this.perLvl2 = perLvl2;
	}

	public int getPerLvl3() {
		return perLvl3;
	}

	public void setPerLvl3(int perLvl3) {
		this.perLvl3 = perLvl3;
	}

	public int getPerLvl4() {
		return perLvl4;
	}

	public void setPerLvl4(int perLvl4) {
		this.perLvl4 = perLvl4;
	}

	public int getPerLvl5() {
		return perLvl5;
	}

	public void setPerLvl5(int perLvl5) {
		this.perLvl5 = perLvl5;
	}

	public QueryAssessCondition getQueryAssessCondition() {
		if(queryAssessCondition==null){
			queryAssessCondition = new QueryAssessCondition();
		}
		return queryAssessCondition;
	}

	public void setQueryAssessCondition(QueryAssessCondition queryAssessCondition) {
		this.queryAssessCondition = queryAssessCondition;
	}

	public int getPerSum() {
		return perSum;
	}

	public void setPerSum(int perSum) {
		this.perSum = perSum;
	}

	public static int getLoginId() {
		return loginId;
	}

	public static void setLoginId(int loginId) {
		AssWebAction.loginId = loginId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public QueryReassessCondition getQueryReassessCondition() {
		if(this.queryReassessCondition==null){
			this.queryReassessCondition = new QueryReassessCondition();
		}
		return queryReassessCondition;
	}

	public void setQueryReassessCondition(
			QueryReassessCondition queryReassessCondition) {
		this.queryReassessCondition = queryReassessCondition;
	}

	public Reassess getReassess() {
		return reassess;
	}

	public void setReassess(Reassess reassess) {
		this.reassess = reassess;
	}

	public List<Reassess> getReAssList() {
		return reAssList;
	}

	public void setReAssList(List<Reassess> reAssList) {
		this.reAssList = reAssList;
	}	
	public List<CashRecord> getCashList() {
		return cashList;
	}
	public void setCashList(List<CashRecord> cashList) {
		this.cashList = cashList;
	}
	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}
	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}
	public QueryCashRecordCondition getQueryCashRecordCondition() {
		if(this.queryCashRecordCondition ==null){
			this.queryCashRecordCondition = new QueryCashRecordCondition();
		}
		return queryCashRecordCondition;
	}
	public void setQueryCashRecordCondition(
			QueryCashRecordCondition queryCashRecordCondition) {
		this.queryCashRecordCondition = queryCashRecordCondition;
	}
	public int getCouId() {
		return couId;
	}
	public void setCouId(int couId) {
		this.couId = couId;
	}
	public QueryKpointCondition getQueryKpointCondition() {
		return queryKpointCondition;
	}
	public void setQueryKpointCondition(QueryKpointCondition queryKpointCondition) {
		this.queryKpointCondition = queryKpointCondition;
	}
	public String getVoSize() {
		return voSize;
	}
	public void setVoSize(String voSize) {
		this.voSize = voSize;
	}
	public IVedio getVedioService() {
		return vedioService;
	}
	public void setVedioService(IVedio vedioService) {
		this.vedioService = vedioService;
	}

	public ITeacher getTeacherService() {
		return teacherService;
	}
	public void setTeacherService(ITeacher teacherService) {
		this.teacherService = teacherService;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public List<Subject> getUnBuySubList() {
		return unBuySubList;
	}
	public void setUnBuySubList(List<Subject> unBuySubList) {
		this.unBuySubList = unBuySubList;
	}
	public String getAssContext() {
		return assContext;
	}
	public void setAssContext(String assContext) {
		this.assContext = assContext;
	}
	public IDisWord getDisWordService() {
		return disWordService;
	}
	public void setDisWordService(IDisWord disWordService) {
		this.disWordService = disWordService;
	}
}
