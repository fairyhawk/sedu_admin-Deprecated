package com.shangde.edu.cou.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.service.ConfigService;
import com.shangde.common.util.Result;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.condition.QueryTeacherCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cou.dto.KeyValueDTO;
import com.shangde.edu.cou.dto.UserKpointDTO;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cou.service.ITeacher;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cusmgr.condition.QueryCusCouKpointCondition;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.kno.domain.Sys;
import com.shangde.edu.kno.domain.SysNode;
import com.shangde.edu.kno.service.ISys;
import com.shangde.edu.kno.service.ISysNode;
import com.shangde.edu.res.domain.Books;
import com.shangde.edu.res.domain.RelateKnowledge;
import com.shangde.edu.res.domain.Vedio;
import com.shangde.edu.res.domain.VedioKnowledge;
import com.shangde.edu.res.service.IBooks;
import com.shangde.edu.res.service.IVedio;
import com.shangde.edu.res.service.RelateKnowledgeService;
import com.shangde.edu.res.service.VedioKnowledgeService;
import com.shangde.edu.stu.service.IVideoStatistics;
import com.shangde.edu.tk.service.ITaskCus;

/**
 * 知识点action
 * @author chenshuai
 */
public class KPointAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(KPointAction.class);
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 知识点
	 */
	private Kpoint kpoint;
	
	/**
	 * 视频
	 */
	private Vedio vedio;
	
	/**
	 * 课程Id
	 */
	private int courseId;
	
	/**
	 * 知识点服务
	 */
	private IKpoint kpointService;
	
	/**
	 * 批量导入视频
	 */
	private IVedio vedioService;
	
	/**
	 * 课程服务
	 */
	private ICourse courseService; 
	
	/**
	 * 配置
	 */
	private ConfigService configurator;
	
	/**
	 * 知识点集合
	 */
	private List<Kpoint> kpointList;
	
	/**
	 * 知识点查询条件
	 */
	private QueryKpointCondition queryKpointCondition;
	
	/**
	 * 查询条件
	 */
	private String searchKey;
	
	/**
	 * 是否为叶子
	 */
	private int isLeaf = 0;
	
	/**
	 * 课程分类集合
	 */
	private List<Coursesort> courseSortList = new ArrayList<Coursesort>();
	
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService;
	
	/**
	 * 知识点ID集合
	 */
	private int[] kpointIds;
	
	/**
	 * 添加或更新标志
	 */
	private int addOrUpdate;
	
	/**
	 * 课程
	 */
	private Course course;
	
	/**
	 * 视频前缀
	 */
	private String prefix;
	
	/**
	 * 视频服务器
	 */
	private String vedioUrl;
	
	/**
	 * 教师Id
	 */
	private int tcId;
	
	/**
	 * 教师list
	 */
	private List<Teacher> teacherList=new ArrayList<Teacher>();
	
	/**
	 * 教师服务对象
	 */
	private ITeacher teacherService;
	
	/**
	 * 父节点名称
	 */
	private String pName;
	
	/**
	 * 课程分类集合Map
	 */
	private Map<Integer,String> coursesortMap;
	
	/**
	 * 教师
	 */
	private Teacher teacher;
	
	/**
	 * 书籍
	 */
	private Books book;
	
	private IBooks booksService;
	
	/**
	 * 课程知识点记录
	 */
	private ICusCouKpoint cusCouKpointService;
	
	private ITaskCus taskCusService;
	
    private IVideoStatistics   videoStatisticsService;
    
	
	/**
	 * 用户服务
	 */
	private ICustomer customerService;
	
	private Sys sys;
	
	private SysNode sysNode;
	
	private int ksId;
	private int ksnId;
	private ISys sysService;
	
	private ISysNode sysNodeService;
	
	private VedioKnowledgeService vedioKnowledgeService;
	
	
	

	private List<SysNode> sysTree=new ArrayList<SysNode>();
	
	private List<VedioKnowledge> vedioKnowledgeList = new ArrayList<VedioKnowledge>();
	

	private RelateKnowledgeService relateKnowledgeService;
	
	private List<RelateKnowledge> relateKnowledgeList=new ArrayList<RelateKnowledge>();

	private String[] relateKnowledgePoints;//关联知识点
	
	private String[] knowledgePointId ;//关联知识点ID

	private String[] importantLevel; //重要程度
	
    private String[] knowledgePoint; //视频知识点

	private String[] timePoint; //时间
	
	private String subjectId;//项目分类
	private String saveSuccess;//保存成功表示



	/**
	 * @return
	 * @authorcxs
	 * 功能：预添加视频
	 * @param args
	 */
	public String toAddVedioByCourse(){
		try{
		QueryTeacherCondition queryTeacherCondition = new QueryTeacherCondition();
		teacherList=this.teacherService.getTeacherList(queryTeacherCondition);
		courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
		return "toAddVedioByCourse";
		}catch(Exception e){
			logger.error("KPointAction.toAddVedioByCourse", e);
			return ERROR;
		}
	}
	
	

	/**
	 * @return
	 * @param args
	 * 功能：批量插入叶子节点视频
	 */
	public String addVedioByCourse(){
		try{
			Vedio vedio = null;
			String voName = null;
			String voUrl = null;
			
			QueryKpointCondition queryKpointCondition = new QueryKpointCondition();
			queryKpointCondition.setCourseId(kpoint.getCourseId());
			queryKpointCondition.setLeaf(1);
			List<KeyValueDTO> kpointDTOList = kpointService.getKpointDTOListByCourseId(queryKpointCondition);
			course = courseService.getCourseById(kpoint.getCourseId());
			
			if(kpointDTOList != null){
				for(int i = 0; i < kpointDTOList.size(); i ++){//清理原该课程的视频
					vedioService.deleteVedioByPointid(kpointDTOList.get(i).getId());
				}
				
				for(int i = 1; i <= kpointDTOList.size(); i ++){//添加视频
					vedio = new Vedio();
					voName = course.getTitle() + "-" + kpointDTOList.get(i-1).getName();
					voUrl = vedioUrl + "/" + prefix + "_" + i + ".mp4";
					vedio.setVoName(voName);
					vedio.setVoUrl(voUrl);
					vedio.setTcId(tcId);
					vedio.setLecture("");
					vedio.setVoRadOne("0");
					vedio.setPointid(kpointDTOList.get(i-1).getId());
					vedioService.addVedio(vedio);
				}
			}
		}catch(Exception ex){
			logger.error("KPointAction.addVedioByCourse", ex);
			return ERROR;
		}
		return "changeSuccess";
	}
	
	/**
	 * 根据知识点ID集合批量冻结知识点
	 * @return
	 */
	public String freezeKpoints(){
		try{
			if(kpointIds != null){
				Kpoint temp = null;
				for(int i = 0; i <kpointIds.length ; i++){
					
					temp = kpointService.getKpointById(kpointIds[i]);
					temp.setStatus(Kpoint.KPOINT_FREEZE_STATUS);
					kpointService.updateKpoint(temp);
					
					/**
					 * add by shixf
					 */
					Course course = courseService.getCourseById(temp.getCourseId());
					course.setUpdateTime(new Date());
					int planNum = course.getLessionTime();//计划课时
					int realNum = kpointService.getRealUpNumByCourseId(course.getCourseId());//实际课时
					 if(realNum>planNum){
						course.setLessionTime(Float.valueOf(String.valueOf(realNum)));
						course.setRealUpNum(realNum);
					}else {
						course.setRealUpNum(realNum);
					}
					/**
					 * add by shixf
					 */
					courseService.updateCourse(course);
				}
			}
		}catch(Exception ex){
			logger.error("KPointAction.freezeKpoints", ex);
			return ERROR;
		}
		return "freezeKpoints";
	}
	
	public synchronized void getCourseTreeMake() {
		   // List<Books> bookList;
			try{
				Course course = new Course();
				course.setCourseId(getCourseId());
				//int userId = getLoginUserId();
				//List<Integer> mycourseList = cusCouKpointService.getCusCouIdKpointListByCusId(userId);
				Course courseTemp = courseService.getCourseById(course.getCourseId());
				boolean isshitingable =false;
				Date dateishavebuy = new Date();
			//	Customer customer24 = customerService.getCustomerById(userId);
	            //是否在24小时之内
	           // if(dateishavebuy.getTime()-customer24.getRegTime().getTime()<86400000){
	             //   isshitingable=true;
	         //   }
				if( isshitingable || true/* mycourseList.contains(courseTemp.getCourseId())*/ ){//如果已购买此课程，则取出数据
					//第一次听课任务
					/*QueryTaskCusCondition queryTaskCusCondition = new QueryTaskCusCondition();
					queryTaskCusCondition.setCusId(userId);
					queryTaskCusCondition.setKeyWord(Task.TASK_KEY_STUDYCOURSE);
					TaskCus tc = taskCusService.getTaskCusByKey(queryTaskCusCondition);
					if(tc != null && tc.getIsComplete() == 0){//若果未完成则设置完成
						tc.setIsComplete(1);
						taskCusService.updateTaskCus(tc);
					}
					//第一次听课任务结束
					//bookList = booksService.getBooksListByCourseId(course.getCourseId());*/
					QueryCusCouKpointCondition queryCusCouKpointCondition = new QueryCusCouKpointCondition();
					queryCusCouKpointCondition.setCourseId(course.getCourseId());
					queryCusCouKpointCondition.setCusId(1);
					queryCusCouKpointCondition.setType("buy");
				List<UserKpointDTO> myList = cusCouKpointService.getKpointListByCusIdAndCourseId(queryCusCouKpointCondition);
					//更新已经看过的课程状态start 
					    List<String> watchlist = videoStatisticsService.getUserLearnKpointByCourseId(getCourseId(),1);
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
					//开始生成数据开始
					//String nowTime = sdf.format(customer24.getRegTime());
					String begin="[{\"kpointlist\":[";
					String end="}]";
					String str=begin;
					for(UserKpointDTO kpointDTO:myList)
					{
						str+="{'id':'"+kpointDTO.getId()+"','PId':'"+kpointDTO.getPId()+"','name':'"+kpointDTO.getName()+"','level':'"
					+kpointDTO.getLevel()+"','isWatch':'"+kpointDTO.getIsWatch()+"','voId':'"+kpointDTO.getVoId()+"','vedioUrl':'"
								+kpointDTO.getVedioUrl()+"','courseid':'"+getCourseId()+"','ccURL':'"+kpointDTO.getCcUrl()+"','playType':'"+kpointDTO.getPlayType()+"'},";
						
					}
					str+="],'timelist':'"+new Date()+"'";
					str+=end;
					String path="/static/tree";
					String fileDirectory = ServletActionContext.getRequest().getSession().getServletContext().getRealPath(path) ;
					File isD = new File(fileDirectory);
					if (!isD.isDirectory()) {
						isD.mkdirs();
					}
					OutputStreamWriter osw  = new OutputStreamWriter(new FileOutputStream(fileDirectory+"/tree_"+getCourseId()+".html"), "UTF-8"); // 建立文件输出流
					osw.write(str);
	                osw.close();
					//开始生成数据结束
				}		
			} catch (Exception e) {
				logger.error("KpointAction！getCourseTreeMake",e);
			} 
		}
	
	/**
	 * 根据课程ID获取知识点集合（不包含子节点）
	 * 用户显示知识点下拉列表
	 * result的type为json
	 * @return
	 */
	public String getJSONKpoints(){
		try{
			if(kpoint.getCourseId()!=0){
				this.getQueryKpointCondition().setCourseId(kpoint.getCourseId());
				}
			List<KeyValueDTO> myList = kpointService.getKpointDTOListByCourseId(queryKpointCondition);
			this.setResult(new Result<List<KeyValueDTO>>(true,"",null,myList));
		}catch(Exception ex){
			logger.error("KPointAction.getJSONKpoints", ex);
			return ERROR;
		}
		return "getJSONKpoints";
	}
	

	
	/**
	 * 获取所有知识点集合
	 * 用户显示知识点下拉列表
	 * result的type为json
	 * @return
	 */
	public String getAllJSONKpoints(){
		try{
			if(kpoint.getCourseId()!=0){
				this.getQueryKpointCondition().setCourseId(kpoint.getCourseId());
			}
				queryKpointCondition.setLeaf(-1);
				List<KeyValueDTO> myList = kpointService.getKpointDTOListByCourseId(queryKpointCondition);
				this.setResult(new Result<List<KeyValueDTO>>(true,"",null,myList));
			
		}catch(Exception ex){
			logger.error("KPointAction.getAllJSONKpoints", ex);
			return ERROR;
		}
		return "getJSONKpoints";
	}
	
	/**
	 * 根据知识点ID冻结知识点
	 * @return String
	 */
	public String freezeKpoint(){
		try{
			kpoint = kpointService.getKpointById(kpoint.getPointId());
			
			if(kpoint.getStatus() == Kpoint.KPOINT_FREEZE_STATUS){
				kpoint.setStatus(Kpoint.KPOINT_DEFAULT_STATUS);
			}else{
				kpoint.setStatus(Kpoint.KPOINT_FREEZE_STATUS);
			}
			kpointService.updateKpoint(kpoint);
			
			/**
			 * add by shixf
			 */
			Course course = courseService.getCourseById(kpoint.getCourseId());
			course.setUpdateTime(new Date());
			int planNum = course.getLessionTime();//计划课时
			int realNum = kpointService.getRealUpNumByCourseId(course.getCourseId());//实际课时
			 if(realNum>planNum){
				course.setLessionTime(Float.valueOf(String.valueOf(realNum)));
				course.setRealUpNum(realNum);
			}else {
				course.setRealUpNum(realNum);
			}
			/**
			 * add by shixf
			 */
			courseService.updateCourse(course);
		}catch(Exception ex){
			logger.error("KPointAction.freezeKpoint", ex);
			return ERROR;
		}
		
		return "listKpointsByCondition";
	}
	
	/**
	 * 根据知识点ID删除课程知识点
	 * @return String
	 */
	public String deleteKpoint(){
		try{
			int pId = kpoint.getPointId();
			/**
			 * 删除视频时，修改课程表中的实际上传数
			 */
			Kpoint pKpoint = kpointService.getKpointById(pId);
			Course course = courseService.getCourseById(pKpoint.getCourseId());
			
			kpointService.delKpointById(kpoint.getPointId());
			
			/**
			 * add by shixf
			 */
			course.setUpdateTime(new Date());
			int planNum = course.getLessionTime();//计划课时
			int realNum = kpointService.getRealUpNumByCourseId(course.getCourseId());//实际课时
			 if(realNum>planNum){
				course.setLessionTime(Float.valueOf(String.valueOf(realNum)));
				course.setRealUpNum(realNum);
			}else {
				course.setRealUpNum(realNum);
			}
			/**
			 * add by shixf
			 */
			courseService.updateCourse(course);
		}catch(Exception ex){
			logger.error("KPointAction.deleteKpoint", ex);
			return ERROR;
		}
		
		return "deleteKpoint";
	}
	
	/**
	 * 删除单个视频
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String deleteVedio() {
		try {
			this.vedioService.delVedioById(vedio.getVoId());
			vedio = null;
			course = courseService.getCourseById(kpoint.getCourseId());
			this.getQueryKpointCondition().setCourseId(kpoint.getCourseId());
			this.kpointList = kpointService.getKpointListByCourseId(queryKpointCondition);
		} catch (Exception e) {
			logger.error("KPointAction.deleteVedio", e);
			return ERROR;
		}
		return "toAddOrUpdateKpoint";
	}
	
	/**
	 * 预更新知识点
	 * @return String
	 */
	public String toAddOrUpdateKpoint(){
		try{
			
			//查询所有教师
			QueryTeacherCondition getQueryTeacherCondition = new QueryTeacherCondition();
			teacherList=this.teacherService.getTeacherList(getQueryTeacherCondition);
			
			servletRequest.getSession().removeAttribute("article");//移除article,保证其他模块fck上传文件好使
			
			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
			
			
			if(kpoint != null ){
				this.getQueryKpointCondition().setCourseId(kpoint.getCourseId());
				this.kpointList = kpointService.getKpointListByCourseId(queryKpointCondition);
				course = courseService.getCourseById(kpoint.getCourseId());
				
			}
			
			if(kpoint != null && kpoint.getPointId() != 0){
				course = courseService.getCourseById(kpoint.getCourseId());
				this.kpoint = kpointService.getKpointById(kpoint.getPointId());
				this.addOrUpdate = 1;
				
				vedio = vedioService.getVedioByPointid(kpoint.getPointId());
				
				List<Books> bkList = booksService.getBooksListByPointId(kpoint.getPointId());
				if(bkList != null && bkList.size() > 0){
					book = bkList.get(0);
				}
				
				if(kpoint.getPId() != 0 && kpoint.getPId() != -2){
					Kpoint pKpoint = kpointService.getKpointById(kpoint.getPId());
					
					this.setPName(pKpoint.getName());
				}else{
					this.setPName("根节点");
				}
			}else{
				this.setPName("根节点");
			}
			//关联知识库
			this.relateKnowledge();
				
			if(vedio!=null){
				//查询视频知识点
				vedioKnowledgeList=vedioKnowledgeService.getVedioKnowledgeList(vedio.getVoId());
				//查询与视频关联的知识点
				relateKnowledgeList=relateKnowledgeService.getRelateKnowledgeList(vedio.getVoId());
			}
			
			
			
			
		}catch(Exception ex){
			logger.error("KPointAction.toAddOrUpdateKpoint", ex);
			return ERROR;
		}
		
		return "toAddOrUpdateKpoint";
	}
	
	/**
	 * 根据条件更新或添加知识点
	 * @return String
	 */
	public String addOrUpdateKpoint(){
		try{
			
			
			//查询所有教师
			QueryTeacherCondition getQueryTeacherCondition = new QueryTeacherCondition();
			teacherList=this.teacherService.getTeacherList(getQueryTeacherCondition);
			
			if(teacher != null && teacher.getTcId() != 0){
				teacher = teacherService.getTeacherById(teacher.getTcId());
			}
			
			String levelTemp = "";
			String sortTemp = "";
			int sortNumTemp = 0;
			course = courseService.getCourseById(kpoint.getCourseId());
			
			if(kpoint.getLevel() == 1){//一级
				levelTemp = kpoint.getSort() + "000000";
			}else if(kpoint.getLevel() == 2){//二级
				
				sortNumTemp = kpointService.getKpointById(kpoint.getPId()).getSortNum();
				
				if(sortNumTemp >= 10000000){
					sortTemp = ("" + sortNumTemp).substring(0,2);
				}else{
					sortTemp = ("" + sortNumTemp).substring(0,1);
				}
				
				if(kpoint.getSort() < 10){
					levelTemp =  sortTemp + "0" + kpoint.getSort() + "0000";
				}else{
					levelTemp =  sortTemp + kpoint.getSort() + "0000";
				}
			}else if(kpoint.getLevel() == 3){//三级
				Kpoint temp = kpointService.getKpointById(kpoint.getPId());//2
				
				sortNumTemp = temp.getSortNum();
				
				if(sortNumTemp >= 10000000){
					sortTemp = ("" + sortNumTemp).substring(0,4);
				}else{
					sortTemp = ("" + sortNumTemp).substring(0,3);
				}
				
				if(kpoint.getSort() < 10){
					levelTemp =  sortTemp + "0" + kpoint.getSort() + "00";
				}else{
					levelTemp =  sortTemp + kpoint.getSort() + "00";
				}
			}else if(kpoint.getLevel() == 4){//四级
				Kpoint temp = kpointService.getKpointById(kpoint.getPId());//3
				sortNumTemp = temp.getSortNum();
				
				if(sortNumTemp >= 10000000){
					sortTemp = ("" + sortNumTemp).substring(0,6);
				}else{
					sortTemp = ("" + sortNumTemp).substring(0,5);
				}
				
				if(kpoint.getSort() < 10){
					levelTemp =  sortTemp + "0" + kpoint.getSort();
				}else{
					levelTemp =  sortTemp + kpoint.getSort();
				}
			}
			
			kpoint.setSortNum(Integer.parseInt(levelTemp));
			
			if(this.addOrUpdate == 0){//添加
				kpoint.setPointId(0);
				kpoint.setLeaf(1);
				Kpoint fatherKp = kpointService.getKpointById(kpoint.getPId());//3
				if(fatherKp!=null && fatherKp.getLeaf()==1){
					fatherKp.setLeaf(0);
					kpointService.updateKpoint(fatherKp);
				}
				
				int pointid = kpointService.addKpoint(kpoint);
				
				if(vedio != null && vedio.getCcUrl() != null && !vedio.getCcUrl().trim().equals("")){//添加视频
					vedio.setVoName(kpoint.getName());
					vedio.setContent(kpoint.getName());
					vedio.setPointid(pointid);
					vedio.setVoScores(10.0);
					vedio.setLecture("");
					vedio.setVoId(0);
					vedioService.addVedio(vedio);
				}
				
				
				if(book != null && ((book.getBkUrl() != null && !book.getBkUrl().trim().equals(""))||( book.getYpUrl() != null && !book.getYpUrl().trim().equals("")))){
					book.setBkId(0);
					book.setBkName(kpoint.getName());
					book.setBkTitle(kpoint.getName());
					book.setBkAuthor("highso");
					book.setBkType(book.getBkUrl().substring((book.getBkUrl().lastIndexOf(".") + 1)));
					book.setBarcode("barcode");
					book.setPointid(pointid);
					booksService.addBooks(book);
				}	 
			}else{//修改
				Kpoint pointTemp = kpointService.getKpointById(kpoint.getPointId());
				
				kpoint.setAddTime(pointTemp.getAddTime());//添加时间
				kpoint.setLeaf(pointTemp.getLeaf());//叶子节点
				
				if(kpoint.getPointId() != 0 && kpoint.getPointId() != kpoint.getPId()){//判断知识点ID不能为O，并且不能父子节点他、相同
					kpointService.updateKpoint(kpoint);
				
					//Yaning modify this for ccurl
					if(vedio != null){
//						Vedio vedioTemp = vedioService.getVedioByPointid(kpoint.getPointId());
//						
//						if(vedioTemp != null && vedioTemp.getVoId() != 0){//若有则更新
//							vedioTemp.setTcId(vedio.getTcId());
//							vedioTemp.setVoUrl(vedio.getVoUrl());
//							vedioTemp.setVoName(kpoint.getName());
//							vedioTemp.setPointid(kpoint.getPointId());
//							vedioTemp.setVoSize(vedio.getVoSize());
//							
//							vedioService.updateVedio(vedio);
//						}else 
							vedio.setVoName(kpoint.getName());
							vedio.setContent(kpoint.getName());
							vedio.setPointid(kpoint.getPointId());
							vedio.setVoScores(10.0);
							vedioService.updateVedio(vedio);
					}
					
					if(book != null && (book.getBkUrl() != null|| book.getYpUrl() != null)){
						List<Books> bkList = booksService.getBooksListByPointId(kpoint.getPointId());
						Books bookTemp = null;
						if(bkList != null && bkList.size() > 0){//取第一个
							bookTemp = bkList.get(0);
						}
						
						if(bookTemp != null && bookTemp.getBkId() != 0){//若存在书籍，则更新
							bookTemp.setBkName(kpoint.getName());
							bookTemp.setBkTitle(kpoint.getName());
							bookTemp.setBkUrl(book.getBkUrl());
							bookTemp.setYpUrl(book.getYpUrl());
							bookTemp.setPointid(kpoint.getPointId());//知识点ID
							bookTemp.setBkType(book.getBkUrl().substring((book.getBkUrl().lastIndexOf(".") + 1)));//后缀名
							
							booksService.updateBooks(bookTemp);
						}else if(!book.getBkUrl().trim().equals("")|| !book.getYpUrl().trim().equals("")){
							book.setBkId(0);
							book.setBkName(kpoint.getName());
							book.setBkTitle(kpoint.getName());
							book.setBkAuthor("highso");
							book.setBkType(book.getBkUrl().substring((book.getBkUrl().lastIndexOf(".") + 1)));
							book.setBarcode("barcode");
							book.setPointid(kpoint.getPointId());//知识点ID
							
							booksService.addBooks(book);
						}
						
					}
					
					/**
					 * 调整当前节点父节点的类型
					 */
					Kpoint fatherKp = kpointService.getKpointById(pointTemp.getPId());//3
					if(fatherKp!=null && fatherKp.getLeaf()==0){
						int childCount = kpointService.getChildKpointCount(fatherKp.getPointId());
						if(childCount==0){
							fatherKp.setLeaf(1);
							kpointService.updateKpoint(fatherKp);
						}
					}
					Kpoint newFatherKp = kpointService.getKpointById(kpoint.getPId());//3
					if(newFatherKp!=null && newFatherKp.getLeaf()==1){					
							newFatherKp.setLeaf(0);
							kpointService.updateKpoint(newFatherKp);
						
					}
				}
				
			}
			
			/**
			 * add by shixf
			 */
			course.setUpdateTime(new Date());
			int planNum = course.getLessionTime();//计划课时
			int realNum = kpointService.getRealUpNumByCourseId(course.getCourseId());//实际课时
			 if(realNum>planNum){
				course.setLessionTime(Float.valueOf(String.valueOf(realNum)));
				course.setRealUpNum(realNum);
			}else {
				course.setRealUpNum(realNum);
			}
			 courseService.updateCourse(course);
			/**
			 * add by shixf
			 */
			this.getQueryKpointCondition().setCourseId(kpoint.getCourseId());
			this.kpointList = kpointService.getKpointListByCourseId(queryKpointCondition);
			setCourseId(kpoint.getCourseId());
			getCourseTreeMake();
			//关联知识库
			this.relateKnowledge();
			// 添加视频知识点信息 
			this.addKnowledgePoint(vedio);
			//添加关联知识库信息
			this.addRelateKnowledge(vedio);	
			
		}catch(Exception ex){
			logger.error("KPointAction.addOrUpdateKpoint", ex);
			return ERROR;
		}
		return "addOrUpdateKpoint";
	}
	
	/**
	 * 插入关联知识库信息
	 */
	public void addRelateKnowledge(Vedio vedio){
		// 在增加之前，先将旧的知识点进行删除   
		relateKnowledgeService.deleteByVoId(vedio.getVoId());
		if(this.getRelateKnowledgePoints().length>0){
			for(int i=0;i<relateKnowledgePoints.length;i++){
				RelateKnowledge relateKnowledge = new RelateKnowledge();
				relateKnowledge.setRelateKnowledgePoint(relateKnowledgePoints[i]);
				relateKnowledge.setKnowledgePointId(Integer.parseInt(knowledgePointId[i]));
				try{
					relateKnowledge.setImportantLevel(importantLevel[i]);
				}catch(ArrayIndexOutOfBoundsException ex){
					relateKnowledge.setImportantLevel("");
				}
				relateKnowledge.setVoId(vedio.getVoId());
				relateKnowledgeService.addRelateKnowledge(relateKnowledge);
			}
			
		}
	}
	/**
	 * 添加视频知识点
	 * @param vedio
	 */
	public void addKnowledgePoint(Vedio vedio){
		// 在更新之前，先将该视频所属的知识点删除，再进行插入
		vedioKnowledgeService.deleteByVoId(vedio.getVoId());
		if(this.getKnowledgePoint().length>0){
			for(int i = 0 ;i<knowledgePoint.length;i++){
				VedioKnowledge vedioKnowledge=new VedioKnowledge();
				vedioKnowledge.setKnowledgePoint(knowledgePoint[i]);
				vedioKnowledge.setVoId(vedio.getVoId());
				try{
					vedioKnowledge.setTimePoint(timePoint[i]);
				}catch(ArrayIndexOutOfBoundsException ex){
					vedioKnowledge.setTimePoint("");
				}
				vedioKnowledgeService.addKnowledgePoint(vedioKnowledge);
			}
		}
	}
	/**
	 * 获取关联知识库信息
	 */
	public void relateKnowledge(){
		if(subjectId!=null&&!("").equals(subjectId)){
			try{
				List<Sys> sysList=sysService.getSysBySubjectId(Integer.parseInt(subjectId));
				if(sysList!=null&&sysList.size()>0){
					sys=sysList.get(0);
					sysNode=sysNodeService.getSysNodeBySysId(sys.getKsId());
					sysTree=sysNodeService.getSysTreeById(sys.getKsId());
				}
			}catch(Exception ex){
				logger.error("KPointAction.relateKnowledge",ex);
			}
		}
	}
	
	/**
	 * 修复知识树
	 * 子节点的判断
	 * @return
	 */
	public String fixKpoints(){
		try{
			int courseId = kpoint.getCourseId();
			this.getQueryKpointCondition().setCourseId(kpoint.getCourseId());
			this.kpointList = kpointService.getKpointListByCourseId(queryKpointCondition);//获取所有知识点集合
			
			Kpoint temp = null;
			int leafNum = 0;
			for(int i = 0; i < kpointList.size(); i ++){
				temp = kpointList.get(i);
				
				fixLevel(temp);//修复知识点级别
				fixSorNum(temp);//修复知识点分类
				leafNum = kpointService.getChildKpointCount(temp.getPointId());
				
				if(leafNum > 0){
					if(temp.getLeaf() == 1){
						temp.setLeaf(0);
					}
				}else{
					if(temp.getLeaf() == 0){
						temp.setLeaf(1);
					}
				}
				
				kpointService.updateKpoint(temp);
			}
			
			/**
			 * add by shixf
			 */
			course = courseService.getCourseById(courseId);
			course.setUpdateTime(new Date());
			int planNum = course.getLessionTime();//计划课时
			int realNum = kpointService.getRealUpNumByCourseId(course.getCourseId());//实际课时
			 if(realNum>planNum){
				course.setLessionTime(Float.valueOf(String.valueOf(realNum)));
				course.setRealUpNum(realNum);
			}else {
				course.setRealUpNum(realNum);
			}
			/**
			 * add by shixf
			 */
			 
			courseService.updateCourse(course);
			
		}catch(Exception ex){
			logger.error("KPointAction.fixKpoints", ex);
			return ERROR;
		}
		
		return "fixKpoints";
	}
	
	/**
	 * @author chenshuai
	 * 功能：修复用户级别
	 * @param args
	 * @param kTemp
	 */
	private void fixLevel(Kpoint kTemp){
		try{
			if(kTemp.getPId() == -2){//一级
				kTemp.setLevel(1);
			}else{
				Kpoint kTemp2 = kpointService.getKpointById(kTemp.getPId());
				if(kTemp2.getPId() == -2){//二级
					kTemp.setLevel(2);
				}else{
					Kpoint kTemp3 = kpointService.getKpointById(kTemp2.getPId());
					
					if(kTemp3.getPId() == -2){//三级
						kTemp.setLevel(3);
					}else{
						Kpoint kTemp4 = kpointService.getKpointById(kTemp3.getPId());
						
						if(kTemp4.getPId() == -2){//四级
							kTemp.setLevel(4);
						}else{
							kTemp.setLevel(5);
						}
					}
				}
			}
		}catch(Exception ex){
			logger.error("KPointAction.fixLevel", ex);
		}
	}
	
	/**
	 * @author chenshuai
	 * 功能：修复知识点的排序号
	 * @param args
	 * @param kTemp
	 */
	private void fixSorNum(Kpoint kTemp){
		try{
			String levelTemp = "";
			String sortTemp = "";
			int sortNumTemp = 0;
			
			if(kTemp.getLevel() == 1){//一级
				levelTemp = kTemp.getSort() + "000000";
			}else if(kTemp.getLevel() == 2){//二级
				
				sortNumTemp = kpointService.getKpointById(kTemp.getPId()).getSortNum();
				
				if(sortNumTemp >= 10000000){
					sortTemp = ("" + sortNumTemp).substring(0,2);
				}else{
					sortTemp = ("" + sortNumTemp).substring(0,1);
				}
				
				if(kTemp.getSort() < 10){
					levelTemp =  sortTemp + "0" + kTemp.getSort() + "0000";
				}else{
					levelTemp =  sortTemp + kTemp.getSort() + "0000";
				}
			}else if(kTemp.getLevel() == 3){//三级
				Kpoint temp = kpointService.getKpointById(kTemp.getPId());//2
				
				sortNumTemp = temp.getSortNum();
				
				if(sortNumTemp >= 10000000){
					sortTemp = ("" + sortNumTemp).substring(0,4);
				}else{
					sortTemp = ("" + sortNumTemp).substring(0,3);
				}
				
				if(kTemp.getSort() < 10){
					levelTemp =  sortTemp + "0" + kTemp.getSort() + "00";
				}else{
					levelTemp =  sortTemp + kTemp.getSort() + "00";
				}
			}else if(kTemp.getLevel() == 4){//四级
				Kpoint temp = kpointService.getKpointById(kTemp.getPId());//3
				sortNumTemp = temp.getSortNum();
				
				if(sortNumTemp >= 10000000){
					sortTemp = ("" + sortNumTemp).substring(0,6);
				}else{
					sortTemp = ("" + sortNumTemp).substring(0,5);
				}
				
				if(kTemp.getSort() < 10){
					levelTemp =  sortTemp + "0" + kTemp.getSort();
				}else{
					levelTemp =  sortTemp + kTemp.getSort();
				}
			}
			
			kTemp.setSortNum(Integer.parseInt(levelTemp));
		}catch(Exception ex){
			logger.error("KPointAction.fixSorNum", ex);
		}
	}
	
	/**
	 * 通过条件查询知识点
	 * 条件为 关键字
	 * @return String
	 */
	public String listKpointsByCondition(){
		try{
			if(searchKey != null && !searchKey.trim().equals("")){
				searchKey = URLDecoder.decode(searchKey,"UTF-8");//将这类代码转换成中文
				this.getQueryKpointCondition().setSearchKey(searchKey);
			}
			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
			
			if(kpoint != null){
				this.getQueryKpointCondition().setCourseId(kpoint.getCourseId());
				
				course = courseService.getCourseById(kpoint.getCourseId());
				//获取课程所属分类
				Coursesort fSort = null;
				Coursesort sSort = null;
				Coursesort tSort = null;
				if(course != null){
					if(course.getSortId() != 0){
						tSort = coursesortService.getCoursesortById(course.getSortId());
					}
				}
				
				if(tSort != null && tSort.getPId() != 0){
					sSort = coursesortService.getCoursesortById(tSort.getPId());
				}
				
				if(sSort!= null && sSort.getPId() != 0){
					fSort = coursesortService.getCoursesortById(sSort.getPId());
				}
				
				if(fSort != null){
					this.getCoursesortMap().put(1, fSort.getCoursesortName());
				}
				
				if(sSort != null){
					this.getCoursesortMap().put(2, sSort.getCoursesortName());
				}
				
				if(tSort != null){
					this.getCoursesortMap().put(3, tSort.getCoursesortName());
				}
				
				if(course != null){
					this.getCoursesortMap().put(4, course.getTitle());
				}
			}
			this.getQueryKpointCondition().setPageSize(20);
			setPage(this.kpointService.getKpointListByCondition(this.getQueryKpointCondition()));
			this.getPage().setPageSize(20);
			setPageUrlParms();
		}catch(Exception ex){
			logger.error("KPointAction.listKpointsByCondition", ex);
			return ERROR;
		}
		return "listKpointsByCondition";
	}
	
	/**
	 * 批量生成静态树
	 * @throws IOException 
	 */
	public void genTree() throws IOException{
		try{
	    Integer treeSubjectId=null;
	    if(subjectId!=null){
	    	treeSubjectId=Integer.parseInt(subjectId); 
	    }
		List<Integer> courses=courseService.getCourseIdAll(treeSubjectId);
		for(Integer courseId:courses){
			setCourseId(courseId);
			getCourseTreeMake();
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.getWriter().print("tree success . gen size is : "+courses.size());
		}catch(Exception e){
			logger.error("批量生成静态树"+e.toString());
			HttpServletResponse response=ServletActionContext.getResponse();
			response.getWriter().print("tree gen fail");
		}
	}
	
	

	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
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

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public List<Kpoint> getKpointList() {
		return kpointList;
	}
	public void setKpointList(List<Kpoint> kpointList) {
		this.kpointList = kpointList;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Kpoint getKpoint() {
		return kpoint;
	}

	public void setKpoint(Kpoint kpoint) {
		this.kpoint = kpoint;
	}
	
	public int getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
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
	
	public int[] getKpointIds() {
		return kpointIds;
	}

	public void setKpointIds(int[] kpointIds) {
		this.kpointIds = kpointIds;
	}
	
	public int getAddOrUpdate() {
		return addOrUpdate;
	}

	public void setAddOrUpdate(int addOrUpdate) {
		this.addOrUpdate = addOrUpdate;
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

	public IVedio getVedioService() {
		return vedioService;
	}

	public void setVedioService(IVedio vedioService) {
		this.vedioService = vedioService;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getVedioUrl() {
		return vedioUrl;
	}

	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}

	public int getTcId() {
		return tcId;
	}

	public void setTcId(int tcId) {
		this.tcId = tcId;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	public ITeacher getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(ITeacher teacherService) {
		this.teacherService = teacherService;
	}

	public String getPName() {
		return pName;
	}

	public void setPName(String name) {
		pName = name;
	}

	public Vedio getVedio() {
		return vedio;
	}

	public void setVedio(Vedio vedio) {
		this.vedio = vedio;
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Books getBook() {
		return book;
	}

	public void setBook(Books book) {
		this.book = book;
	}

	public IBooks getBooksService() {
		return booksService;
	}

	public void setBooksService(IBooks booksService) {
		this.booksService = booksService;
	}

	public ICusCouKpoint getCusCouKpointService() {
		return cusCouKpointService;
	}

	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}

	public ITaskCus getTaskCusService() {
		return taskCusService;
	}

	public void setTaskCusService(ITaskCus taskCusService) {
		this.taskCusService = taskCusService;
	}

	public IVideoStatistics getVideoStatisticsService() {
		return videoStatisticsService;
	}

	public void setVideoStatisticsService(IVideoStatistics videoStatisticsService) {
		this.videoStatisticsService = videoStatisticsService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}
	
	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public SysNode getSysNode() {
		return sysNode;
	}

	public void setSysNode(SysNode sysNode) {
		this.sysNode = sysNode;
	}

	public int getKsId() {
		return ksId;
	}

	public void setKsId(int ksId) {
		this.ksId = ksId;
	}

	public int getKsnId() {
		return ksnId;
	}

	public void setKsnId(int ksnId) {
		this.ksnId = ksnId;
	}

	public ISys getSysService() {
		return sysService;
	}

	public void setSysService(ISys sysService) {
		this.sysService = sysService;
	}

	public ISysNode getSysNodeService() {
		return sysNodeService;
	}

	public void setSysNodeService(ISysNode sysNodeService) {
		this.sysNodeService = sysNodeService;
	}

	public List<SysNode> getSysTree() {
		return sysTree;
	}

	public void setSysTree(List<SysNode> sysTree) {
		this.sysTree = sysTree;
	}
	
	public VedioKnowledgeService getVedioKnowledgeService() {
		return vedioKnowledgeService;
	}



	public void setVedioKnowledgeService(VedioKnowledgeService vedioKnowledgeService) {
		this.vedioKnowledgeService = vedioKnowledgeService;
	}

	public List<VedioKnowledge> getVedioKnowledgeList() {
		return vedioKnowledgeList;
	}



	public void setVedioKnowledgeList(List<VedioKnowledge> vedioKnowledgeList) {
		this.vedioKnowledgeList = vedioKnowledgeList;
	}
	
	public RelateKnowledgeService getRelateKnowledgeService() {
		return relateKnowledgeService;
	}



	public void setRelateKnowledgeService(
			RelateKnowledgeService relateKnowledgeService) {
		this.relateKnowledgeService = relateKnowledgeService;
	}

	public List<RelateKnowledge> getRelateKnowledgeList() {
		return relateKnowledgeList;
	}



	public void setRelateKnowledgeList(List<RelateKnowledge> relateKnowledgeList) {
		this.relateKnowledgeList = relateKnowledgeList;
	}
	
	public String[] getKnowledgePointId() {
		return knowledgePointId !=null?knowledgePointId:new String[0];
	}



	public void setKnowledgePointId(String[] knowledgePointId) {
		this.knowledgePointId = knowledgePointId;
	}



	public String[] getImportantLevel() {
		return importantLevel!=null?importantLevel:new String[0];
	}



	public void setImportantLevel(String[] importantLevel) {
		this.importantLevel = importantLevel;
	}



	public String[] getRelateKnowledgePoints() {
		return relateKnowledgePoints!=null?relateKnowledgePoints:new String[0];
	}



	public void setRelateKnowledgePoints(String[] relateKnowledgePoints) {
		this.relateKnowledgePoints = relateKnowledgePoints;
	}
	
	public String[] getKnowledgePoint() {
		return knowledgePoint!=null?knowledgePoint:new String[0];
	}



	public void setKnowledgePoint(String[] knowledgePoint) {
		this.knowledgePoint = knowledgePoint;
	}



	public String[] getTimePoint() {
		return timePoint!=null?timePoint:new String[0];
	}



	public void setTimePoint(String[] timePoint) {
		this.timePoint = timePoint;
	}
	

	public String getSubjectId() {
		return subjectId;
	}



	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}



	public String getSaveSuccess() {
		return saveSuccess;
	}



	public void setSaveSuccess(String saveSuccess) {
		this.saveSuccess = saveSuccess;
	}

}
