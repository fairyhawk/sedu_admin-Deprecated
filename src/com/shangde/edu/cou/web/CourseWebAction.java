package com.shangde.edu.cou.web;

import java.io.BufferedInputStream;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import com.shangde.edu.cou.domain.SellWay;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import com.shangde.edu.cou.webdto.UserCenterSubjectCourseDTO;
import javax.servlet.http.HttpServletResponse;
import com.shangde.edu.cou.service.ISellWay;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.intercepter.LimitIntercepterForWeb;
import com.shangde.common.util.CookieHandler;
import com.shangde.common.util.Result;
import com.shangde.common.util.URLUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.condition.QueryTjcourseCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Gmrecord;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.domain.SellCou;
import com.shangde.edu.cou.domain.Tjcourse;
import com.shangde.edu.cou.dto.CourseDTO;
import com.shangde.edu.cou.dto.CourseSortListDTO;
import com.shangde.edu.cou.dto.CouseResultDTO;
import com.shangde.edu.cou.dto.KeyValueDTO;
import com.shangde.edu.cou.dto.ListeningCourseDTO;
import com.shangde.edu.cou.dto.UserCourseDTO;
import com.shangde.edu.cou.dto.UserKpointDTO;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursepic;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.IGmrecord;
import com.shangde.edu.cou.service.IHistoryPrice;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cou.service.ISellCou;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cou.service.ITjcourse;
import com.shangde.edu.cou.webdto.StudyStatisticsDTO;
import com.shangde.edu.cou.webdto.UserCenterCourseDTO;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cusmgr.condition.QueryCusCouKpointCondition;
import com.shangde.edu.cusmgr.domain.CusCouKpoint;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.exam.condition.QueryExampaperRecordCondition;
import com.shangde.edu.exam.dto.ExamAnalysisDTO;
import com.shangde.edu.exam.service.IExampaper;
import com.shangde.edu.exam.service.IExampaperRecord;
import com.shangde.edu.res.condition.QueryNotesCondition;
import com.shangde.edu.res.domain.Books;
import com.shangde.edu.res.domain.Notes;
import com.shangde.edu.res.service.IBooks;
import com.shangde.edu.res.service.INotes;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.tk.condition.QueryTaskCusCondition;
import com.shangde.edu.tk.domain.Task;
import com.shangde.edu.tk.domain.TaskCus;
import com.shangde.edu.tk.service.ITaskCus;
import com.shangde.edu.stu.condition.QueryVideoStatisticsCondition;
import com.shangde.edu.stu.domain.VideoStatistics;
import com.shangde.edu.stu.service.IVideoStatistics;
/**
 * 课程
 * @author chenshuai
 */
public class CourseWebAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	public static final String COOKIE_COURSE_LISTENING_KEY="sedu.cookie.listeningcourse";
	public static final String COOKIE_COURSE_BROWE_KEY="sedu.cookie.listeningcourse";
	
	private static Logger logger = Logger.getLogger(CourseWebAction.class);

	/**
	 * 课程
	 */
	private Course course; 
	
	/**
	 * 用户
	 */
	private Customer customer;
	/**
	 * 课程服务
	 */
	private ICourse courseService;
	
	/**
	 * 课程知识点记录
	 */
	private ICusCouKpoint cusCouKpointService;
	
	/**
	 * 学科服务
	 */
	private ISubject subjectService;
	
	/**
	 * 书籍服务
	 */
	private IBooks booksService;
	
	/**
	 * 课程图片服务
	 */
	private ICoursepic coursepicService;
	
	/**
	 * 历史价格
	 */
	private IHistoryPrice  historyPriceService;
	
	/**
	 * 用户服务
	 */
	private ICustomer customerService;
	
	/**
	 * 推荐课程服务
	 */
	private ITjcourse tjcourseService;
	
	/**
	 * 知识点服务
	 */
	private IKpoint kpointService;
	
	/**
	 * 购买记录服务
	 */
	private IGmrecord gmrecordService;
	
	private ITaskCus taskCusService;
	
	/**
	 * 课程查询条件
	 */
	private QueryCourseCondition queryCourseCondition;
	
	/**
	 * 课程集合
	 */
	private List<Course> courseList;
	
	private List<KeyValueDTO> kpointList;
	
	private List<Course> tjcourseList;
	
	private List<Books> bookList;
	
	/**
	 * 课程数量
	 */
	private int courseSortSize;
	
	private String seduListeningCourses;
	
	private CouseResultDTO courseReult;
	
	private int bookId;
	
	private Subject subject;
	
	/**
	 * 用户知识点ID
	 */
	private int cusCouKpointId;
	
	private List<CourseSortListDTO> courseSortListDTOList;
	/**
	 * 考试服务
	 */
	private IExampaper exampaperService;

	private IExampaperRecord exampaperRecordService;

	private ExamAnalysisDTO examAnalysisDTO;

	private QueryExampaperRecordCondition queryExampaperRecordCondition;
	/**
	 * 知识点查询条件
	 */
	private QueryKpointCondition queryKpointCondition;
	/**
	 * 笔记服务对象
	 */
	private INotes notesService;
	/**
	 * 笔记查询条件
	 */
	private QueryNotesCondition queryNotesCondition;
	/**
	 * 笔记对象list
	 */
	private List<Notes> notesList=new ArrayList<Notes>();
	
	/**
	 * 课程分类接口
	 */
	private ICoursesort coursesortService; 
	
	/**
	 * 用户课程集合
	 */
	private List<UserCenterCourseDTO> userCourseList;
	
	/**
	 * 用户课程集合
	 */
	private List<UserCenterCourseDTO> userUnReadCourseList;
	
	/**
	 * 最后观看知识点
	 */
	private Kpoint kpoint;

	private boolean ishavebuy=false;
	
	private List<UserCenterSubjectCourseDTO> sellWayCourseList = new ArrayList<UserCenterSubjectCourseDTO>();//购买的包集合
	
	private String tid;
    private String	styleid;
    
    private List<Subject> subjectList;
    
    private IVideoStatistics   videoStatisticsService;
    
    
    /**
     * 售卖方式 服务
     * @return
     */
    private ISellWay sellWayService;
    private ISellCou sellCouService;
    private SellWay sellWay;
    
    private QuerySellCouCondition querySellCouCondition;
    
    private List<CourseDTO> couDtoList;
    

	public QuerySellCouCondition getQuerySellCouCondition() {
    	if(this.querySellCouCondition==null){
    		querySellCouCondition= new QuerySellCouCondition();
    	}
		return querySellCouCondition;
	}

	public void setQuerySellCouCondition(QuerySellCouCondition querySellCouCondition) {
		this.querySellCouCondition = querySellCouCondition;
	}

	public ISellCou getSellCouService() {
		return sellCouService;
	}

	public void setSellCouService(ISellCou sellCouService) {
		this.sellCouService = sellCouService;
	}

	public ISellWay getSellWayService() {
		return sellWayService;
	}

	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}

	public IVideoStatistics getVideoStatisticsService() {
        return videoStatisticsService;
    }

    public void setVideoStatisticsService(IVideoStatistics videoStatisticsService) {
        this.videoStatisticsService = videoStatisticsService;
    }
    
    /*------------------------------宁肖添加变量关于播放器----------------------------*/
    /**
     * 穿入课程ID
     */
    private Integer courseId;
    /*------------------------------宁肖添加变量关于播放器----------------------------*/
    /**
     *更新顶和菜变量方法
     */
    public String updatedc()
    {
    	
    	try {
    		Course coursetemp = courseService.getCourseById(new Integer(tid));
			if (styleid.equals("1")) {
				coursetemp.setSupportNum(coursetemp.getSupportNum() + 1);
				courseService.updateCourse(coursetemp);
			} else if (styleid.equals("2")) {
				coursetemp.setUnsupportNum(coursetemp.getUnsupportNum() + 1);
				courseService.updateCourse(coursetemp);
			}
			
			this.setResult(new Result<Object>(true, "1", null, null));
		} catch (Exception e) {
			this.setResult(new Result<Object>(true, "0", null, null));
		}
		
    	return "updatedc";
    }
    
    /**
     * 添加推荐课程，通过tjcourse_add.jsp页面提交信息添加
     * @param tjcourse
     * @return
     */
    public int addTjCourse(Tjcourse tjcourse)
    {   	       
    	return tjcourseService.addTjcourse(tjcourse);
    }
    /**
     * 获得推荐课程,通过当前课程ID查找当前课程推荐的课程集合
     * @param id
     * @return
     */    
	public Map<String, Object> getTjCourses(int id) {
		QueryTjcourseCondition queryTjcourseCondition = new QueryTjcourseCondition();
		queryTjcourseCondition.setId(id);
		List<Tjcourse> tjcourseList = tjcourseService
				.getTjcourseList(queryTjcourseCondition);

		Tjcourse tjcourse = tjcourseList.get(0);
		Map<String, String> current = new HashMap<String, String>();

		current.put("title", tjcourse.getTitle());
		current.put("picPath", tjcourse.getPicPath());
		current.put("info", tjcourse.getInfo());
		current.put("buyMethod", tjcourse.getBuyMethod());

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < tjcourseList.size(); i++) {
			Tjcourse temp = tjcourseList.get(i);
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("picPath", temp.getTjPicPath());
			tempMap.put("title", temp.getTjTitle());
			tempMap.put("clickTimes", temp.getTjClickTimes());
			tempMap.put("url", temp.getTjURL());
			tempMap.put("buyMethod", temp.getTjBuyMethod());
			tempMap.put("videoId", temp.getTjcourseId());
			tempMap.put("index", i + 1);
			list.add(tempMap);
		}

		map.put("recommendVedios", list);
		map.put("currentVedio", current);
		return map;
	}
    
    /**
     * @author chenshuai
     * 功能：新版正式听课（2011-1-18）
     * @param args
     * @return
     */
    public String toListenMyCourse(){
    	try{
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		return ERROR;
    	}
    	return "toListenMyCourse";
    }
	
    public String toallcourse() {
        return "allcourse";
    }
	/**
	 * 根据subjectID进入频道展示
	 * 要提供的信息为课程集合、课程专业
	 */
	public String toPreparePindao(){
		try{
			this.getQueryCourseCondition().setNum(10);
			
			if(course != null){
				queryCourseCondition.setSubjectId(course.getSubjectId());//按频道查询
			}
			
			List<Course> temp =	courseService.getCourseListBySortId(queryCourseCondition);
			
			if(temp != null){
				this.courseSortSize = temp.size();
			}
			courseList = courseService.getCourseListtBySortIdLimit(queryCourseCondition);
			
			subject = subjectService.getSubjectById(course.getSubjectId());
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "toPreparePindao";
	}
	
	/**
	 * 第二版频道页展示
	 * 要提供的信息为课程集合、课程专业
	 */
	public String toPreparePindaoTest(){
		try{
			this.getQueryCourseCondition().setNum(6);
			
			if(course != null){
				queryCourseCondition.setSubjectId(course.getSubjectId());//按频道查询
			}
			
			List<Course> temp =	courseService.getCourseListBySortId(queryCourseCondition);
			
			if(temp != null){
				this.courseSortSize = temp.size();
			}
			courseList = courseService.getCourseListtBySortIdLimit(queryCourseCondition);
			subject = subjectService.getSubjectById(course.getSubjectId());
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "toPreparePindaoTest";
	}
	
	/**
	 * 频道展示json
	 * result 的type为json(待用)
	 */
	public String toPreparePindaoDTO(){
		try{
			this.getQueryCourseCondition().setNum(6);
			
			if(course != null){
				queryCourseCondition.setSubjectId(course.getSubjectId());//按频道查询
			}
			
			List<Course> temp =	courseService.getCourseListBySortId(queryCourseCondition);
			
			if(temp != null){
				this.courseSortSize = temp.size();
			}
			
			courseList = courseService.getCourseListtBySortIdLimit(queryCourseCondition);
			List<CourseDTO> courseDTOList = new ArrayList<CourseDTO>();
			CourseDTO dto = null;
			Course coursetemp = null;
			
			for(int i = 0; i < courseList.size(); i ++){
				coursetemp = courseList.get(i);
				dto = new CourseDTO();
				
				dto.setCourseId(coursetemp.getCourseId());
				dto.setTeacher(coursetemp.getTeacher());
				dto.setTitle(coursetemp.getTitle());
				if(coursetemp.getTjvedio() != null){
					dto.setVoUrl(coursetemp.getTjvedio().getVoUrl());
				}else{
					dto.setVoUrl("");
				}
				
				dto.setIntroduce(coursetemp.getIntroduce());
				dto.setCourseTime(coursetemp.getLessionTime());
				courseDTOList.add(dto);
			}
			
			JSONArray jslist = JSONArray.fromObject(courseDTOList);
			
			this.setResult(new Result<Object>(true, jslist.toString(), null, null));
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 频道展示json js完成（待用）
	 */
	public String toOldPreparePindaoDTO(){
		try{
			this.getQueryCourseCondition().setNum(6);
			
			if(course != null){
				queryCourseCondition.setSubjectId(course.getSubjectId());//按频道查询
			}
			
			List<Course> temp =	courseService.getCourseListBySortId(queryCourseCondition);
			
			if(temp != null){
				this.courseSortSize = temp.size();
			}
			
			courseList = courseService.getCourseListtBySortIdLimit(queryCourseCondition);
			
			JSONArray jslist = JSONArray.fromObject(courseList);
			this.setResult(new Result<Object>(true, jslist.toString(), null, null));
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 根据课程ID获取课程页信息
	 * 提供信息为课程信息、相关课程集合
	 * @return
	 */
	public String toPrepareCourse(){
		try{
			if(course != null){
				course = courseService.getCourseById(course.getCourseId());//课程信息
				
				QueryTjcourseCondition queryTjcourseCondition = new QueryTjcourseCondition();//推荐课程
				queryTjcourseCondition.setYcourseId(course.getCourseId());
				
				List<Tjcourse> temp = tjcourseService.getTjcourseList(queryTjcourseCondition);
				tjcourseList = new ArrayList<Course>();
				for(int i = 0; i < temp.size(); i ++){
					this.tjcourseList.add(courseService.getCourseById(temp.get(i).getTjcourseId()));
				}
				
				this.getQueryCourseCondition().setNum(6);
				this.getQueryCourseCondition().setSubjectId(course.getSubjectId());
				courseList = courseService.getCourseListtBySortIdLimit(queryCourseCondition);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		
		return "toPrepareCourse";
	}
	
	/**
	 * 根据用户ID获取用户购买课程的知识树
	 * 该知识树的知识点有是否读过状态
	 * 用于学员中心我的课程中的知识树展现
	 * @return
	 */
	public String getUserKpointTree(){
		try{
			int userId = this.getLoginUserId();
			
			QueryCusCouKpointCondition queryCusCouKpointCondition = new QueryCusCouKpointCondition();
			
			queryCusCouKpointCondition.setCourseId(course.getCourseId());
			queryCusCouKpointCondition.setCusId(userId);
			
			List<UserKpointDTO> myList = cusCouKpointService.getKpointListByCusIdAndCourseId(queryCusCouKpointCondition);
			
			JSONArray jslist = JSONArray.fromObject(myList);
			this.setResult(new Result<Object>(true, jslist.toString(), null, null));
			
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		
		return "json";
	}
	
	/**
	 * 知识树的展现：获取课程知识树，用于课程页面知识树的展现
	 * 根据课程ID
	 * @return
	 */
	public String getFrontKpointTree(){
		try{
			
			QueryKpointCondition queryKpointCondition = new QueryKpointCondition();
			queryKpointCondition.setCourseId(course.getCourseId());
			queryKpointCondition.setLeaf(-1);
			
			List<KeyValueDTO> myList = kpointService.getKpointDTOListByCourseId(queryKpointCondition);
			
			JSONArray jslist = JSONArray.fromObject(myList);
			this.setResult(new Result<Object>(true, jslist.toString(), null, null));
			
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		
		return "json";
	}
	
	/**
	 * 获取指定数量的最新课程
	 * retult的type为json
	 * @return "json"
	 */
	public String getCoursesLimit(){
		try{
			List<CourseDTO> dtoList = courseService.getWebCourseListLimit(6);
			
			JSONArray jslist = JSONArray.fromObject(dtoList);
			this.setResult(new Result<Object>(true, jslist.toString(), null, null));
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	private List<SellWay> butSellWayList;// 购买的包
	private List<UserCenterCourseDTO> templist = new ArrayList<UserCenterCourseDTO>();
	
	/**
	 * @author chenshuai
	 * 功能：进入学员中心学员课程
	 * @param args
	 * @return
	 */
	public String toMyCourse(){
		try{
			int userId = getLoginUserId();
			if(userId == 0){
				userId = customer.getCusId();
			}
		customer = customerService.getCustomerById(userId);
		if(userId !=0){
			userUnReadCourseList = new ArrayList<UserCenterCourseDTO>();//未听课程集合
			QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
			QueryCusCouKpointCondition queryCusCouKpointCondition = new QueryCusCouKpointCondition(); 
			
			if(subject != null && subject.getSubjectId() > 0){//设置专业条件
				subject = subjectService.getSubjectById(subject.getSubjectId());
				if(subject != null){
					queryCourseCondition.setSubjectId(subject.getSubjectId());
					queryCusCouKpointCondition.setSubjectId(subject.getSubjectId());
				}
				
			}
			queryCourseCondition.setCusId(this.getLoginUserId());
			butSellWayList= courseService.getUserCenterSellWayListByCusId(queryCourseCondition);
			UserCenterSubjectCourseDTO sellwayCourseTemp = null;
			queryCusCouKpointCondition.setCusId(userId);
			subjectList = cusCouKpointService.getUserCenterSubjectListByCusId(queryCusCouKpointCondition);
			
			if(null != butSellWayList && butSellWayList.size()>0){
			    ishavebuy=true;
				for(int n = 0;n<butSellWayList.size();n++){
					SellWay sellWay = butSellWayList.get(n);
					sellwayCourseTemp = new UserCenterSubjectCourseDTO();
					sellwayCourseTemp.setSellWay(sellWay);
					templist = cusCouKpointService.getCouserListBySellId(sellWay.getSellId()+"");
					//图表
					if(null != templist){
						UserCenterCourseDTO temp = null;
						CusCouKpoint cusCKTemp = null;
						StudyStatisticsDTO studyStatisticsDTO = null;
						//int userSize = 0;
						Integer lastKpoint = null;
						Integer cusCKId = null;
						int totalNum = 0;
						int thanOneNum = 0;
						int thanTwoNum = 0;
						int thanThreeNum = 0;
						
						float thanOnePc = 0.0f;
						float thanTwoPc = 0.0f;
						float thanThreePc = 0.0f; 
		                 
		                 
						Random rd = new Random(); 
						for(int i = templist.size() - 1; i >= 0; i --){
							temp = templist.get(i);
							queryCusCouKpointCondition.setCourseId(temp.getCourseId());
							//temp.setKpointList(cusCouKpointService.getUserCenterKpointDTOByCusIdAndCourseId(queryCusCouKpointCondition));
							temp.setUploadedSize(0);
							temp.setVedioSize(0);
							
							//temp.setKpointList(cusCouKpointService.getUserCenterKpointDTOByCusIdAndCourseId(queryCusCouKpointCondition));
							//temp.setUploadedSize(cusCouKpointService.getCourseVedioSizeByCourseId(temp.getCourseId()));
							//temp.setVedioSize(temp.getKpointList().size());
							
							//计算学习分布图
							studyStatisticsDTO = new StudyStatisticsDTO();
							totalNum = 3648;//cusCouKpointService.getCusSizeByCourseId(queryCusCouKpointCondition) + 200;
							thanOnePc = Float.parseFloat("0.2" + Math.abs(rd.nextInt(10)));
							thanTwoPc = Float.parseFloat("0.1" + Math.abs(rd.nextInt(10)));
							thanThreePc = Float.parseFloat("0.0" + Math.abs(rd.nextInt(10))) + 0.05f;
							
							studyStatisticsDTO.setTotalUserNum(totalNum);
							
							thanOneNum = (int)Math.floor(thanOnePc * totalNum);
							thanTwoNum = (int)Math.floor(thanTwoPc * totalNum);
							thanThreeNum = (int)Math.floor(thanThreePc * totalNum);
							
							studyStatisticsDTO.setThanOneNum(thanOneNum);
							studyStatisticsDTO.setThanTwoNum(thanTwoNum);
							studyStatisticsDTO.setThanThreeNum(thanThreeNum);
							
							studyStatisticsDTO.setUserSelfNum(totalNum - thanOneNum - thanTwoNum - thanThreeNum);
							temp.setStudyStatisticsDTO(studyStatisticsDTO);
							//计算学习分布图
						}
						
						for(int j = 0;j<templist.size();j++){
							UserCenterCourseDTO templ = templist.get(j);
							sellwayCourseTemp.getCourseList().add(templ);//设置包下的课程课程
						}
					}
					sellWayCourseList.add(sellwayCourseTemp);//添加到包的list中
				}
			}
			if(sellWayCourseList != null && sellWayCourseList.size() > 0
					&& sellWayCourseList.get(0).getCourseList() != null
					&& sellWayCourseList.get(0).getCourseList().size() > 0
					){ 
				course = courseService.getCourseById(sellWayCourseList.get(0).getCourseList().get(0).getCourseId());
			}
		}
			
		}catch(Exception e){
			logger.error("to mycouse error", e);
			return ERROR;
		}
			
		return "toMyCourse";
	}
	
	
	
	///////////////////////////////学员中心///////////////////////////////////
	
	/**
	 * 用户中心 学员中心首页 初始化正在听的课程
	 * 提供的信息为用户课程集合
	 * retult的type为json
	 * @return "json"
	 */
	public String listeningCourse(){		
		
		Set<ListeningCourseDTO> listeningCourseSet = new HashSet<ListeningCourseDTO>();
		
		try{
			seduListeningCourses = CookieHandler.getCookieValueByName(this.servletRequest, "COOKIE_COURSE_LISTENING_KEY");
			
			if(seduListeningCourses != null){
				String[] listeningcourseIds = seduListeningCourses.split(",");
				int idTemp = 0;
				
				for(int i = 0; i < listeningcourseIds.length; i++){//获取课程集合
					idTemp = Integer.parseInt(listeningcourseIds[i]);
					listeningCourseSet.add(courseService.getWebListenintCourseDTOByVedioId(idTemp));
				}
			}
			JSONArray jslist = JSONArray.fromObject(listeningCourseSet);
			this.setResult(new Result<Object>(true, jslist.toString(), null, null));
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 最近购买记录
	 * @return
	 */
	public String getLastGMRecord() {
		try{
			String ukey = CookieHandler.getCookieValueByName(getServletRequest(), LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY);
			int userId = Integer.valueOf(ukey.split(",")[0]);
			Gmrecord gmrecord = this.gmrecordService.getLastGMRecord(userId);
			if(gmrecord!=null) {
				course = courseService.getCourseById(gmrecord.getCourseId());
			}
			course = course==null?new Course():course;
			this.setResult(new Result<Gmrecord>(false, course.getTitle(), null, gmrecord));
		}catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 进入正式听课页面，根据课程ID
	 * @return
	 */
	public String toListenCourse(){
		try{
			if(course != null){
				course = courseService.getCourseById(course.getCourseId());
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "toListenCourse";
	}
	
	/**
	 * 进入试听页面，根据课程ID
	 * @return
	 */
	public String toTryListenCourse(){
		try{
			if(course != null){
				course = courseService.getCourseById(course.getCourseId());
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "toTryListenCourse";
	}
	
	/**
	 * 判断用户信息是否完善信息(试听)
	 * 普通学员在注册完以后可以有免费七天的试听视频
	 * 根据学员ID 和 课程ID
	 * @return
	 */
	public String userInfoIsCompleted(){
		try{
			if(course != null){
				int userId = this.getLoginUserId();
				
				this.customer = customerService.getCustomerById(userId);
				
				if(customer.getIsComplete() == 1){
					
					if(customerService.isComplete(userId) > 0){
						this.setResult(new Result<Object>(true, "true", null, null));
					}else{
						this.setResult(new Result<Object>(true, "已经过期", null, null));
					}
				}else{
					this.setResult(new Result<Object>(true, "您的个人信息还未完善", null, null));
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 获取视频所需要的信息(试听)
	 * 普通学员在注册完以后可以有免费多的天的试听视频
	 * 根据学员ID 和 课程ID
	 * @return
	 */
	public String getJSONVedioAuditionCourseInfo(){
		try{
			if(course != null){
				int userId = this.getLoginUserId();
				
				this.customer = customerService.getCustomerById(userId);
				
				
				bookList = booksService.getBooksListByCourseId(course.getCourseId());
				
				List<UserKpointDTO> myList = kpointService.getTryKpointTreeByCourseId(course.getCourseId());
				
				Map<String, List<?>> infoResult = new HashMap<String, List<?>>();
				
				infoResult.put("kpointlist", myList);
				infoResult.put("booklist", bookList);
				
				UserCourseDTO<List<?>> dto = new UserCourseDTO<List<?>>();
				
				if(customer.getCompleteTime() == null){
					dto.setIsCompleted(0);
				}else{
					dto.setIsCompleted(1);
				}
				
				dto.setType("try");
				dto.setTreeArray(infoResult);
				
				JSONArray jslist = JSONArray.fromObject(dto);
				
				this.setResult(new Result<Object>(true, jslist.toString(), null, null));
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 获取视频所需要的信息(正式听课)
	 * 播放学员所购买的课程
	 * 根据学员ID 和 课程ID
	 * @return
	 */
	public void getJSONVedioCourseInfo() {
		UserCourseDTO<List<?>> dto = null;
		try{
			Course course = new Course();
			course.setCourseId(getCourseId());
			
			int userId = getLoginUserId();
			//int userId = 347;
			List<Course> mycourseList = cusCouKpointService.getCusCouKpointListByCusId(userId);
			Course courseTemp = courseService.getCourseById(course.getCourseId());
			boolean isshitingable =false;
			Date dateishavebuy = new Date();
			Customer customer24 = customerService.getCustomerById(userId);
            //是否在24小时之内
            if(dateishavebuy.getTime()-customer24.getRegTime().getTime()<86400000){
                isshitingable=true;
            }
			if( isshitingable || mycourseList.contains(courseTemp) ){//如果已购买此课程，则取出数据
				
				//第一次听课任务
				QueryTaskCusCondition queryTaskCusCondition = new QueryTaskCusCondition();
				queryTaskCusCondition.setCusId(userId);
				queryTaskCusCondition.setKeyWord(Task.TASK_KEY_STUDYCOURSE);
				
				TaskCus tc = taskCusService.getTaskCusByKey(queryTaskCusCondition);
				
				if(tc != null && tc.getIsComplete() == 0){//若果未完成则设置完成
					tc.setIsComplete(1);
					taskCusService.updateTaskCus(tc);
				}
				//第一次听课任务结束
				
				bookList = booksService.getBooksListByCourseId(course.getCourseId());
				QueryCusCouKpointCondition queryCusCouKpointCondition = new QueryCusCouKpointCondition();
				
				queryCusCouKpointCondition.setCourseId(course.getCourseId());
				queryCusCouKpointCondition.setCusId(userId);
				queryCusCouKpointCondition.setType("buy");
				
				List<UserKpointDTO> myList = cusCouKpointService.getKpointListByCusIdAndCourseId(queryCusCouKpointCondition);
				
				//更新已经看过的课程状态start 
				try {
				    List<String> watchlist = videoStatisticsService.getUserLearnKpointByCourseId(getCourseId(),userId);
				    for (UserKpointDTO kpointDTO:myList){
	                    boolean tmptb = true;
	                    for(String watchKid:watchlist){
	                        if(tmptb){
	                            if(watchKid.equals(kpointDTO.getId()+"")){
	                                kpointDTO.setIsWatch("1");
	                                tmptb=false;
	                            }
	                        }
	                    }
	                }
                } catch (Exception e) { 
                    logger.error("取得看过的课程错误！",e);
                }
				
				//更新已经看过的课程状态end
				
				Map<String, List<?>> infoResult = new HashMap<String, List<?>>();
				
				infoResult.put("kpointlist", myList);
				infoResult.put("booklist", bookList);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
                String nowTime = sdf.format(customer24.getRegTime());
                List<String> regtime = new ArrayList<String>();
                regtime.add(nowTime);
                infoResult.put("timelist", regtime);
				dto = new UserCourseDTO<List<?>>();
				 
				dto.setType("buy");
				dto.setTreeArray(infoResult);	
			}		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		JSONObject jo = JSONObject.fromObject(dto);
		try {
			Post_outObj(jo.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
    /**
     * 根据学员课程知识点ID更新用户知识点
     * 把看过的知识点更新到表中
     * 参数格式courseId@@KpointId
     * @return
     */
    public void updateUserKpoint(String courseInfo){
        try {
            int tmpus=getLoginUserId();
            if (tmpus!=0){
            courseInfo = courseInfo + "@@" + tmpus;
            videoStatisticsService.updateUserWatchKpoint(courseInfo);
            };
        } catch (Exception e) {
            logger.error("更新看过的课程错误：！"+courseInfo,e);
        } 
    }
	
	/**
	 * 我的课程：下载书籍
	 * 根据书籍的ID下载书籍
	 * @return
	 */
	public String downloadBooks(){
		try{
			
			Books book = booksService.getBooksById(bookId);
			if(book != null){
				this.setDownloadFileName(new String(book.getBkName().getBytes(), "ISO8859-1") + this.getFileType(book.getBkUrl()));
				
				String bkUrl = book.getBkUrl();
				
				bkUrl = URLUtil.encodeURL(bkUrl,"UTF-8");   
				
				URL url = new URL(bkUrl);
				
				HttpURLConnection httpUrl = (HttpURLConnection)url.openConnection();
				
				//连接指定的网络资源
				httpUrl.connect();
				//获取网络输入流
				this.setDownloadStream(new BufferedInputStream(httpUrl.getInputStream()));
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "download2";
	}
	
	
	
	/**
	 * 用户中心 学员中心首页 初始化我购买的课程
	 * 提供的信息为用户课程集合
	 * retult的type为json
	 * @return "json"
	 */
	public String toUserCourseList(){
		try{
			int userId = this.getLoginUserId();
			
			//得到用户课程集合
			courseList = cusCouKpointService.getCusCouKpointListByCusId(userId);
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "toUserCourseList";
	}
	
	
	/**
	 * 进入学员中心我的课程（个人中心）（单个课程）
	 * 提供为课程集合、当前课程信息、课程资料
	 * @return
	 */
	public String toUserCourse(){
		try{
			int userId = getLoginUserId();
			
			//得到用户课程集合
			courseList = cusCouKpointService.getCusCouKpointListByCusId(userId);
			
			course = courseService.getCourseById(course.getCourseId());
			
			if(!courseList.contains(course)){//不存在的情况下不能观看
				return ERROR;
			}
			
			course = courseService.getCourseById(course.getCourseId());//课程
			bookList = booksService.getBooksListByCourseId(course.getCourseId());//课程资料
			
			this.getQueryExampaperRecordCondition().setCusId(userId);// 试卷记录查询条件，设置用户
			queryExampaperRecordCondition.setCourseId(course.getCourseId());// 设置课程

			List<ExamAnalysisDTO> anaylisList = exampaperRecordService.getExampaperAnalysisDTO(queryExampaperRecordCondition);

			if (anaylisList != null) {// 如果存在，则会查出两条数据或一条数据

				examAnalysisDTO = anaylisList.get(0);
				
				float maxaccuryDTO = (float) (Math.round(examAnalysisDTO.getMaxAccuracy() * 1000)) / 10;
				float minaccuryDTO = (float) (Math.round(examAnalysisDTO.getMinAccuracy() * 1000)) / 10;
				
				examAnalysisDTO.setMaxAccuracy(maxaccuryDTO);
				examAnalysisDTO.setMinAccuracy(minaccuryDTO);

				if (anaylisList.size() == 2) {

					ExamAnalysisDTO examAnalysisDTOTemp = anaylisList.get(1);
					
					float maxaccury = (float) (Math.round(examAnalysisDTOTemp.getMaxAccuracy() * 1000)) / 10;
					float minaccury = (float) (Math.round(examAnalysisDTOTemp.getMinAccuracy() * 1000)) / 10;
					
					examAnalysisDTOTemp.setMaxAccuracy(maxaccury);
					examAnalysisDTOTemp.setMinAccuracy(minaccury);
					
					examAnalysisDTO.setExamSum(examAnalysisDTO.getExamSum()
							+ examAnalysisDTOTemp.getExamSum());// 试卷数

					if (examAnalysisDTOTemp.getExamSum() != 0) {// 第二个试卷数为0时，没有可比较性
						if (examAnalysisDTO.getMaxAccuracy() < examAnalysisDTOTemp
								.getMaxAccuracy()) {
							examAnalysisDTO.setMaxAccuracy(examAnalysisDTOTemp
									.getMaxAccuracy());
						}

						if (examAnalysisDTO.getMinAccuracy() > examAnalysisDTOTemp
								.getMinAccuracy()) {
							examAnalysisDTO.setMinAccuracy(examAnalysisDTOTemp
									.getMinAccuracy());
						}
					}
				}

				examAnalysisDTO.setQstSum(exampaperRecordService.getQstNumByCourseId(queryExampaperRecordCondition));// 设置该课程答题数
				examAnalysisDTO.setRightSum(exampaperRecordService.getRightQstNumByCourseId(queryExampaperRecordCondition));// 设置该课程正确答题数

				if (examAnalysisDTO.getQstSum() != 0) {
					float avgtemp = ((float) examAnalysisDTO.getRightSum() / examAnalysisDTO
							.getQstSum()) * 100;

					avgtemp = (float) (Math.round(avgtemp * 10)) / 10;
					examAnalysisDTO.setAvgAccuracy(avgtemp);
				}
				examAnalysisDTO.setAvgScore(examAnalysisDTO.getAvgAccuracy());
			}
			
			this.getQueryNotesCondition().setCourseId(course.getCourseId());
			this.getQueryNotesCondition().setCusId(userId);
			getQueryNotesCondition().setPageSize(8);
			setPage(notesService.getNotesListByCourse(queryNotesCondition));
			this.getPage().setPageSize(8);
			setPageUrlParms();
			
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "toUserCourse";
	}
	
	public String toTryCourse(){
		try{
			course = courseService.getCourseById(17);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "toTryCourse";
	}
	
	//====================================王郑，课程进度所用到的方法=============================
	/**
	 * 课程进度主方法
	 * @return
	 */
	
	public String getCoursePlan(){
		if(this.queryCourseCondition!=null){
			if(this.queryCourseCondition.getSellWayId()!=0){
				this.sellWay=this.sellWayService.getSellWayById(this.queryCourseCondition.getSellWayId());
				this.getQuerySellCouCondition().setSellId(this.queryCourseCondition.getSellWayId());
				List<SellCou> sellCouList = sellCouService.getSellCouList(this.getQuerySellCouCondition());
				for (int i = 0; i < sellCouList.size(); i++) {
					List<CourseDTO> coursePlan = this.courseService.getCoursePlan(sellCouList.get(i).getCourseId()); //得到当前课程所有节点
			        //更新已经看过的课程状态start 
		            if (getLoginUserId()!=0){
		                try {
		                List<String> watchlist = videoStatisticsService.getUserLearnKpointByCourseId(sellCouList.get(i).getCourseId(),getLoginUserId());
		                
		                for (CourseDTO courseDTO:coursePlan){
		                    boolean tmptb = true;
		                    for(String watchKid:watchlist){
		                        if(tmptb){
		                            if(watchKid.equals(courseDTO.getPointId()+"")){
		                                courseDTO.setIswatch("1");
		                                tmptb=false;
		                            }
		                        }
		                    }
		                }
		                } catch (Exception e) { 
		                    logger.error("iphone取得看过的课程错误！",e);
		                }
		            }
		           
		        
		        //更新已经看过的课程状态end
					getCoursePlanList(coursePlan);  //调用章节处理方法
				}
			}
		}
		return "coursePlan";
	}
	
	/**
	 * 处理课程进度集合  节点显示方式，即（章，节，知识点，） 没有数据则有无代替
	 */
	private void getCoursePlanList(List<CourseDTO> courseDtoList){
        
		for (int i = 0; i < courseDtoList.size(); i++) {  //遍历传进来的  课程节点集合
			String[] str = getJName(courseDtoList,courseDtoList.get(i).getPId());  //调用节处理方法，传进当前子节点 pid
			courseDtoList.get(i).setJName(str[0]);
			courseDtoList.get(i).setZName(str[1]);
			this.getCouDtoList().add(courseDtoList.get(i));
			
		}
		if(this.getCouDtoList()!=null){
			for (int i = 0; i < this.getCouDtoList().size(); i++) {
				if(this.getCouDtoList().get(i).getLeaf()!=1 || this.getCouDtoList().get(i).getPointName().indexOf("上传")!=-1){
					this.getCouDtoList().remove(i);
					i=0;
				}
			}
		}
	}
	
	/**
	 * 循环遍历  根据 根据传进来当前节点的上级节点PID  得到上级节点名字
	 * @param courseDtoList
	 * @param pId
	 * @return
	 */
	private String[] getJName(List<CourseDTO> courseDtoList,int pId){
		String[] str={"无","无"};  //创建  节，章  数组
		for (int i = 0; i < courseDtoList.size(); i++) {  //遍历所有课程节点，如果招到节点与当前节点相同对象，又不是根节点 （即 节 这一级别）
			if(courseDtoList.get(i).getPointId()==pId){
				if(courseDtoList.get(i).getPId()!=-2){
					str[0]=courseDtoList.get(i).getPointName();  //则向数组中 添加节名称
					str[1]=getZName(courseDtoList, courseDtoList.get(i).getPId());  //如果当前节 的 pid  不是跟节点则继续向上查询
				}else {
					str[1]=getZName(courseDtoList, pId);  //然后再把 当前节的 pid 传到 章处理方法中
				}
			}
		}
		return str;
	}
	
	/**
	 * 根据当前节点级别   得到 上级节点的名称
	 * @param courseDtoList
	 * @param pId
	 * @return
	 */
	private String getZName(List<CourseDTO> courseDtoList,int pId){  //参数为当前课程所有节点， 节这一级别的  pid 也就是 上一级pointId
		String str="无";
		for (int i = 0; i < courseDtoList.size(); i++) {
			if(courseDtoList.get(i).getPointId()==pId &&courseDtoList.get(i).getPId()==-2){  //遍历所有节点 如果找到 与当前pid相同的pointId，节点级别又是根节点
				str=courseDtoList.get(i).getPointName(); //则添加 当前章的名字，跳出循环
				break;
			}
		}
		return str;
	}
	
	//====================================王郑，课程进度所用到的方法=============================
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}


	public QueryCourseCondition getQueryCourseCondition() {
		if(this.queryCourseCondition == null){
			this.queryCourseCondition = new QueryCourseCondition();
		}
		return queryCourseCondition;
	}

	public void setQueryCourseCondition(QueryCourseCondition queryCourseCondition) {
		this.queryCourseCondition = queryCourseCondition;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	
	public ICoursepic getCoursepicService() {
		return coursepicService;
	}

	public void setCoursepicService(ICoursepic coursepicService) {
		this.coursepicService = coursepicService;
	}

	public IHistoryPrice getHistoryPriceService() {
		return historyPriceService;
	}

	public void setHistoryPriceService(IHistoryPrice historyPriceService) {
		this.historyPriceService = historyPriceService;
	}

	public int getCourseSortSize() {
		return courseSortSize;
	}

	public void setCourseSortSize(int courseSortSize) {
		this.courseSortSize = courseSortSize;
	}

	public ITjcourse getTjcourseService() {
		return tjcourseService;
	}

	public void setTjcourseService(ITjcourse tjcourseService) {
		this.tjcourseService = tjcourseService;
	}

	public List<Course> getTjcourseList() {
		return tjcourseList;
	}

	public void setTjcourseList(List<Course> tjcourseList) {
		this.tjcourseList = tjcourseList;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ICusCouKpoint getCusCouKpointService() {
		return cusCouKpointService;
	}

	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}

	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	public List<KeyValueDTO> getKpointList() {
		return kpointList;
	}

	public void setKpointList(List<KeyValueDTO> kpointList) {
		this.kpointList = kpointList;
	}

	public String getSeduListeningCourses() {
		return seduListeningCourses;
	}

	public void setSeduListeningCourses(String seduListeningCourses) {
		this.seduListeningCourses = seduListeningCourses;
	}

	public void setGmrecordService(IGmrecord gmrecordService) {
		this.gmrecordService = gmrecordService;
	}

	public CouseResultDTO getCourseReult() {
		return courseReult;
	}

	public void setCourseReult(CouseResultDTO courseReult) {
		this.courseReult = courseReult;
	}

	public IBooks getBooksService() {
		return booksService;
	}

	public void setBooksService(IBooks booksService) {
		this.booksService = booksService;
	}

	public List<Books> getBookList() {
		return bookList;
	}

	public void setBookList(List<Books> bookList) {
		this.bookList = bookList;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
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

	public int getCusCouKpointId() {
		return cusCouKpointId;
	}

	public void setCusCouKpointId(int cusCouKpointId) {
		this.cusCouKpointId = cusCouKpointId;
	}

	public List<CourseSortListDTO> getCourseSortListDTOList() {
		return courseSortListDTOList;
	}

	public void setCourseSortListDTOList(
			List<CourseSortListDTO> courseSortListDTOList) {
		this.courseSortListDTOList = courseSortListDTOList;
	}

	public IExampaper getExampaperService() {
		return exampaperService;
	}

	public void setExampaperService(IExampaper exampaperService) {
		this.exampaperService = exampaperService;
	}

	public IExampaperRecord getExampaperRecordService() {
		return exampaperRecordService;
	}

	public void setExampaperRecordService(IExampaperRecord exampaperRecordService) {
		this.exampaperRecordService = exampaperRecordService;
	}

	public ExamAnalysisDTO getExamAnalysisDTO() {
		return examAnalysisDTO;
	}

	public void setExamAnalysisDTO(ExamAnalysisDTO examAnalysisDTO) {
		this.examAnalysisDTO = examAnalysisDTO;
	}

	public QueryExampaperRecordCondition getQueryExampaperRecordCondition() {
		if (queryExampaperRecordCondition == null) {
			queryExampaperRecordCondition = new QueryExampaperRecordCondition();
		}
		return queryExampaperRecordCondition;
	}

	public void setQueryExampaperRecordCondition(
			QueryExampaperRecordCondition queryExampaperRecordCondition) {
		this.queryExampaperRecordCondition = queryExampaperRecordCondition;
	}
	public QueryKpointCondition getQueryKpointCondition() {
		if(queryKpointCondition == null){
			queryKpointCondition = new QueryKpointCondition();
		}
		return queryKpointCondition;
	}

	public void setQueryKpointCondition(QueryKpointCondition queryKpointCondition) {
		this.queryKpointCondition = queryKpointCondition;
	}

	public INotes getNotesService() {
		return notesService;
	}

	public void setNotesService(INotes notesService) {
		this.notesService = notesService;
	}
	public QueryNotesCondition getQueryNotesCondition() {
		if(queryNotesCondition == null) {
			queryNotesCondition = new QueryNotesCondition();
		}
		return queryNotesCondition;
	}

	public void setQueryNotesCondition(QueryNotesCondition queryNotesCondition) {
		this.queryNotesCondition = queryNotesCondition;
	}

	public List<Notes> getNotesList() {
		return notesList;
	}

	public void setNotesList(List<Notes> notesList) {
		this.notesList = notesList;
	}



	public String getTid() {
		return tid;
	}



	public void setTid(String tid) {
		this.tid = tid;
	}



	public String getStyleid() {
		return styleid;
	}



	public void setStyleid(String styleid) {
		this.styleid = styleid;
	}



	public void setTaskCusService(ITaskCus taskCusService) {
		this.taskCusService = taskCusService;
	}



	public List<UserCenterCourseDTO> getUserCourseList() {
		return userCourseList;
	}



	public void setUserCourseList(List<UserCenterCourseDTO> userCourseList) {
		this.userCourseList = userCourseList;
	}



	public List<UserCenterCourseDTO> getUserUnReadCourseList() {
		return userUnReadCourseList;
	}



	public void setUserUnReadCourseList(List<UserCenterCourseDTO> userUnReadCourseList) {
		this.userUnReadCourseList = userUnReadCourseList;
	}



	public Kpoint getKpoint() {
		return kpoint;
	}



	public void setKpoint(Kpoint kpoint) {
		this.kpoint = kpoint;
	}



	public List<Subject> getSubjectList() {
		return subjectList;
	}



	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	
	
	
	/**
	 * @author fanxin
	 * 功能：生成360所需的xml
	 * @param 
	 * @return String
	 */
	
	public String getAllcourseInfor(){
		List<Course> list = courseService.getWebCourseList(queryCourseCondition);
		
		try{
			//获取当前时间 （注意格式）
			SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd" + " " + "hh:mm:ss");
			String datetime = tempDate.format(new java.util.Date());

			/*String currpath = System.getProperty("user.dir");
			System.out.println(currpath);
			String path = currpath.replace("bin", "webapps");
			System.out.println(path);
			String truePath = path.replaceAll("\\\\", "/");
			System.out.println(truePath);
			FileOutputStream fo = new FileOutputStream(truePath+"/sedu/static/apixml/infor.xml");*/
			
			FileOutputStream fo = new FileOutputStream("D:/Program Files/work space/sedu/WebRoot/static/apixml/courseinf.xml");
			
	  		PrintStream so = new PrintStream(fo,true,"utf-8");
			so.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			so.println("<root>");
			so.println("<version>1.0</version>");
			so.println("<datetime>"+datetime+"</datetime>");
			so.println("<company_id>尚德机构</company_id>");
			so.println("<items>");
			
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					Course c = list.get(i);
					
					so.println("<item>");
					so.println("<outer_id>"+c.getCourseId()+"</outer_id>");
					so.println("<title>"+c.getTitle()+"</title>");
					so.println("<hours>"+c.getLessionTime()+"</hours>");
					
					String url = null;
					String desc = null;
					String tg = "";
					if(c.getSubjectId()==1){     //人力资源管理师
						url = "http://highso.org.cn/rl";
						desc = "本课程针对2011年5月全国人力资源师考试，全国通用！课程根据尚德机构人力资源师最高端班型录制，通过率75%以上,课程有效期至：2011年12月30日，可反复听反复学，通过率更高。在有效期之前听课次数不限。";
						tg = "人力资源师-";
					}else if(c.getSubjectId()==3){    //会计资格证
						url = "http://highso.org.cn/kjz";
						desc = "本套课程通过精准的考试要点分析，打造完善的知识体系，在保证过关的同时，更有对会计从业的实战培训。在有效期之前听课次数不限。本课程针对北京会计从业资格证考试。";
						tg = "会计资格证-";
					}else if(c.getSubjectId()==5){    //司法考试
						url = "http://highso.org.cn/sf";
						desc = "2套课程均针对2011年司法考试全国统考！尚德机构力邀18位司法考试辅导大牌名师精心录制，不限听课次数、通过率达到60%以上。大牌老师最新2011年的全套讲义、课件可免费下载 ";
						tg = "司法考试-";
					}else if(c.getSubjectId()==7){   //注册会计师
						url = "http://highso.org.cn/cpa";
						desc = "本课程针对2011年注册会计师全国统考,购买全科课程有效期至2012年10月31日止，在有效期之前听课次数不限。即使是零基础，也能用2年的时间通过考试。通过精准的考试要点分析，打造专门针对考试的知识体系，而且将每一个知识点都讲到透彻。";
						tg = "CPA-";
					}else if(c.getSubjectId()==8){    //证券从业
						url = "http://highso.org.cn/zq";
						desc = "本课程针对2011年考试，与尚德证券最高端班型课程设置完全一样，通过精准的考试要点分析，详细地讲解要点，全真题库，解析一次通过考试完全不成问题，在有效期之前听课次数不限。";
						tg = "证券从业-";
					}else if(c.getSubjectId()==9){	   //建造师考试课程
						url = "http://highso.org.cn/jz";
						desc = "本套课程针对2011年一级建造师考试，通过精准的考试要点分析，在保证过关的同时，更有对一级建造师的实战培训。在有效期之前听课次数不限。";
						tg = "建造师-";
					}else if(c.getSubjectId()==10){		//高级会计师
						url = "http://highso.org.cn/gk";
						desc = "本课程针对2011年高会考试全国统考！力邀8位高会大牌名师精心录制，可反复听反复看。通过率达90%以上";
						tg = "高级会计师-";
					}else if(c.getSubjectId()==11){		//公务员
						url = "http://highso.org.cn/gwy";
						desc = "本课程针对2012年中央国家机关招录公务员考试！力邀16位国考大牌名师精心录制，可反复听反复看。";
						tg = "公务员-";
					}
					so.println("<listen_url>"+url+"</listen_url>");
					so.println("<price>"+c.getOldPrice()+"</price>");
					so.println("<newprice>"+c.getPrice()+"</newprice>");
					so.println("<desc>"+desc+"</desc>");
					so.println("<detail_url>"+url+"</detail_url>");
					so.println("<regi_url>"+url+"</regi_url>");
					
					String Addtime = tempDate.format(c.getAddtime());
					so.println("<update>"+Addtime+"</update>");
					
					String tag[] =  c.getIntroduce().split("-");
					String tags = tag[0]+"-"+tag[1];
					so.println("<tags>"+tg+tags+"</tags>");
					so.println("</item>");
				}
			}
			
		 	so.println("</items>");
		 	so.println("</root>");
		 	
		 	System.out.println("文件生成成功！");
		 	
	}catch(Exception e){
		e.printStackTrace();
		System.out.println("文件生成失败！");
	}
		return "fan";
	}
	
	

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
    
    /**
     *  指定要被下载的文件路径
     */
    private String inputPath;
	
	/**
	 * 用户中心实现文件下载
	 * @author baiang.zhao . 2011-5-10 17:32:49
	 * @return
	 */
	public String toDownloadFile() {
		// 得到完整的下载路径
		// String realDownloadPath = ServletActionContext.getServletContext().getRealPath(inputPath);
		String realDownloadPath = this.inputPath;
		// 文件输出
		downloadFile(realDownloadPath , ServletActionContext.getResponse());
		
		return "downloadFile" ;
	}

	/**
	 * 文件输出
	 * @author baiang.zhao. 2011-5-11 11:44:46
	 * @param filePath:文件完整路径
	 * @param response:HttpServletResponse对象
	 */
	public void downloadFile(String filePath, HttpServletResponse response) {
		String fileName = ""; // 文件名，输出到用户的下载对话框
		// 从文件完整路径中提取文件名，并进行编码转换，防止不能正确显示中文名
		try {
			if (filePath.lastIndexOf("/") > 0) {
				fileName = new String(filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length()).getBytes("GB2312"), "ISO8859_1");
			} else if (filePath.lastIndexOf("\\") > 0) {
				fileName = new String(filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length()).getBytes("GB2312"), "ISO8859_1");
			}
		} catch (Exception e) {
			
		}
		// 清理缓存
		response.reset();
		// 打开指定文件的流信息
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 设置响应头和保存文件名
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		// 写出流信息
		int b = 0;
		PrintWriter out = null ;
		try {
			out = response.getWriter();
			while ((b = fs.read()) != -1) {
				out.write(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fs != null ) {
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				out.close();
			}
		}
	}
	
	/*-----宁肖修改-----------------发送流的方式-------------------------------------------------*/
    /**
    * 将封装好的JSON数据以流的形式发送到客户端
    */
	public void Post_outObj(String json) throws Exception{
    	try {
    	getServletResponse().setContentType("text/html;charset=UTF-8");
		PrintWriter out = getServletResponse().getWriter();//获取页面输出流
		out.println(json.toString());
		out.flush();
		out.close();
    	} catch (Exception e) {
		}
    }
	
	public int sellWayId;
	
	
	
	public int getSellWayId() {
		return sellWayId;
	}

	public void setSellWayId(int sellWayId) {
		this.sellWayId = sellWayId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
	public List<SellWay> getButSellWayList() {
		return butSellWayList;
	}

	public void setButSellWayList(List<SellWay> butSellWayList) {
		this.butSellWayList = butSellWayList;
	}

	public List<CourseDTO> getCouDtoList() {
		if(this.couDtoList==null){
			this.couDtoList = new ArrayList<CourseDTO>();
		}
		
		return couDtoList;
	}

	public void setCouDtoList(List<CourseDTO> couDtoList) {
		this.couDtoList = couDtoList;
	}
	
	public List<UserCenterCourseDTO> getTemplist() {
		return templist;
	}

	public void setTemplist(List<UserCenterCourseDTO> templist) {
		this.templist = templist;
	}
	
	public List<UserCenterSubjectCourseDTO> getSellWayCourseList() {
		return sellWayCourseList;
	}

	public void setSellWayCourseList(
			List<UserCenterSubjectCourseDTO> sellWayCourseList) {
		this.sellWayCourseList = sellWayCourseList;
	}

	public SellWay getSellWay() {
		if(this.sellWay==null){
			this.sellWay = new SellWay();
		}
		return sellWay;
	}

	public void setSellWay(SellWay sellWay) {
		this.sellWay = sellWay;
	}
	
}
