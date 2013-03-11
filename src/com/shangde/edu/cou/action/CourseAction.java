package com.shangde.edu.cou.action;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.common.service.ConfigService;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.FileExportImportUtil;
import com.shangde.common.util.HTMLDecoder;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCouFreeGiveCondition;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.condition.QueryCoursepicCondition;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.condition.QueryHistoryPriceCondition;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.condition.QuerySellWayCondition;
import com.shangde.edu.cou.condition.QueryTeacherCondition;
import com.shangde.edu.cou.domain.CouFreeGive;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Coursepic;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.domain.Gmrecord;
import com.shangde.edu.cou.domain.HistoryPrice;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.domain.SellCou;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cou.dto.KeyValueDTO;
import com.shangde.edu.cou.service.ICouFreeGive;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursepic;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.IGmrecord;
import com.shangde.edu.cou.service.IHistoryPrice;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cou.service.ISellCou;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cou.service.ITeacher;
import com.shangde.edu.cou.service.ITjcourse;
import com.shangde.edu.cus.domain.Area;
import com.shangde.edu.cusmgr.condition.QueryCusCouKpointCondition;
import com.shangde.edu.cusmgr.domain.CusCouKpoint;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.dto.WaybillDTO;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.sys.action.BackLoginAction;
import com.shangde.edu.sys.domain.Grade;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.IGrade;
import com.shangde.edu.sys.service.ISubject;


/**
 * 课程
 * @author chenshuai
 */
public class CourseAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(CourseAction.class);
	
	/**
	 * 课程
	 */
	private Course course;
	
	/**
	 * 课程服务
	 */
	private ICourse courseService;
	
	/**
	 * 课程图片服务
	 */
	private ICoursepic coursepicService;
	
	/**
	 * 历史价格
	 */
	private IHistoryPrice  historyPriceService;
	
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService; 
	
	/**
	 * 推荐课程服务
	 */
	private ITjcourse tjcourseService;
	
	/**
	 * 课程附属图片
	 */
	private List<File> otherPics = new ArrayList<File>();
	
	private List<String> otherPicsFileName = new ArrayList<String>();
	
	/**
	 * 课程首图片
	 */
	private List<File> firstPic = new ArrayList<File>();
	
	private List<String> firstPicFileName = new ArrayList<String>();
	
	/**
	 * 课程已添加的课程附属图片
	 */
	private List<File> otherPicEd = new ArrayList<File>();
	
	private List<String> otherPicEdFileName = new ArrayList<String>();
	
	/**
	 * 配置服务
	 */
	private ConfigService configurator;
	

	/**
	 *推荐模式
	 */
	private Map<Integer, String> courseMode;
	
	/**
	 *课程分类集合 
	 */
	private List<Coursesort> courseSortList ;
	
	
	/**
	 *课程查询条件 
	 */
	private QueryCourseCondition queryCourseCondition;
	
	/**
	 * 查询条件
	 */
	private String searchKey;
	
	/**
	 * 课程分类集合Map
	 */
	private Map<Integer,String> coursesortMap;
	
	/**
	 * 课程集合
	 */
	private List<Course> courseList = new ArrayList<Course>();
	
	/**
	 * 分类Id
	 */
	private int sortId;//分类ID
	
	private IGrade gradeService;//年级服务
	
	private ISubject subjectService;//学科服务
	
	private ITeacher teacherService; //教师服务
	
	private List<Grade> gradeList;//年级集合
	
	private List<Subject> subjectList;//学科集合
	
	private List<Coursesort> allcoursesortList;//所有课程集合
	
	private List<Teacher> teacherList;//教师集合
	
	private int[] courseIds;//课程集合
	
	private List<Coursepic> mainpicList;//主图片集合
	
	private List<Coursepic> otherpicList;//附属图片集合
	
	private int[] pic;//图片ids
	
	private List<Course> tjcourseList;
	
	private ISellWay sellWayService;
	private ISellCou sellCouService;
	private List<SellWay> sellWayList=new ArrayList<SellWay>();
	private QuerySellWayCondition querySellWayCondition;
	private SellWay sellWay;
	private List<SellCou> sellCouList=new ArrayList<SellCou>();
	private QuerySellCouCondition querySellCouCondition;
	
	private String exportName;
	private InputStream excelFile;
	public String getExportName() {
		return exportName;
	}

	public void setExportName(String exportName) {
		this.exportName = exportName;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	/**
	 * 课程数量
	 */
	private int courseSortSize;
	
	/**
	 * 主图片ID
	 */
	private int mainpicid;
	/**
	 * 注册用户ID
	 */
	private int cusId;
	/**
	 * 声明流水服务
	 */
	private ICashRecord cashRecordService;
	/**
	 * 购买记录服务
	 */
	private IGmrecord gmrecordService;
	/**
	 * 知识点查询条件
	 */
	private QueryKpointCondition queryKpointCondition;
	/**
	 * 服务
	 */
	private ICusCouKpoint  cusCouKpointService;
	/**
	 * 知识点服务
	 */
	private IKpoint kpointService;
	/**
	 * 声明订单服务
	 */
	private IContract contractService;
	
	private List<File> servicePic = new ArrayList();
	
	private List<String> servicePicFileName;//服务图片文件名称
	
	private List<Course> course1List=new ArrayList<Course>();
	
	private List<HistoryPrice> historyPriceList;//历史价格
	
	private int fuSortId = -2;
	private int suSortId;
	private int tuSortId;
	
	private String fuSortIdString;
	private String suSortIdString;
	private String tuSortIdString;
	
	private int subjectId;
	private String saveSuccess;//保存成功表示
	private String pjsortname;//	课程列表 所属课程分类拼接
	
	private int uploadStatus;// 上传状态
	private String addStartDate;// 添加开始日期
	private String addEndDate;// 添加结束日期
	private String updateStartDate;// 最新更新开始日期
	private String updateEndDate;// 最新更新结束日期


	public int getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(int uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public String getAddStartDate() {
		return addStartDate;
	}

	public void setAddStartDate(String addStartDate) {
		this.addStartDate = addStartDate;
	}

	public String getAddEndDate() {
		return addEndDate;
	}

	public void setAddEndDate(String addEndDate) {
		this.addEndDate = addEndDate;
	}

	public String getUpdateStartDate() {
		return updateStartDate;
	}

	public void setUpdateStartDate(String updateStartDate) {
		this.updateStartDate = updateStartDate;
	}

	public String getUpdateEndDate() {
		return updateEndDate;
	}

	public void setUpdateEndDate(String updateEndDate) {
		this.updateEndDate = updateEndDate;
	}

	public String getCourseListBySubjectId()
	{
		try{
			
			if(queryCourseCondition.getSubjectId()==0)
			{
				setResult(new Result<List<Course>>(false, "success", "", null));
				return "";
			}		
			courseList = courseService.getCourseList(queryCourseCondition);
			Map<String, String> tempCourseMap = null;
			List<Map<String, String>> newCourseList=new ArrayList<Map<String,String>>();
			for(int i=0;i<courseList.size();i++)
			{
				tempCourseMap = new HashMap<String, String>();
				tempCourseMap.put("courseId", courseList.get(i).getCourseId()+"");			
				tempCourseMap.put("courseName", courseList.get(i).getTitle());
				tempCourseMap.put("teacherName", courseList.get(i).getTeacherName());
				newCourseList.add(tempCourseMap);
			}
			setResult(new Result<List<Map<String,String>>>(false, "success", "", newCourseList));
			return "json";
		}catch(Exception e){
			logger.error("CourseAction.getCourseListBySubjectId", e);
			setResult(new Result(false, "error", null, null));
			return "json";
		}
		
	}
	
	/**
	 *  添加课程
	 *  进入课程添加页面
	 *  @return String 
	 */
	public String toAddCourse() {
		try{
			servletRequest.getSession().removeAttribute("article");//移除article,保证其他模块fck上传文件好使
			gradeList = gradeService.getGradeListByStatus(Grade.GRADE_DEFAULT_STATUS);
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			teacherList = teacherService.getTeacherList(new QueryTeacherCondition());

			courseMode = configurator.getCourseMode();
			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
		}catch(Exception ex){
			logger.error("CourseAction.toAddCourse", ex);
			return ERROR;
		}
		return "toUpdateCourse";
	}
	
	/**
	 * 批量冻结课程
	 * @return
	 */
	public String freezeAllCourse(){
		try{
			if(courseIds != null){
				Course temp = null;
				for(int i = 0; i <courseIds.length ; i++){
					
					temp = courseService.getCourseById(courseIds[i]);
					temp.setStatus(Course.COURSE_FREEZE_STATUS);
					courseService.updateCourse(temp);
				}
			}
		}catch(Exception ex){
			logger.error("CourseAction.freezeAllCourse", ex);
			return ERROR;
		}
		
		return "freezeAllCourse";
	}
	
	/**
	 * 按条件查询课程
	 * 条件：关键字、课程分类
	 * @return String
	 */
	public String listCoursesBySortId() {
		try{
			
			if(searchKey != null && !searchKey.trim().equals("")){
					searchKey = URLDecoder.decode(searchKey,"UTF-8");//将这类代码转换成中文
					this.getQueryCourseCondition().setSearchKey(searchKey);
			}
			
			if(course.getSortId()!=0){
			this.getQueryCourseCondition().setSortId(course.getSortId());
			}
			/**
			 * 课程添加时间
			 */
			this.getQueryCourseCondition().setAddStartDate(
					this.strToDate(addStartDate, "yyyy-MM-dd"));

			this.getQueryCourseCondition().setAddEndDate(
					this.strToDate(addEndDate, "yyyy-MM-dd"));
			/**
			 * 课程最新更新时间
			 */
			this.getQueryCourseCondition().setUpdateStartDate(
					this.strToDate(updateStartDate, "yyyy-MM-dd"));
			this.getQueryCourseCondition().setUpdateEndDate(
					this.strToDate(updateEndDate, "yyyy-MM-dd"));
			/**
			 * 上传状态
			 */
			this.getQueryCourseCondition().setUploadStatus(uploadStatus);
			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
			
			//获取课程所属分类
			Coursesort fSort = null;
			Coursesort sSort = null;
			Coursesort tSort = null;
			pjsortname="";
			if(course != null){
				if(course.getSortId() != 0){
					tSort = coursesortService.getCoursesortById(course.getSortId());
				}
				getQueryCourseCondition().setSortId(course.getSortId());
			}
			
			if(tSort != null && tSort.getPId() != 0){
				sSort = coursesortService.getCoursesortById(tSort.getPId());
			}
			
			if(sSort!= null && sSort.getPId() != 0){
				fSort = coursesortService.getCoursesortById(sSort.getPId());
			}
			
			if(fSort != null){
				this.getCoursesortMap().put(fSort.getCoursesortId(), fSort.getCoursesortName());
				pjsortname +=fSort.getCoursesortName()+"--";
			}
			
			if(sSort != null){
				this.getCoursesortMap().put(sSort.getCoursesortId(), sSort.getCoursesortName());
				pjsortname +=sSort.getCoursesortName()+"--";
			}
			
			if(tSort != null){
				this.getCoursesortMap().put(tSort.getCoursesortId(), tSort.getCoursesortName());
				pjsortname +=tSort.getCoursesortName();//拼接的课程分类名称
				
			}
			getQueryCourseCondition().setPageSize(20);
			PageResult pr = courseService.getNomalCourseListBySortId(getQueryCourseCondition());
			List<Course> prList = pr.getPageResult();
			for(int i=0;i<prList.size();i++){
				prList.get(i).setUpRate(Tool.divide(prList.get(i).getRealUpNum(), prList.get(i).getLessionTime(), 2));
			}
			pr.setPageResult(prList);
			setPage(pr);
			this.getPage().setPageSize(20);
			setPageUrlParms();
		}catch(Exception ex){
			logger.error("CourseAction.listCoursesBySortId", ex);
			return ERROR;
		}
		
		return "listCoursesBySortId";
	}
	/**
	 * 
	 * @Title: strToDate 
	 * @Description: TODO(日期转化方法) 
	 * @param @param dateStr
	 * @param @param format
	 * @param @return    设定文件 
	 * @return Date    返回类型
	 * @author shixiaofeng@sunland.org.cn 
	 * @throws
	 */
	private Date strToDate(String dateStr, String format) {
		if (dateStr == null || "".equals(dateStr) || format == null
				|| "".equals(format)) {
			return null;
		} else {
			try {
				Date date = DateUtil.toDate(dateStr, format);
				return date;
			} catch (Exception e) {
				return new Date();
			}
		}
	}
	
	/**
	 * 按条件查询课程
	 * 条件：关键字、课程分类---------------------------------------查询售卖方式列表
	 * @return String
	 */
	public String listCoursesFree() {
		try{
			
			if(searchKey != null && !searchKey.trim().equals("")){
				if(searchKey.indexOf("&#") > -1){
					searchKey = HTMLDecoder.decode(searchKey);//将这类代码转换成中文
				}
			}
			
			sellWayList=this.getSellWayService().getSellWayList(querySellWayCondition);
		}catch(Exception ex){
			logger.error("CourseAction.listCoursesFree", ex);
			return ERROR;
		}
		
		return "listCoursesFree";
	}
	/**
	 * 内部学员免费赠送课程
	 * @return String
	 */
	public String listCourses1Free() {
		try{
			this.getQueryCourseCondition().setStatus(3);
			course1List=this.courseService.getCourseListByStatus(getQueryCourseCondition());
		}catch(Exception ex){
			logger.error("CourseAction.listCourses1Free", ex);
			return ERROR;
		}
		
		return "listCourses1Free";
	}
	
	private ICouFreeGive  couFreeGiveService;
	
	private QueryCouFreeGiveCondition  quCoFrGiCondition;
	
	private List<CouFreeGive>  couFrGiList;	

	/**
	 * fanxin 
	 * 免费赠送列表
	 */
	public String getCouFreeGiveList(){
		try{
			
			couFrGiList = couFreeGiveService.getCouFreeGiveList(this.getQuCoFrGiCondition());
			
			return "getCouFreeGiveList";
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getCouFreeGiveList方法出错！");
			return "error";
		}
	}
	
	/**
	 * fanxin  分页获取
	 * 免费赠送列表
	 */
	public String getCouFreeGiveByPage(){
		try{
			String email=getQuCoFrGiCondition().getEmail();
			String userName=getQuCoFrGiCondition().getUserName();
			if(email!=null&&!"".equals(email)){
				getQuCoFrGiCondition().setEmail(email.trim());
			}
			if(userName!=null&&!"".equals(userName)){
				getQuCoFrGiCondition().setUserName(userName.trim());
			}
			couFrGiList = couFreeGiveService.getCouFreeGiveList(this.getQuCoFrGiCondition());
			getQuCoFrGiCondition().setPageSize(20);
			setPage(couFreeGiveService.getCouFreeGiveByCondition(this.getQuCoFrGiCondition()));
			// 需要再设置一次分页
			getPage().setPageSize(20);
			setPageUrlParms();
			
			return "getCouFreeGiveList";
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getCouFreeGiveByPage方法出错！");
			return "error";
		}
	}
	
	
	
	/**
	 * @return the couFrGiList
	 */
	public List<CouFreeGive> getCouFrGiList() {
		return couFrGiList;
	}

	/**
	 * @param couFrGiList the couFrGiList to set
	 */
	public void setCouFrGiList(List<CouFreeGive> couFrGiList) {
		this.couFrGiList = couFrGiList;
	}

	/**
	 * @return the quCoFrGiCondition
	 */
	public QueryCouFreeGiveCondition getQuCoFrGiCondition() {
		if(this.quCoFrGiCondition == null){
			this.quCoFrGiCondition = new QueryCouFreeGiveCondition();
		}
		return quCoFrGiCondition;
	}
	
	/**
	 * @param quCoFrGiCondition the quCoFrGiCondition to set
	 */
	public void setQuCoFrGiCondition(QueryCouFreeGiveCondition quCoFrGiCondition) {
		this.quCoFrGiCondition = quCoFrGiCondition;
	}



	/**
	 * 
	 * 免费赠送课程添加流水和订单中
	 * @return String
	 */
	public String freeAdmin() {
		try{
			Contract contract=new Contract();
			
			
			Date date=new Date();
			Long cId=date.getTime();
			//添加订单表
			contract.setContractId(cusId+cId.toString());
			contract.setCusId(cusId);
			contract.setCreateTime(date);
			contract.setContractFrom("后台用户");
			contract.setPayType(0);
			contract.setStatus(4);//4代表修复或赠送
			
			contract.setContractCutSumMoney(0.0);//减去优惠券应付的价格，折后总金额。
			contract.setContractSumMoney(0.0);//应付总价，总金额。
			contract.setCouponMoney(0.0);
			contract.setUseCoupon(0);//0代表没有使用 1代表使用
			int ctId=this.contractService.addContract(contract);
			//记录流水
			SellWay sellWay2 =   sellWayService.getSellWayById(sellWay.getSellId());
            CashRecord cashRecord=new CashRecord();
            cashRecord.setCrInfo("购买《"+sellWay2.getSellName()+"》");
            cashRecord.setPrice(sellWay2.getSellPrice());
            cashRecord.setContractId(cusId+cId.toString());
            cashRecord.setCreateTime(date);
            cashRecord.setCusId(cusId);
            cashRecord.setCourseId(0);
            cashRecord.setType(3);//2代表后台赠送 3代表后台修复
            cashRecord.setCtId(ctId);//把订单id记录到流水表中
            cashRecord.setStatus(1);
            //-------------流水升级新增数据-------王郑 start-------
            cashRecord.setPackId(sellWay.getSellId());
            
            //杨宁修改赠送时学员统计bug
            cashRecord.setCrSubjectId(sellWay2.getSubjectId());
            cashRecord.setCashRecordPrice(0);
            cashRecord.setShopPayType(0);
            cashRecord.setShopPayTime(date);
            cashRecord.setShopStatus(1); //设置流水商品状态 0:未激活/1:已激活/2:已延期/3:已关闭
            
            cashRecord.setShopType(1);   //设置商品的类型  1为课程 2为书籍
            
            cashRecord.setReliefPrice(0); //特别减免，暂时没用到，设置初始值0

            cashRecord.setCouponMoney(0);
            
            //-------------流水升级新增数据-------王郑 end-------
            
            this.cashRecordService.addCashRecord(cashRecord);
            
            
            //范昕  添加赠课记录
            CouFreeGive couFreeGive = new CouFreeGive();
            couFreeGive.setCusId(cusId);
            couFreeGive.setCtId(cusId+cId.toString());
            User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
            couFreeGive.setUserName(users.getLoginName());
            couFreeGive.setCreateTime(date);
            
            couFreeGiveService.addCouFreeGive(couFreeGive);
			
		}catch(Exception ex){
			logger.error("CourseAction.freeAdmin", ex);
			return ERROR;
		}
		
		return "changeSuccess";
	}
	/**
	 * 
	 * 免费赠送课程添加流水和订单中(内部的赠送)已经不用了
	 * @return String
	 */
	public String freeAdmin1() {
		try{
//			if(courseIds!=null){
//				for(int i=0;i<courseIds.length;i++){
//					course=this.courseService.getCourseById(courseIds[i]);
//					//删除历史课程知识树记录
//					QueryCusCouKpointCondition cckc = new QueryCusCouKpointCondition();
//					cckc.setCourseId(courseIds[i]);
//					cckc.setCusId(cusId);
//					List<CusCouKpoint> cckcList = this.cusCouKpointService.getCusCouKpointList(cckc);
//					for(int j = 0 ; j < cckcList.size() ; j++){
//						this.cusCouKpointService.delCusCouKpointById(cckcList.get(j).getId());
//					}
//					//初始化学员购买课程的知识树
//					CusCouKpoint cck =null;
//					this.getQueryKpointCondition().setCourseId(courseIds[i]);
//					List<Kpoint> kpList = this.kpointService.getKpointListByCourseId(getQueryKpointCondition());
//					for(int k=0;kpList!=null&&k<kpList.size();k++){
//						cck= new CusCouKpoint();
//						cck.setCourseId(courseIds[i]);
//						cck.setCusId(cusId);
//						cck.setPointId(kpList.get(k).getPointId());
//						//初始化状态为0
//						cck.setRdState(0);
//						this.cusCouKpointService.addCusCouKpoint(cck);
//					}
//					
//				}
//				
//			}
			
		}catch(Exception ex){
			logger.error("CourseAction.freeAdmin1", ex);
			return ERROR;
		}
		
		return "ERROR";
	}
	
	
	/**
	 *根据用户填写的修改信息更新课程
	 *@return String
	 *
	 */
	public String updateCourse() {
		try{
			preAddCourse(course);
			Random r = new Random();
			Course coursetemp = courseService.getCourseById(course.getCourseId());
			course.setAddtime(coursetemp.getAddtime());//页面无法改动的量
			course.setUnsupportNum(coursetemp.getUnsupportNum());
			course.setClickNum(coursetemp.getClickNum());
			//course.setVedioPicUrl(coursetemp.getVedioPicUrl());
			if(coursetemp!=null && coursetemp.getTjVedioId()!=0 )
			{
			course.setTjVedioId(coursetemp.getTjVedioId());
			}else{
			course.setTjVedioId(0);
			}
			course.setSupportNum(coursetemp.getSupportNum());
			course.setScNum(coursetemp.getScNum());
			course.setStatus(coursetemp.getStatus());
			
			if(coursetemp.getPrice() != course.getPrice() && course.getStatus() != 3){//如果不相同则需要修改价格表,内部课程不记录
				Date nowtemp = new Date();
				
				QueryHistoryPriceCondition queryHistoryPriceCondition = new QueryHistoryPriceCondition();//修改
				queryHistoryPriceCondition.setCourseId(course.getCourseId());
				List<HistoryPrice> historylist = historyPriceService.getHistoryPriceList(queryHistoryPriceCondition);
				
				if(historylist != null && historylist.size() > 0){//修改最近的历史价格
					HistoryPrice pricetemp = historylist.get(0);
					
					pricetemp.setStopTime(nowtemp);
					
					historyPriceService.updateHistoryPrice(pricetemp);
				}
				
				HistoryPrice historyPrice = new HistoryPrice();//修改历史价格
				
				historyPrice.setPrice(course.getPrice());
				historyPrice.setCourseId(course.getCourseId());
				historyPrice.setCreadeTime(nowtemp);
				historyPrice.setStopTime(null);
				
				User user = (User)ActionContext.getContext().getSession().get(BackLoginAction.SYS_USER_SESSION_NAME);
				historyPrice.setUmanId(user.getUserId());//操作人ID
				
				historyPriceService.addHistoryPrice(historyPrice);
			}
			
			File fileTemp = null;
			String filename = null;
			String filetype = null;
			
			//包含服务修改
			List<String> filenamelist = new ArrayList<String>();//包含服务修改
			for(int i =0; i < servicePic.size(); i ++){
				
				fileTemp = servicePic.get(i);
				filename = sdf.format(new Date());
				filetype = this.getFileType(servicePicFileName.get(i));
				filename = filename + "-" + course.getCourseId() +"-" +  r.nextInt(100) + this.getFileType(filetype);
				
				course.setIncludeService(filename);
				filenamelist.add(filename);
			}
			
			if(servicePic == null || servicePic.size() == 0){
				course.setIncludeService(coursetemp.getIncludeService());
			}
			
			this.upload(filenamelist, servicePic);
			//包含服务修改
			
			Coursesort sorttemp = coursesortService.getCoursesortById(course.getSortId());
			course.setStatus(sorttemp.getStatus());//设置成课程状态
			
			//谢添加开始
			HttpServletRequest  request=ServletActionContext.getRequest();
			String [] disProperty=request.getParameterValues("disProperty");
			String disPropertys="";
			if(disProperty!=null&&disProperty.length!=0)
			{
			for(String dis:disProperty){
				disPropertys+=(dis+",");
			}
			}
			String [] disPosition =request.getParameterValues("disPosition");
			String disPositions="";
			if(disPosition!=null&&disPosition.length!=0)
			{
			for(String dis:disPosition){
				disPositions+=(dis+",");
			}
			}
			course.setDisPosition(disPositions);
			course.setDisProperty(disPropertys);
			//谢添加结束
			course.setUpdateTime(new Date());
			//course.setUpRate(Tool.divide(course.getRealUpNum(), course.getLessionTime(), 2));
			courseService.updateCourse(course);
			
			
			String isExistFile = servletRequest.getParameter("mainpic" + mainpicid);//主图片是否被修改
			List<String> filenames = new ArrayList<String>();
			
			Coursepic cpic = null;
			String fileName = null;
			String filePath = null;
			
			if(isExistFile != null && !isExistFile.equals("")){//主图片改动
				
				fileName = sdf.format(new Date()) + "-" + this.mainpicid + this.getFileType(this.firstPicFileName.get(0));
				filePath = "/back/upload/course/" + fileName;
				cpic = coursepicService.getCoursepicById(this.mainpicid);
				
				cpic.setType(this.getFileType(this.firstPicFileName.get(0)));
				cpic.setName(fileName);
				cpic.setPicUrl(filePath);
				coursepicService.updateCoursepic(cpic);
				
				filenames.add(fileName);
				this.upload(filenames, this.firstPic);
				filenames = new ArrayList<String>();
			}
			if(pic != null){
				for(int i = 0; i < this.pic.length; i ++){
					isExistFile = servletRequest.getParameter("otherPicEd" + pic[i]);
					if(isExistFile != null && !isExistFile.equals("")){//附属图片改动
						
						cpic = coursepicService.getCoursepicById(this.pic[i]);
						
						fileName = sdf.format(new Date()) + "-" + pic[i] + this.getFileType(this.otherPicEdFileName.get(0));
						filePath = "/back/upload/course/" + fileName;
						
						cpic.setType(this.getFileType(this.otherPicEdFileName.get(0)));
						cpic.setName(fileName);
						cpic.setPicUrl(filePath);
						filenames.add(fileName);
						
						coursepicService.updateCoursepic(cpic);
					}
				}
			}
			
			this.upload(filenames, otherPicEd);
			filenames = new ArrayList<String>();

			int picid = 0;
			long filesize = 0;
			
			for(int i =0; i < otherPics.size(); i ++){//添加新的附属图片
				cpic = new Coursepic();
				
				fileTemp = otherPics.get(i);
				fileName = sdf.format(new Date());
				filePath = "/back/upload/course/" + fileName;
				filesize = fileTemp.length();
				filetype = this.getFileType(otherPicsFileName.get(i));
				
				cpic.setCourseId(course.getCourseId());
				cpic.setHomeFlag(Coursepic.COURSEPIC_OTHER_PIC);
				cpic.setName(fileName);
				cpic.setPicUrl(filePath);
				cpic.setSize(filesize);
				cpic.setType(filetype);
				
				picid = coursepicService.addCoursepic(cpic);
				
				cpic = coursepicService.getCoursepicById(picid);
				fileName = fileName + "-" + picid + this.getFileType(filetype);
				filePath = filePath + "-" + picid + this.getFileType(filetype);
				cpic.setName(fileName);
				cpic.setPicUrl(filePath);
				filenames.add(fileName);
				coursepicService.updateCoursepic(cpic);
			}
			
			this.upload(filenames, otherPics);
			
		}catch(Exception ex){
			logger.error("CourseAction.updateCourse", ex);
			return ERROR;
		}
		return "loadCourseInfo";
	}
	
	/**
	 * 根据课程图片ID删除图片
	 * @return
	 */
	public String deletePic(){
		try{
			coursepicService.delCoursepicById(this.mainpicid);
			
		}catch(Exception ex){
			logger.error("CourseAction.deletePic", ex);
			return ERROR;
		}
		return "deletePicSuccess";
	}
	
	/**
	 *通过课程分类id获取课程集合
	 *用于课程下拉联动框
	 *result的type为JSON
	 *@return String 
	 */
	public String getCourseBySortId(){
		try {
			this.getQueryCourseCondition().setSortId(sortId);
			
			List<KeyValueDTO> myList = courseService.getCourseDTOListAddOrUpdate(queryCourseCondition);
			
			this.setResult(new Result<List<KeyValueDTO>>(true,"",null,myList));
		} catch (Exception e) {
			logger.error("CourseAction.getCourseBySortId", e);
			this.setResult(new Result<String>(false,"error",null,null));
		}
		return "getCourseBySortId";
	}
	
	
	/**
	 *根据课程id进入课程修改页面
	 *@return String 
	 */
	public String toUpdateCourse() {
		try{
			if(searchKey != null){
				searchKey = URLEncoder.encode(searchKey,"UTF-8");
			}
			gradeList = gradeService.getGradeListByStatus(Grade.GRADE_DEFAULT_STATUS);//所属年份
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);//专业列表
			teacherList = teacherService.getTeacherList(new QueryTeacherCondition());//教师列表
			courseMode = configurator.getCourseMode();//推荐模式
			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());//课程分类列表
			
			QueryCoursepicCondition queryCoursepicCondition = new QueryCoursepicCondition();
			
			queryCoursepicCondition.setCourseId(course.getCourseId());//主图片集合
			queryCoursepicCondition.setHomeFlag(Coursepic.COURSEPIC_MAIN_PIC);
			mainpicList = coursepicService.getMyCoursepicList(queryCoursepicCondition);
			
			queryCoursepicCondition.setHomeFlag(Coursepic.COURSEPIC_OTHER_PIC);//附属图片集合
			otherpicList = coursepicService.getMyCoursepicList(queryCoursepicCondition);
			
			course = courseService.getCourseById(course.getCourseId());
			course.setUpRate(Tool.divide(course.getRealUpNum(), course.getLessionTime(), 2));
			Coursesort temp1 = coursesortService.getCoursesortById(course.getSortId());
			Coursesort temp2 = coursesortService.getCoursesortById(temp1.getPId());
			Coursesort temp3 = coursesortService.getCoursesortById(temp2.getPId());
			
			this.fuSortIdString = temp3.getCoursesortName();
			this.suSortIdString = temp2.getCoursesortName();
			this.tuSortIdString = temp1.getCoursesortName();
			
			this.fuSortId = temp3.getCoursesortId();
			this.suSortId = temp2.getCoursesortId();
			this.tuSortId = temp1.getCoursesortId();
			this.setSubjectId(temp1.getSubjectId());
		}catch (Exception e) {
			logger.error("CourseAction.toUpdateCourse", e);
			return ERROR;
		}
		return "toUpdateCourse";
	}
	
	
	/**
	 * 添加课程
	 * 将用户添加的课程信息添加到数据库中
	 * @return String
	 * 
	 */
	public String addCourse() {
		try{
			preAddCourse(course);
			Coursesort sorttemp = coursesortService.getCoursesortById(course.getSortId());
			course.setStatus(sorttemp.getStatus());//设置成课程状态
			//谢添加开始
			HttpServletRequest  request=ServletActionContext.getRequest();
			String [] disProperty=request.getParameterValues("disProperty");
			String disPropertys="";
			if(disProperty!=null&&disProperty.length!=0)
			{
			for(String dis:disProperty){
				disPropertys+=(dis+",");
			}
			}
			String [] disPosition =request.getParameterValues("disPosition");
			String disPositions="";
			if(disPosition!=null&&disPosition.length!=0)
			{
			for(String dis:disPosition){
				disPositions+=(dis+",");
			}
			}
			course.setDisPosition(disPositions);
			course.setDisProperty(disPropertys);
			//谢添加结束
			course.setUpdateTime(new Date());
			course.setRealUpNum(0);
			course.setUpRate(0);
			int courseid = courseService.addCourse(course);
			
			if(course.getStatus() != 3){//内部课程不记录
				HistoryPrice historyPrice = new HistoryPrice();//设置历史价格
				
				historyPrice.setPrice(course.getPrice());
				historyPrice.setCourseId(courseid);
				historyPrice.setCreadeTime(new Date());
				historyPrice.setStopTime(null);
				
				User user = (User)ActionContext.getContext().getSession().get(BackLoginAction.SYS_USER_SESSION_NAME);
				historyPrice.setUmanId(user.getUserId());
				
				historyPriceService.addHistoryPrice(historyPrice);
			}
			
			File fileTemp = null;
			String filename = null;
			String filePath = null;
			String filetype = null;
			
			List<String> filenamelist = new ArrayList<String>();//添加服务图片
			for(int i =0; i < servicePic.size(); i ++){
				
				fileTemp = servicePic.get(i);
				filename = sdf.format(new Date());
				filetype = this.getFileType(servicePicFileName.get(i));
				filename = filename + "-" + courseid + this.getFileType(filetype);
				
				course.setIncludeService(filename);
				filenamelist.add(filename);
			}
			
			this.upload(filenamelist, servicePic);
			
			int picid = 0;
			long filesize = 0;
			Coursepic cpic = null;
			for(int i =0; i < otherPics.size(); i ++){//添加主图片
				cpic = new Coursepic();
				
				fileTemp = otherPics.get(i);
				filename = sdf.format(new Date());
				filePath = "/back/upload/course/" + filename;
				filesize = fileTemp.length();
				filetype = this.getFileType(otherPicsFileName.get(i));
				
				cpic.setCourseId(courseid);
				cpic.setHomeFlag(Coursepic.COURSEPIC_OTHER_PIC);
				cpic.setName(filename);
				cpic.setPicUrl(filePath);
				cpic.setSize(filesize);
				cpic.setType(filetype);
				
				picid = coursepicService.addCoursepic(cpic);
				
				cpic = coursepicService.getCoursepicById(picid);
				filename = filename + "-" + picid + this.getFileType(filetype);
				filePath = filePath + "-" + picid + this.getFileType(filetype);
				cpic.setName(filename);
				cpic.setPicUrl(filePath);
				filenamelist.add(filename);
				coursepicService.updateCoursepic(cpic);
			}
			
			this.upload(filenamelist, otherPics);
			
			filenamelist = new ArrayList<String>();
			for(int i =0; i < firstPic.size(); i ++){//主图片
				cpic = new Coursepic();
				
				fileTemp = firstPic.get(i);
				filename = sdf.format(new Date());
				filePath = "/back/upload/course/" + filename;
				filesize = fileTemp.length();
				filetype = this.getFileType(firstPicFileName.get(i));
				
				cpic.setCourseId(courseid);
				cpic.setHomeFlag(Coursepic.COURSEPIC_MAIN_PIC);
				cpic.setName(filename);
				cpic.setPicUrl(filePath);
				cpic.setSize(filesize);
				cpic.setType(filetype);
				
				picid = coursepicService.addCoursepic(cpic);
				
				cpic = coursepicService.getCoursepicById(picid);
				filename = filename + "-" + picid + this.getFileType(filetype);
				filePath = filePath + "-" + picid + this.getFileType(filetype);
				cpic.setName(filename);
				cpic.setPicUrl(filePath);
				filenamelist.add(filename);
				coursepicService.updateCoursepic(cpic);
			}
			
			this.upload(filenamelist, firstPic);
		}catch (Exception e) {
			logger.error("CourseAction.addCourse", e);
			return ERROR;
		}
		return "loadCourseInfo";
	}
	
	/**
	 * 添加或修改前准备工作
	 * 保证一个教师在同一个分类下的只有一个推荐课程
	 * @param courseTemp
	 * @throws Exception
	 */
	protected void preAddCourse(Course courseTemp) throws Exception{
		try{
			if(courseTemp.getTeacherTjCourse() == 1){//一个教师在同一个分类下的只有一个推荐课程
				this.getQueryCourseCondition().setTeacherId(course.getTeacherId());
				this.queryCourseCondition.setSubjectId(course.getSubjectId());
				this.queryCourseCondition.setTeacherTjCourse(1);
				List<Course> result = courseService.getWebCourseList(queryCourseCondition);
				
				Course temp = null;
				if(result != null){
					for(int i = 0; i < result.size(); i ++){
						temp = result.get(i);
						temp.setTeacherTjCourse(0);
						courseService.updateCourse(temp);
					}
				}
			}
			
		}catch(Exception ex){
			logger.error("CourseAction.preAddCourse", ex);
			throw new Exception(ex);
		}
	}
	
	/**
	 * 根据课程ID删除课程
	 * @return String
	 */
	public String deleteCourse() {
		try{
			course = courseService.getCourseById(course.getCourseId());
			course.setStatus(Course.COURSE_DELETE_STATUS);
			courseService.updateCourse(course);
		}catch (Exception e) {
			logger.error("CourseAction.deleteCourse", e);
			return ERROR;
		}
		return "deleteCourse";
	}
	
	
	/**
	 *根据课程ID冻结课程
	 *@return
	 */
	public String freezeCourse() {
		try{
			course = this.courseService.getCourseById(course.getCourseId());
			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());//课程分类列表
			if (course.getStatus() == Course.COURSE_DEFAULT_STATUS) {
				course.setStatus(Course.COURSE_FREEZE_STATUS);
			} else {
				course.setStatus(Course.COURSE_DEFAULT_STATUS);
			}
			this.courseService.updateCourse(course);
		}catch (Exception e) {
			logger.error("CourseAction.freezeCourse", e);
			return ERROR;
		}
		return "listCoursesBySortId";
	}
	
	public String toDeleteCourse(){
		try{
			courseList = courseService.getCourseList(this.getQueryCourseCondition());
		}catch(Exception ex){
			logger.error("CourseAction.toDeleteCourse", ex);
			return ERROR;
		}
		return "toDeleteCourse";
	}
	
	/**
	 * @author chenshuai
	 * 功能：删除真正的课程
	 * @param args
	 * @return
	 */
	public String deleteRealCourse(){
		try{
			course = courseService.getCourseById(course.getCourseId());
			if(course.getStatus() == 3){
				coursepicService.deleteCoursepicByCourseId(course.getCourseId());//删除课程图片
				historyPriceService.deleteHistoryPriceByCourseId(course.getCourseId());//删除历史价格
				cusCouKpointService.deleteCusCouKpointByCourseId(course.getCourseId());//删除cus_cou_kpoint_tbl中的数据
				kpointService.deleteKpointByCourseId(course.getCourseId());//删除知识点（）
				courseService.delCourseById(course.getCourseId());//删除课程
			}
			
			courseList = courseService.getCourseList(this.getQueryCourseCondition());
		}catch(Exception ex){
			logger.error("CourseAction.deleteRealCourse", ex);
			return ERROR;
		}
		return "toDeleteCourse";
	}
	
	/**
	 * @author chenshuai
	 * 功能：按课程ID获取历史价格列表
	 * @param args
	 * @return
	 */
	public String getHistoryListByCourseId(){
		try{
			QueryHistoryPriceCondition queryHistoryPriceCondition = new QueryHistoryPriceCondition();
			
			if(course != null && course.getCourseId() > 0){
				course = courseService.getCourseById(course.getCourseId());//课程对象
				
				queryHistoryPriceCondition.setCourseId(course.getCourseId());//课程ID
				queryHistoryPriceCondition.setPageSize(20);//设置每页显示数量
				
				PageResult pr = this.historyPriceService.getHistoryListByCourseId(queryHistoryPriceCondition);
				List result = pr.getPageResult();
				
				if(result != null){
					HistoryPrice hp = null;//临时历史价格
					for(int i = 0; i < result.size(); i ++){
						hp = (HistoryPrice)result.get(i);
						
						queryHistoryPriceCondition.setStartTime(hp.getCreadeTime());
						if(hp.getStopTime() == null){//设置时间段
							queryHistoryPriceCondition.setEndTime(new Date());
						}else{
							queryHistoryPriceCondition.setEndTime(hp.getStopTime());
						}
						
						hp.setBuyNum(historyPriceService.getHistoryPriceCourseNum(queryHistoryPriceCondition));//设置购买人数
					}
				}
				
				setPage(pr);
				this.getPage().setPageSize(20);//显示每页显示数量
				setPageUrlParms();
			}
			
			
		}catch(Exception ex){
			logger.error("CourseAction.getHistoryListByCourseId", ex);
			return ERROR;
		}
		
		return "listHistoryPrice";
	}
	
	public String exportCSV(){
		try{
			//设置导出excel文件名称
			this.addExpName("赠送课程查询列表");
			//设定允许最多导出条数
			this.setExportPageSize(10000);
			List<List<String>> list = new ArrayList<List<String>>();
			//将表头字段放入list中
			list.add(this.getExcelHeadInfo());
			//取得需要填充至Excel中的详细信息
			this.getExcelDetailInfo(this.freeCourseList(),list);
			FileExportImportUtil export = new FileExportImportUtil();
			InputStream is = export.export(list);
			setExcelFile(is);
			return "exportCouFreeGive";
		}catch(Exception e){
			logger.error("WaybillAction.exportCSV",e);
			return ERROR;
		}
		
	}
	
	private void addExpName(String expNameStr) throws UnsupportedEncodingException{
		String expName=expNameStr + DateUtil.getCurrentDate() + ".xls";
			if (ServletActionContext.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0)
			 {
				 setExportName(URLEncoder.encode(expName, "UTF-8"));//IE浏览器
			 }else{
				 setExportName(new String((expName).getBytes(),"iso-8859-1"));
			 }
		
	}
	private void setExportPageSize(int size){
		this.getQuCoFrGiCondition().setPageSize(size);
	}
	
	private List<String> getExcelHeadInfo(){
		List<String> headList = new ArrayList<String>();
		headList.add("赠送描述");
		headList.add("电子邮箱");
		headList.add("订单号码");
		headList.add("操作人");
		headList.add("赠送时间");
		return headList;
	}
	private List<CouFreeGive> freeCourseList(){
		PageResult result = this.getCouFreeGiveService().getCouFreeGiveByCondition(this.getQuCoFrGiCondition());
		return result.getPageResult();
	}
	private List<List<String>>getExcelDetailInfo(List<CouFreeGive> freeCourseList,List<List<String>> list){
		List<String> tempList=null;
		java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for(CouFreeGive couFreeGive:freeCourseList){
			tempList=new ArrayList<String>();
			tempList.add(couFreeGive.getCrInfo());
			tempList.add(couFreeGive.getEmail());
			tempList.add(couFreeGive.getCtId());
			tempList.add(couFreeGive.getUserName());
			tempList.add(df.format(couFreeGive.getCreateTime()));
			list.add(tempList);
		}
		return list;
		
	}
	
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

	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}

	public Map<Integer, String> getCourseMode() {
		return courseMode;
	}

	public void setCourseMode(Map<Integer, String> courseMode) {
		this.courseMode = courseMode;
	}

	public List<Coursesort> getCourseSortList() {
		return courseSortList;
	}
	
	public void setCourseSortList(List<Coursesort> courseSortList) {
		this.courseSortList = courseSortList;
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

	public String getSearchKey() throws UnsupportedEncodingException {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		
		this.searchKey = searchKey;
		
	}

	public Map<Integer, String> getCoursesortMap() {
		if(coursesortMap == null){
			coursesortMap = new HashMap<Integer, String>();
		}
		return coursesortMap;
	}

	public void setCoursesortMap(Map<Integer, String> coursesortMap) {
		this.coursesortMap = coursesortMap;
	}
	
	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public IGrade getGradeService() {
		return gradeService;
	}

	public void setGradeService(IGrade gradeService) {
		this.gradeService = gradeService;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<Coursesort> getAllcoursesortList() {
		return allcoursesortList;
	}

	public void setAllcoursesortList(List<Coursesort> allcoursesortList) {
		this.allcoursesortList = allcoursesortList;
	}
	
	public int[] getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(int[] courseIds) {
		this.courseIds = courseIds;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	public void setTeacherService(ITeacher teacherService) {
		this.teacherService = teacherService;
	}

	public List<File> getOtherPics() {
		return otherPics;
	}

	public void setOtherPics(List<File> otherPics) {
		this.otherPics = otherPics;
	}

	public List<File> getFirstPic() {
		return firstPic;
	}

	public void setFirstPic(List<File> firstPic) {
		this.firstPic = firstPic;
	}

	public ITeacher getTeacherService() {
		return teacherService;
	}

	
	public ICoursepic getCoursepicService() {
		return coursepicService;
	}

	public void setCoursepicService(ICoursepic coursepicService) {
		this.coursepicService = coursepicService;
	}

	public List<String> getOtherPicsFileName() {
		return otherPicsFileName;
	}

	public void setOtherPicsFileName(List<String> otherPicsFileName) {
		this.otherPicsFileName = otherPicsFileName;
	}

	public List<String> getFirstPicFileName() {
		return firstPicFileName;
	}

	public void setFirstPicFileName(List<String> firstPicFileName) {
		this.firstPicFileName = firstPicFileName;
	}

	public List<Coursepic> getMainpicList() {
		return mainpicList;
	}

	public void setMainpicList(List<Coursepic> mainpicList) {
		this.mainpicList = mainpicList;
	}

	public List<Coursepic> getOtherpicList() {
		return otherpicList;
	}

	public void setOtherpicList(List<Coursepic> otherpicList) {
		this.otherpicList = otherpicList;
	}
	public int[] getPic() {
		return pic;
	}

	public void setPic(int[] pic) {
		this.pic = pic;
	}
	
	public int getMainpicid() {
		return mainpicid;
	}

	public void setMainpicid(int mainpicid) {
		this.mainpicid = mainpicid;
	}

	public List<File> getOtherPicEd() {
		return otherPicEd;
	}

	public void setOtherPicEd(List<File> otherPicEd) {
		this.otherPicEd = otherPicEd;
	}

	public List<String> getOtherPicEdFileName() {
		return otherPicEdFileName;
	}

	public void setOtherPicEdFileName(List<String> otherPicEdFileName) {
		this.otherPicEdFileName = otherPicEdFileName;
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

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}

	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}

	public IGmrecord getGmrecordService() {
		return gmrecordService;
	}

	public void setGmrecordService(IGmrecord gmrecordService) {
		this.gmrecordService = gmrecordService;
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

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public List<File> getServicePic() {
		return servicePic;
	}

	public void setServicePic(List<File> servicePic) {
		this.servicePic = servicePic;
	}

	public List<String> getServicePicFileName() {
		return servicePicFileName;
	}

	public void setServicePicFileName(List<String> servicePicFileName) {
		this.servicePicFileName = servicePicFileName;
	}

	public List<Course> getCourse1List() {
		return course1List;
	}

	public void setCourse1List(List<Course> course1List) {
		this.course1List = course1List;
	}

	public int getFuSortId() {
		return fuSortId;
	}

	public int getSuSortId() {
		return suSortId;
	}

	public int getTuSortId() {
		return tuSortId;
	}

	public String getFuSortIdString() {
		return fuSortIdString;
	}

	public String getSuSortIdString() {
		return suSortIdString;
	}

	public String getTuSortIdString() {
		return tuSortIdString;
	}

	public List<HistoryPrice> getHistoryPriceList() {
		return historyPriceList;
	}

	public void setHistoryPriceList(List<HistoryPrice> historyPriceList) {
		this.historyPriceList = historyPriceList;
	}

	public ISellWay getSellWayService() {
		return sellWayService;
	}

	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}

	public ISellCou getSellCouService() {
		return sellCouService;
	}

	public void setSellCouService(ISellCou sellCouService) {
		this.sellCouService = sellCouService;
	}

	public List<SellWay> getSellWayList() {
		return sellWayList;
	}

	public void setSellWayList(List<SellWay> sellWayList) {
		this.sellWayList = sellWayList;
	}

	public QuerySellWayCondition getQuerySellWayCondition() {
		if(querySellWayCondition==null)
		{
			querySellWayCondition=new QuerySellWayCondition();
		}
			
		return querySellWayCondition;
	}

	public void setQuerySellWayCondition(QuerySellWayCondition querySellWayCondition) {
		this.querySellWayCondition = querySellWayCondition;
	}

	public SellWay getSellWay() {
		return sellWay;
	}

	public void setSellWay(SellWay sellWay) {
		this.sellWay = sellWay;
	}

	public List<SellCou> getSellCouList() {
		return sellCouList;
	}

	public void setSellCouList(List<SellCou> sellCouList) {
		this.sellCouList = sellCouList;
	}

	public QuerySellCouCondition getQuerySellCouCondition() {
		if(querySellCouCondition==null)
		{
			querySellCouCondition=new QuerySellCouCondition();
		}
		return querySellCouCondition;
	}

	public void setQuerySellCouCondition(QuerySellCouCondition querySellCouCondition) {
		this.querySellCouCondition = querySellCouCondition;
	}

	
	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the couFreeGiveService
	 */
	public ICouFreeGive getCouFreeGiveService() {
		return couFreeGiveService;
	}

	/**
	 * @param couFreeGiveService the couFreeGiveService to set
	 */
	public void setCouFreeGiveService(ICouFreeGive couFreeGiveService) {
		this.couFreeGiveService = couFreeGiveService;
	}

	public String getSaveSuccess() {
		return saveSuccess;
	}

	public void setSaveSuccess(String saveSuccess) {
		this.saveSuccess = saveSuccess;
	}

	public String getPjsortname() {
		return pjsortname;
	}

	public void setPjsortname(String pjsortname) {
		this.pjsortname = pjsortname;
	}
	
}
