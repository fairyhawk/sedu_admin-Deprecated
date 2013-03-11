package com.shangde.edu.feed.web.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;


import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.GetRequsetResponseUtil;
import com.shangde.common.util.StringUtil;
import com.shangde.common.vo.SelectModel;
import com.shangde.edu.feed.condition.QueryCourseCondition;
import com.shangde.edu.feed.condition.QueryCourseStaticsCondition;
import com.shangde.edu.feed.domain.Course;
import com.shangde.edu.feed.domain.CourseCategory;
import com.shangde.edu.feed.domain.CourseStatic;
import com.shangde.edu.feed.domain.MicroVideo;
import com.shangde.edu.feed.dto.UserStatDTO;
import com.shangde.edu.feed.service.ICourse;
import com.shangde.edu.feed.service.ICourseCategory;
import com.shangde.edu.feed.service.ICourseStatics;
import com.shangde.edu.feed.service.IMicroVideo;
import com.shangde.edu.kno.domain.Sys;
import com.shangde.edu.kno.domain.SysNode;
import com.shangde.edu.kno.service.ISys;
import com.shangde.edu.kno.service.ISysNode;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;

/**
 * 
 * @author chensong 添加课程和查看课程action
 */
public class CourseAddAction extends CommonAction {

	private static final Logger logger = Logger.getLogger(CourseAddAction.class);
	
	private static final long serialVersionUID = 1L;
	/** 服务接口 */
	private ICourse feedCourseService;
	public ICourseCategory getCourseCategoryService() {
		return courseCategoryService;
	}

	public void setCourseCategoryService(ICourseCategory courseCategoryService) {
		this.courseCategoryService = courseCategoryService;
	}

	private ISubject subjectService;
	private IMicroVideo microVideoService;
	private ISys sysService;//1.知识树服务接口,王超提供
	private ISysNode sysNodeService;//2.某个知识树下的详细节点列表,王超提供
	private ICourseCategory courseCategoryService;
	private ICourseStatics courseStaticsService;
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public ICourseStatics getCourseStaticsService() {
		return courseStaticsService;
	}

	public void setCourseStaticsService(ICourseStatics courseStaticsService) {
		this.courseStaticsService = courseStaticsService;
	}

	/** 集合对象 */
	private List<Subject> subjectList;
	private List<CourseCategory> courseCategoryList = new ArrayList<CourseCategory>();
	public List<CourseCategory> getCourseCategoryList() {
		return courseCategoryList;
	}

	public void setCourseCategoryList(List<CourseCategory> courseCategoryList) {
		this.courseCategoryList = courseCategoryList;
	}

	private Course course = new Course();
	private MicroVideo microVideo = new MicroVideo();
	private QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
	private QueryCourseStaticsCondition queryCourseStaticsCondition = new QueryCourseStaticsCondition();
	public QueryCourseStaticsCondition getQueryCourseStaticsCondition() {
		return queryCourseStaticsCondition;
	}

	public void setQueryCourseStaticsCondition(
			QueryCourseStaticsCondition queryCourseStaticsCondition) {
		this.queryCourseStaticsCondition = queryCourseStaticsCondition;
	}

	private List<Sys> sysList;//1.知识树列表（已发布）
	private List<SysNode> sysTree;//2.知识树列表byknId
	private SysNode sysNode;//知识树关联节点对象
	private Sys sys;//知识树对象
	private List<SelectModel> importanceList;//视频重要程度[1-5个星级]
	
	
	/** 常量 */
	private static Integer courseId;//课程id
	private Integer id;//课程id
	private Integer videoId;//视频id
	private String msg;
	private Integer ksId;//知识树专业id
	private Integer ksnId;//知识树id
	private String ksnIdArr;//知识树id集合[*,*,*]
	private String jsonResult;

	/**
	 * 去课程列表首页
	 * 
	 * @return
	 */
	public String courseList() {
		try{
			subjectList = subjectService.getAllSubject();
			courseCategoryList = courseCategoryService.queryList();
			setPage(feedCourseService.getCoursePageList(queryCourseCondition));
			setPageUrlParms();
		}catch(Exception e){
			logger.error("CourseAddAction.courseList", e);
			return ERROR;
		}
		return "courseListPage";
	}

	/**
	 * 去添加课程页面
	 * 
	 * @return
	 */
	public String courseAdd() {
		try{
			subjectList = subjectService.getAllSubject();
			courseCategoryList = courseCategoryService.queryList();
		}catch(Exception e){
			logger.error("CourseAddAction.courseAdd", e);
			return ERROR;
		}
		return "courseAddPage";
	}

	/**
	 * 添加课程方法
	 * 
	 * @return
	 */
	public String addCourse() {
		try{
			course.setModified(new Date());
			course.setPubDate(new Date());
			course.setStatus(1);//1代表可用
			String subjectId = course.getSubjectId();
			if("0".equals(subjectId)){
				course.setIsExclusive(0);
			}else{
				course.setIsExclusive(1);
			}
			course.setCourseImgUrl(null);
			feedCourseService.addCourse(course);
			courseId = course.getId();
			id = courseId;
		}catch(Exception e){
			logger.error("CourseAddAction.addCourse", e);
			return ERROR;
		}
		//进入添加视频框架页面
		return "video_add_frameset";
	}
	
	/**
	 * 添加/修改视频 框架页面
	 * @return
	 */
	public String toVideoFramesetPage(){
		
		//课程id
		courseId = id;
		
		//进入添加视频框架页面		
		return "video_add_frameset";
	}
	
	/**
	 * 根据课程id选择要修改的课程,进入修改页面
	 * @return
	 */
	public String edtorCourse(){
		try{
			subjectList = subjectService.getAllSubject();
			courseCategoryList = courseCategoryService.queryList();
			HttpServletRequest request = GetRequsetResponseUtil.getRequest();
			String courseId = request.getParameter("courseId");
			course = feedCourseService.getCourseById(Integer.parseInt(courseId));
		}catch(Exception e){
			logger.error("CourseAddAction.edtorCourse", e);
			return ERROR;
		}
		return "courseAddPage";
	}
	
	/**
	 * 更新课程
	 * 
	 * @author chensong
	 * @author Libg
	 * 
	 * @return
	 */
	public String updateCourse(){
		try{
			course.setModified(new Date());
			String subjectId = course.getSubjectId();
			if("0".equals(subjectId)){
				course.setIsExclusive(0);
			}else{
				course.setIsExclusive(1);
			}
			course.setCourseImgUrl(null);
			if(feedCourseService.updateCourse(course) > 0){
				msg = "课程修改完成";
			}else{
				msg = "课程修改失败";
			}
		}catch(Exception e){
			logger.error("CourseAddAction.updateCourse", e);
			return ERROR;
		}
		return "msg";
	}
	
	/**
	 * 删除课程
	 * @return
	 */
	public String deleteCourse(){
		try{
			HttpServletRequest request = GetRequsetResponseUtil.getRequest();
			PrintWriter out = GetRequsetResponseUtil.getPrintWriter();
			String courseId = request.getParameter("courseId");
			course = feedCourseService.getCourseById(Integer.parseInt(courseId));
			if(course.getStatus() == 1){
				out.print("error");
				out.close();
			}else{
				course.setStatus(3);//3代表删除
				feedCourseService.updateCourse(course);
				out.print("success");
				out.close();
			}
		}catch(Exception e){
			logger.error("CourseAddAction.deleteCourse", e);
			return ERROR;
		}
		return "courseListPage";
	}
	
	/**
	 * 冻结课程
	 * @return
	 */
	public String freezeCourse(){
		try{
			HttpServletRequest request = GetRequsetResponseUtil.getRequest();
			PrintWriter out = GetRequsetResponseUtil.getPrintWriter();
			String courseId = request.getParameter("courseId");
			course = feedCourseService.getCourseById(Integer.parseInt(courseId));
			if(course.getStatus() == 2){
				course.setStatus(1);//1代表取消冻结
				feedCourseService.updateCourse(course);
				out.print("success");
				out.close();
			}else{
				course.setStatus(2);//2代表冻结
				feedCourseService.updateCourse(course);
				out.print("success");
				out.close();
			}
		}catch(Exception e){
			logger.error("CourseAddAction.freezeCourse", e);
			return ERROR;
		}
		return "courseListPage";
	}
	
	/**
	 * 查询课程
	 * @return
	 */
	public String selectCourse(){
		String isStatics = GetRequsetResponseUtil.getRequest().getParameter("isStatics");
		
		if("1".equals(isStatics)){
			try{
				if(-1 == queryCourseStaticsCondition.getCourseCategoryId()){
					queryCourseStaticsCondition.setCourseCategoryId(null);
				}
				if(queryCourseStaticsCondition.getStartTime() == null || queryCourseStaticsCondition.getStartTime().equals("")){
					queryCourseStaticsCondition.setStartTime(null);
				}
				if(queryCourseStaticsCondition.getEndTime() == null || queryCourseStaticsCondition.getEndTime().equals("")){
					queryCourseStaticsCondition.setEndTime(null);
				}
				courseCategoryList = courseCategoryService.queryList();
				setPage(courseStaticsService.getCourseStaticsList(queryCourseStaticsCondition));
				setPageUrlParms();
				this.queryCourseStaticsCondition.setPageSize(100000);
				setSession("courseStatitcs", courseStaticsService.getCourseStaticsList(queryCourseStaticsCondition).getPageResult());
			}catch(Exception e){
				logger.error("CourseAddAction.selectCourse", e);
				return ERROR;
			}
			return "goStaticsPage";
		}else{
			try{
				if(-1 == queryCourseCondition.getCourseCategoryId()){
					queryCourseCondition.setCourseCategoryId(null);
				}
				if(queryCourseCondition.getStartTime() == null || queryCourseCondition.getStartTime().equals("")){
					queryCourseCondition.setStartTime(null);
				}
				if(queryCourseCondition.getEndTime() == null || queryCourseCondition.getEndTime().equals("")){
					queryCourseCondition.setEndTime(null);
				}
				subjectList = subjectService.getAllSubject();
				courseCategoryList = courseCategoryService.queryList();
				setPage(feedCourseService.getCoursePageListByTerm(queryCourseCondition));
				setPageUrlParms();
			}catch(Exception e){
				logger.error("CourseAddAction.selectCourse", e);
				return ERROR;
			}
			return "courseListPage";
		}
		
	}
	
	public String couserStatics(){
		
		return "";
	}
	
	/**
	 * 去课程树页面
	 * 
	 * @author chensong
	 * @author Libg
	 * 
	 * @return
	 */
	public String goCourseVideoTreePage(){
		try{
			//这里的id用于在请求地址中传参数而来,如果为空说明是从添加课程流程而来
			if(id != null){
				courseId = id;
			}
			
			String courseTree = this.createCourseVideoTree(courseId);
			HttpServletRequest request = GetRequsetResponseUtil.getRequest();
			request.setAttribute("courseTree", courseTree);
			
			id = courseId;//课程id,传递到页面上
		}catch(Exception e){
			logger.error("CourseAddAction.goCourseVideoTreePage", e);
			return ERROR;
		}
		return "course_video_tree";
	}
	
	/**
	 * 进入视频判断页面[添加页面/修改页面]，根据参数决定
	 * @return
	 */
	public String goVideoJumpPage(){
		/**
		HttpServletRequest request = GetRequsetResponseUtil.getRequest();
		String id = request.getParameter("id");
		if(!"0".equals(id)){
			if(feedCourseService.getCourseById(Integer.parseInt(id)) != null){
				course = feedCourseService.getCourseById(Integer.parseInt(id));
			}else{
				microVideo = microVideoService.getVideoAddressById(Integer.parseInt(id));
			}
			return "course_video";
		}else{
			return "course_video";
		}*/
		
		
		/**知识树专业列表*/
		try{
		sysList = sysService.getFaBuSysList();
		
		course = feedCourseService.getCourseById(id);
		if(videoId != null){
			microVideo = microVideoService.getVideoAddressById(videoId);
			
			//进入修改视频页面
			return "video_update";
		}
		}catch(Exception e){
			logger.error("CourseAddAction.goVideoJumpPage", e);
			return ERROR;
		}
		/**
		 * 进入添加 视频页面
		 */
		return "video_add";
	}
	
	/**
	 * 转到知识树页面
	 * 
	 * @author Libg
	 * 
	 * @return
	 */
	public String toKnowledgeTree(){
		
		try {
			
			//条件成立进入提示页面
			if(ksId != null && ksId.intValue() == -1){
				msg = "请选择知识树";
				return "msg";
			}
			
			//获取知识树列表
			sys=sysService.getSysById(ksId);
			sysNode=sysNodeService.getSysNodeBySysId(ksId);
			sysTree = sysNodeService.getSysTreeById(ksId);
			
		} catch (RuntimeException e) {
			logger.error("CourseAddAction.toKnowledgeTree", e);
			return ERROR;
		}
		
		return "knowledgeTree";
	}
	
	
	/**
	 * 获取 某ksnId上级节点名称
	 * 
	 * @return jsonResult json格式
	 */
	public String getKnowNodes(){
	
		jsonResult = getKnowNode(ksnId);
		
		return "jsonResult";
	}
	
	/**
	 * 根据ksnId组，取得集合项，每一个大项(根据ksnId查询的结果)使用符号“|”分割
	 * @return
	 */
	public String getKnowNodeList(){
		try{
			StringBuffer nodeS = new StringBuffer();
			if(ksnIdArr != null){
				
				String []ksnIds = ksnIdArr.split(",");
				
				/**
				 * 每一项之间使用"|"分割
				 */
				for(String s : ksnIds){
					nodeS.append(getKnowNode(Integer.parseInt(s))).append("|");
				}
			}
			
			jsonResult = nodeS.toString();
			
			if(jsonResult != null && jsonResult.length() > 0){
				jsonResult = jsonResult.substring(0,jsonResult.length() - 1);
			}
		}catch(Exception e){
			logger.error("CourseAddAction.getKnowNodeList", e);
			return ERROR;
		}
		return "jsonResult";
	}
	/**
	 * 根据ksnId取得数据,接口由王超提供
	 * 
	 * @param ksnId
	 * @return
	 */
	private String getKnowNode(int ksnId){
		
		StringBuffer nodeS = new StringBuffer();
		List<SysNode> sysNodeList = new ArrayList<SysNode>();
		
		//获取父节点列表sysNodeList是传到页面上的list
		int tempKsnId = ksnId;
		while(tempKsnId != -1){
			sysNode = sysNodeService.getSysNodeById(tempKsnId);
			tempKsnId = sysNode.getParentId();
			sysNodeList.add(sysNode);
		}
		int size = sysNodeList.size();
		/**追加数据*/
		nodeS.append("eg:");
		for(int i = size;i>0;i--){
			nodeS.append(sysNodeList.get(i-1).getNodeName()).append(">>");
		}
		//最后追加一个ksnId,
		nodeS.append("&").append(ksnId);
		
		
		return nodeS.toString();
	}
	
	/**
	 * 生成课程树
	 * @param id
	 * @return String
	 */
	private String createCourseVideoTree(int id) {

		Course course = feedCourseService.getCourseById(id);
		List<MicroVideo> microVideoList = microVideoService.getVideoByCourseId(id);
		StringBuffer sb = new StringBuffer();
		sb.append("<script type='text/javascript'>");
		sb.append("d = new dTree('d');");
		sb.append("d.add(0,-1,'视频列表');");
		try{
			sb.append("d.add(1,0,'"+StringUtil.chop(course.getName(), 8, "")+"<a href=\"sedu/feed/course!edtorCourse.action?courseId="+ id +"\" style=\"color: blue\" target=\"rightMain\">修改</a>','sedu/feed/course!goVideoJumpPage.action?id="+id+"','"+course.getName()+"','rightMain');");
			for(int i=0;i<microVideoList.size();i++){
				try{
					sb.append("d.add("+i+2+",1,'"+ microVideoList.get(i).getName()+"','sedu/feed/course!goVideoJumpPage.action?id="+id+"&videoId="+microVideoList.get(i).getId()+"','"+microVideoList.get(i).getName()+"','rightMain');");
				}catch (Exception e) {
					logger.error("微学习，后台-查询课程对应的视频列表-组合树结构-异常",e);
				}
			}
		}catch (Exception e) {
			logger.error("微学习，后台组合树结构-异常",e);
		}
		sb.append("document.write(d);");
		sb.append("</script>");
		
		return sb.toString();
	}
	
	/**
	 * 去课程统计页面
	 * @return
	 */
	
	public String goCourseStaticPage(){
		try{
			subjectList = subjectService.getAllSubject();
			courseCategoryList = courseCategoryService.queryList();
			setPage(courseStaticsService.getCourseStaticsList(queryCourseStaticsCondition));
			setPageUrlParms();
			this.queryCourseStaticsCondition.setPageSize(100000);
			setSession("courseStatitcs", courseStaticsService.getCourseStaticsList(queryCourseStaticsCondition).getPageResult());
		}catch(Exception e){
			logger.error("CourseAddAction.courseList", e);
			return ERROR;
		}
		return "goStaticsPage";
	}
	
	/**
	 * 查看课程详情
	 * @return
	 */
	public String viewWatchUser(){
		String courseId = GetRequsetResponseUtil.getRequest().getParameter("courseId");
		queryCourseStaticsCondition.setCourseId(Integer.parseInt(courseId));
		setPage(courseStaticsService.getUseUserListByCourseId(queryCourseStaticsCondition));
		setPageUrlParms();
		return "viewUsePerson";
	}
	
	
	public String promtExcel(){
		List<CourseStatic> courseStaticList = null;
		try{
			courseStaticList = getSession("courseStatitcs");
			//size = courseStaticList.size();
		}catch (Exception e) {
			logger.error("获取导出数据异常",e);
		}
		try {	
			// 创建新的Excel 工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel 工作簿中建一工作表
			HSSFSheet sheet = workbook.createSheet("Sheet1");
			// 设置单元格格式(文本)
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
			

			// 在索引0的位置创建行（第一行）
			HSSFRow row = sheet.createRow((short) 0);
			HSSFCell cell1 = row.createCell((short) 0);// 第一列
			HSSFCell cell2 = row.createCell((short) 1);// 第一列
			HSSFCell cell3 = row.createCell((short) 2);// 第一列
			sheet.setColumnWidth((short)0, (short)9000);
			sheet.setColumnWidth((short)1, (short)6000);
			sheet.setColumnWidth((short)2, (short)6000);
			cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell3.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell1.setCellValue("课程id");
			cell2.setCellValue("观看用户数");
			cell3.setCellValue("观看次数");
			
			int j = 1;
			for (int i = 0; i < courseStaticList.size(); i++) {
				CourseStatic courseStatic = courseStaticList.get(i);
				row = sheet.createRow((short) j);
				for(int x=1;x<=3;x++){//设置列数
					 HSSFCell cell = row.createCell((short) (0)); // 设置单元格格式 
					 cell.setCellStyle(cellStyle);
					 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					 cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					 cell.setCellValue(courseStatic.getCourseName());
					 HSSFCell cell4 = row.createCell((short) (1)); // 设置单元格格式 
					 cell4.setCellStyle(cellStyle);
					 cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
					 cell4.setEncoding(HSSFCell.ENCODING_UTF_16);
					 cell4.setCellValue(courseStatic.getUsePersonNumber());
					 HSSFCell cell5 = row.createCell((short) (2)); // 设置单元格格式 
					 cell5.setCellStyle(cellStyle);
					 cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
					 cell5.setEncoding(HSSFCell.ENCODING_UTF_16);
					 cell5.setCellValue(courseStatic.getWatchNumber());
					 
				}
			    j++;	
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);
			byte[] ba = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(ba);
			this.setExcelFile(bais);

		} catch (Exception ioexception) {
			ioexception.printStackTrace();
		}
		
		return "exportExcelAgent";
	}


	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public ICourse getFeedCourseService() {
		return feedCourseService;
	}

	public void setFeedCourseService(ICourse feedCourseService) {
		this.feedCourseService = feedCourseService;
	}

	public QueryCourseCondition getQueryCourseCondition() {
		return queryCourseCondition;
	}

	public void setQueryCourseCondition(
			QueryCourseCondition queryCourseCondition) {
		this.queryCourseCondition = queryCourseCondition;
	}

	public IMicroVideo getMicroVideoService() {
		return microVideoService;
	}

	public void setMicroVideoService(IMicroVideo microVideoService) {
		this.microVideoService = microVideoService;
	}

	public MicroVideo getMicroVideo() {
		return microVideo;
	}

	public void setMicroVideo(MicroVideo microVideo) {
		this.microVideo = microVideo;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public ISys getSysService() {
		return sysService;
	}

	public void setSysService(ISys sysService) {
		this.sysService = sysService;
	}

	public List<Sys> getSysList() {
		return sysList;
	}

	public void setSysList(List<Sys> sysList) {
		this.sysList = sysList;
	}

	public ISysNode getSysNodeService() {
		return sysNodeService;
	}

	public void setSysNodeService(ISysNode sysNodeService) {
		this.sysNodeService = sysNodeService;
	}

	public Integer getKsId() {
		return ksId;
	}

	public void setKsId(Integer ksId) {
		this.ksId = ksId;
	}

	public List<SysNode> getSysTree() {
		return sysTree;
	}

	public void setSysTree(List<SysNode> sysTree) {
		this.sysTree = sysTree;
	}

	public SysNode getSysNode() {
		return sysNode;
	}

	public void setSysNode(SysNode sysNode) {
		this.sysNode = sysNode;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public Integer getKsnId() {
		return ksnId;
	}

	public void setKsnId(Integer ksnId) {
		this.ksnId = ksnId;
	}

	public String getKsnIdArr() {
		return ksnIdArr;
	}

	public void setKsnIdArr(String ksnIdArr) {
		this.ksnIdArr = ksnIdArr;
	}

	public List<SelectModel> getImportanceList() {
		return importanceList;
	}

	public void setImportanceList(List<SelectModel> importanceList) {
		this.importanceList = importanceList;
	}

}
